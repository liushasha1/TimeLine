package com.example.dingluxin.timeline.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dingluxin.timeline.R;
import com.example.dingluxin.timeline.util.WebServicePost;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText regTelephone;
    private EditText regUserName;
    private EditText regPassWord;
    private EditText regConfPassWord;
    private String gender = "男";
    private String info;
    private ProgressDialog dialog;
    // 返回主线程更新数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RadioGroup rgroup;
        Button btn_reg;

        //初始化信息
        regTelephone = findViewById(R.id.regTelephone);
        regUserName = findViewById(R.id.name);
        regPassWord = findViewById(R.id.regPassword);
        regConfPassWord = findViewById(R.id.regConfPassword);
        rgroup = findViewById(R.id.radioGroup);
        btn_reg = findViewById(R.id.btn_reg);


        btn_reg.setOnClickListener(this);
        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                switch (checkedId) {
                    case R.id.Man:
                        gender = "男";
                        break;
                    case R.id.Woman:
                        gender = "女";
                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reg:

                if(!checkNetwork()){
                    Toast.makeText(RegisterActivity.this, "网络未连接！", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (regUserName.getText().length() == 0 || regTelephone.getText().length() == 0 || regPassWord.getText().length() == 0 || regConfPassWord.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    if (!regPassWord.getText().toString().equals(regConfPassWord.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "两次密码不一致！", Toast.LENGTH_SHORT).show();
                    } else {
                        dialog = new ProgressDialog(RegisterActivity.this);
                        dialog.setTitle("正在注册");
                        dialog.setMessage("请稍后");
                        dialog.show();
                        new Thread(new RegThread()).start();
                    }
                }

                break;
        }
    }

    private class RegThread implements Runnable {
        @Override
        public void run() {

            Map<String, String> params = new HashMap<>();
            params.put("teleNum", regTelephone.getText().toString());
            params.put("name", regUserName.getText().toString());
            params.put("password1", regPassWord.getText().toString());
            params.put("password2", regConfPassWord.getText().toString());
            params.put("gender", gender);

            //获取服务器返回数据
            final String RegRet = WebServicePost.executeHttpPost("registerServlet", params);

            dialog.dismiss();
            setInfo(RegRet);
            //更新UI，界面处理
            showReq(RegRet);
        }
    }

    private void showReq(final String RegRet) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (RegRet == null) {
                    Toast.makeText(RegisterActivity.this, "连接错误", Toast.LENGTH_SHORT).show();
                } else {
                    if (RegRet.equals("true")) {
                        dialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setTitle("注册信息");
                        builder.setMessage("注册成功");
                        builder.setCancelable(false);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        builder.show();
                    } else {
                        dialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setTitle("注册信息");
                        builder.setMessage("注册失败：" + RegRet);
                        builder.setCancelable(true);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.show();
                    }
                }


            }
        });
    }

    public String getInfo() {
        return info;
    }

    private void setInfo(String info) {
        this.info = info;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }
}
