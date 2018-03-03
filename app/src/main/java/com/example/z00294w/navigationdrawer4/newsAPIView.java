package com.example.z00294w.navigationdrawer4;

/**
 * Created by z00294w on 2/13/18.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Intent;

public class newsAPIView extends AppCompatActivity {

    String API_KEY = "bedaede6855141709884425dde2c9b01";    //bbc
    String NEWS_SOURCE = "crypto-coins-news";
    ListView listNews;
    ProgressBar loader;
    String cc;




    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    static final String KEY_AUTHOR = "author";
    static final String KEY_TITLE = "title";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_URL = "url";
    static final String KEY_URLTOIMAGE = "urlToImage";
    static final String KEY_PUBLISHEDAT = "publishedAt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsapi);
        final ListNewsAdapter adapter = new ListNewsAdapter(newsAPIView.this, dataList);

        listNews = (ListView) findViewById(R.id.listNews);
        loader = (ProgressBar) findViewById(R.id.loader);
        listNews.setEmptyView(loader);



//        Bundle extras = getIntent().getExtras();
//
//        if (extras != null) {
//            cc = extras.getString("COUNTRY_CODE");
//            Toast.makeText(newsAPIView.this,"Country code"+cc,Toast.LENGTH_LONG).show();
//
//
//            //The key argument here must match that used in the other activity
//        }
//        else{
//            Toast.makeText(newsAPIView.this,"NoCode",Toast.LENGTH_LONG).show();
//        }

        if(Function.isNetworkAvailable(getApplicationContext()))
        {
            DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        }else{
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }

    }



    class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            LocationClass locationobj=new LocationClass();
//            cc=locationobj.countryCode;
//            Toast.makeText(newsAPIView.this,"CC"+cc,Toast.LENGTH_LONG).show();


        }
        protected String doInBackground(String... args) {
            String xml = "";


            String urlParameters = "";
           // xml = Function.excuteGet("https://newsapi.org/v2/top-headlines?sources="+NEWS_SOURCE+"&sortBy=top&apiKey="+API_KEY, urlParameters);
            // xml = Function.excuteGet("https://www.secureworks.com/rss?feed=blog&category=threats-and-defenses",urlParameters);
            // xml = Function.excuteGet("https://newsapi.org/v2/top-headlines?q=IT&apiKey="+API_KEY,urlParameters);
            //Toast.makeText(newsAPIView.this,"Country code is:"+cc,Toast.LENGTH_LONG).show();
            xml = Function.excuteGet("https://newsapi.org/v2/top-headlines?country=in"+"&category=technology&apiKey="+API_KEY,urlParameters);
            return  xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            if(xml.length()>10){ // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        //map.put(KEY_AUTHOR, jsonObject.optString(KEY_AUTHOR).toString());
                        map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE).toString());
                        map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION).toString());
                        map.put(KEY_URL, jsonObject.optString(KEY_URL).toString());
                        map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE).toString());
                        map.put(KEY_PUBLISHEDAT, jsonObject.optString(KEY_PUBLISHEDAT).toString());
                        dataList.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Unexpected error", Toast.LENGTH_SHORT).show();
                }

                ListNewsAdapter adapter = new ListNewsAdapter(newsAPIView.this, dataList);
                listNews.setAdapter(adapter);

                listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent i = new Intent(newsAPIView.this, DetailsActivity.class);
                        i.putExtra("url", dataList.get(+position).get(KEY_URL));
                        startActivity(i);
                    }
                });

            }else{
                Toast.makeText(getApplicationContext(), "No news found", Toast.LENGTH_SHORT).show();
            }
        }



    }



}