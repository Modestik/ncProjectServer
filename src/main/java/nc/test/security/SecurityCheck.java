package nc.test.security;

import lombok.Setter;
import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Base64;

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
        if (basic.equals("")) {
            basicAuth = "";
            return false;
        }
        if (basicAuth.equals("")) {
            basicAuth = basic;
            return true;
        }
        if (!basicAuth.equals(basic) || basic.equals("")) {
            basicAuth = "";
            return false;
        } else
            return true;
    }
}
