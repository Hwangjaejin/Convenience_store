package com.example.jh.a313115;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    SharedPreferences preferences;
    Intent intent;
    private ListView listView;
    private StoreListAdapter adapter;
    private List<Store> storeLists;
    private List<Store> saveLists;
    final int Astore = 1;
    final int Bstore = 0;
    Button myinfo_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BackgroundTask().execute(); //BackgroundTask class 실행

        preferences = getSharedPreferences("point",MODE_PRIVATE);

        getUserData().setPoint(preferences.getInt("point",0));

        myinfo_btn = (Button)findViewById(R.id.myinfo_btn);
        myinfo_btn.setOnClickListener(this);



        listView = (ListView)findViewById(R.id.list_view);
        storeLists = new ArrayList<Store>();
        saveLists = new ArrayList<Store>();

        adapter = new StoreListAdapter(getApplicationContext(),storeLists,this,saveLists);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(storeLists.get(position).getStore_name().equals("Bstore")){
                    Intent mainActivity = new Intent(MainActivity.this,BstoreActivity.class);
                    startActivity(mainActivity);
                }else if(storeLists.get(position).getStore_name().equals("Astore")){
                    Intent mainActivity = new Intent(MainActivity.this,AstoreActivity.class);
                    startActivity(mainActivity);
                }
            }
        });

        EditText search = (EditText)findViewById(R.id.search_store);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override //Text가 바뀔때마다 실행되는 함수
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchStore(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        switch (id){
            case R.id.myinfo_btn:
                goMyinfoActivity();
                break;
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        getUserData().setFirstLogin(true);
    }

    public void goMyinfoActivity(){
        Intent myinfoActivity = new Intent(MainActivity.this,MyinfoActivity.class);
        startActivity(myinfoActivity);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>{
        String target;

        @Override
        protected void onPreExecute(){
            target = "http://jaejindb.cafe24.com/List.php";
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
                String store_name, store_address;
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    store_name = object.getString("Name");
                    store_address = object.getString("Address");

                    Store storeList = new Store(store_name,store_address);
                    storeLists.add(storeList);
                    saveLists.add(storeList);
                    count++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void searchStore(String search){
        storeLists.clear();
        for(int i = 0; i < saveLists.size(); i++){
            if(saveLists.get(i).getStore_name().contains(search)){
                storeLists.add(saveLists.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }
}
