package com.code.freud.jerentre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
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
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("123456789",null,"Je rentre. Bisous",null,null);
                    textViewStatus.setText("SMS envoyé !");
                }
                catch (Exception e) {
                    textViewStatus.setText(e.getMessage());
                }
            }
        });
    }
}
