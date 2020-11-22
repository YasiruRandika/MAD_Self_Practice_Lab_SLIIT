package com.example.madselfpracticelab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student extends AppCompatActivity {
    private ListView listView;
    private TextView welcomeMsg;
    private ArrayList<messageModel> messageModels;
    private DB_Handler db_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("user");

        db_handler = new DB_Handler(this);
        listView = findViewById(R.id.list);
        messageModels = db_handler.getMessageList();
        welcomeMsg = findViewById(R.id.wStu);

        welcomeMsg.setText("Welcome " + userName);

        ArrayList<String> list = new ArrayList<>();

        for (messageModel  msg: messageModels) {
            list.add(msg.getSubject());
        }

        MyArrayAdapter arrayAdapter = new MyArrayAdapter(this, list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(Student.this, Message.class);
                intent.putExtra("id", messageModels.get(position).getMessageId());
                startActivity(intent);
            }
        });
    }

    public class MyArrayAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> arrayList;

        MyArrayAdapter(Context context, ArrayList<String> list) {
            super(context, R.layout.listtemplate, R.id.subjectName, list);
            this.context = context;
            this.arrayList = list;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.listtemplate, parent, false);
            TextView textView = view.findViewById(R.id.subjectName);

            textView.setText(arrayList.get(position));
            return super.getView(position, convertView, parent);
        }
    }
}