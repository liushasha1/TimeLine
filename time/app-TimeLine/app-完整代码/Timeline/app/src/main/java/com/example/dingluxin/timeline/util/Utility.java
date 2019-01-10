package com.example.dingluxin.timeline.util;

import android.text.TextUtils;

import com.example.dingluxin.timeline.adapter.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Utility {
    public static List<Item> handleItemResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                List<Item> itemList;
                Gson gson = new Gson();
                itemList = gson.fromJson(response, new TypeToken<List<Item>>(){}.getType());
                return itemList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
