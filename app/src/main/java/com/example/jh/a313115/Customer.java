package com.example.jh.a313115;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Customer {

    private String kakaoID, kakaoName;
    private int point;
    boolean first_login;

    public void setKakaoID(String kakaoID){
        this.kakaoID = kakaoID;
    }
    public String getKakaoID(){
        return this.kakaoID;
    }

    public void setKakaoName(String kakaoName){
        this.kakaoName = kakaoName;
    }
    public String getKakaoName(){
        return this.kakaoName;
    }

    public void setFirstLogin(boolean first_login){
        this.first_login = first_login;
    }
    public boolean getFirstLogin(){
        return this.first_login;
    }

    public void setPoint(int point){
        this.point = point;
    }
    public int getPoint(){
        return this.point;
    }

}
