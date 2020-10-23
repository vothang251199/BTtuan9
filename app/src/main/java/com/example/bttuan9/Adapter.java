package com.example.bttuan9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    public ArrayList<ArrBean> arrayingListener;
    Context mContext;

    public Adapter(ArrayList<ArrBean> mListenerList, Context context) {
        mContext = context;
        this.arrayingListener = new ArrayList<ArrBean>();
        this.arrayingListener =mListenerList;
    }

    public class ViewHolder {
        ImageView mItemPic;
        TextView mLangName,moTa;
    }

    @Override
    public int getCount() {
        return arrayingListener.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayingListener.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.mItemPic = (ImageView) view.findViewById(R.id.img_logo);
            holder.mLangName=(TextView)view.findViewById(R.id.tv_Name);
            holder.moTa=(TextView)view.findViewById(R.id.mota);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();

        }
        try {
            int image = arrayingListener.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            holder.mLangName.setText(arrayingListener.get(position).getLangName());
            holder.moTa.setText(arrayingListener.get(position).getMoTa());
        }catch (Exception ex){


        }

        return view;
    }

}