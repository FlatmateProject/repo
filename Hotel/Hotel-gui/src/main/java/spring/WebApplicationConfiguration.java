package spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackages = {"view"})
@Import(ApplicationConfiguration.class)
@Configuration
public class WebApplicationConfiguration {

}
