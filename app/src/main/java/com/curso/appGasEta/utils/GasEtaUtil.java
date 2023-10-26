package com.curso.appGasEta.utils;

public class GasEtaUtil {

    public static String calculaMelhorOpcao(double gasolina, double etanol){
        double opcaoIdeal = gasolina * 0.7;
        String mensagem;

        if(etanol<=opcaoIdeal){
            mensagem = "Abasteça com Etanol";
        } else {
            mensagem = " Abasteça com Gasolina";
        }

        return mensagem;
    }
}
