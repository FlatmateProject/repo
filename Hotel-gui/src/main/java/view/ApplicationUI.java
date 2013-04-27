package view;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.WebApplicationConfiguration;

@Title("Hotel")
public class ApplicationUI extends UI {

    protected void init(VaadinRequest request) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WebApplicationConfiguration.class);
        HotelUI gui = context.getBean(HotelUI.class);
        gui.construct();
        setContent(gui);
    }
}
