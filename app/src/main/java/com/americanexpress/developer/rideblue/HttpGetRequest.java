package com.americanexpress.developer.rideblue;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sachinsomasundar on 8/28/18.
 */
public class HttpGetRequest {
    private String callType;
    private String basedURL = "http://54.87.76.170:8080/";
    public void getDetailsFromDB(int key , String resource)throws IOException{

        Log.i("in get req", "insrtdb");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        URL url = new URL(basedURL.concat(resource));
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setDoOutput(true);
        http.setDoInput(true);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.setRequestProperty("Accept", "application/json");
        http.setRequestMethod("POST");
        http.connect();

        OutputStreamWriter wr = new OutputStreamWriter(http.getOutputStream());
        wr.write(key);
        wr.flush();
        StringBuilder sb = new StringBuilder();
        int HttpResult = http.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(http.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
                Log.i("data", line);
            }
            br.close();
            //         System.out.println("" + sb.toString());
            Log.i("in post requ succ", sb.toString());
        } else {
            //          System.out.println(http.getResponseMessage());
            Log.i("in post requ fail", http.getResponseMessage());
        }
    }
}