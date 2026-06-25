package utils;

import java.util.regex.Pattern;

public class CpfUtils {
    private static final String CPF_REGEX = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$";

    public static boolean ehFormatoValido(String cpf) {
        if (cpf == null) return false;
        return Pattern.matches(CPF_REGEX, cpf.trim());
    }
}
