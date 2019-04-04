package nc.test.security;
import lombok.extern.log4j.Log4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j
@Component
public class MyBasicAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(final HttpServletRequest request,
                       final HttpServletResponse response,
                       final AccessDeniedException accessDeniedException)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        final PrintWriter writer = response.getWriter();
        writer.println("HTTP Status " + HttpServletResponse.SC_FORBIDDEN + " - FORBIDDEN");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            log.error(new StringBuilder()
                    .append("Доступ для ")
                    .append(auth.getName())
                    .append("по ссылке ")
                    .append(request.getRequestURL())
                    .append(" запрещен").toString()
            );
        }
    }
}
