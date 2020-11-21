package com.example.madselfpracticelab;

import android.provider.BaseColumns;

public class TableMaster {
    private TableMaster(){}

    public static class User implements BaseColumns{
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_USERNAME = "userName";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_TYPE = "type";
    }

    public static class Message implements BaseColumns {
        public static final String TABLE_NAME = "Message";
        public static final String COLUMN_NAME_SUBJECT = "subject";
        public static final String COLUMN_NAME_MESSAGE = "message";
    }
}
