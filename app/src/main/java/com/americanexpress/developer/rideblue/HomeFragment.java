package com.americanexpress.developer.rideblue;

import android.annotation.SuppressLint;
import android.location.Criteria;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.location.Location;

import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A fragment that launches other parts of the demo application.
 */
public class HomeFragment extends Fragment
        implements OnMapReadyCallback {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1;
    MapView mMapView;
    RadioGroup rideCategory;
    private GoogleMap googleMap;
    private GoogleApiClient googleApiClient;
    double lat;
    double lng;

//    private Location mLastLocation = LocationServices.FusedLocationApi
//            .getLastLocation(googleApiClient);

    // boolean flag to toggle periodic location updates

    private LocationRequest mLocationRequest;
    GoogleSignInOptions gso;
    GoogleSignInAccount account;
    private HttpPostRequest httpPostRequest;

    // Location updates intervals in sec
    private static int UPDATE_INTERVAL = 10000; // 10 sec
    private static int FATEST_INTERVAL = 5000; // 5 sec
    private static int DISPLACEMENT = 10; // 10 meters
    int tokenID;
    int key;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout




        View v = inflater.inflate(R.layout.fragment_home, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        rideCategory = v.findViewById(R.id.ridetoggle);
        rideCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.request:
                        // switch to request method
                        Log.i("botton","request");
                        doAllForRequest();
                        break;
                    case R.id.offer:
                        // switch to offer method
                        Log.i("botton","offer");
                        doAllForOffer();
                        break;
                    case R.id.rideoff:
                        // switch to offer method
                        Log.i("botton","cancel");
                        doAllForRideCancel();
                        break;
                }
            }
        });

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // TO-DO
        // latitude and longitude
   //     double lat = 33.44277;
   //     double lon = -112.0728;

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMyLocationEnabled(true);

//        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//        double lat  = mLastLocation.getLatitude();
//        double lon = mLastLocation.getLongitude();


        Criteria criteria = new Criteria();
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        lat = location.getLatitude();
        lng = location.getLongitude();

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(lat, lng)).title("Hello Maps");

        // Changing marker icon
        //marker.icon(BitmapDescriptorFactory
        //        .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(lat, lng)).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    public void doAllForOffer() {


        //TO-DO



        double startlat = 33.4484;
        double startLong = -112.0740;

        double destLat = 33.654270;
        double destLong = -111.959220;

        String resource = "transactionPut";
        tokenID = getArguments().getInt("tokenID");

        try {
            // Create the JSON message for Registering Offer to DB
            JSONObject mainObject = new JSONObject();
            mainObject.put("key","activeUsers");


            JSONObject valueObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            JSONObject userListObject = new JSONObject();
            userListObject.put("userKey", tokenID);
            userListObject.put("startLat", lat);
            userListObject.put("startLong", lng);
            userListObject.put("destLat", destLat);
            userListObject.put("destLong", destLong);
            userListObject.put("rideType", "Offer");

            jsonArray.put(userListObject);

            valueObject.put("userList",jsonArray);
            mainObject.put("value", valueObject);
            httpPostRequest = new HttpPostRequest();
            httpPostRequest.insertIntoDB(mainObject, resource);
            periodicMatchApiCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void doAllForRequest() {
        //TO-DO

        double startlat = 33.4484;
        double startLong = -112.0740;

        double destLat = 33.654270;
        double destLong = -111.959220;

        String resource = "transactionPut";
        tokenID = getArguments().getInt("tokenID");

        try {
            // Create the JSON message for Registering Offer to DB
            JSONObject mainObject = new JSONObject();
            mainObject.put("key","activeUsers");


            JSONObject valueObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            JSONObject userListObject = new JSONObject();
            userListObject.put("userKey", tokenID);
            userListObject.put("startLat", lat);
            userListObject.put("startLong", lng);
            userListObject.put("destLat", destLat);
            userListObject.put("destLong", destLong);
            userListObject.put("rideType", "Request");

            jsonArray.put(userListObject);

            valueObject.put("userList",jsonArray);
            mainObject.put("value", valueObject);
            httpPostRequest = new HttpPostRequest();
            httpPostRequest.insertIntoDB(mainObject, resource);
            periodicMatchApiCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void doAllForRideCancel() {
        //TO-DO
    }

    public void periodicMatchApiCall() {
        String resource = "transactionGet";
        String data = String.valueOf(tokenID).concat("matched");

        int counter =0;
        boolean gotResponse = false;
        while(!gotResponse) {

            httpPostRequest = new HttpPostRequest();

            try {
                String response = httpPostRequest.getDataFromDB(data, resource);
                if (response.contains("\"value\":\"\"")) {
                    Log.d("rideshare", "match-api -> value is null");
                    Thread.sleep(10000);

                } else {
                    gotResponse= true;
                    //we got a match
                    // handle match
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            counter++;
            if(counter>50)
                break;

        }

    }
}