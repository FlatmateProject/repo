package behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import common.tableBuilder.TableContent;
import dictionary.TABLE;
import model.ManagerModel;
import org.springframework.beans.factory.annotation.Autowired;
import service.manager.Manager;
import view.common.TableUIBuilder;
import view.managerPanel.ManagerPanel;

public class ControlButtonBehavior implements Button.ClickListener {

    @Autowired
    private Manager manager;

    @Autowired
    private ManagerModel managerModel;

    private ManagerPanel managerPanel;

    private final TABLE usedTable;

    public ControlButtonBehavior(ManagerPanel managerPanel, TABLE usedTable) {
        this.managerPanel = managerPanel;
        this.usedTable = usedTable;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        managerModel.setCurrentlySelectedTable(usedTable);
        TableContent tableContent = manager.createTable(managerModel.getCurrentlySelectedTable());
        Table table = TableUIBuilder.table()
                .withTitle(managerModel.getCurrentlySelectedTable())
                .withContent(tableContent)
                .withSelection()
                .withClickListener(new ClickManagerTableRowBehavior())
                .build();
        managerPanel.refreshTable(table);
        managerPanel.createDataPanel();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setManagerModel(ManagerModel managerModel) {
        this.managerModel = managerModel;
    }

}