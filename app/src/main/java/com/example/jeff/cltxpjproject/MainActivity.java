package com.example.jeff.cltxpjproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private Button botaoComparar;
    private EditText salarioBrutoMEI;
    private EditText salarioBrutoCLT;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-4729635888446528~4416516683");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        botaoComparar = findViewById(R.id.button_comparar);
        botaoComparar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ActivityResults.class);

                salarioBrutoCLT = findViewById(R.id.editText_salarioBrutoCLT);
                salarioBrutoMEI = findViewById(R.id.editText_SalarioBrutoMEI);

                String txtSalarioBrutoMEI = "";
                String txtSalarioBrutoCLT = "";

                txtSalarioBrutoCLT = salarioBrutoCLT.getText().toString();
                txtSalarioBrutoMEI = salarioBrutoMEI.getText().toString();

                Bundle bundle = new Bundle();

                bundle.putString("txtCLT", txtSalarioBrutoCLT);
                bundle.putString("txtMEI", txtSalarioBrutoMEI);
                it.putExtras(bundle);

                startActivity(it);
            }
        });
    }
}
