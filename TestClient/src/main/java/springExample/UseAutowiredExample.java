package springExample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UseAutowiredExample {

    public static void main(String[] argv) {
        ApplicationContext context = new AnnotationConfigApplicationContext(WebApplicationConfiguration.class);
        SimpleComponent simpleComponent = context.getBean(SimpleComponent.class);
        System.out.println("SimpleComponent " + simpleComponent);
    }
}
