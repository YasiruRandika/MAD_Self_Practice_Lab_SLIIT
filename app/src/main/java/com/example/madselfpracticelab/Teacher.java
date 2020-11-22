package com.example.madselfpracticelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Teacher extends AppCompatActivity {
    EditText subject, message;
    TextView welcomeMsg;
    Button submit;
    DB_Handler db_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("user");

        subject = findViewById(R.id.subjectInsert);
        message = findViewById(R.id.messageInsert);
        submit = findViewById(R.id.submit);
        welcomeMsg = findViewById(R.id.wTeac);

        welcomeMsg.setText("Welcome " + userName);

        db_handler = new DB_Handler(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFields()) {
                    boolean status = db_handler.addMessage(subject.getText().toString(), message.getText().toString());

                    if (status) {
                        subject.setText("");
                        subject.setText("");
                        Toast.makeText(Teacher.this, "Message Added Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Teacher.this, "Message Adding Failed", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public boolean checkFields() {
        boolean status = true;

        if (subject.getText().toString().length() == 0) {
            status = false;

            Toast.makeText(this, "Subject cannot be blank", Toast.LENGTH_SHORT).show();
        } else if (message.getText().toString().length() == 0) {
            status = false;

            Toast.makeText(this, "Message cannot be blank", Toast.LENGTH_SHORT).show();
        }
        return status;
    }
}