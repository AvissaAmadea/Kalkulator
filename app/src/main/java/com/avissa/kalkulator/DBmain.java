package com.avissa.kalkulator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    public static final String DB_name = "kalkulator.db";
    public static final String TABLE_NAME = "course";
    public static final String KEY_ID = "id";
    public static final String KEY_input1 = "input1";
    public static final String KEY_KALKU = "operasi";
    public static final String KEY_input2 = "input2";
    public static final String KEY_hasil = "hasil";
    public static final int VER = 1;

    public DBmain(@Nullable Context context) { super(context, DB_name, null, VER);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(" CREATE table " + TABLE_NAME + "(" + KEY_ID + " integer primary key, " + KEY_input1 + " text, " + KEY_KALKU + " text, " + KEY_input2 + " text, " + KEY_hasil + " text)");
        }
        catch (Exception e) {
            Log.e("DBmain", "ERROR WHEN CREATE TABLE", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" drop table if exists " + TABLE_NAME + "");
        onCreate(db);
    }
}
