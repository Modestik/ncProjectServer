package nc.test.service.impl;

import lombok.extern.log4j.Log4j;
import nc.test.service.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String getRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toArray()[0].toString();
        int delim = role.indexOf("_");

        log.info(new StringBuilder()
                .append("Получена роль ")
                .append(role)
                .append(" для пользователя ")
                .append(auth.getName())
        );

        return role.substring(delim + 1);
    }
}
