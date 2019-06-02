package com.example.jh.a313115;

import java.util.List;

public class PurshasesLineItem {
    private int quantity;
    private String name;
    ProductDescription productDescription;

    public PurshasesLineItem(String name,int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getSubtotal() {
        productDescription = new ProductDescription("",0);
        return 0;
    }

}
