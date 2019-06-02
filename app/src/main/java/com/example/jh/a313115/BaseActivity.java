package com.example.jh.a313115;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    private static Customer userData;
    private static Point pointData;
    SharedPreferences preferences;
    SharedPreferences pref;


    public static void createPointData() {
        pointData = new Point();
    }

    public Point getPointData() {
        return pointData;
    }

    public static void createUserData() {
        userData = new Customer();
    }

    public Customer getUserData() {
        return userData;
    }
    @Override
    public void onClick(View view) {

    }
}
