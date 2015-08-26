package com.myrep.async;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myrep.R;
import com.myrep.activity.Results;
import com.myrep.model.Representative;
import com.myrep.utils.Toasts;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael Brennan on 8/25/15.
 */
public class GetRepResponse extends AsyncTask<String, Void, String> {

    Context mContext;
    private static final String TAG = Activity.class.getName();

    public GetRepResponse(Context context){

        this.mContext = context;
    }

    @Override
    protected String doInBackground(String... urls) {

        try {
            return downloadUrl(urls[0]);
        } catch (IOException e) {
            //all urls are hardcoded to the correct url, this should never occur, if there is an invalid search string there will be a error in the response
            return "Unable to retrieve web page. URL may be invalid.";
        }

    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
//        textView.setText(result);
        //start new activity with list view of results.
        //if this is not
        JsonFactory jsonFactory = new JsonFactory();
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, ArrayList<Representative>> repMap = null;
        try {
            JsonParser jsonParser = jsonFactory.createParser(result);
            repMap = objectMapper.readValue(jsonParser, new TypeReference<Map<String, ArrayList<Representative>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, mContext.getString(R.string.JSONERR_LOG));
            Toasts.shortToast(mContext, mContext.getString(R.string.DATAERR_TOAST));
        }

        if(repMap!= null){
            Log.i(TAG, repMap.get(mContext.getString(R.string.RESULT_STR)).get(0).getName());
            Intent intent = new Intent(mContext, Results.class);
            intent.putExtra("map", repMap);
            mContext.startActivity(intent);
        }
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 100000;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod(mContext.getString(R.string.HTTPGET));
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(TAG, mContext.getString(R.string.RESPONSE_LOG) + response);
            is = conn.getInputStream();

            // Convert the InputStream into a string
            //todo do NOT need to convert to a string Jackson can convert to model DIRECTLY from input stream;
            String contentAsString = readIt(is, len);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}
