package view;


import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.manager.Manager;

@Component
public class ManagerPanel extends CustomComponent implements ComponentLifeCycle {

    @Autowired
    private Manager manager;

    public ManagerPanel() {
        create();
        addEvents();
    }

    @Override
    public void create() {
        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(new TextField("Name"));
        vertical.addComponent(new TextField("Street address"));
        vertical.addComponent(new TextField("Postal code"));
        setCompositionRoot(vertical);
    }

    @Override
    public void addEvents() {

    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
