package com.example.z00294w.navigationdrawer4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by saloni on 03/02/18.
 */

public class TM_source extends AppCompatActivity {

   // Button submit = (Button) findViewById(R.id.submitTM);
   // EditText txtinputList = (EditText) findViewById(R.id.txtinput);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmsource);

        Button submitURL = (Button) findViewById(R.id.submitTM);


//        TextView t2 = (TextView) findViewById(R.id.newsUrl);
//        t2.setMovementMethod(LinkMovementMethod.getInstance());

    }

    public void sendToTMListView(View v) {
        Intent intent_TMnewsList = new Intent(TM_source.this, tmListView.class);

        EditText txtinput = (EditText) findViewById(R.id.txtinput);

        intent_TMnewsList.putExtra("url",txtinput.getText().toString());
        startActivity(intent_TMnewsList);
    }



    //create function for submit TM news and go to next screem
    //add onclick func name in function.
}

