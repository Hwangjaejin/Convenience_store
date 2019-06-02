package com.example.jh.a313115;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class PurchasesLineItemActivity extends BaseActivity {

    private Context mContext;
    TextView itemsText;
    TextView totalcosttext;
    String name;
    String price;
    ProductDescription productDescription;
    private Purchase purchase;
    int totalCost;
    private int myPoint;
    boolean isFirstResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases_line_item);
        productDescription = new ProductDescription("",0);
        purchase = new Purchase(this);
        preferences = getSharedPreferences("point",MODE_PRIVATE);
        pref = getSharedPreferences("itemlist",MODE_PRIVATE);
        itemsText = findViewById(R.id.itemsText);
        totalcosttext = findViewById(R.id.total_cost_text);

        myPoint = getPointData().getPoint();
        Button buyButton = (Button)findViewById(R.id.buyButton);
        buyButton.setOnClickListener(this);
        Button clearButton = (Button)findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);

        isFirstResponse = true;
        showLineItems();
        showTotalCost();
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.buyButton:
                makePayment();
                break;
            case R.id.clearButton:
                clear();
        }
    }

    private void makePayment(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success) {
                        if (isFirstResponse) {
                            myPoint = myPoint - totalCost;
                            purchase.setPurchaseTotal(totalCost);
                            getPointData().setPoint(myPoint);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("point", getPointData().getPoint());
                            editor.commit();
                            GlobalApplication.lineItems.clear();
                            Intent mainActivity = new Intent(PurchasesLineItemActivity.this, MainActivity.class);
                            startActivity(mainActivity);
                            isFirstResponse = false;
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
            for(int i = 0 ; i < GlobalApplication.lineItems.size(); i++) {
                UpdateRequest updateRequest = new UpdateRequest(GlobalApplication.lineItems.get(i).getQuantity() + "", GlobalApplication.lineItems.get(i).getName(), responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(updateRequest);
            }

    }
    private void clear(){
        GlobalApplication.lineItems.clear();
        Intent mainActivity = new Intent(PurchasesLineItemActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }
    private void showLineItems(){
        for(int i = 0 ; i < GlobalApplication.lineItems.size() ; i++) {
            itemsText.append(GlobalApplication.lineItems.get(i).getName());
            itemsText.append("      "+(GlobalApplication.lineItems.get(i).getQuantity()+"개"));
            itemsText.append("\r\n");
        }
    }
    private void showTotalCost(){
        for(int i = 0 ; i < GlobalApplication.lineItems.size() ; i++){
            totalCost += GlobalApplication.lineItems.get(i).getQuantity() * productDescription.getProductPrice(GlobalApplication.lineItems.get(i).getName());
        }
        totalcosttext.setText("총 금액 : "+totalCost+"원");
    }

}
