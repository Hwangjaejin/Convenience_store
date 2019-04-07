package com.example.jh.a313115;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    private static UserData userData;

    public static void createUserData() {
        userData = new UserData();
    }

    public UserData getUserData() {
        return userData;
    }
    @Override
    public void onClick(View view) {

    }
}
