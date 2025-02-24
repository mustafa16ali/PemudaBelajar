package com.example.nyoba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//Import 2 library di bawah ini seperti biasanya ya
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    //Nama variabel bebas, boleh ikut namanya seperti pa guru atau bikin sendiri
    private TextView inputmasuk;
    private TextView hasil;
    private String currentInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputmasuk = findViewById(R.id.inputmasuk);

        hasil = findViewById(R.id.hasil);

        // Tombol untuk angka dan operasi
        Button button0 = findViewById(R.id.nol);
        Button button1 = findViewById(R.id.satu);
        Button button2 = findViewById(R.id.dua);
        Button button3 = findViewById(R.id.tiga);
        Button button4 = findViewById(R.id.empat);
        Button button5 = findViewById(R.id.lima);
        Button button6 = findViewById(R.id.enam);
        Button button7 = findViewById(R.id.tujuh);
        Button button8 = findViewById(R.id.delapan);
        Button button9 = findViewById(R.id.sembilan);
        Button buttonAdd = findViewById(R.id.tambah);
        Button buttonSubtract = findViewById(R.id.kurang);
        Button buttonKali = findViewById(R.id.kali);
        Button buttonBagi = findViewById(R.id.bagi);
        Button buttonEqual = findViewById(R.id.samadengan);
        Button buttonClear = findViewById(R.id.c);
        Button buttonKoma = findViewById(R.id.koma);

        // Tombol untuk angka dan operasi bilangan
        button0.setOnClickListener(v -> appendToInput("0"));
        button1.setOnClickListener(v -> appendToInput("1"));
        button2.setOnClickListener(v -> appendToInput("2"));
        button3.setOnClickListener(v -> appendToInput("3"));
        button4.setOnClickListener(v -> appendToInput("4"));
        button5.setOnClickListener(v -> appendToInput("5"));
        button6.setOnClickListener(v -> appendToInput("6"));
        button7.setOnClickListener(v -> appendToInput("7"));
        button8.setOnClickListener(v -> appendToInput("8"));
        button9.setOnClickListener(v -> appendToInput("9"));
        buttonKoma.setOnClickListener(v -> appendToInput("."));
        buttonAdd.setOnClickListener(v -> appendToInput("+"));
        buttonSubtract.setOnClickListener(v -> appendToInput("-"));
        buttonKali.setOnClickListener(v -> appendToInput("*"));
        buttonBagi.setOnClickListener(v -> appendToInput("/"));


        buttonEqual.setOnClickListener(v -> evaluateExpression());
        buttonClear.setOnClickListener(v -> clearInput());
    }

    private void appendToInput(String value) {
        currentInput += value;
        hasil.setText(currentInput);
    }


    // Methode ini digunakan untuk mengecek bilamana ada kesalahan atau error
    private void evaluateExpression() {
        try {
            Expression expression = new ExpressionBuilder(currentInput).build();
            double result = expression.evaluate();
            hasil.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
        } catch (Exception e) {
            hasil.setText("Error");
            currentInput = "";
        }
    }

    private void clearInput() {
        currentInput = "";
        hasil.setText("");
    }
}
//Penjelasan Kode
//exp4j digunakan untuk mengevaluasi ekspresi matematika yang dimasukkan oleh pengguna.
//currentInput menyimpan ekspresi yang sedang diketik oleh pengguna.
//Ketika tombol "=" ditekan, ekspresi di-evaluasi dan hasilnya ditampilkan di layar.
//Tombol "C" digunakan untuk membersihkan input yang sedang diproses.
//Fungsi appendToInput menambahkan angka atau operator ke currentInput dan memperbarui tampilan layar.
//Fungsi evaluateExpression mengonversi string ekspresi menjadi objek Expression dari exp4j, mengevaluasi hasilnya, dan menampilkan hasilnya.
//Fungsi clearInput menghapus semua input.
