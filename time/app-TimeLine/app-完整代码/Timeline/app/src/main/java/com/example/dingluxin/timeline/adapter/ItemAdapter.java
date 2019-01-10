package com.example.dingluxin.timeline.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dingluxin.timeline.R;
import com.example.dingluxin.timeline.activity.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;



public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Item> mDatas;
    private static final int ITEM_TYPE_CONTENT = 1;
    private static final int ITEM_TYPE_BOTTOM = 2;
    private Context context;

    //构造函数
    public ItemAdapter(Context context, List<Item> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        startTime();
    }

    //内部类
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView time;
        public TextView content;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            time = view.findViewById(R.id.time);
            content = view.findViewById(R.id.content);
            image = view.findViewById(R.id.image);
        }
    }

    //内部类，最底部的按钮
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        Button more;

        public BottomViewHolder(View view) {
            super(view);
            more = view.findViewById(R.id.btn_more);
        }
    }

    @NonNull
    @Override //创建实例
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_CONTENT) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        } else {
            return new BottomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bottom_button, parent, false));
        }
    }


    @Override  //赋值
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            Item myItem = mDatas.get(position); //获取单个item

            viewHolder.name.setText(myItem.getUserName());
            viewHolder.content.setText(myItem.getContent());

            //使用Glide加载图片
            String url = myItem.getImage();
            if(url!=null && !url.equals("")){
                Glide.with(context).load(url).thumbnail(0.1f).into(viewHolder.image);
            }else{
                viewHolder.image.setVisibility(View.GONE);
            }

            //处理时间
            try {
                setTime(viewHolder, position);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else if (holder instanceof BottomViewHolder) {
            BottomViewHolder bottomViewHolder= ((BottomViewHolder) holder);
            final Button loadMore = bottomViewHolder.more;
            bottomViewHolder.more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) context).onClick(loadMore);
                }
            });

        }
    }

    private void setTime(ViewHolder holder, int position) throws ParseException {
        Item myItem = mDatas.get(position); //获取单个item

        String time = myItem.getTime();

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        Date oldTime = fmt.parse(time);
        Date currentTime = new Date(System.currentTimeMillis());
        long dv_day = (currentTime.getTime() - oldTime.getTime()) / (1000 * 24 * 60 * 60);

        //判断是否大于两天
        if (dv_day >= 2) {
            holder.time.setText(myItem.getTime());
        } else if (dv_day >= 1) {
            int n_hour = oldTime.getHours();
            String s_hour = n_hour + "";
            int n_min = oldTime.getMinutes();
            String s_min = n_min + "";

            String res = "昨天 " + s_hour + ":" + s_min;
            holder.time.setText(res);
        } else {
            long n_hour = (currentTime.getTime() - oldTime.getTime()) / (1000 * 60 * 60);
            if (n_hour >= 1) {
                String res = n_hour + "小时前";
                holder.time.setText(res);
            } else {
                long n_min = Math.abs(currentTime.getTime() - oldTime.getTime()) / (1000 * 60);
                String res = n_min + "分钟前";
                holder.time.setText(res);
            }
        }

    }

    private void startTime() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < mDatas.size(); i++) {
                            ItemAdapter.this.notifyItemChanged(i);
                        }
                    }
                });
            }
        }, 0, 1000);
    }




    @Override  //数据源的长度
    public int getItemCount() {
        return mDatas.size();
    }

    // 判断当前item是否是底部
    private boolean isBottomView(int position) {
        return position >= getItemCount() - 1;
    }

    // 判断当前item类型
    @Override
    public int getItemViewType(int position) {
        if (isBottomView(position)) {
            // 底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            // 内容View
            return ITEM_TYPE_CONTENT;
        }
    }


}