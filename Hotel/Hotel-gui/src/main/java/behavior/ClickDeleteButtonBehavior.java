package behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import common.TableContent;
import exception.IncorrectDataException;
import model.ManagerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.DeleteService;
import service.manager.Manager;
import view.PopUpComponent;
import view.common.TableUIBuilder;
import view.managerPanel.ManagerPanel;

@Component
public class ClickDeleteButtonBehavior implements Button.ClickListener {

    @Autowired
    private Manager manager;

    @Autowired
    private DeleteService deleteService;

    @Autowired
    private ManagerModel managerModel;

    @Autowired
    private PopUpComponent popUp;

    private ManagerPanel managerPanel;

    @Override
    public void buttonClick(Button.ClickEvent event) {
        try {
            deleteRow();
        } catch (IncorrectDataException e) {
            popUp.showError("Error during deleting the row!");
        }
    }

    private void deleteRow() throws IncorrectDataException {
        long id = managerPanel.getDataPanel().getIdValue();
        if (id > 0) {
            String primaryKey = managerPanel.getDataPanel().getPrimaryKey();
            deleteService.deleteData(managerModel.getCurrentlySelectedTable(), primaryKey, id);
            TableContent tableContent = manager.createTable(managerModel.getCurrentlySelectedTable());
            Table table = TableUIBuilder.table()
                    .withTitle(managerModel.getCurrentlySelectedTable())
                    .withContent(tableContent)
                    .withSelection()
                    .build();
            managerPanel.refreshTable(table);
        }
    }

    public void setManagerPanel(ManagerPanel managerPanel) {
        this.managerPanel = managerPanel;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setDeleteService(DeleteService deleteService) {
        this.deleteService = deleteService;
    }

    public void setManagerModel(ManagerModel managerModel) {
        this.managerModel = managerModel;
    }

    public void setPopUp(PopUpComponent popUp) {
        this.popUp = popUp;
    }
}