package com.code.freud.jerentre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buttonSendSMS;
    TextView textViewStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSendSMS=(Button)findViewById(R.id.buttonSendSMS);
        textViewStatus=(TextView)findViewById(R.id.textViewStatus);

        buttonSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
            }
        });
    }

    private void sendSMS() {
        try {
            Context context = getApplicationContext();
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            String smsNumber=prefs.getString("sms_number", "");
            String smsText=prefs.getString("sms_text", "");

            if (smsNumber.isEmpty() || smsText.isEmpty()) {
                textViewStatus.setText("Please, configure the sms' text and number");
            } else {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(smsNumber,null,smsText,null,null);
                textViewStatus.setText("The SMS has been sent !");
            }
        }
        catch (Exception e) {
            textViewStatus.setText(e.getMessage());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.configurer:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.quitter:
                finish();
                return true;
        }
        return false;
    }
}
