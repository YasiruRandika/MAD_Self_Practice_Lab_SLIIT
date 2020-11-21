package com.example.madselfpracticelab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Teacher extends AppCompatActivity {
    EditText subject, message;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        subject = findViewById(R.id.subjectInsert);
        message = findViewById(R.id.messageInsert);
        submit = findViewById(R.id.submit);
    }
}