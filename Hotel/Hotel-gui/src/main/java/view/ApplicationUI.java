package view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

public class ApplicationUI extends UI {

    @Override
    protected void init(VaadinRequest request) {
        ServletContext servletContext = VaadinServlet.getCurrent().getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        HotelUI gui = webApplicationContext.getBean(HotelUI.class);
        gui.construct();
        setContent(gui);
    }
}
