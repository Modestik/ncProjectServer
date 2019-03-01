package nc.test.security;

import nc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Base64;

public class SecurityCheck {

    public static boolean checkBasicAuth(String basic, String password){
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        String notEncoded = user + ":" + password;
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes(Charset.forName("US-ASCII")));

        return basic.equals(encodedAuth);
    }
}
