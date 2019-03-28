package nc.test.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

//todo maxim
//basicAuth.equals("") лучше использовать такое org.springframework.util.StringUtils.isEmpty
//idea подсвечивает basic.equals("") обрати внимание
//если не ошибаюсь получается так что мы можем хранить только basic auth для одного пользователя..
//мы это конечно переделаем.. но если такая логика то можно делать мапу - login and basic auth

public class SecurityCheck {
    /**
     * Переменная, хранящая ключ BasicAuth, текущего залогинивошегося пользователя
     */
/*    private static String basicAuth = "";

    *//**
     * Метод, проверяющий ключ BasicAuth
     *
     * @param basic
     * @return true, если ключ тот же или новый
     * false, если ключ отличается
     *//*
    public static boolean checkBasicAuth(String basic) {
        //StringUtils.isEmpty()
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
    }*/
}
