package behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import common.TableContent;
import exception.IncorrectDataException;
import model.ManagerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.UpdateService;
import service.manager.Manager;
import view.PopUpComponent;
import view.common.TableUIBuilder;
import view.managerPanel.ManagerPanel;

@Component
public class ClickUpdateButtonBehavior implements Button.ClickListener {

    @Autowired
    private Manager manager;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private ManagerModel managerModel;

    @Autowired
    private PopUpComponent popUp;

    private ManagerPanel managerPanel;

    @Override
    public void buttonClick(Button.ClickEvent event) {
        try {
            String[] labels = managerPanel.getDataPanel().getLabels();
            String[] data = managerPanel.getDataPanel().getData();
            updateService.updateClientData(managerModel.getCurrentlySelectedTable(), labels, data);
            TableContent tableContent = manager.createTable(managerModel.getCurrentlySelectedTable());
            Table table = TableUIBuilder.table()
                    .withTitle(managerModel.getCurrentlySelectedTable())
                    .withContent(tableContent)
                    .withSelection()
                    .build();
            managerPanel.refreshTable(table);
        } catch (IncorrectDataException e) {
            popUp.showError("Update error! Check correctness of data!");
        }
    }

    public void setManagerPanel(ManagerPanel managerPanel) {
        this.managerPanel = managerPanel;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setManagerModel(ManagerModel managerModel) {
        this.managerModel = managerModel;
    }

    public void setUpdateService(UpdateService updateService) {
        this.updateService = updateService;
    }

    public void setPopUp(PopUpComponent popUp) {
        this.popUp = popUp;
    }
}