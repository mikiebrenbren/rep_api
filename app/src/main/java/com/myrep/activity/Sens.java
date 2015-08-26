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

public class Sens extends Activity implements View.OnClickListener{

    private static final String TAG = Activity.class.getName();

    EditText mNameText;
    EditText mStateText;
    Button mNameButton;
    Button mStateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sens);

        mNameText = (EditText) findViewById(R.id.name_et_sen);
        mStateText =(EditText) findViewById(R.id.state_et_sen);

        mNameButton = (Button) findViewById(R.id.namebutton_senator);
        mStateButton = (Button) findViewById(R.id.senatorstate_btn);

        mNameButton.setOnClickListener(this);
        mStateButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        GetRepResponse response;

        if(isConnected()){
            switch(view.getId()){
                case R.id.namebutton_senator:
                    String repName = mNameText.getText().toString();
                    Log.i(TAG, getString(R.string.REPNAME_LOG) + repName);
                    response = new GetRepResponse(this);
                    response.execute(getString(R.string.SENNAME_URL)+repName);
                    emptyTextField();
                    break;
                case R.id.senatorstate_btn:
                    String repState = mStateText.getText().toString();
                    Log.i(TAG, getString(R.string.REPSTATE_LOG) + repState);
                    response = new GetRepResponse(this);
                    response.execute(getString(R.string.SENSTATE_URL)+repState);
                    emptyTextField();
                    break;
            }
        }else{
            Toasts.shortToast(this, getString(R.string.NETERR_TOAST));
        }

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
        mStateText.setText("");
        mNameText.setText("");
    }
}
