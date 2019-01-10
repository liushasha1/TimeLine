package com.example.dingluxin.timeline.activity;

import android.content.Intent;
import android.widget.EditText;

import com.example.dingluxin.timeline.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class RegisterActivityTest {
    private RegisterActivity activity;
    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(RegisterActivity.class);
    }

    @Test
    public void registerOldUser_shouldShowAlreadyExists() throws InterruptedException {
        EditText regTelephone = (EditText) activity.findViewById(R.id.regTelephone);
        EditText regUserName = (EditText) activity.findViewById(R.id.name);
        EditText regPassWord = (EditText) activity.findViewById(R.id.regPassword);
        EditText regConfPassWord = (EditText) activity.findViewById(R.id.regConfPassword);
        String gender = "男";

        activity.setGender(gender);
        regTelephone.setText("13511112222");
        regUserName.setText("Even");
        regPassWord.setText("123456@@");
        regConfPassWord.setText("123456@@");

        activity.findViewById(R.id.btn_reg).performClick();

        Thread.sleep(3000);
        assertEquals("该用户已存在",activity.getInfo());
    }

    @Test
    public void registerNewUser_shouldStartTrue() throws InterruptedException {
        EditText regTelephone = (EditText) activity.findViewById(R.id.regTelephone);
        EditText regUserName = (EditText) activity.findViewById(R.id.name);
        EditText regPassWord = (EditText) activity.findViewById(R.id.regPassword);
        EditText regConfPassWord = (EditText) activity.findViewById(R.id.regConfPassword);
        String gender = "男";

        activity.setGender(gender);
        regTelephone.setText("17783456766");
        regUserName.setText("Clida");
        regPassWord.setText("123456@@");
        regConfPassWord.setText("123456@@");

        activity.findViewById(R.id.btn_reg).performClick();

        Thread.sleep(3000);
        assertEquals("true",activity.getInfo());
   }

}