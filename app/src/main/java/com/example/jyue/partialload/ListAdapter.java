package com.example.jyue.partialload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jyue on 2018/2/25.
 */

public class ListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Data> arrayList;

    //初始化
    public ListAdapter(ArrayList<Data> arrayList, LayoutInflater inflater){
        this.arrayList = arrayList;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item, null);
            holder.tvCounty = view.findViewById(R.id.tvCounty);
            holder.tvPublishAgency = view.findViewById(R.id.tvPublishAgency);
            holder.tvSiteName = view.findViewById(R.id.tvSiteName);
            holder.tvUvi = view.findViewById(R.id.tvUvi);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        holder.tvCounty.setText(arrayList.get(i).getCounty());
        holder.tvPublishAgency.setText(arrayList.get(i).getPublishAgency());
        holder.tvSiteName.setText(arrayList.get(i).getSiteName());
        holder.tvUvi.setText(arrayList.get(i).getUvi());

        return view;
    }

    private static class ViewHolder {
        private TextView tvCounty;
        private TextView tvPublishAgency;
        private TextView tvSiteName;
        private TextView tvUvi;
    }
}
