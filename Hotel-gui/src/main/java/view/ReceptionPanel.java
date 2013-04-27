package view;

import com.vaadin.ui.CustomComponent;
import org.springframework.stereotype.Component;

@Component
public class ReceptionPanel extends CustomComponent implements ComponentLifeCycle {

    public ReceptionPanel() {
        create();
        addEvents();
    }

    @Override
    public void create() {

    }

    @Override
    public void addEvents() {

    }
}
