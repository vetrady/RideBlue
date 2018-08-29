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

import java.io.IOException;

public class RequestUserDetails extends AppCompatActivity

{
    private EditText textInputHouseAddress;
    private NestedScrollView nestedScrollView;
    private Spinner officeSpinner;
//    private HttpPostRequest postRequest;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("in request", "in create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        initViews();

    }




    public void initViews() {


        textInputHouseAddress = (EditText) findViewById(R.id.HouseAddress);
        String houseAddress = textInputHouseAddress.getText().toString();

        officeSpinner = (Spinner) findViewById(R.id.OfficeDetails);
        String officeAddress = officeSpinner.getSelectedItem().toString();

        //to do call the method to insert details

        insertUserDetails();




    }

    public void saveDetails(View button) {
        // Do click handling here
        Log.i("in request", "in save details");
        button = (Button) findViewById(R.id.saveresponse);
        closeActivity(button);


    }

    public void insertUserDetails() {

        // insert details into DB
        //TO-DO

    }
    public void closeActivity (View view) {
        setResult(RESULT_OK);
        finish();
    }


}