package com.example.bayuridho.bakthiidhatalibrary;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bayuridho.bakthiidhatalibrary.Database.Database;
import com.example.bayuridho.bakthiidhatalibrary.Model.Buku;

public class DetailItemActivity extends AppCompatActivity {

    Toolbar toolbar;
    Database database;

    TextView det_namaBuku,det_stok,det_idRak,det_idSekat,det_penulis,det_penerbit,det_deskripsi;
    ImageView det_sampul;
    String s_namaBuku,s_stok,s_idRak,s_idSekat,s_idBuku,s_penulis,s_penerbit,s_deskripsi;
    byte[] s_sampul;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);

        toolbar = findViewById(R.id.actionBar);
        int color = Color.WHITE;

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new Database(this);

        toolbar.getNavigationIcon().mutate().setColorFilter(color,PorterDuff.Mode.SRC_IN);

        det_namaBuku = (TextView)findViewById(R.id.det_namaBuku);
        det_stok = (TextView)findViewById(R.id.det_stok);
        det_idRak = (TextView)findViewById(R.id.det_idRak);
        det_idSekat = (TextView)findViewById(R.id.det_idSekat);
        det_penulis = (TextView)findViewById(R.id.det_penulis);
        det_penerbit = (TextView)findViewById(R.id.det_penerbit);
        det_deskripsi = (TextView)findViewById(R.id.det_deskripsi);
        det_sampul = (ImageView) findViewById(R.id.det_cover);
        final Button btn_pinjam = findViewById(R.id.btn_pinjam);

        Intent intent = getIntent();
        if (intent !=null){
            s_namaBuku = intent.getStringExtra("judulBuku");
            s_stok = intent.getStringExtra("stok");
            s_idRak = intent.getStringExtra("idRak");
            s_idSekat = intent.getStringExtra("idSekat");
            s_idBuku = intent.getStringExtra("idBuku");
            s_penulis = intent.getStringExtra("penulis");
            s_penerbit = intent.getStringExtra("penerbit");
            s_deskripsi = intent.getStringExtra("deskripsi");
            s_sampul = intent.getByteArrayExtra("sampul");
        }

        final int getStok = Integer.parseInt(s_stok);

        det_namaBuku.setText(s_namaBuku);
        det_stok.setText(getStok+"");
        det_idRak.setText(s_idRak);
        det_idSekat.setText(s_idSekat);
        det_penulis.setText(s_penulis);
        det_penerbit.setText(s_penerbit);
        det_deskripsi.setText(s_deskripsi);
        det_sampul.setImageBitmap(BitmapFactory.decodeByteArray(s_sampul,0,s_sampul.length));

        toolbar.setTitle(s_namaBuku);
        toolbar.setTitleTextColor(color);
        toolbar.setTitleMarginEnd(50);

        //TODO Kurang mengerti untuk pemanggilan updateData
        btn_pinjam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                int a = getStok - 1;
                String hasil = Integer.toString(a);
                String judul = s_namaBuku;
                if (getStok == 0)
                {
                    pinjam(judul,getStok);
                    btn_pinjam.setEnabled(false);
                } else {
                database.updateData(hasil, judul);
                finish();
                Intent intent = new Intent(DetailItemActivity.this,MainActivity.class);
                startActivity(intent);}

//               Toast.makeText(DetailItemActivity.this,hasil, Toast.LENGTH_SHORT).show();
                /*try {


                }
                catch (Exception e){
                    Log.e("error",e.getMessage());
                }*/
            }
        });

    }

    private void pinjam(String s_namaBuku, int det_stok) {
        if (det_stok < 1){
            Toast.makeText(this,"Maaf, Stok buku ini sudah habis", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    /*public void pijam() {
        if (det_stok < 1){
            Toast.makeText(this,"Maaf, Stok buku ini sudah habis", Toast.LENGTH_SHORT).show();
            return;
        }
        det_stok = det_stok - 1;

        ContentValues values = new ContentValues();
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
