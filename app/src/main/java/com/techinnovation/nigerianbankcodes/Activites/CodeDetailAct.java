package com.techinnovation.nigerianbankcodes.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.techinnovation.nigerianbankcodes.Adapter.CodeAdapter;
import com.techinnovation.nigerianbankcodes.Model.CodeModel;
import com.techinnovation.nigerianbankcodes.R;
import com.techinnovation.nigerianbankcodes.Repositry.Repositry;

import java.util.ArrayList;

public class CodeDetailAct extends AppCompatActivity {
    RecyclerView recyclerView;
    CodeAdapter adapter;
    ArrayList<CodeModel> list;
String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_detail);
        recyclerView=findViewById(R.id.recycearviewmain);


        title=getIntent().getStringExtra("key");
        switch (title){
            case "SwiftCode":
                list= Repositry.swiftcode();
                break;
            case "UBA":
                list= Repositry.Uba();
                break;
            case "Union":
                list = Repositry.Union();
                break;
            case  "Heritage":
                list = Repositry.Heritage();
                break;
            case "Keystone":
                list = Repositry.KeyStone();
                break;
            case "Access":
                list = Repositry.Access();
                break;
            case "Stanbic":
                list = Repositry.Stanbic();
                break;
            case "Zeenith":
                list  =Repositry.Zeenith();
                break;
            case "Skye":
                list = Repositry.Skye();
                break;
            case "Sterling":
                list  = Repositry.Sterling();
                break;



            case "Airtel":
                list = Repositry.Airtel();
                case "9Mobile":
                list = Repositry.NMObile();
                break;
            case "MTN":
                list = Repositry.MTN();
                break;
            case "GLO":
                list  = Repositry.GLO();
                break;
            case "Generals":
                list = Repositry.Generals();
                break;

        }
        adapter=new CodeAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }
}