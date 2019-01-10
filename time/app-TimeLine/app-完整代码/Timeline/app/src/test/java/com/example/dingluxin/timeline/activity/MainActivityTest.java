package com.example.dingluxin.timeline.activity;

import com.example.dingluxin.timeline.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void clickButtonLoadMore_shouldReturnLoad() throws InterruptedException{

        activity.findViewById(R.id.btn_more).performClick();
        Thread.sleep(5000);
        assertEquals("LOAD_MORE",activity.getInfo());
    }

    @Test
    public void clickButtonRefresh_shouldReturnRefresh() throws InterruptedException{

        activity.findViewById(R.id.btn_refresh).performClick();
        Thread.sleep(5000);
        assertEquals("REFRESH_NEW",activity.getInfo());
    }

    @Test
    public void checkNewMessage_shouldStartTask() throws InterruptedException{

        activity.startTask();
        Thread.sleep(12*1000);
        assertEquals("START_TASK",activity.getInfo());
    }

    @Test
    public void activityDestroy_shouldStopTask(){
        activity.onDestroy();
        assertEquals("STOP_TASK",activity.getInfo());
    }


}