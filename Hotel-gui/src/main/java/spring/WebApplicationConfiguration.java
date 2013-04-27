package spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

//@EnableWebMvc
@ComponentScan(basePackages = {"view"})
@ImportResource("classpath:properties-gui-config.xml")
@Import(ApplicationConfiguration.class)
@Configuration
public class WebApplicationConfiguration /*extends WebMvcConfigurerAdapter */ {
}
