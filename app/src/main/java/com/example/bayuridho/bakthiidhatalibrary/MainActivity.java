package com.example.bayuridho.bakthiidhatalibrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bayuridho.bakthiidhatalibrary.Adapter.ListViewAdapter;
import com.example.bayuridho.bakthiidhatalibrary.Database.Database;
import com.example.bayuridho.bakthiidhatalibrary.Model.Buku;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    ArrayList<Buku> list;
    ListViewAdapter adapter;
    Buku buku;

    CardView cardView;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();
    Database database;
    Toolbar toolbar;

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        cardView = (CardView)findViewById(R.id.welcome);
        listView = (ListView) findViewById(R.id.listView);
        materialSearchBar = (MaterialSearchBar)findViewById(R.id.searchBar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        list = new ArrayList<>();

        navigationView.setNavigationItemSelectedListener(this);
        //memulai DB
        database = new Database(this);

        //Mengatur search bar
        materialSearchBar.setPlaceHolder("Bakti Idhata Library");
        materialSearchBar.setHint("Buku apa yang anda cari?");
        materialSearchBar.setCardViewElevation(10);
        //loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               /* List<String> suggest = new ArrayList<>();
                for (String search : suggestList) {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled){
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                    searchName(text.toString());
                    cardView.setVisibility(View.GONE);
                    materialSearchBar.setText("");
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                    switch (buttonCode)
                    {
                        case MaterialSearchBar.BUTTON_NAVIGATION:
                            drawer.openDrawer(Gravity.LEFT);
                            break;

                        case MaterialSearchBar.BUTTON_BACK:
                            materialSearchBar.disableSearch();
                            searchName("".toString());
                            listView.setAdapter(adapter);
                            cardView.setVisibility(View.VISIBLE);

                            break;
                    }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                buku = (Buku) adapter.getItem(position);
                Intent intent = new Intent(MainActivity.this,DetailItemActivity.class);
                intent.putExtra("idRak",buku.getIdRak());
                intent.putExtra("idSekat",buku.getIdSekat());
                intent.putExtra("idBuku",buku.getIdBuku());
                intent.putExtra("judulBuku",buku.getJudulBuku());
                intent.putExtra("penulis",buku.getPenulis());
                intent.putExtra("penerbit",buku.getPenerbit());
                intent.putExtra("stok",buku.getStok());
                intent.putExtra("deskripsi",buku.getDeskripsi());
                intent.putExtra("sampul",buku.getSampul());
                startActivity(intent);
            }
        });

        //Init Adapter default set all result
        adapter = new ListViewAdapter(this, database.getSemuaBuku());
        listView.setAdapter(adapter);   

    }

    private void searchName(String text) {

        adapter = new ListViewAdapter(this, database.getBukuDrNama(text));
        listView.setAdapter(adapter);
    }
/*
    private void loadSuggestList() {
        suggestList = database.getSemuaNama();
        materialSearchBar.setLastSuggestions(suggestList);
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.info_App){
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

            dialog.setTitle("Informasi Aplikasi");
            dialog.setMessage("Bakti Idhata Library adalah aplikasi yang bertujuan" +
                    " untuk memudahkan pengunjung perpustakaan baik itu siswa ataupun" +
                    " guru dalam mencari informasi buku yang diinginkan.\nDisajikan dalam" +
                    " bentuk List dan aplikasi ini juga dapat menampilkan detail dari buku tergantung dari buku yang dipilih.");
            dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
        }
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
