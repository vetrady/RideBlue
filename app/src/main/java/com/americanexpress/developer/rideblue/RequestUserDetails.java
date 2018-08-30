package com.americanexpress.developer.rideblue;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
public class RequestUserDetails extends AppCompatActivity
{
    private EditText textInputHouseAddress;
    private EditText textCar;
    private NestedScrollView nestedScrollView;
    private Spinner officeSpinner;
    private HttpPostRequest postRequest;
    Button button;
    GoogleSignInOptions gso;
    GoogleSignInAccount account;
    String houseAddress;
    String officeAddress;
    String carDetails;
    int tokenID;
    UserDetails userData;
    JSONObject user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("in request", "in create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        initViews();
    }
    public void initViews() {
        Log.i("in request", "in views");
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestEmail()
                .build();
        account = GoogleSignIn.getLastSignedInAccount(this);
        //     tokenID = String.valueOf(account.getServerAuthCode());
        String emailID = String.valueOf(account.getEmail());
        tokenID = emailID.hashCode();
        String userName = String.valueOf(account.getDisplayName());
        Log.i("username", userName);
        //to do call the method to insert details
    }
    public void saveDetails(View button) {
        // Do click handling here
        Log.i("in request", "in save details");
        button = (Button) findViewById(R.id.saveresponse);
        textInputHouseAddress = (EditText) findViewById(R.id.HouseAddress);
        houseAddress = textInputHouseAddress.getText().toString();
        textCar = (EditText) findViewById(R.id.Car);
        carDetails = textCar.getText().toString();
        officeSpinner = (Spinner) findViewById(R.id.OfficeDetails);
        officeAddress = officeSpinner.getSelectedItem().toString();
        insertUserDetails();
        closeActivity(button);
    }
    public void insertUserDetails() {
        postRequest = new HttpPostRequest();
        try {
            user = new JSONObject();
            user.put("key", tokenID);
            JSONObject value= new JSONObject();
            value.put("name" ,"balu");
            value.put("HomeAddr" ,houseAddress);
            value.put("OfficeAddr" ,officeAddress);
            value.put("Vehicle" ,carDetails);
            user.put("value",value);
            postRequest.insertIntoDB(user,"transactionPut");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("in request", "exception");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("in request", "exception");
        }
    }
    public void closeActivity (View view) {
        setResult(RESULT_OK);
        finish();
    }
}