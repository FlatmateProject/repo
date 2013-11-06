package behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import common.tableBuilder.TableContent;
import exception.IncorrectDataException;
import model.ManagerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.AddService;
import service.manager.Manager;
import view.PopUpComponent;
import view.common.TableUIBuilder;
import view.managerPanel.ManagerPanel;

/**
 * User: piotro
 * Date: 11/6/13
 * Time: 7:16 PM
 */
@Component
public class ClickAddButtonBehavior implements Button.ClickListener {

    @Autowired
    private AddService addService;

    @Autowired
    private Manager manager;

    @Autowired
    private ManagerModel managerModel;

    @Autowired
    private PopUpComponent popUp;

    private ManagerPanel managerPanel;

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (managerPanel.getDataPanel().areValidFields()) {
            addRow();
        }
    }

    private void addRow() {
        try {
            String labels[] = managerPanel.getDataPanel().getLabels();
            String data[] = managerPanel.getDataPanel().getData();
            addService.insertData(managerModel.getCurrentlySelectedTable(), labels, data);
            TableContent tableContent = manager.createTable(managerModel.getCurrentlySelectedTable());
            Table table = TableUIBuilder.table()
                    .withTitle(managerModel.getCurrentlySelectedTable())
                    .withContent(tableContent)
                    .withSelection()
                    .build();
            managerPanel.refreshTable(table);
        } catch (IncorrectDataException e) {
            popUp.showError("Wrong ID or given ID already exists!");
        }
    }

    public void setManagerPanel(ManagerPanel managerPanel) {
        this.managerPanel = managerPanel;
    }

    public void setAddService(AddService addService) {
        this.addService = addService;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setPopUp(PopUpComponent popUp) {
        this.popUp = popUp;
    }

    public void setManagerModel(ManagerModel managerModel) {
        this.managerModel = managerModel;
    }
}
