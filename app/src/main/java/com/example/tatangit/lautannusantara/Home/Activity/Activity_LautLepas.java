package com.example.tatangit.lautannusantara.Home.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatangit.lautannusantara.Home.Adapter.Adapter_InfoWindows;
import com.example.tatangit.lautannusantara.Home.Model.InfoWindowData;
import com.example.tatangit.lautannusantara.Library.GoogleMaps.CustomInfoWindowGoogleMap;
import com.example.tatangit.lautannusantara.Library.Retrofit.Interface.Interface_Api;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.MessageItemLogin;
import com.example.tatangit.lautannusantara.Library.Retrofit.Model.ModelManager;
import com.example.tatangit.lautannusantara.Library.Retrofit.Utils.Utils;
import com.example.tatangit.lautannusantara.R;
import com.example.tatangit.lautannusantara.SignUp.Activity.Activity_Login;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_LautLepas extends AppCompatActivity implements OnMapReadyCallback {


    Intent mIntent;
    SweetAlertDialog pDialog;
    Interface_Api interface_api;
    Toolbar toolbar;
    TextView mTitle;
    CircleImageView toolbar_iconView, id_icon_toolbar_start;
    MessageItemLogin messageItemLogin;

    private ArrayList<LatLng> belitung = new ArrayList<>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lautlepas);
        toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        belitung.add(new LatLng(-2.440282, 108.046600));
        belitung.add(new LatLng(-2.317332, 108.115743));
        belitung.add(new LatLng(-2.527814, 108.242033));
        belitung.add(new LatLng(-2.458998, 108.363364));
        belitung.add(new LatLng(-2.609685, 108.494562));
        belitung.add(new LatLng(-2.683419, 108.358455));
        belitung.add(new LatLng(-2.843943, 108.422440));
        belitung.add(new LatLng(-2.837379, 108.555317));
        belitung.add(new LatLng(-3.056973, 108.553792));
        belitung.add(new LatLng(-3.091382, 108.376571));

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = toolbar.findViewById(R.id.id_title_toolbar);
        mTitle.setText("Laut Lepas");

        interface_api = Utils.getSOService("p");
        messageItemLogin = ModelManager.getInstance(getApplicationContext()).getUser();

        id_icon_toolbar_start = toolbar.findViewById(R.id.id_icon_toolbar_start);
        id_icon_toolbar_start.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_back));
        id_icon_toolbar_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        toolbar_iconView = toolbar.findViewById(R.id.id_icon_toolbar);
        toolbar_iconView.setImageDrawable(getApplication().getResources().getDrawable(R.drawable.ic_reload));
        toolbar_iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Mohon Maaf Sedang Proses Deployment", Toast.LENGTH_SHORT).show();
            }
        });





        /*
            Loading
         */
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Mohon Menunggu");
        pDialog.setCancelable(false);



        /*
            Session
        */

        try {
            if (!ModelManager.getInstance(getApplicationContext()).isLoggedIn()) {
                startActivity(new Intent(getApplicationContext(), Activity_Login.class));
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap map) {

        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMinZoomPreference(8);


        for (int i=0; i<belitung.size();i++){

            map.addMarker(new MarkerOptions()
                    .position(belitung.get(i))
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .title("Belitung Timur").snippet("Indonesian, Laut Lepas"));
        }

        map.setInfoWindowAdapter(new Adapter_InfoWindows(getApplicationContext()));

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(belitung.get(7), 9));






    }

    @OnClick(R.id.id_goLautanLepas)
    public void goLautLepas(){
        mIntent = new Intent(getApplicationContext(), Activity_Detail.class);
        mIntent.putExtra("title", "activity_lautlepas");
        startActivity(mIntent);
        overridePendingTransition(R.anim.slide_up, R.anim.slide_dwon);
    }

    }
