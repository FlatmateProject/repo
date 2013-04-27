package view;

import com.vaadin.ui.CustomComponent;
import org.springframework.stereotype.Component;

@Component
public class StatisticPanel extends CustomComponent implements ComponentLifeCycle {

    public StatisticPanel() {
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
