package com.example.jh.a313115;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductListAdapter extends BaseAdapter{

    private Context context;
    private List<Product> productLists;
    private List<Product> saveLists;

    public ProductListAdapter(Context context, List<Product> productLists, List<Product> saveLists){
        this.context = context;
        this.productLists = productLists;
        this.saveLists = saveLists;
    }

    @Override
    public int getCount() {
        return productLists.size();
    }

    @Override
    public Object getItem(int i) {
        return productLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.item_list, null);
        TextView product_name = (TextView)v.findViewById(R.id.product_name);

        product_name.setText(productLists.get(i).getProduct_name());
        return v;
    }
}
