package com.example.employeecontect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<PhoneInfo> lists;
    private LinearLayout layout;
    private Context context;

    public MyAdapter(List<PhoneInfo> lists, Context context) {
        this.lists=lists;
        this.context=context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        layout=(LinearLayout) inflater.inflate(R.layout.cail,null);
        TextView nametv = layout.findViewById(R.id.name);
        TextView numbertv = layout.findViewById(R.id.number);
        nametv.setText(lists.get(position).getName());
        numbertv.setText(lists.get(position).getNumber());
        return layout;
    }
}
