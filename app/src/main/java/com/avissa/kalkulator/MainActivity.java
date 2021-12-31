package com.avissa.kalkulator;

import static com.avissa.kalkulator.DBmain.KEY_input1;
import static com.avissa.kalkulator.DBmain.KEY_KALKU;
import static com.avissa.kalkulator.DBmain.KEY_input2;
import static com.avissa.kalkulator.DBmain.KEY_hasil;
import static com.avissa.kalkulator.DBmain.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText angka1, angka2;
    RadioGroup radioGroup;
    RadioButton Tambah, Kurang, Kali,Bagi;
    Button ButtonHasil, ButtonSave, ButtonDisplay, ButtonClear;
    TextView hasilHitung;
    DBmain db;
    SQLiteDatabase sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findid();
        insertdata();
        ButtonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angka1.setText("");
                angka2.setText("");
                hasilHitung.setText("");
                Tambah.setChecked(false);
                Kurang.setChecked(false);
                Kali.setChecked(false);
                Bagi.setChecked(false);
            }
        });

        ButtonHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (angka1.length()==0 && angka2.length()==0){
                    Toast.makeText(getApplication(), "Inputkan angka terlebih dahulu!", Toast.LENGTH_LONG).show();
                }
                else if (angka1.length()==0){
                    Toast.makeText(getApplication(), "Angka pertama tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
                else if (angka2.length()==0){
                    Toast.makeText(getApplication(), "Angka kedua tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
                else {
                    if (Tambah.isChecked()) {
                        int input1 = Integer.parseInt(angka1.getText().toString());
                        int input2 = Integer.parseInt(angka2.getText().toString());
                        int radiotambah = input1 + input2;
                        hasilHitung.setText(String.valueOf(radiotambah));
                    }
                    else if (Kurang.isChecked()) {
                        int input1 = Integer.parseInt(angka1.getText().toString());
                        int input2 = Integer.parseInt(angka2.getText().toString());
                        int radiokurang = input1 - input2;
                        hasilHitung.setText(String.valueOf(radiokurang));
                    }
                    else if (Kali.isChecked()) {
                        int input1 = Integer.parseInt(angka1.getText().toString());
                        int input2 = Integer.parseInt(angka2.getText().toString());
                        int radiokali = input1 * input2;
                        hasilHitung.setText(String.valueOf(radiokali));
                    }
                    else if (Bagi.isChecked()) {
                        double input1 = Double.parseDouble(angka1.getText().toString());
                        double input2 = Double.parseDouble(angka2.getText().toString());
                        double radiobagi = input1 / input2;
                        hasilHitung.setText(Double.toString(radiobagi));
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
    private void findid() {
        angka1 = (EditText) findViewById(R.id.input1);
        angka2 = (EditText) findViewById(R.id.input2);
        hasilHitung = (TextView) findViewById(R.id.hasilnya);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        Tambah = (RadioButton) findViewById(R.id.radiotambah);
        Kurang = (RadioButton) findViewById(R.id.radiokurang);
        Kali = (RadioButton) findViewById(R.id.radiokali);
        Bagi = (RadioButton) findViewById(R.id.radiobagi);

        ButtonHasil = (Button) findViewById(R.id.hitung);
        ButtonSave = (Button) findViewById(R.id.save);
        ButtonDisplay = (Button) findViewById(R.id.display);
        ButtonClear = (Button) findViewById(R.id.clear);
    }

    private void insertdata() {
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = new DBmain(MainActivity.this);
                sq = db.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(KEY_input1, angka1.getText().toString());
                cv.put(KEY_input2, angka2.getText().toString());
                cv.put(KEY_hasil, hasilHitung.getText().toString());

                if (Tambah.isChecked()) {
                    cv.put(KEY_KALKU, Tambah.getText().toString());
                }
                if (Kurang.isChecked()) {
                    cv.put(KEY_KALKU, Kurang.getText().toString());
                }
                if (Kali.isChecked()) {
                    cv.put(KEY_KALKU, Kali.getText().toString());
                }
                if (Bagi.isChecked()) {
                    cv.put(KEY_KALKU, Bagi.getText().toString());
                }
                Long rec = sq.insert(TABLE_NAME, null,cv);
                if (rec != null) {
                    Toast.makeText(MainActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data Gagal Disimpan", Toast.LENGTH_LONG).show();
                }
            }
        });

        ButtonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Display.class);
                startActivity(intent);
            }
        });
    }
}