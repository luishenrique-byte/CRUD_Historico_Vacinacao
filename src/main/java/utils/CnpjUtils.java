package utils;

import java.util.regex.Pattern;

public class CnpjUtils {

    // Aceita: 00.000.000/0000-00  OU  14 dígitos seguidos
    private static final String CNPJ_REGEX = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$|^\\d{14}$";

    public static boolean ehFormatoValido(String cnpj) {
        if (cnpj == null) return false;
        return Pattern.matches(CNPJ_REGEX, cnpj.trim());
    }
}
