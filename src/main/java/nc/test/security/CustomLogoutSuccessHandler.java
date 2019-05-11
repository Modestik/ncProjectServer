package nc.test.security;

import nc.test.model.Sessions;
import nc.test.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

public class CustomLogoutSuccessHandler extends
        SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private SessionService sessionService;

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {
        try {
            String header = request.getHeader("Authorization");
            if (header != null && header.startsWith(Sessions.SESSION)) {
                byte[] base64Token = header.substring(Sessions.SESSION.length()).getBytes(Charset.forName("US-ASCII"));
                byte[] decoded = Base64.getDecoder().decode(base64Token);
                String token = new String(decoded);
                String id = token.split(":")[1];
                request.getSession(false).invalidate();
                sessionService.deleteSession(id);
            }
            super.onLogoutSuccess(request, response, authentication);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}