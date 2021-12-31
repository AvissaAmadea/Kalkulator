package com.avissa.kalkulator;

import static com.avissa.kalkulator.DBmain.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Display extends AppCompatActivity {
    DBmain db;
    SQLiteDatabase sq;
    ListView listView;
    String[] input1, input2, hasil, operasi;
    int[] id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        findid();
        display();
    }

    private void display() {
        db = new DBmain(this);
        sq = db.getReadableDatabase();
        Cursor cursor = sq.rawQuery("select *from " + TABLE_NAME + "", null);
        if (cursor.getCount() > 0) {
            id = new int[cursor.getCount()];
            input1 = new String[cursor.getCount()];
            input2 = new String[cursor.getCount()];
            hasil = new String[cursor.getCount()];
            operasi = new String[cursor.getCount()];
            int i = 0;
            while (cursor.moveToNext()) {
                id[i] = cursor.getInt(0);
                input1[i] = cursor.getString(1);
                input2[i] = cursor.getString(2);
                hasil[i] = cursor.getString(3);
                operasi[i] = cursor.getString(4);
                i++;
            }
            Custom custom = new Custom();
            listView.setAdapter(custom);
        }
    }

    private void findid() {
        listView = (ListView) findViewById(R.id.listview);
    }

    private class Custom extends BaseAdapter {

        @Override
        public int getCount() {
            return input1.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView t1, t2, t3, t4;
            convertView = LayoutInflater.from(Display.this).inflate(R.layout.data, parent, false);
            t1 = (TextView) convertView.findViewById(R.id.satu);
            t2 = (TextView) convertView.findViewById(R.id.tiga);
            t3 = (TextView) convertView.findViewById(R.id.empat);
            t4 = (TextView) convertView.findViewById(R.id.dua);

            t1.setText(input1[position]);
            t2.setText(operasi[position]);
            t3.setText(input2[position]);
            t4.setText(hasil[position]);
            return convertView;
        }
    }
}