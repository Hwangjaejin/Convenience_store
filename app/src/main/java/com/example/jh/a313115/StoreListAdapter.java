package com.example.jh.a313115;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class StoreListAdapter extends BaseAdapter{

    private Context context;
    private List<Store> storeLists;

    public StoreListAdapter(Context context, List<Store> storeLists){
        this.context = context;
        this.storeLists = storeLists;
    }

    @Override
    public int getCount() {
        return storeLists.size();
    }

    @Override
    public Object getItem(int i) {
        return storeLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.store_list, null);
        TextView store_name = (TextView)v.findViewById(R.id.store_name);
        TextView store_address = (TextView)v.findViewById(R.id.store_address);

        store_name.setText(storeLists.get(i).getStore_name());
        store_address.setText(storeLists.get(i).getStore_address());
        return v;
    }
}
