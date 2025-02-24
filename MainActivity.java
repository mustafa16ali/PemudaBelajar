package com.example.kalkulatorku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


//masukkan import ini

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
//id numerik
    private int[] tombolnumerik = {R.id.nomor0, R.id.nomor1, R.id.nomor2,R.id.nomor3,R.id.nomor4,R.id.nomor5,R.id.nomor6,R.id.nomor6,R.id.nomor7
    ,R.id.nomor8, R.id.nomor9};
// id operator
    private int[] tomboloperasi = {R.id.kali,R.id.tambah,R.id.bagi,R.id.pengkurangan};

//hasil
    private TextView tampil;
    private TextView hasil;
    private boolean angkaTerakhir;
    private boolean jikaerror;
    private boolean setelahtitik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nyari textview
        this.tampil = (TextView) findViewById(R.id.solve);
        this.hasil = (TextView) findViewById(R.id.result);

        //temukan listener
        setnumerikdiklik();

        setOperatoropadasaatklik();

    }

    private void setOperatoropadasaatklik() {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (angkaTerakhir && !jikaerror) {
                    Button tombol = (Button) v;
                    tampil.append(tombol.getText());
                    angkaTerakhir = false;
                    setelahtitik = false; //setel ulang titik
                }
            }
        };
        //tetapkan listener ke semua operator
        for (int id : tomboloperasi) {
            findViewById(id).setOnClickListener(listener);
        }
        findViewById(R.id.tombolKoma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (angkaTerakhir && !jikaerror && !setelahtitik) {
                    tampil.append(".");
                    angkaTerakhir = false;
                    setelahtitik = true;
                }

            }
        });
        findViewById(R.id.C).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampil.setText(" ");//bersih bersih
                hasil.setText(" ");
                angkaTerakhir = false;
                jikaerror = false;
                setelahtitik = false;
            }
        });
        //terakhirdisini
        findViewById(R.id.samadengan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEqual();
            }
        });
    }

    private void onEqual() {
        if(angkaTerakhir && !jikaerror) {
            String txt = tampil.getText().toString();
            Expression expression =  new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                hasil.setText(Double.toString(result));
                setelahtitik = true;
            } catch (ArithmeticException ex) {
                hasil.setText("gabisa sayang");
                jikaerror = true;
                angkaTerakhir = false;
            }
        }
    }

    private void setnumerikdiklik() {
        //untuk oncliklistener
        View.OnClickListener  listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //atur teks tombol
                Button tombol = (Button) v;
                if (jikaerror) {
                    tampil.setText(tombol.getText());
                    jikaerror = false;
                } else {
                    tampil.append(tombol.getText());
                }
                angkaTerakhir = true;
            }
        };
        //tetapkan listener ke semua tombol nuimerik
            for (int id : tombolnumerik) {
                findViewById(id).setOnClickListener(listener);
            }

    }
}
