package com.example.np.study_room_scheduler;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);

        LazyCodedStaticValues.populateValues();

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
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void login(View view) throws Exception {
        if(((EditText) findViewById(R.id.email)).getText().toString().isEmpty() ||
                ((EditText) findViewById(R.id.password)).getText().toString().isEmpty()) {
            Toast.makeText(LoginUI.this, R.string.login_error_msg, Toast.LENGTH_SHORT).show();
            return;
        }

        new PerformLogin(this).execute(((EditText) findViewById(R.id.email)).getText().toString(),
                ((EditText) findViewById(R.id.password)).getText().toString());

    }

    private class PerformLogin extends AsyncTask<String, Void, Boolean> {

        private Activity activity;

        public PerformLogin(Activity activity) {
            this.activity = activity;
        }

        @Override
        public Boolean doInBackground(String... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                if(EWS.createEWS(params[0], params[1]) != null) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ((Button) findViewById(R.id.login_button)).setVisibility(View.GONE);
            ((ProgressBar) findViewById(R.id.spinning_wheel)).setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean created) {
            super.onPostExecute(created);

            if(created) {
                Intent intent = new Intent(activity, SelectOrSearchUI.class);
                activity.startActivity(intent);
            } else {
                Toast.makeText(activity, R.string.invalid_cred_msg, Toast.LENGTH_SHORT).show();
            }

            ((EditText) findViewById(R.id.email)).setText("");
            ((EditText) findViewById(R.id.password)).setText("");

            ((Button) findViewById(R.id.login_button)).setVisibility(View.VISIBLE);
            ((ProgressBar) findViewById(R.id.spinning_wheel)).setVisibility(View.GONE);
        }
    }
}
