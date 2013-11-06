package view;

import com.vaadin.ui.Notification;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class PopUpComponent {

    public void showError(String message) {
        Notification.show(message);
    }

}
