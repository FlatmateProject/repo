package view;

import com.vaadin.ui.CustomComponent;
import org.springframework.stereotype.Component;

@Component
public class GuessBookPanel extends CustomComponent implements ComponentLifeCycle {

    public GuessBookPanel() {
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
