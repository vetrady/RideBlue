package com.americanexpress.developer.rideblue;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;

public class AccountFragment extends Fragment {

    private TextView textViewName;
    private TextView textUserGmail;

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

        return view;

    }

}
