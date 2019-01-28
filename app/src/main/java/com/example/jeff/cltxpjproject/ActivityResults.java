package com.example.jeff.cltxpjproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

public class ActivityResults extends AppCompatActivity {
    private TextView txtSalarioBrutoCLT;
    private TextView txtINSSCLT;
    private TextView txtIRRFCLT;
    private TextView txtSalarioLiquidoCLT;
    private TextView txtTotalAnualCLT;
    private TextView txtSalarioBrutoMei;
    private TextView txtINSSMei;
    private TextView txtIRRFMei;
    private TextView txtSalarioLiquidoMei;
    private TextView txtTotalAnualMei;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        MobileAds.initialize(this, "ca-app-pub-4729635888446528~4416516683");
        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        String SalarioBrutoCLT = bundle.getString("txtCLT");
        String SalarioBrutoMEI = bundle.getString("txtMEI");

        //Salários brutos ------------------------------------------------------
        txtSalarioBrutoCLT = findViewById(R.id.textView_salarioBruto_results_CLT);
        txtSalarioBrutoMei = findViewById(R.id.textView_salarioBruto_results_MEI);

        txtSalarioBrutoCLT.setText(SalarioBrutoCLT);
        txtSalarioBrutoMei.setText(SalarioBrutoMEI);

        txtINSSCLT = findViewById(R.id.textView_INSS_results_CLT);
        txtINSSMei = findViewById(R.id.textView_INSS_results_MEI);
        float valorINSS = 0;

        //Calculo INSS CLT ------------------------------------------------------
        if(Integer.parseInt(txtSalarioBrutoCLT.getText().toString()) <= 1751){
            valorINSS = (int) (Integer.parseInt(txtSalarioBrutoCLT.getText().toString())* 0.08);
            String txtRes = String.valueOf(valorINSS);
            txtINSSCLT.setText(txtRes);
        }else {
            if (Integer.parseInt(txtSalarioBrutoCLT.getText().toString()) <= 2919) {
                valorINSS = (int) (Integer.parseInt(txtSalarioBrutoCLT.getText().toString())* 0.09);
                String txtRes = String.valueOf(valorINSS);
                txtINSSCLT.setText(txtRes);
            }else{
                if(Integer.parseInt(txtSalarioBrutoCLT.getText().toString()) > 2919){
                    valorINSS = (int) (Integer.parseInt(txtSalarioBrutoCLT.getText().toString())* 0.11);
                    String txtRes = String.valueOf(valorINSS);
                    txtINSSCLT.setText(txtRes);
                }
            }
        }

        //Calculo DAS MEI ------------------------------------------------------
        float valorDas = (float) 56.90;
        String txtRes = String.valueOf(valorDas);
        txtINSSMei.setText(txtRes);

        //Calculo salários Líquidos ------------------------------------------------------
        txtSalarioLiquidoCLT = findViewById(R.id.textView_salarioLiquido_CLT);
        txtSalarioLiquidoMei = findViewById(R.id.textView_salarioLiquidoMEI);

        float salarioLiquidoCLT = (float) (Integer.parseInt(txtSalarioBrutoCLT.getText().toString()) - valorINSS);
        String txtLiquidoCLT = String.valueOf(salarioLiquidoCLT);
        txtSalarioLiquidoCLT.setText(txtLiquidoCLT);

        float salarioLiquidoMEI = (float) (Integer.parseInt(txtSalarioBrutoMei.getText().toString()) - valorDas);
        String txtLiquidoMEI = String.valueOf(salarioLiquidoMEI);
        txtSalarioLiquidoMei.setText(txtLiquidoMEI);

        //Calculo Total Anual ------------------------------------------------------
        txtTotalAnualMei = findViewById(R.id.textView_TotalAnualMei);
        float totalAnualMEI = (salarioLiquidoMEI * 12);
        txtTotalAnualMei.setText(String.valueOf(totalAnualMEI + "\n-Não possui 13º e 1/3 de férias" +
                                                                "\n-Não descontado o IR"));

        txtTotalAnualCLT = findViewById(R.id.textView_totalAnualCLT);
        float totalAnualCLT = (salarioLiquidoCLT * 13) + (salarioLiquidoCLT/3);
        txtTotalAnualCLT.setText(String.valueOf(totalAnualCLT + "\n-Acrescido de 13º e 1/3 de férias" +
                                                                "\n-Não descontado o IR"));

        //Cálculo IRRF ------------------------------------------------------
        txtIRRFCLT = findViewById(R.id.textView_IRRF_results_CLT);
        float valorIRCLT = 0;
        if(Integer.parseInt(txtSalarioBrutoCLT.getText().toString()) > 1903 || totalAnualCLT >28559.70){
            if(totalAnualCLT >= 22847.77 && totalAnualCLT <= 33919.80){
                valorIRCLT = (float) (totalAnualCLT * 0.075);
            }else{
                if(totalAnualCLT >= 33919.81 && totalAnualCLT <= 45012.60){
                    valorIRCLT = (float) (totalAnualCLT * 0.15);
                }else{
                    if(totalAnualCLT >= 45012.61 && totalAnualCLT <= 55976.16){
                        valorIRCLT = (float) (totalAnualCLT * 0.225);
                    }else{
                        if(totalAnualCLT > 55976.16){
                            valorIRCLT = (float) (totalAnualCLT * 0.275);
                        }
                    }
                }
            }
            txtIRRFCLT.setText(String.valueOf(valorIRCLT + "\nValor aproximado, pode variar" +
                                                            "\ntendo uma % de insenção"));
        }else{
            valorIRCLT = 0;
            txtIRRFCLT.setText(String.valueOf(valorIRCLT));
        }


        txtIRRFMei = findViewById(R.id.textView_IRRF_results_MEI);
        float valorIRMEI = 0;
        if(totalAnualMEI > 28559.70){
            valorIRMEI = (float) (totalAnualMEI * 0.15);
            txtIRRFMei.setText(String.valueOf(valorIRMEI + "\nValor aproximado, pode variar" +
                                                            "\ntendo uma % de insenção"));
        }else{
            valorIRMEI = 0;
            txtIRRFMei.setText(String.valueOf(valorIRMEI));
        }
    }
}
