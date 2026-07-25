package com.luishenrique.cap.Historico_Vacinacao.utils;

public class CpfUtils {

    // Aceita CPF com máscara (000.000.000-00) ou só números (00000000000)
    private static final String REGEX = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";

    // Remove qualquer caractere que não seja dígito (pontos, traços, espaços)
    private static final String REGEX_REMOVE_NAO_NUMEROS = "[^0-9]";

    // CPFs com todos os dígitos iguais (ex: 111.111.111-11) são matematicamente
    // válidos, mas não são CPFs reais — precisam ser rejeitados manualmente
    private static final String REGEX_TODOS_IGUAIS = "(\\d)\\1{10}";

    // Valida o formato antes da matemática para evitar processamento desnecessário
    public static boolean isValid(String cpf){
        return validString(cpf) && validMath(cpf);
    }

    // Verifica se o CPF tem o formato correto (com ou sem máscara)
    private static boolean validString(String cpf){
        return cpf.matches(REGEX);
    }

    private static boolean validMath(String cpf){

        // Rejeita CPFs com todos os dígitos iguais
        if (cpf.matches(REGEX_TODOS_IGUAIS)) return false;

        // Remove máscara para trabalhar só com os 11 dígitos
        cpf = cpf.replaceAll(REGEX_REMOVE_NAO_NUMEROS,"");

        // Extrai os dois dígitos verificadores (posições 9 e 10)
        int penultimo = Integer.parseInt((cpf.charAt(9) + ""));
        int ultimo = Integer.parseInt((cpf.charAt(10) + ""));

        // Calcula os dígitos verificadores esperados
        // O módulo 11 do somatório define o dígito: se < 2 → 0, senão → 11 - resultado
        int validPenult = sum(cpf.substring(0,9))%11;
        int validUlt = sum(cpf.substring(0,10))%11;

        // Valida o penúltimo dígito
        if (validPenult < 2){
            if (penultimo != 0) return false;
        } else {
            if (penultimo != 11-validPenult) return false;
        }
        // Valida o último dígito
        if (validUlt < 2){
            if (ultimo != 0) return false;
        } else {
            if (ultimo != 11-validUlt) return false;
        }

        return true;

    }

    /**
     * Soma recursiva ponderada dos dígitos do CPF.
     * Cada dígito é multiplicado pelo seu peso, que corresponde
     * ao tamanho atual da string + 1 (ex: para 9 dígitos, o primeiro
     * é multiplicado por 10, o segundo por 9, e assim por diante).
     *
     * Exemplo para "123456789":
     * 1x10 + 2x9 + 3x8 + 4x7 + 5x6 + 6x5 + 7x4 + 8x3 + 9x2 = resultado
     */
    private static int sum(String cpf){

        if (cpf.length() == 0) return 0;

        int mulplicacao = Integer.parseInt((cpf.charAt(0) +"")) * (cpf.length()+1);

        System.out.println(mulplicacao);

        return mulplicacao + sum(cpf.substring(1));

    }
}
