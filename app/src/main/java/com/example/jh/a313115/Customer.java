package com.example.jh.a313115;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

public class Customer {
    private String customerID, customerName;
    boolean first_login;
    public int first_lineItems;

    public void setcustomerID(String kakaoID){
        this.customerID = kakaoID;
    }
    public String getKakaoID(){
        return this.customerID;
    }

    public void setcustomerName(String kakaoName){
        this.customerName = kakaoName;
    }
    public String getcustomerName(){
        return this.customerName;
    }

    public void setFirstLogin(boolean first_login){
        this.first_login = first_login;
    }
    public boolean getFirstLogin(){
        return this.first_login;
    }

    public void setFirst_lineItems(int first_lineItems){
        this.first_lineItems = first_lineItems;
    }
    public int getFirst_lineItems(){
        return this.first_lineItems;
    }

}
