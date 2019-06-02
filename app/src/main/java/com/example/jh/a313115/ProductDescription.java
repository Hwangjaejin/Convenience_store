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
    public int getProductPrice(String name){
        if(name.equals("apple"))
            return 3900;
        else if(name.equals("banana"))
            return 5000;
        else if(name.equals("banana milk"))
            return 2000;
        else if(name.equals("beef"))
            return 2000;
        else if(name.equals("bread"))
            return 400;
        else if(name.equals("chicken"))
            return 200;
        else if(name.equals("gum"))
            return 100;
        else if(name.equals("milk"))
            return 2000;
        else if(name.equals("pork"))
            return 300;
        else if(name.equals("snack"))
            return 3000;
        else
            return 11;
    }
}
