package com.example.madselfpracticelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText userName, password;
    private RadioGroup radioGroup;
    private RadioButton btnTeacher, btnStudent;
    private DB_Handler db_handler;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        radioGroup = findViewById(R.id.radioGroup);
        btnStudent = findViewById(R.id.student);
        btnTeacher = findViewById(R.id.teacher);
        register = findViewById(R.id.register);

        db_handler = new DB_Handler(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInputs()) {
                    boolean status = db_handler.addUser(userName.getText().toString().trim(), password.getText().toString().trim(), getType());

                    if (status) {
                        Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Register.this, "Register Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public String getType() {
        int type = radioGroup.getCheckedRadioButtonId();

        if (type == btnStudent.getId()) {
            return "Student";
        } else {
            return "Teacher";
        }
    }

    public boolean checkInputs() {
        if (userName.getText().toString().length() == 0) {
            Toast.makeText(this, "Input User Name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().length() == 0) {
            Toast.makeText(this, "Input Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (radioGroup.getCheckedRadioButtonId() != btnStudent.getId() || radioGroup.getCheckedRadioButtonId() != btnTeacher.getId()) {
            Toast.makeText(this, "Input User Type", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}