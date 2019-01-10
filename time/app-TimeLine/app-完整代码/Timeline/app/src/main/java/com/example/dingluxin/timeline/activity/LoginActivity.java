package com.example.dingluxin.timeline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dingluxin.timeline.R;
import com.example.dingluxin.timeline.util.WebServiceGet;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.net.ConnectivityManager;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    //提示框
    private ProgressDialog dialog;
    //服务器返回的数据
    private String infoString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化信息
        username = findViewById(R.id.telephone);
        password =  findViewById(R.id.password);
        Button login = findViewById(R.id.btn_login);
        TextView register = findViewById(R.id.register);

        //设置按钮监听器
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login:
                // 检测网络，无法检测wifi
                if (!checkNetwork()) {
                    Toast toast = Toast.makeText(LoginActivity.this, "网络未连接", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    break;
                }
                //设置提示框
                dialog = new ProgressDialog(LoginActivity.this);
                dialog.setTitle("正在登陆");
                dialog.setMessage("请稍后");
                dialog.setCancelable(false);//设置可以通过back键取消
                dialog.show();
                //设置子线程，分别进行Get和Post传输数据
                new Thread(new MyThread()).start();

                break;

            case R.id.register:
                //跳转注册页面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            infoString = WebServiceGet.executeHttpGet(username.getText().toString(), password.getText().toString(), "loginServlet");//获取服务器返回的数据
            dialog.dismiss();
            showOnUI(infoString);
            if (infoString!=null && infoString.equals("true")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    private void showOnUI(final String response) {
        runOnUiThread(new Runnable() {
            //更新UI
            @Override
            public void run() {
                if(response==null){
                    Toast.makeText(LoginActivity.this, "网络连接错误！", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "登陆失败！"+response, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

    public String getInfoString() {
        System.out.println("get "+infoString);
        return infoString;
    }

}
