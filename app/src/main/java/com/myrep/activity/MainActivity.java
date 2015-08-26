package com.myrep.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.myrep.R;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button mRepButton;
    private Button mSenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSenButton = (Button) findViewById(R.id.sen_btn);
        mRepButton = (Button) findViewById(R.id.rep_btn);

        mSenButton.setOnClickListener(this);
        mRepButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){

            case R.id.sen_btn:
                intent = new Intent(this, Sens.class);
                startActivity(intent);
                break;
            case R.id.rep_btn:
                intent = new Intent(this, Reps.class);
                startActivity(intent);
                break;
        }
    }
}
