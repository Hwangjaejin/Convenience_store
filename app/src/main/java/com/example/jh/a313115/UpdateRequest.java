package com.example.jh.a313115;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateRequest extends StringRequest{

    final static private String URL = "http://jaejindb.cafe24.com/Update_stock.php";
    private Map<String, String> parameters;

    public UpdateRequest(String item_num, String item_name, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("item_num",item_num);
        parameters.put("item_name",item_name);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}
