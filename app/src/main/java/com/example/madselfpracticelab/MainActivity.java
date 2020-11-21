package com.example.madselfpracticelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText userName, password;
    private Button login, register;
    private DB_Handler db_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        db_handler = new DB_Handler(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFields()) {
                    String user_name = userName.getText().toString().trim();
                    String password_user = password.getText().toString().trim();

                    String type = db_handler.login(user_name, password_user);
                    Intent intent;

                    switch (type) {
                        case "Student" :
                            intent = new Intent(MainActivity.this, Student.class);
                            startActivity(intent);
                            finish();
                            break;
                        case "Teacher":
                            intent = new Intent(MainActivity.this, Teacher.class);
                            startActivity(intent);
                            finish();
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }

    public boolean checkFields() {
        boolean status = true;

        if (userName.getText().toString().length() == 0) {
            status = false;

            Toast.makeText(this, "User Name cannot be blank", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().length() == 0) {
            status = false;

            Toast.makeText(this, "User Name cannot be blank", Toast.LENGTH_SHORT).show();
        }
        return status;
    }
}