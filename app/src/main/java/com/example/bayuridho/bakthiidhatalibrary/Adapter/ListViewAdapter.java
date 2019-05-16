package com.example.bayuridho.bakthiidhatalibrary.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bayuridho.bakthiidhatalibrary.Model.Buku;
import com.example.bayuridho.bakthiidhatalibrary.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    //variabel
    private Context context;
    private List<Buku> modelList;
    private ArrayList<Buku> arrayList;

    //konstruktor

    public ListViewAdapter(Context context, List<Buku> modelList) {
        this.context = context;
        this.modelList = modelList;
        this.arrayList = new ArrayList<Buku>();
        this.arrayList.addAll(modelList);
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView mJudulTv,mPenulisTv,mStokTv,mIdRakTv,mIdSekatTv;
        ImageView mSampulIv;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View item = view;
        ViewHolder holder = new ViewHolder();

        if (item==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(R.layout.layout_item,null);

            //temukan tampilan di layout_item.xml
            holder.mJudulTv = item.findViewById(R.id.judul);
            holder.mPenulisTv = item.findViewById(R.id.penulis);
            holder.mStokTv = item.findViewById(R.id.stok);
            holder.mIdRakTv = item.findViewById(R.id.idRak);
            holder.mIdSekatTv = item.findViewById(R.id.idSekat);
            holder.mSampulIv = item.findViewById(R.id.sampul);
            item.setTag(holder);
        }
        else {
            holder = (ViewHolder)item.getTag();
        }
        Buku buku = arrayList.get(position);
        //atur hasilnya ke textview
        holder.mJudulTv.setText(buku.getJudulBuku());
        holder.mPenulisTv.setText(buku.getPenulis());
        holder.mStokTv.setText(buku.getStok());
        holder.mIdRakTv.setText(buku.getIdRak());
        holder.mIdSekatTv.setText(buku.getIdSekat());

        //atur hasilnya ke imageview
        byte[] itemImage = buku.getSampul();
        Bitmap bitmap = BitmapFactory.decodeByteArray(itemImage, 0 , itemImage.length);
        holder.mSampulIv.setImageBitmap(bitmap);

        return item;
    }
}
