package com.example.jh.a313115;

public class Product {
    int stockAmount;
    String product_name;

    public Product(String product_name, int stockAmount) {
        this.product_name = product_name;
        this.stockAmount = stockAmount;
    }
    public String getProduct_name(){
        return product_name;
    }
    public void setProduct_name(String product_name){
        this.product_name = product_name;
    }

    public int getStockAmount(){
        return stockAmount;
    }
    public void setStockAmount(int stockAmount){
        this.stockAmount = stockAmount;
    }
}
