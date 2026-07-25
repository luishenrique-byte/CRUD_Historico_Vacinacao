package com.luishenrique.cap.Historico_Vacinacao.utils;

public class CnpjUtils {

    // Aceita CPNJ nos formatos:
    // Formatado padrão: (12.345.678/0001-90),
    // Sem os pontos: (12345678/0001-90),
    // Sem barra e traço: (12.345.678000190),
    // Só números: (12345678000190)
    private static final String REGEX = "^\\d{2}\\.?\\d{3}\\.?\\d{3}\\/?\\d{4}\\-?\\d{2}$";

    // Remove qualquer caractere que não seja dígito (pontos, traços, espaços)
    private static final String REGEX_REMOVE_NAO_NUMEROS = "[^0-9]";

    // CNPJs com todos os dígitos iguais (ex: 111.111.111-11) são matematicamente
    // válidos, mas não são CNPJs reais — precisam ser rejeitados manualmente
    private static final String REGEX_TODOS_IGUAIS = "(\\d)\\1{13}";

    //Pesos pré-determinados utilizados na soma ponderada da verificação matemática
    private static final int[] pesosArray = {6,5,4,3,2,9,8,7,6,5,4,3,2};

    // Valida o formato antes da matemática para evitar processamento desnecessário
    public static boolean isValid(String cnpj){
        return validString(cnpj) && validMath(cnpj);
    }

    // Verifica se o CNPJ tem o formato correto (com ou sem máscara)
    private static boolean validString(String cnpj){
        return cnpj.matches(REGEX);
    }

    private static boolean validMath(String cnpj){

        // Remove máscara para trabalhar só com os 14 dígitos
        cnpj = cnpj.replaceAll(REGEX_REMOVE_NAO_NUMEROS,"");

        // Rejeita CPNJs com todos os dígitos iguais
        if (cnpj.matches(REGEX_TODOS_IGUAIS)) return false;

        // Extrai os dois dígitos verificadores (posições 9 e 10)
        int penultimo = Integer.parseInt((cnpj.charAt(12) + ""));
        int ultimo = Integer.parseInt((cnpj.charAt(13) + ""));

        // Calcula os dígitos verificadores esperados
        // O módulo 11 do somatório define o dígito: se < 2 → 0, senão → 11 - resultado
        int validPenult = sum(cnpj.substring(0,12))%11;
        int validUlt = sum(cnpj.substring(0,13))%11;

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
     *
     * @param cnpj
     *
     * Soma recursiva ponderada dos dígitos do CNPJ.
     * Cada dígito é multiplicado pelo seu peso, que corresponde
     * a uma posição pré-determinada do array dita pelo
     * [TAMANHO_do_ARRAY - TAMANHO_da_Sring]
     * ex.: para 12 dígitos, o primeiro digito é multiplicado pelo peso 5 do array (index = 1),
     * o segundo por 4 (index = 2), e assim por diante.
     * Para 13 dígitos, o primeiro digito é multiplicado pelo peso 6 do array (index = 0),
     * o segundo por 5 (index = 1), e assim por diante.
     *
     *
     * Exemplo para "123456780001"
     * 1x5 + 2x4 + 3x3 + 4x2 + 5x9 + 6x8 + 7x7 + 8x6 + 0x5 + 0x4 + 0x3 + 1x2 = resultado
     *
     * @return Soma Ponderada do digitos de um CNPJ
     */
    private static int sum(String cnpj){

        if (cnpj.isEmpty()) return 0;

        int mulplicacao = Integer.parseInt((cnpj.charAt(0) +"")) * pesosArray[pesosArray.length-cnpj.length()];

        System.out.println(mulplicacao);

        return mulplicacao + sum(cnpj.substring(1));
    }
}