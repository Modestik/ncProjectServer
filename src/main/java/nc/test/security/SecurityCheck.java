package nc.test.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityCheck {
    /**
     * Переменная, хранящая ключ BasicAuth, текущего залогинивошегося пользователя
     */
    private static String basicAuth = "";

    /**
     * Метод, проверяющий ключ BasicAuth
     *
     * @param basic
     * @return true, если ключ тот же или новый
     * false, если ключ отличается
     */
    public static boolean checkBasicAuth(String basic) {
        if (basicAuth.equals("")) {
            basicAuth = basic;
            return true;
        }
        if (!basicAuth.equals(basic) || basic.equals("")) {
            basicAuth = "";
            SecurityContextHolder.clearContext();
            return false;
        } else
            return true;
    }
}
