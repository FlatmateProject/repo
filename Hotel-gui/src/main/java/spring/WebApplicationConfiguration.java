package spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan(basePackages = {"view"})
@ImportResource("classpath:properties-config.xml")
@Import(ApplicationConfiguration.class)
@Configuration
public class WebApplicationConfiguration extends WebMvcConfigurerAdapter {

}
