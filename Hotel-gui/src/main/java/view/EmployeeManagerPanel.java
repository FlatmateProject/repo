package view;

import com.vaadin.ui.CustomComponent;
import org.springframework.stereotype.Component;

@Component
public class EmployeeManagerPanel extends CustomComponent implements ComponentLifeCycle {

    public EmployeeManagerPanel() {
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
