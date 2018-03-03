package com.example.z00294w.navigationdrawer4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by saloni on 07/02/18.
 */

public class tmListView extends AppCompatActivity implements Preview.PreviewListener{

    // Button submit = (Button) findViewById(R.id.submitTM);
    // EditText txtinputList = (EditText) findViewById(R.id.txtinput);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview);


        Preview preview;
        ArrayList<String> arrayList;
        EditText txtInput;
        ArrayAdapter<String> adapter;
        String newitem;

        preview = findViewById(R.id.preview);
        preview.setListener(this);

        Intent i = getIntent();
        newitem = i.getExtras().getString("url");
        preview.setData(newitem);


//        txtInput = (EditText) findViewById(R.id.txtinput);
//        ListView listView = (ListView) findViewById(R.id.listv);
//        String items []={};
//        arrayList = new ArrayList<>(Arrays.asList(items));
//        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem, arrayList);
//        listView.setAdapter(adapter);
//        Intent i = getIntent();
//
//        newitem = i.getExtras().getString("url");
//
//
//        arrayList.add("https://www.infosecurity-magazine.com/news/suspected-atm-jackpotting/");
//        arrayList.add("https://www.infosecurity-magazine.com/news/2017-worst-year-ever-for-data-loss/");
//        arrayList.add(newitem);
//        adapter.notifyDataSetChanged();



    }

    @Override
    public void onDataReady(Preview preview) {
        preview.setMessage(preview.getLink());
    }


    //create function for submit TM news and go to next screem
    //add onclick func name in function.
}

