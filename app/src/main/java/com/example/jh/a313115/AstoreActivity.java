package com.example.jh.a313115;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AstoreActivity extends BaseActivity {

    private ListView listView;
    private ProductListAdapter adapter;
    private List<Product> productLists;
    private List<Product> saveLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astore);

        Handler delayHandler = new Handler();
        new BackgroundTask().execute();

        listView = (ListView)findViewById(R.id.list_view);
        productLists = new ArrayList<Product>();
        saveLists = new ArrayList<Product>();

        adapter = new ProductListAdapter(getApplicationContext(),productLists,saveLists);
        delayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adapter);
            }
        },100);

        EditText search = (EditText)findViewById(R.id.search_product);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchProduct(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute(){
            target = "http://jaejindb.cafe24.com/AstoreItem.php";
        }

        @Override
        protected String doInBackground(Void... voids){
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        public void onPostExecute(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String product_name;
                int price, stockAmount;
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    product_name = object.getString("Name");
                    price = object.getInt("Price");
                    stockAmount = object.getInt("Stock");

                    Product productList = new Product(product_name, stockAmount);
                    productLists.add(productList);
                    saveLists.add(productList);
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void searchProduct(String search){
        productLists.clear();
        for(int i = 0; i < saveLists.size(); i++){
            if(saveLists.get(i).getProduct_name().contains(search)){
                productLists.add(saveLists.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }
}
