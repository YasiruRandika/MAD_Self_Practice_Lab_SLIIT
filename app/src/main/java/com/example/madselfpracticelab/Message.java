package com.example.madselfpracticelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Message extends AppCompatActivity {
    TextView message, subject;
    DB_Handler db_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        db_handler = new DB_Handler(this);
        messageModel messageModel = db_handler.getMessageById(id);

        message = findViewById(R.id.messageView);
        subject = findViewById(R.id.subjectView);

        message.setText(messageModel.getMessage());
        subject.setText(messageModel.getSubject());
    }
}