package com.example.dingluxin.timeline.activity;


import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.dingluxin.timeline.R;
import com.example.dingluxin.timeline.adapter.Item;
import com.example.dingluxin.timeline.adapter.ItemAdapter;
import com.example.dingluxin.timeline.util.Utility;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final List<Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private Button refresh;
    private MyAsyncTask myAsyncTasktask = null;
    private Handler handler = new Handler();
    private String info;
    private static String url = "http://172.30.142.143:8080/ABC/ContentServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据
        initList();

        recyclerView = findViewById(R.id.dynamic);
        refresh = findViewById(R.id.btn_refresh);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        itemAdapter = new ItemAdapter(MainActivity.this, itemList);
        recyclerView.setAdapter(itemAdapter);
        // 更新item时防止抖动


        //设置点击监听事件
        refresh.setOnClickListener(this);

        //调用循环，定时更新按钮的数字显示
        handler = new Handler();
        handler.postDelayed(runnable, 2 * 1000);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startTask();
            //设置循环，设置延迟时间
            handler.postDelayed(this, 1000 * 10);//每隔10s执行
        }
    };

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_refresh:
                new Thread(new refreshThread()).start();
                break;

            case R.id.btn_more:
                new Thread(new moreThread()).start();
                break;
        }
    }


    private void initList() {
        itemList.add(new Item("0", "底部按钮", "底部按钮", "底部按钮", ""));
        //首先要发送一次请求，获得数据-
        new Thread(new moreThread()).start();
    }


    //顶部刷新
    private class refreshThread implements Runnable {
        @Override
        public void run() {
            try {
                //Timestamp sysTime = new Timestamp(System.currentTimeMillis());//当前系统时间

                // 如果list为空，则传入0
                String topID = "0";
                // list不为空，获取itemList第一个元素的ID
                if (itemList.size()>1) {
                    Item lastItem = itemList.get(0);
                    topID = lastItem.getID();
                }

                setInfo("REFRESH_NEW");
                RequestBody requestBody = new FormBody.Builder()
                        .add("ID", topID)
                        .add("type", "REFRESH_NEW")
                        .build();
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                        .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Response response = client.newCall(request).execute();
                if(response.body()!=null){
                    String responseData = response.body().string();
                    //这里获得OKhttp传回来的数据后，调用Utility中的方法，将json数据解析成itemList，并加到现有的list中
                    List<Item> newItemList =  Utility.handleItemResponse(responseData);
                    if(newItemList!=null) {
                        itemList.addAll(0, newItemList);
                    }

                    //对UI操作
                    showButton();
                    showUI();

                }

            } catch (Exception e) {
                e.printStackTrace();
                if(e instanceof SocketTimeoutException){//判断超时异常
                    showException("网络连接超时！");
                }
                if(e instanceof ConnectException){//判断连接异常
                    showException("连接异常!");
                }
            }
        }
    }


    //底部加载更多
    private class moreThread implements Runnable {
        @Override
        public void run() {
            try {
                //如果itemList为空,bottomID初始化为-1
                String bottomID = "-1";
                //不为空，获取itemList最后一个元素的ID
                if (itemList.size() > 1) {
                    Item lastItem = itemList.get(itemList.size() - 2);
                    bottomID = lastItem.getID();
                }

                setInfo("LOAD_MORE");
                RequestBody requestBody = new FormBody.Builder()
                        .add("ID", bottomID)
                        .add("type", "LOAD_MORE")
                        .build();
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                        .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Response response = client.newCall(request).execute();

                if(response.body()!=null){
                    String responseData = response.body().string();
                    List<Item>  newItemList = Utility.handleItemResponse(responseData);
                    if(newItemList!=null){
                        itemList.addAll(itemList.size() - 1, newItemList);
                    }
                    showUI();
                    itemAdapter.notifyDataSetChanged();
                }


            } catch (Exception e) {
                e.printStackTrace();
                if(e instanceof SocketTimeoutException){//判断超时异常
                    showException("网络连接超时！");
                }
                if(e instanceof ConnectException){//判断连接异常
                    showException("连接异常!");
                }
            }
        }
    }

    private void showButton() {
        runOnUiThread(new Runnable() {
            //更新UI
            @Override
            public void run() {
                refresh.setText("更新");
            }
        });
    }

    private void showUI() {
        runOnUiThread(new Runnable() {
            //更新UI
            @Override
            public void run() {
                itemAdapter.notifyDataSetChanged();
            }
        });
    }

    private void showException(final String wrongInfo) {
        runOnUiThread(new Runnable() {
            //更新UI
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, wrongInfo, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private static class MyAsyncTask extends AsyncTask<Void, Integer, String> {
        private Button button;
        private WeakReference<Button> buttonWeakReference;

        MyAsyncTask(Button buttonRefresh) {
            this.buttonWeakReference = new WeakReference<>(buttonRefresh);
            this.button = buttonRefresh;
        }

        @Override
        protected String doInBackground(Void... voids) {

            //所执行的任务
            try {
                // 如果list为空，则传入0
                String topID = "0";
                // list不为空，获取itemList第一个元素的ID
                if (!itemList.isEmpty()) {
                    Item lastItem = itemList.get(0);
                    topID = lastItem.getID();
                }

                RequestBody requestBody = new FormBody.Builder()
                        .add("ID", topID)
                        .add("type", "GET_UPDATE_NUMBER")
                        .build();
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)//设置连接超时时间
                        .readTimeout(20, TimeUnit.SECONDS)//设置读取超时时间
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();
                Response response = client.newCall(request).execute();
                if(response.body()!=null){
                    return response.body().string() + " 更新";
                }
                return "更新";

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            Button buttonRefresh = buttonWeakReference.get();
            buttonRefresh.setText(s);
            button.setText("");
            super.onPostExecute(s);
        }

    }

    protected void startTask() {
        stopTask();
        myAsyncTasktask = (MyAsyncTask) new MyAsyncTask(refresh).execute();
        setInfo("START_TASK");
    }

    private void stopTask() {
        setInfo("STOP_TASK");
        if (myAsyncTasktask != null) {
            myAsyncTasktask.cancel(true);
            myAsyncTasktask = null;
        }
    }

    //这个活动的销毁时, task也停止
    @Override
    protected void onDestroy() {
        stopTask();
        super.onDestroy();
    }

    public String getInfo() {
        return info;
    }

    private void setInfo(String info) {
        this.info = info;
    }


}


