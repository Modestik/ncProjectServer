package nc.test.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;
import java.util.Set;

/*public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {// implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class);
        ctx.setServletContext(servletContext);
        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}*/

public class WebAppInitializer implements WebApplicationInitializer {

    public WebAppInitializer() {
        super();
    }

    //

    /**
     * Register and configure all Servlet container components necessary to power the web application.
     * Зарегистрируйте и настройте все компоненты контейнера сервлета, необходимые для работы веб-приложения.
     */
    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {

        /**
         *         Create the 'root' Spring application context
         *         Создайте «корневой» контекст приложения Spring
         *         WebApplicationContext реализация, которая принимает аннотированные классы в качестве входных данных - в частности,
         *         @Configuration аннотированные классы, но также простые @Component
         */
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("nc.test.config.parent");
        // Manages the lifecycle of the root application context
        // Управляет жизненным циклом контекста корневого приложения
        servletContext.addListener(new ContextLoaderListener(root));

        // Handles requests into the application
        // Обрабатывает запросы в приложение
        AnnotationConfigWebApplicationContext childWebApplicationContext = new AnnotationConfigWebApplicationContext();
        childWebApplicationContext.scan("nc.test.config.child");
        Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(childWebApplicationContext));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);


        // spring security filter
        final DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
        final FilterRegistration.Dynamic addedFilter = servletContext.addFilter("springSecurityFilterChain", springSecurityFilterChain);
        addedFilter.addMappingForUrlPatterns(null, false, "/*");
    }

}