package com.curso.appGasEta.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.curso.appGasEta.R;
import com.curso.appGasEta.controller.CombustivelController;
import com.curso.appGasEta.model.Combustivel;
import com.curso.appGasEta.utils.GasEtaUtil;

public class MainActivity extends AppCompatActivity {

    CombustivelController controller;

    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;

    EditText litroGasolina;
    EditText litroEtanol;

    TextView txtResultado;

    Button btnCalcular;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    double precoGasolina;
    double precoEtanol;
    String recomendacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new CombustivelController(MainActivity.this);

        litroGasolina = findViewById(R.id.edit_litro_gasolina);
        litroEtanol = findViewById(R.id.edit_litro_etanol);

        txtResultado = findViewById(R.id.txt_resultado);

        btnCalcular = findViewById(R.id.btn_calcular);
        btnLimpar = findViewById(R.id.btn_limpar);
        btnSalvar = findViewById(R.id.btn_salvar);
        btnFinalizar = findViewById(R.id.btn_finalizar);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isDadosOk = true;

                if(TextUtils.isEmpty(litroGasolina.getText())){
                    litroGasolina.setError("* Obrigatório!");
                    litroGasolina.requestFocus();
                    isDadosOk = false;
                }

                if(TextUtils.isEmpty(litroEtanol.getText())){
                    litroEtanol.setError("* Obrigatório!");
                    litroEtanol.requestFocus();
                    isDadosOk = false;
                }

                if(isDadosOk){

                    precoGasolina = Double.parseDouble(litroGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(litroEtanol.getText().toString());

                    recomendacao = GasEtaUtil.calculaMelhorOpcao(precoGasolina, precoEtanol);

                    txtResultado.setText(recomendacao);

                    btnSalvar.setEnabled(true);

                }else{
                    Toast.makeText(MainActivity.this, "Por favor, digite os dados obrigatórios...", Toast.LENGTH_SHORT).show();
                    btnSalvar.setEnabled(false);
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                litroGasolina.setText("");
                litroEtanol.setText("");

                btnSalvar.setEnabled(false);

                controller.limpar();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelEtanol.setNomeCombustivel("Etanol");
                combustivelEtanol.setPrecoCombustivel(precoEtanol);

                combustivelGasolina.setNomeCombustivel("Gasolina");
                combustivelGasolina.setPrecoCombustivel(precoGasolina);

                combustivelGasolina.setRecomendacao(GasEtaUtil.calculaMelhorOpcao(precoGasolina, precoEtanol));
                combustivelEtanol.setRecomendacao(GasEtaUtil.calculaMelhorOpcao(precoGasolina, precoEtanol));

                controller.salvar(combustivelEtanol);
                controller.salvar(combustivelGasolina);
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Volte sempre!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}