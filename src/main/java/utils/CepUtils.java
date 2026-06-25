package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CepUtils {
    // Regex para CEP: 5 números, hífen opcional, 3 números
    private static final String CEP_REGEX = "^\\d{5}-?\\d{3}$";
    private static final Pattern PATTERN = Pattern.compile(CEP_REGEX);

    public static boolean ehCepValido(String cep) {
        if (cep == null) {
            return false;
        }
        Matcher matcher = PATTERN.matcher(cep);
        return matcher.matches();
    }
}
