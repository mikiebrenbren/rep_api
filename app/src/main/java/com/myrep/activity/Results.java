package com.myrep.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.myrep.R;
import com.myrep.model.Representative;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Results extends Activity{

    private static final String TAG = Activity.class.getName();
    ListView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        Intent intent = getIntent();
        HashMap<String, ArrayList<Representative>> repMap =
                (HashMap<String, ArrayList<Representative>> ) intent.getSerializableExtra("map");

        resultView = (ListView) findViewById(R.id.result_lv);

        ArrayAdapter<Representative> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                repMap.get("results"));

        resultView.setAdapter(arrayAdapter);


    }

}
