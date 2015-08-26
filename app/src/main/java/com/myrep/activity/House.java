package com.myrep.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myrep.R;
import com.myrep.async.GetRepResponse;
import com.myrep.utils.Toasts;
import com.myrep.utils.Validate;

public class House extends Activity implements View.OnClickListener{

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

        mZipText = (EditText) findViewById(R.id.zip_et_house);
        mNameText = (EditText) findViewById(R.id.state_et_house);
        mStateText = (EditText) findViewById(R.id.state_et_rep);

        mZipButton = (Button) findViewById(R.id.zip_btn_sen);
        mNameButton = (Button) findViewById(R.id.name_btn_sen);
        mStateButton = (Button) findViewById(R.id.state_btn_rep);

        mZipButton.setOnClickListener(this);
        mNameButton.setOnClickListener(this);
        mStateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        GetRepResponse response;
        //check network connection before attempting to make api call
        if (isConnected()) {
            switch (view.getId()){
                case R.id.zip_btn_sen:
                    String zipCode = mZipText.getText().toString();
                    Log.i(TAG, getString(R.string.zipstring_log) + zipCode);
                    if(Validate.validateZip(zipCode)){
                        //make the api call
                        response = new GetRepResponse(this);
                        response.execute(getString(R.string.REPBYZIP_URL)+zipCode);
                    }else{
                        Toasts.shortToast(this, getString(R.string.ZIPMESSAGE_TOAST));
                    }
                    emptyTextField();
                    break;
                case R.id.name_btn_sen:
                    String repName = mNameText.getText().toString();
                    Log.i(TAG, getString(R.string.REPNAME_LOG) + repName);
                    response = new GetRepResponse(this);
                    response.execute(getString(R.string.REPBYNAME_URL) + repName);
                    emptyTextField();
                    break;
                case R.id.state_btn_rep:
                    String repState = mStateText.getText().toString();
                    Log.i(TAG, getString(R.string.REPSTATE_LOG) + repState);
                    response = new GetRepResponse(this);
                    response.execute((getString(R.string.REPBYSTATE_URL)+repState));
                    emptyTextField();
                    break;
            }
        } else {
            Toasts.shortToast(this, getString(R.string.NETERR_TOAST));
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

    private boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }

    private void emptyTextField(){
        mNameText.setText("");
        mStateText.setText("");
        mZipText.setText("");
    }

}
