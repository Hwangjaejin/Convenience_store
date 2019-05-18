package com.example.jh.a313115;

public class ProductDescription {
    int price;
    String product_name;

    public ProductDescription(String product_name, int price){
        this.product_name = product_name;
        this.price = price;
    }

    public String getProduct_name(){
        return product_name;
    }
    public void setProduct_name(String product_name){
        this.product_name = product_name;
    }

    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }
}
