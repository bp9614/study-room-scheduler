package com.example.np.study_room_scheduler;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectOrSearchUI extends AppCompatActivity {

    public static final int SEARCH = 1;
    public static final int SELECT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_or_search_ui);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new SelectOrSearchFragmentPagerAdapter(getSupportFragmentManager(),
                SelectOrSearchUI.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void selectDay(View view) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date selectedTime = dateFormat.parse(((Spinner) findViewById(R.id.select_year)).getSelectedItem().toString() + "/" +
                ((Spinner) findViewById(R.id.select_month)).getSelectedItem().toString() + "/" +
                ((Spinner) findViewById(R.id.select_day)).getSelectedItem().toString() +
                " " + dateFormat.format(new Date()).split(" ")[1]);

        long diff_in_days = (selectedTime.getTime() - new Date().getTime()) / (24 * 60 * 60 * 1000);

        if(diff_in_days < 0) {
            Toast.makeText(this, R.string.less_diff, Toast.LENGTH_SHORT).show();
        } else if(diff_in_days >= 14){
            Toast.makeText(this, R.string.in_advance, Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    public void searchDay(View view) {
        Intent intent = new Intent(this, ResultsUI.class);
        startActivityForResult(intent, SELECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (SELECT == requestCode && resultCode == RESULT_OK) {

        }
    }
}
