package nc.test.config.parent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("nc.test")
@PropertySource("classpath:log4j.properties")
public class ServiceConfig {
    public ServiceConfig() {
        super();
    }
}
