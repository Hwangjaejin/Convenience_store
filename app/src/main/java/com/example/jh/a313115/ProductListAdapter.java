package com.example.jh.a313115;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ProductListAdapter extends BaseAdapter{

    private Context context;
    private List<ProductDescription> productDescriptionLists;
    private List<ProductDescription> saveLists;
    private List<Product> productLists;
    private List<Product> saveLists2;

    public ProductListAdapter(Context context, List<ProductDescription> productDescriptionLists, List<ProductDescription> saveLists
                                                ,List<Product> productLists, List<Product> saveLists2){
        this.context = context;
        this.productDescriptionLists = productDescriptionLists;
        this.saveLists = saveLists;
        this.productLists = productLists;
        this.saveLists2 = saveLists2;
    }

    @Override
    public int getCount() {
        return productDescriptionLists.size();
    }

    @Override
    public Object getItem(int i) {
        return productDescriptionLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.item_list, null);
        TextView product_name = (TextView)v.findViewById(R.id.product_name);
        TextView product_price = (TextView)v.findViewById(R.id.product_price);
        TextView product_stock = (TextView)v.findViewById(R.id.product_stock);

        product_name.setText(productDescriptionLists.get(i).getProduct_name());
        product_price.setText(productDescriptionLists.get(i).getPrice()+"원");
        product_stock.setText("재고 : "+productLists.get(i).getStockAmount()+"개");

        Button btn = (Button)v.findViewById(R.id.buy_btn);
        final String name;
        name = productDescriptionLists.get(i).getProduct_name();
        final int price;
        price = productDescriptionLists.get(i).getPrice();
        final int stockAmount;
        stockAmount = productLists.get(i).getStockAmount();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product(stockAmount);
                ProductDescription productDescription = new ProductDescription(name,price);
                product.setStockAmount(stockAmount);
                productDescription.setProduct_name(name);
                productDescription.setPrice(price);

                Intent purchaseActivity = new Intent(context,PurchaseActivity.class);
                purchaseActivity.putExtra("name",name);
                purchaseActivity.putExtra("price",price);
                purchaseActivity.putExtra("stockAmount",stockAmount);
                view.getContext().startActivity(purchaseActivity);
            }
        });
        return v;
    }
}
