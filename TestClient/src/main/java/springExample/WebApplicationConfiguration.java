package springExample;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.ApplicationConfiguration;

@ComponentScan(basePackages = {"springExample"})
@Import(ApplicationConfiguration.class)
@Configuration
public class WebApplicationConfiguration {

}
