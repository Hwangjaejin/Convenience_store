package com.example.jh.a313115;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class PurchaseActivity extends BaseActivity {

    Intent intent;
    private String product_name;
    private int product_price;
    private int product_stock;
    EditText edit_count;
    EditText edit_arriveTime;
    TextView text_point;
    private int total_count;
    private int total_cost;
    private int myPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        intent = getIntent();
        preferences = getSharedPreferences("point",MODE_PRIVATE);

        product_name = intent.getStringExtra("name");
        product_price = intent.getIntExtra("price",0);
        product_stock = intent.getIntExtra("stockAmount",0);

        total_count = 1;
        total_cost = total_count * product_price;

        TextView name = (TextView)findViewById(R.id.product_name);
        TextView price = (TextView)findViewById(R.id.product_price);
        final TextView totalcost = (TextView)findViewById(R.id.totalcost);

        name.setText(product_name);
        price.setText(product_price+"원");

        edit_count = (EditText)findViewById(R.id.edit_count);
        edit_arriveTime = (EditText)findViewById(R.id.edit_arriveTime);
        text_point = (TextView) findViewById(R.id.text_point);

        myPoint = getUserData().getPoint();
        text_point.setText(myPoint+"원");
        totalcost.setText("총 금액 : "+product_price+'원');
        edit_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().replace(" ","").equals(""))
                    total_count = 0;
                else
                    total_count = Integer.parseInt(charSequence.toString());
                totalcost.setText("총 금액 : "+total_count * product_price +"원");
                total_cost = total_count * product_price;
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        Button buy_button = (Button)findViewById(R.id.button);
        buy_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.button:
                payPoint();
                break;
        }
    }

    public void payPoint(){
        if(edit_count.getText().toString().replace(" ","").equals("")){
            Toast.makeText(getApplicationContext(), "몇 개 살 건지 정하세요",Toast.LENGTH_LONG).show();
        }else if(edit_arriveTime.getText().toString().replace(" ","").equals("")) {
            Toast.makeText(getApplicationContext(), "도착시간을 정하세요", Toast.LENGTH_LONG).show();
        }else if(total_count < 1){
            Toast.makeText(getApplicationContext(), "1개 이상 선택하세요",Toast.LENGTH_LONG).show();
        }else if(total_cost > getUserData().getPoint()){
            Toast.makeText(getApplicationContext(), "포인트가 부족합니다",Toast.LENGTH_LONG).show();
        }else {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        if(success){
                            myPoint = myPoint - total_cost;
                            getUserData().setPoint(myPoint);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("point",getUserData().getPoint());
                            editor.commit();

                            Intent mainActivity = new Intent(PurchaseActivity.this,MainActivity.class);
                          //  mainActivity.putExtra("point",preferences.getInt("point",0));
                            startActivity(mainActivity);
                        }
                    }
                    catch (Exception e){
                        Log.d("jaejin","update fail");
                        e.printStackTrace();
                    }
                }
            };
            UpdateRequest updateRequest = new UpdateRequest(edit_count.getText().toString(), product_name, responseListener);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(updateRequest);
        }
    }

}
