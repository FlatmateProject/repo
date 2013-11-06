package behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import common.tableBuilder.TableContent;
import model.ManagerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.guessBook.GuestBook;
import view.common.TableUIBuilder;
import view.managerPanel.ManagerPanel;

@Component
public class ClickSearchButtonBehavior implements Button.ClickListener {

    @Autowired
    private ManagerModel managerModel;

    @Autowired
    private GuestBook guestBook;

    private ManagerPanel managerPanel;

    @Override
    public void buttonClick(Button.ClickEvent event) {
        String[] labels = managerPanel.getDataPanel().getLabels();
        String[] data = managerPanel.getDataPanel().getData();
        TableContent tableContent = guestBook.createTable(managerModel.getCurrentlySelectedTable(), labels, data);
        Table table = TableUIBuilder.table()
                .withTitle(managerModel.getCurrentlySelectedTable())
                .withContent(tableContent)
                .withSelection()
                .build();
        managerPanel.refreshTable(table);
    }

    public void setManagerPanel(ManagerPanel managerPanel) {
        this.managerPanel = managerPanel;
    }

    public void setManagerModel(ManagerModel managerModel) {
        this.managerModel = managerModel;
    }

    public void setGuestBook(GuestBook guestBook) {
        this.guestBook = guestBook;
    }
}