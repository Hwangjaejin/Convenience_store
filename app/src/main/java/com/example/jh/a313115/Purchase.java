package com.example.jh.a313115;

public class Purchase {
    int arriveTime;
    int purchaseTime;
    int purchaseTotal;

    public Purchase(int purchaseTime, int purchaseTotal){
        this.purchaseTime = purchaseTime;
        this.purchaseTotal = purchaseTotal;
    }

    public int getPurchaseTime(){
        return purchaseTime;
    }
    public void setPurchaseTime(int purchaseTime){
        this.purchaseTime = purchaseTime;
    }

    public int getPurchaseTotal(){
        return purchaseTotal;
    }
    public void setPurchaseTotal(int purchaseTotal){
        this.purchaseTotal = purchaseTotal;
    }
}
