package view;

import com.vaadin.ui.CustomComponent;
import org.springframework.stereotype.Component;

@Component
public class SchedulerPanel extends CustomComponent implements ComponentLifeCycle {

    public SchedulerPanel() {
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
