package com.example.np.study_room_scheduler;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);

        ((EditText) findViewById(R.id.email)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        ((EditText) findViewById(R.id.password)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        ((Button) findViewById(R.id.login_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(((EditText) findViewById(R.id.email)).getText().toString().isEmpty() ||
                        ((EditText) findViewById(R.id.password)).getText().toString().isEmpty()) {
                    Toast.makeText(LoginUI.this, R.string.login_error_msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void skip(View view) {
        Intent intent = new Intent(this, RoomSchedulingUI.class);
        startActivity(intent);
    }
}
