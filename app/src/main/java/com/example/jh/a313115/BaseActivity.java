package com.example.jh.a313115;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    private static Customer userData;
    SharedPreferences preferences;

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
