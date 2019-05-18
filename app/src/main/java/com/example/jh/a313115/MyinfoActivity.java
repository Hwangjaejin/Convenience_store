package com.example.jh.a313115;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyinfoActivity extends BaseActivity {

    TextView nameText, pointText;
    Button point_btn, purchaseList_btn;
    SharedPreferences preferences;
    private int point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        preferences = getSharedPreferences("point",MODE_PRIVATE);

        nameText = (TextView)findViewById(R.id.nameText);
        nameText.setText("안녕하세요 "+getUserData().getKakaoName()+"님!");

        point_btn = (Button)findViewById(R.id.point_btn);
        point_btn.setOnClickListener(this);

        pointText = (TextView)findViewById(R.id.pointText);
        pointText.setText(preferences.getInt("point",0)+"원");

        purchaseList_btn = (Button)findViewById(R.id.purchaseList_btn);
        purchaseList_btn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Intent mainActivity = new Intent(MyinfoActivity.this,MainActivity.class);
        mainActivity.putExtra("point",preferences.getInt("point",0));
        startActivity(mainActivity);
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.point_btn:
                chargePoint();
                break;
            case R.id.purchaseList_btn:
                showPurchaseList();
        }
    }

    public void chargePoint(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MyinfoActivity.this);
        dialog.setTitle("포인트충전");
        dialog.setMessage("충전할 금액을 입력해주세요");

        final EditText point_edit = new EditText(MyinfoActivity.this);
        point_edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        dialog.setView(point_edit);

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences.Editor editor = preferences.edit();

                String value = point_edit.getText().toString();
                getUserData().setPoint(preferences.getInt("point",0)+Integer.parseInt(value));

                editor.putInt("point",getUserData().getPoint());
                editor.commit();

                pointText.setText(preferences.getInt("point",0)+"원");
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showPurchaseList(){

    }
}
