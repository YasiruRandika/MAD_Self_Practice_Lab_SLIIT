package com.example.madselfpracticelab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Message extends AppCompatActivity {
    TextView message, subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        message = findViewById(R.id.messageView);
        subject = findViewById(R.id.subjectView);
    }
}