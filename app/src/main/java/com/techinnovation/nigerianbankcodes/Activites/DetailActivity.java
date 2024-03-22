package com.techinnovation.nigerianbankcodes.Activites;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.control.ads.AperoAd;
import com.techinnovation.nigerianbankcodes.Adapter.CodeAdapter;
import com.techinnovation.nigerianbankcodes.Adapter.DetailAdapter;
import com.techinnovation.nigerianbankcodes.Models.CodeModel;
import com.techinnovation.nigerianbankcodes.Models.DetailModel;
import com.techinnovation.nigerianbankcodes.R;
import com.techinnovation.nigerianbankcodes.Repositry.Repositry;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;


    ArrayList<DetailModel> datalist;
    ArrayList<CodeModel>list;
    DetailAdapter detailAdapter;
    CodeAdapter adapter;


    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerView = findViewById(R.id.recyclerViewDetail);
        Tools.hideBottomNavigation(this);
      /*  AperoAd.getInstance().loadNativeAd(this, getResources().getString(R.string.native_ad), com.ads.control.R.layout.custom_native_admod_medium);
        pos = getIntent().getIntExtra("key", 0);*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        switch (pos){
            case 0:

            datalist = Repositry.getbankdata();
                detailAdapter = new DetailAdapter(this, datalist);
                recyclerView.setAdapter(detailAdapter);

            break;

            case 1:
                //Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
                list=Repositry.swiftcode();
                adapter = new CodeAdapter(this,list);
                recyclerView.setAdapter(adapter);
                break;
            case 2:
                datalist = Repositry.getnetwork();
                detailAdapter = new DetailAdapter(this, datalist);
                recyclerView.setAdapter(detailAdapter);
                break;
            case 3:
                list=Repositry.genral();
                adapter = new CodeAdapter(this,list);
                recyclerView.setAdapter(adapter);
                break;

        }


    }

}
