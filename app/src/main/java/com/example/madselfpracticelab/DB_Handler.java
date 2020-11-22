package com.example.madselfpracticelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_Handler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CourseDB.db";

    public DB_Handler(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_TABLE_USER = "CREATE TABLE " + TableMaster.User.TABLE_NAME + " (" +
                TableMaster.User._ID + " INTEGER PRIMARY KEY," +
                TableMaster.User.COLUMN_NAME_USERNAME + " TEXT," +
                TableMaster.User.COLUMN_NAME_PASSWORD + " TEXT," +
                TableMaster.User.COLUMN_NAME_TYPE + " TEXT)";

        String SQL_CREATE_TABLE_MESSAGE = "CREATE TABLE " + TableMaster.Message.TABLE_NAME + " (" +
                TableMaster.Message._ID + " INTEGER PRIMARY KEY," +
                TableMaster.Message.COLUMN_NAME_SUBJECT + " TEXT," +
                TableMaster.Message.COLUMN_NAME_MESSAGE + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MESSAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(String userName, String password, String type) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TableMaster.User.COLUMN_NAME_USERNAME, userName);
        contentValues.put(TableMaster.User.COLUMN_NAME_PASSWORD, password);
        contentValues.put(TableMaster.User.COLUMN_NAME_TYPE, type);

        long status = sqLiteDatabase.insert(TableMaster.User.TABLE_NAME, null, contentValues);

        if (status > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addMessage(String subject, String message) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TableMaster.Message.COLUMN_NAME_SUBJECT, subject);
        contentValues.put(TableMaster.Message.COLUMN_NAME_MESSAGE, message);

        long status = sqLiteDatabase.insert(TableMaster.Message.TABLE_NAME, null, contentValues);

        if (status > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<messageModel> getMessageList() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<messageModel> messageModelArrayList = new ArrayList<>();

        String[] projections = {
                TableMaster.Message._ID,
                TableMaster.Message.COLUMN_NAME_SUBJECT,
                TableMaster.Message.COLUMN_NAME_MESSAGE,
        };

        Cursor cursor = sqLiteDatabase.query(
                TableMaster.Message.TABLE_NAME,
                projections,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            messageModel messageModel = new messageModel();

            messageModel.setMessageId(cursor.getInt(cursor.getColumnIndexOrThrow(TableMaster.Message._ID)));
            messageModel.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(TableMaster.Message.COLUMN_NAME_SUBJECT)));
            messageModel.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(TableMaster.Message.COLUMN_NAME_MESSAGE)));

            messageModelArrayList.add(messageModel);
        }

        return messageModelArrayList;
    }

    public String login(String userName, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] projection = {TableMaster.User.COLUMN_NAME_PASSWORD,  TableMaster.User.COLUMN_NAME_TYPE};
        String selection = TableMaster.User.COLUMN_NAME_USERNAME + " LIKE ?";
        String[] selectionArgs = {userName};

        Cursor cursor = sqLiteDatabase.query(
                TableMaster.User.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            Log.d("DB", "Moving to first");
            if (cursor.getString(cursor.getColumnIndexOrThrow(TableMaster.User.COLUMN_NAME_PASSWORD)).equals(password)) {
                Log.d("DB", cursor.getString(cursor.getColumnIndexOrThrow(TableMaster.User.COLUMN_NAME_TYPE)));
                return cursor.getString(cursor.getColumnIndexOrThrow(TableMaster.User.COLUMN_NAME_TYPE));
            } else {
                Log.d("DB", "Not equal");
                return null;
            }
        } else {
            Log.d("DB", "Not moving");
            return null;
        }
    }

    public messageModel getMessageById(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] projection = {TableMaster.Message._ID, TableMaster.Message.COLUMN_NAME_SUBJECT, TableMaster.Message.COLUMN_NAME_MESSAGE};
        String selection = TableMaster.Message._ID + " = " + id;

        Cursor cursor = sqLiteDatabase.query(
                TableMaster.Message.TABLE_NAME,
                projection,
                selection,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            messageModel messageModel = new messageModel();

            messageModel.setMessageId(cursor.getInt(cursor.getColumnIndexOrThrow(TableMaster.Message._ID)));
            messageModel.setMessage(cursor.getString(cursor.getColumnIndexOrThrow(TableMaster.Message.COLUMN_NAME_MESSAGE)));
            messageModel.setSubject(cursor.getString(cursor.getColumnIndexOrThrow(TableMaster.Message.COLUMN_NAME_SUBJECT)));

            return messageModel;
        } else {
            return null;
        }
    }
}
