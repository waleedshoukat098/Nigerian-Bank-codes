package com.techinnovation.nigerianbankcodes.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techinnovation.nigerianbankcodes.R;

public class MainActivity extends AppCompatActivity {
    CardView cardView, cardView1, cardView2, cardView3,cardView4,cardView5;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardView = findViewById(R.id.card1);
        cardView1 = findViewById(R.id.card2);
        cardView2 = findViewById(R.id.card3);
        cardView3 = findViewById(R.id.card4);
        cardView4 = findViewById(R.id.card6);
        cardView5 = findViewById(R.id.card5);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailActivity(0);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailActivity(2);
            }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailActivity(1);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailActivity(3);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Webviewprivacy.class);
                startActivity(intent);
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.moreApps(MainActivity.this);
            }
        });




    }



    private void openDetailActivity(int position) {
        Intent intent=new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("key",position);
        startActivity(intent);

    }
}
