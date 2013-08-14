package spring;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.ApplicationServlet;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class AutowiringApplicationServlet extends ApplicationServlet {

    private WebApplicationContext webApplicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            ServletContext servletContext = config.getServletContext();
            webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        } catch (IllegalStateException e) {
            throw new ServletException("could not locate containing WebApplicationContext");
        }
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        Class<? extends Application> applicationClass = getCurrentApplicationClass();
        AutowireCapableBeanFactory beanFactory = getAutowireCapableBeanFactory();
        try {
            return beanFactory.createBean(applicationClass);
        } catch (BeansException e) {
            throw new ServletException("failed to create new instance of " + applicationClass, e);
        }
    }

    private Class<? extends Application> getCurrentApplicationClass() throws ServletException {
        try {
            return getApplicationClass();
        } catch (ClassNotFoundException e) {
            throw new ServletException("Application class not found");
        }
    }

    protected final AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws ServletException {
        try {
            return webApplicationContext.getAutowireCapableBeanFactory();
        } catch (IllegalStateException e) {
            throw new ServletException("containing context " + webApplicationContext + " is not autowire-capable", e);
        }
    }

}
