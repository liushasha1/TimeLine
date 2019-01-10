package com.example.dingluxin.timeline.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 使用Post方法获取Http服务器数据
 */

public class WebServicePost {

    public static String executeHttpPost(String address, Map<String, String> params) {
        HttpURLConnection connection = null;
        InputStream in = null;

        try {
            String Url = "http://172.30.142.143:8080/ABC/" + address;
            try {
                String teleNum = params.get("teleNum");
                String password1 = params.get("password1");
                String name = params.get("name");
                String gender = params.get("gender");

                System.out.print(teleNum);

                URL url = new URL(Url);
                connection = (HttpURLConnection) url.openConnection();

                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(10000);//建立连接超时
                connection.setReadTimeout(8000);//传递数据超时

                connection.setUseCaches(false);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                connection.connect();

                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                String data = "teleNum=" + teleNum + "&password1=" + password1
                        + "&name=" + URLEncoder.encode(name, "UTF-8") +
                        "&gender=" + URLEncoder.encode(gender, "UTF-8");

                out.writeBytes(data);
                out.flush();
                out.close();

                int resultCode = connection.getResponseCode();
                if (HttpURLConnection.HTTP_OK == resultCode) {
                    in = connection.getInputStream();
                    return parseInfo(in);
                }
                return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //意外退出时，连接关闭保护
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    //得到字节输入流，将字节输入流转化为String类型
    private static String parseInfo(InputStream inputStream) {
        BufferedReader reader = null;
        String line;
        StringBuilder response = new StringBuilder();

        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                Log.d("RegisterActivity", line);
                response.append(line);
            }
            Log.d("RegisterActivity", "response.toString():" + response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
