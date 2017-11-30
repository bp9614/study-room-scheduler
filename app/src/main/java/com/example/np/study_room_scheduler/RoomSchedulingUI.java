package com.example.np.study_room_scheduler;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RoomSchedulingUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_scheduling_ui);

        ((EditText) findViewById(R.id.organizer)).setText(EWS.getEWS().getEmail(), TextView.BufferType.EDITABLE);

        ((EditText) findViewById(R.id.user_1)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        ((EditText) findViewById(R.id.user_2)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        ((EditText) findViewById(R.id.user_3)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    public void schedule(View view) throws ParseException {
        new PerformScheduling().execute("08-207LibGS@wit.edu",
                ((EditText) findViewById(R.id.user_1)).getText().toString(),
                ((EditText) findViewById(R.id.user_2)).getText().toString(),
                ((EditText) findViewById(R.id.user_3)).getText().toString(),
                "2017-12-01 15:00:00",
                "2017-12-01 16:00:00");
        setResult(RESULT_OK);
        finish();
    }

    private class PerformScheduling extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = formatter.parse(strings[4]);
                Date endDate = formatter.parse(strings[5]);

                EWS.getEWS().meeting(Arrays.asList(new String[]{strings[0], strings[1], strings[2], strings[3]}),startDate , endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
