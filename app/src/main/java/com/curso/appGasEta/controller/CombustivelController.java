package com.curso.appGasEta.controller;

import android.content.SharedPreferences;

import com.curso.appGasEta.model.Combustivel;
import com.curso.appGasEta.view.MainActivity;

public class CombustivelController {

    SharedPreferences preferences;

    SharedPreferences.Editor dadosPreferences;

    public static final String NOME_PREFERENCES = "pref_gaseta";

    public CombustivelController(MainActivity activity){
        preferences = activity.getSharedPreferences(NOME_PREFERENCES, 0);

        dadosPreferences = preferences.edit();
    }

    public void salvar(Combustivel combustivel){
        dadosPreferences.putString("combustivel", combustivel.getNomeCombustivel());
        dadosPreferences.putFloat("precoDoCombustivel",(float) combustivel.getPrecoCombustivel());
        dadosPreferences.putString("recomendacao", combustivel.getRecomendacao());
        dadosPreferences.apply();
    }

    public void limpar(){
        dadosPreferences.clear();
        dadosPreferences.apply();
    }
}
