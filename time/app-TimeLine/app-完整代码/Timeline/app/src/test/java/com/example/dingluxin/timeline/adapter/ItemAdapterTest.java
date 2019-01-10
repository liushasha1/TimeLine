package com.example.dingluxin.timeline.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.dingluxin.timeline.R;
import com.example.dingluxin.timeline.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


@RunWith(RobolectricTestRunner.class)
public class ItemAdapterTest {

    private ItemAdapter itemAdapter;
    private ItemAdapter.ViewHolder viewHolder;
    private ItemAdapter.BottomViewHolder bottomViewHolder;
    private View mockView;
    private Activity activity;
    private List<Item> itemList = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        mockView = mock(View.class);
        activity = Robolectric.setupActivity(MainActivity.class);
        itemAdapter = new ItemAdapter(activity, itemList);
    }

    @Test
    public void itemCount(){
        Item item1 = new Item("2","Alida","13500001111","testing","2018-12-20 18:00:00");
        Item item2 = new Item("1","Even","13500002222","testing","http://www.lovethesepics.com/wp-content/uploads/2012/03/Napali-aerial-view-of-the-coast-on-Kauai.jpg","2018-12-20 19:00:00");
        Item item3 = new Item("0","Button","","","");
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        assertEquals(3,itemAdapter.getItemCount());
    }

    @Test
    public void getItemViewType() {
        int ITEM_TYPE_CONTENT = 1;
        int ITEM_TYPE_BOTTOM = 2;
        Item item1 = new Item("2","Alida","13500001111","testing","2018-12-20 18:00:00");
        Item item2 = new Item("1","Even","13500002222","testing","http://www.lovethesepics.com/wp-content/uploads/2012/03/Napali-aerial-view-of-the-coast-on-Kauai.jpg","2018-12-20 19:00:00");
        Item item3 = new Item("0","Button","","","");
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        assertEquals(ITEM_TYPE_CONTENT,itemAdapter.getItemViewType(0));
        assertEquals(ITEM_TYPE_CONTENT,itemAdapter.getItemViewType(1));
        assertEquals(ITEM_TYPE_BOTTOM,itemAdapter.getItemViewType(2));

    }


    @Test
    public void onBindViewHolder_AllHoldersPerformWell() throws InterruptedException {
        Item item1 = new Item("2","Alida","13500001111","testing","2018-12-20 18:00:00");
        Item item2 = new Item("1","Even","13500002222","Have a good day!","http://www.lovethesepics.com/wp-content/uploads/2012/03/Napali-aerial-view-of-the-coast-on-Kauai.jpg","2018-12-20 19:00:00");
        Item item3 = new Item("0","Button","","","");
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        LayoutInflater inflater = LayoutInflater.from(activity);
        //LayoutInflater inflater = (LayoutInflater) RuntimeEnvironment.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listItemView = inflater.inflate(R.layout.item, null, false);
        viewHolder = new ItemAdapter.ViewHolder(listItemView);

        //ViewHolder shows correctly
        itemAdapter.onBindViewHolder(viewHolder,0);
        assertEquals("Alida",viewHolder.name.getText().toString());
        assertEquals("testing",viewHolder.content.getText().toString());
        assertEquals("2018-12-20 18:00:00",viewHolder.time.getText().toString());

        itemAdapter.onBindViewHolder(viewHolder,1);
        assertEquals("Even",viewHolder.name.getText().toString());
        assertEquals("Have a good day!",viewHolder.content.getText().toString());
        assertEquals("2018-12-20 19:00:00",viewHolder.time.getText().toString());

        //Bottom clicks correctly
        View buttonView = inflater.inflate(R.layout.bottom_button, null, false);
        bottomViewHolder = new ItemAdapter.BottomViewHolder(buttonView);
        itemAdapter.onBindViewHolder(bottomViewHolder,2);
        bottomViewHolder.more.performClick();
        Thread.sleep(5000);
        MainActivity mainActivity = (MainActivity)activity;
        assertEquals("LOAD_MORE",mainActivity.getInfo());
    }

    @Test
    public void testSetDifferentTime(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date currentTime = new Date(System.currentTimeMillis());
        String date1 = fmt.format(new Date(currentTime.getTime()-(long) 5*60*1000)); //5 min ago
        String date2 = fmt.format(new Date(currentTime.getTime()-(long) 3*60*60*1000)); //3 hours ago
        String date3 = fmt.format(new Date(currentTime.getTime()-(long) 12*60*60*1000)); //12 hours ago
        String date4 = fmt.format(new Date(currentTime.getTime()-(long) 26*60*60*1000)); //yesterday
        String date5 = fmt.format(new Date(currentTime.getTime()-(long) 3*24*60*60*1000)); //3 days ago

        Item item1 = new Item("5","Alida","13500001111","testing",date1);
        Item item2 = new Item("4","Alida","13500001111","testing",date2);
        Item item3 = new Item("3","Alida","13500001111","testing",date3 );
        Item item4 = new Item("2","Alida","13500001111","testing",date4 );
        Item item5 = new Item("1","Alida","13500001111","testing",date5);
        Item item0 = new Item("0","Button","","","");
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        itemList.add(item0);

        LayoutInflater inflater = LayoutInflater.from(activity);
        View listItemView = inflater.inflate(R.layout.item, null, false);
        viewHolder = new ItemAdapter.ViewHolder(listItemView);

        itemAdapter.onBindViewHolder(viewHolder,0);
        assertEquals("5分钟前",viewHolder.time.getText().toString());
        itemAdapter.onBindViewHolder(viewHolder,1);
        assertEquals("3小时前",viewHolder.time.getText().toString());
        itemAdapter.onBindViewHolder(viewHolder,2);
        assertEquals("12小时前",viewHolder.time.getText().toString());
        itemAdapter.onBindViewHolder(viewHolder,3);
        assertTrue(viewHolder.time.getText().toString().startsWith("昨天"));
        itemAdapter.onBindViewHolder(viewHolder,4);
        assertEquals(date5,viewHolder.time.getText().toString());

    }


}