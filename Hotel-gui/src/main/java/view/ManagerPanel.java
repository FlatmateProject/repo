package view;


import com.vaadin.ui.CustomComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.manager.Manager;

@Component//(value="managerPanel")
public class ManagerPanel extends CustomComponent implements ComponentLifeCycle {

    @Autowired
    private Manager manager;

    public ManagerPanel() {
        create();
        addEvents();
    }

    @Override
    public void create() {
    }

    @Override
    public void addEvents() {
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
