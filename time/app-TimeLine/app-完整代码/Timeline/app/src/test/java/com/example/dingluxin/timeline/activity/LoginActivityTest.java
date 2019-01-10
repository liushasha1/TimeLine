package com.example.dingluxin.timeline.activity;

import android.app.Application;
import android.content.Intent;
import android.widget.TextView;

import com.example.dingluxin.timeline.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.TestRunnable;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    private LoginActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(LoginActivity.class);

    }

    @Test
    public void clickingRegister_shouldStartRegisterActivity() {
        activity.findViewById(R.id.register).performClick();

        Intent expectedIntent = new Intent(activity, RegisterActivity.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

    @Test
    public void successLogin_shouldStartMainActivity() throws InterruptedException {
        String expectedTele = "13511112222";
        String expectedPassword = "1";
        TextView username = (TextView) activity.findViewById(R.id.telephone);
        TextView password = (TextView) activity.findViewById(R.id.password);
        username.setText(expectedTele);
        password.setText(expectedPassword);

        activity.findViewById(R.id.btn_login).performClick();
        Thread.sleep(5000);

        assertEquals("true",activity.getInfoString());
        Intent expectedIntent = new Intent(activity, MainActivity.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }

    @Test
    public void wrongPassword_shouldReturnWrongPassword() throws InterruptedException {
        String expectedTele = "13511112222";
        String expectedPassword = "Wrong";
        TextView username = (TextView) activity.findViewById(R.id.telephone);
        TextView password = (TextView) activity.findViewById(R.id.password);
        username.setText(expectedTele);
        password.setText(expectedPassword);

        activity.findViewById(R.id.btn_login).performClick();
        Thread.sleep(3000);
        assertEquals("密码错误！",activity.getInfoString());
    }

    @Test
    public void wrongUser_shouldReturnWrongUser() throws InterruptedException {
        String expectedTele = "wrong_user";
        String expectedPassword = "1";
        TextView username = (TextView) activity.findViewById(R.id.telephone);
        TextView password = (TextView) activity.findViewById(R.id.password);
        username.setText(expectedTele);
        password.setText(expectedPassword);

        activity.findViewById(R.id.btn_login).performClick();
        Thread.sleep(3000);
        assertEquals("用户不存在！",activity.getInfoString());

    }
}