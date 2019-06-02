package com.example.jh.a313115;

import android.content.Context;

public class Purchase {
    int arriveTime;
    int purchaseTime;
    int purchaseTotal;

    private Context mContext;
    public Purchase(Context context){
        super();
        this.mContext = context;
    }

    public int getArriveTime(){
        return arriveTime;
    }
    public void setArriveTime(int arriveTime){
        this.arriveTime = arriveTime;
    }

    public int getPurchaseTotal(){
        return purchaseTotal;
    }
    public void setPurchaseTotal(int purchaseTotal){
        this.purchaseTotal = purchaseTotal;
    }

//    public void payPoint(){
//        ((PurchaseActivity) mContext).buy_button();
//    }
}
