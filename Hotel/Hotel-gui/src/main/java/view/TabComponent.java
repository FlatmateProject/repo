package view;

import com.vaadin.ui.CustomComponent;

public abstract class TabComponent extends CustomComponent {

    public TabComponent construct() {
        create();
        addEvents();
        return this;
    }

    public abstract void create();

    public abstract void addEvents();


}
