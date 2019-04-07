package com.example.jh.a313115;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text1 = (TextView)findViewById(R.id.text1);
        TextView text2 = (TextView)findViewById(R.id.text2);

        text1.setText(getUserData().getKakaoID());
        text2.setText(getUserData().getKakaoName());
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        getUserData().setFirstLogin(true);
    }
}
