package com.example.np.study_room_scheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultsUI extends AppCompatActivity {

    public static final int SCHEDULE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_ui);
    }

    public void scheduleMeeting(View view) {
        startActivityForResult(new Intent(this, RoomSchedulingUI.class), SCHEDULE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (SCHEDULE == requestCode && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
