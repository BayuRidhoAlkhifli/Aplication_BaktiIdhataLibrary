package com.example.bayuridho.bakthiidhatalibrary.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.view.View;

import com.example.bayuridho.bakthiidhatalibrary.Model.Buku;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;

public class Database extends SQLiteAssetHelper {
    private static final String DB_NAME="bilibrary.db";
    private static final  int DB_VER=1;
    private static final String DATABASE_TABEL = "buku";
    private static final String NAMABUKU = "namaBuku";
    private static final String COLUMN_STOK = "stok";
    private SQLiteDatabase database;

    public Database(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public Database(View.OnClickListener onClickListener) {
        super((Context) onClickListener, DB_NAME, null, 1);
    }


    public List<Buku> getSemuaBuku() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"idRak", "idSekat", "idBuku", "namaBuku", "penulis", "penerbit", "stok", "sampul", "deskripsi"};
        String namaTabel = "buku";
        qb.setTables(namaTabel);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Buku> hasil = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Buku buku = new Buku();
                buku.setIdRak(cursor.getString(cursor.getColumnIndex("idRak")));
                buku.setIdSekat(cursor.getString(cursor.getColumnIndex("idSekat")));
                buku.setIdBuku(cursor.getString(cursor.getColumnIndex("idBuku")));
                buku.setJudulBuku(cursor.getString(cursor.getColumnIndex("namaBuku")));
                buku.setPenulis(cursor.getString(cursor.getColumnIndex("penulis")));
                buku.setPenerbit(cursor.getString(cursor.getColumnIndex("penerbit")));
                buku.setStok(cursor.getString(cursor.getColumnIndex("stok")));
                buku.setSampul(cursor.getBlob(cursor.getColumnIndex("sampul")));
                buku.setDeskripsi(cursor.getString(cursor.getColumnIndex("deskripsi")));
                hasil.add(buku);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return hasil;
    }

    public List<String> getSemuaNama(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"namaBuku"};
        String namaTabel = "buku";

        qb.setTables(namaTabel);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> hasil = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do{
                hasil.add(cursor.getString(cursor.getColumnIndex("namaBuku")));
            }while (cursor.moveToNext());
        }
        return hasil;
    }

    public List<Buku> getBukuDrNama(String namaBuku) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"idRak", "idSekat", "idBuku", "namaBuku", "penulis", "penerbit", "stok", "sampul", "deskripsi"};
        String namaTabel = "buku";
        qb.setTables(namaTabel);
        Cursor cursor = qb.query(db,sqlSelect,"namaBuku LIKE ?",new String[]{"%"+namaBuku+"%"},null,null,null);
        List<Buku> hasil = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Buku buku = new Buku();
                buku.setIdRak(cursor.getString(cursor.getColumnIndex("idRak")));
                buku.setIdSekat(cursor.getString(cursor.getColumnIndex("idSekat")));
                buku.setIdBuku(cursor.getString(cursor.getColumnIndex("idBuku")));
                buku.setJudulBuku(cursor.getString(cursor.getColumnIndex("namaBuku")));
                buku.setPenulis(cursor.getString(cursor.getColumnIndex("penulis")));
                buku.setPenerbit(cursor.getString(cursor.getColumnIndex("penerbit")));
                buku.setStok(cursor.getString(cursor.getColumnIndex("stok")));
                buku.setSampul(cursor.getBlob(cursor.getColumnIndex("sampul")));
                buku.setDeskripsi(cursor.getString(cursor.getColumnIndex("deskripsi")));
                hasil.add(buku);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return hasil;
    }

    //TODO stuck melakukan pendeklarasian updateData
    public void updateData(String hasil, String judul){
       SQLiteDatabase db = getWritableDatabase();

       String sql = "UPDATE buku SET stok ="+ hasil +" WHERE namaBuku ='" + judul+"'";

        db.execSQL(sql);
        db.close();
        /*ContentValues values = new ContentValues();

        values.put(COLUMN_STOK,
                buku.getStok());

        return database.update(
                DATABASE_TABEL, values,
                NAMABUKU
                        + " = '"
                        + buku.getJudulBuku()
                        + "'", null);*/
    }
}
