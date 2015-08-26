package com.myrep.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myrep.R;
import com.myrep.utils.Toasts;

public class Reps extends Activity implements View.OnClickListener{

    private static final String TAG = Activity.class.getName();

    private EditText mZipText;
    private EditText mNameText;
    private EditText mStateText;
    private Button mZipButton;
    private Button mNameButton;
    private Button mStateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reps);

        mZipText = (EditText) findViewById(R.id.zip_et_rep);
        mNameText = (EditText) findViewById(R.id.name_et_rep);
        mStateText = (EditText) findViewById(R.id.state_et_rep);

        mZipButton = (Button) findViewById(R.id.zip_btn_rep);
        mNameButton = (Button) findViewById(R.id.name_btn_rep);
        mStateButton = (Button) findViewById(R.id.state_btn_rep);

        mZipButton.setOnClickListener(this);
        mNameButton.setOnClickListener(this);
        mStateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){
            case R.id.zip_btn_rep:
                String zipCode = mZipText.getText().toString();
                Log.i(TAG, getString(R.string.zipstring_log) +  zipCode);
                if(validateZip(zipCode)){
                    //make the api call
                }else{
                    Toasts.shortToast(this, getString(R.string.zipmessage_toast));
                }
                break;
            case R.id.name_btn_rep:

//                Log.i(TAG, )
                break;
            case R.id.state_btn_rep:

//                Log.i(TAG, )
                break;
        }
    }

    private boolean validateZip(String zip) {

        //I chose to only validate five digit patterns only for simplicity sake, would want to validate full US zip code.
        String zipCodePattern = "\\d{5}(-\\d{4})?";
        if(zip.matches(zipCodePattern)){
            return true;
        }
        return false;
    }

}
