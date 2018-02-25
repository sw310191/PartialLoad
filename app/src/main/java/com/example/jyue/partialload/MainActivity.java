package com.example.jyue.partialload;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    private ListView listview;
    private ListAdapter listAdapter;
    private ArrayList<Data> arrayList = new ArrayList<>();


    private final String TAG = "Jay-debug";
    private final String URL = "http://opendata2.epa.gov.tw/UV/UV.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
        getData();
    }

    private void findViews() {
        listview = findViewById(R.id.listView);
    }

    private void setListener() {
        listview.setOnScrollListener(this);
    }

    private void getData() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, "response = " + response.toString());
                try {
                    parserJson(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "error : " + error.toString());
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void parserJson(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Data data = new Data();

            data.setCounty(jsonObject.getString("County"));
            data.setPublishAgency(jsonObject.getString("PublishAgency"));
            data.setSiteName(jsonObject.getString("SiteName"));
            data.setUvi(jsonObject.getString("UVI"));

            arrayList.add(data);
        }
        
        if (listAdapter == null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listAdapter = new ListAdapter(arrayList, inflater);
            listview.setAdapter(listAdapter);
        } else {
            listAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_IDLE: {
                int lastPosition = listview.getLastVisiblePosition();
                if (lastPosition == (arrayList.size() - 1)) {
                    getData();
                }
                break;
            }
            case SCROLL_STATE_TOUCH_SCROLL: {
                break;
            }
            case SCROLL_STATE_FLING: {
                break;
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
