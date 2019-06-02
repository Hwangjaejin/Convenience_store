package com.example.jh.a313115;

public class Store {
    String store_name;
    String store_address;
    int openTime = 6;
    int closeTime = 23;
    public Store(String store_name, String store_address) {
        this.store_name = store_name;
        this.store_address = store_address;
    }

    public String getStore_name(){
        return store_name;
    }
    public void setStore_name(String store_name){
        this.store_name = store_name;
    }

    public String getStore_address(){
        return store_address;
    }
    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }
}