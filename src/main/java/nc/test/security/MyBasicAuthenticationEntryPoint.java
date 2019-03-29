package nc.test.security;

import lombok.extern.log4j.Log4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j
@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException authException)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final PrintWriter writer = response.getWriter();
        writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " - UNAUTHORIZED");

        log.error(new StringBuilder()
                .append("По ссылке ")
                .append(request.getRequestURL())
                .append(" UNAUTHORIZED").toString()
        );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Modest");
        super.afterPropertiesSet();
    }

}
