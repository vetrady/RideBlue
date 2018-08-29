package com.americanexpress.developer.rideblue;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.americanexpress.developer.rideblue.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Nullable;

public class AccountFragment extends Fragment {


    private TextView textViewName;
    private TextView textUserGmail;
    private TextView textCar;
    private EditText editTextCar;
    private TextView officeDetails;
    private RadioGroup officeSelection;
    private RadioButton loc1;
    private RadioButton loc2;
    private RadioButton loc3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        textViewName = (TextView) view.findViewById(R.id.UserName);
        String getArgument = getArguments().getString("username");
        textViewName.setText(getArgument);

        textUserGmail = (TextView) view.findViewById(R.id.UserGmail);
        String getArgument1 = getArguments().getString("usergmail");
        textUserGmail.setText(getArgument1);



        try {

            ImageView i = (ImageView) view.findViewById(R.id.profile_image);
            String getImageUrl = getArguments().getString("imageurl");
            if(! Patterns.WEB_URL.matcher(getImageUrl).matches()){
                Log.i("in account if ", getImageUrl);
                //       i.setImageResource(R.drawable.ic_photo_library_black_24dp);
            }else {
                Log.i("in account else ", getImageUrl);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(getImageUrl).getContent());
                i.setImageBitmap(bitmap);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // method for creating car details view

   /*     methodForCreatingCarText();
        methodForofficeAddressText();*/

        return view;


    }

   /* @SuppressLint("ResourceType")
    public void methodForCreatingCarText(){

        String token = getArguments().getString("tokenID");

        // method to get car details from table using token, will chnage if car details are already there just
        // will send textView to display car details else send editText to get car details

        if(! token.isEmpty()){
            //set layout params of edittext
            editTextCar = new EditText();
            TableRow.LayoutParams etParams=
                    new TableRow.LayoutParams
                            (120,30);
            etParams.setMargins(10, 0, 0, 0);
      //      editTextCar.setId(new Integer(1));
            editTextCar.setGravity(Gravity.CENTER);
            editTextCar.setMinLines(2);
            editTextCar.setLayoutParams(etParams);
        }else {
            TableRow.LayoutParams etParams=
                    new TableRow.LayoutParams
                            (120,30);
            etParams.setMargins(10, 0, 0, 0);
            textCar = new TextView();
            textCar.setText("Honda Car");
            textCar.setGravity(Gravity.CENTER);
            textCar.setTextColor(Color.parseColor("#0070C0"));
            textCar.setTextSize(26);
            textCar.setLayoutParams(etParams);
        }

    }



    public void methodForofficeAddressText(){

        String token = getArguments().getString("tokenID");

        // method to get address details from table using token, will chnage if car details are already there just
        // will send textView to display address details else send editText to get adress details


   //     officeSelection.setId(new Integer(2));
        officeDetails = new RadioGroup();
        loc1 = new RadioButton();
        loc2 = new RadioGroup();
        officeDetails.setWidth(10);
        officeDetails.setHeight(10);
        loc1.setId(Integer.parseInt("@+id/phoneixAEDR"));
        loc1.setText("Phoneix AEDR");

        loc2.setId(Integer.parseInt("@+id/phoneixTRCW"));
        loc2.setText("Phoneix TRCW");


    }*/

}
