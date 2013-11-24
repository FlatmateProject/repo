package view.managerPanel;

import behavior.*;
import com.vaadin.ui.*;
import common.TableContent;
import dictionary.TABLE;
import dto.ColumnData;
import exception.DAOException;
import model.ManagerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.manager.Manager;
import view.PopUpComponent;
import view.TabComponent;
import view.common.TableUIBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerPanel extends TabComponent {

    private DataPanel dataPanel = DataPanel.empty();

    private Button[] tableButtons;

    private Table table;

    private Button addButton;

    private Button deleteButton;

    private Button updateButton;

    private Button searchButton;

    private Button cleanButton;

    private VerticalLayout tableLayout;

    @Autowired
    private Manager manager;

    @Autowired
    private ManagerModel managerModel;

    @Autowired
    private ClickAddButtonBehavior clickAddButtonBehavior;

    @Autowired
    private ClickDeleteButtonBehavior clickDeleteButtonBehavior;

    @Autowired
    private ClickUpdateButtonBehavior clickUpdateButtonBehavior;

    @Autowired
    private ClickSearchButtonBehavior clickSearchButtonBehavior;

    @Autowired
    private ClickCleanButtonBehavior clickCleanButtonBehavior;

    @Autowired
    private PopUpComponent popUp;

    @Override
    public void create() {
        HorizontalLayout tableButtonsLayout = new HorizontalLayout();
        tableButtons = new Button[10];
        tableButtons[0] = new Button("Klienci");
        tableButtons[1] = new Button("Firmy");
        tableButtons[2] = new Button("Usługi");
        tableButtons[3] = new Button("Pokoje");
        tableButtons[4] = new Button("Stanowiska");
        tableButtons[5] = new Button("Waluty");
        tableButtons[6] = new Button("Pracownicy");
        tableButtons[7] = new Button("Klasy");
        tableButtons[8] = new Button("Archiwum");
        tableButtons[9] = new Button("Rachunki");
        for (Button tableButton : tableButtons) {
            tableButtonsLayout.addComponent(tableButton);
        }

        createDataPanel();

        HorizontalLayout controlButtonsLayout = new HorizontalLayout();
        controlButtonsLayout.setMargin(true);
        addButton = new Button("Dodaj");
        deleteButton = new Button("Usuń");
        updateButton = new Button("Edytuj");
        searchButton = new Button("Szukaj");
        cleanButton = new Button("Wyczyść");
        controlButtonsLayout.addComponent(addButton);
        controlButtonsLayout.addComponent(deleteButton);
        controlButtonsLayout.addComponent(updateButton);
        controlButtonsLayout.addComponent(searchButton);
        controlButtonsLayout.addComponent(cleanButton);

        tableLayout = new VerticalLayout();
        TableContent tableContent = manager.createTable(TABLE.Customer);
        table = TableUIBuilder.table()
                .withTitle(TABLE.Customer)
                .withContent(tableContent)
                .withSelection()
                .withClickListener(new ClickManagerTableRowBehavior())
                .build();
        tableLayout.addComponent(table);

        List<Label> labels = createNews();

        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(tableButtonsLayout);
        vertical.addComponent(dataPanel);
        vertical.addComponent(controlButtonsLayout);
        vertical.addComponent(tableLayout);
        for (Label label : labels) {
            vertical.addComponent(label);
        }
        vertical.setMargin(true);
        setCompositionRoot(vertical);
    }

    public void createDataPanel() {
        try {
            TABLE table = managerModel.getCurrentlySelectedTable();
            List<ColumnData> columns = manager.getColumns(table);
            dataPanel.createDataPanel(columns);
        } catch (DAOException e) {
            popUp.showError("Something went wrong");
        }
    }

    public DataPanel getDataPanel() {
        return dataPanel;
    }

    @Override
    public void addEvents() {
        clickAddButtonBehavior.setManagerPanel(this);
        addButton.addClickListener(clickAddButtonBehavior);

        clickDeleteButtonBehavior.setManagerPanel(this);
        deleteButton.addClickListener(clickDeleteButtonBehavior);

        clickUpdateButtonBehavior.setManagerPanel(this);
        updateButton.addClickListener(clickUpdateButtonBehavior);

        clickSearchButtonBehavior.setManagerPanel(this);
        searchButton.addClickListener(clickSearchButtonBehavior);

        clickCleanButtonBehavior.setManagerPanel(this);
        cleanButton.addClickListener(clickCleanButtonBehavior);

        tableButtons[0].addClickListener(new ControlButtonBehavior(this, TABLE.Customer));
        tableButtons[1].addClickListener(new ControlButtonBehavior(this, TABLE.Company));
        tableButtons[2].addClickListener(new ControlButtonBehavior(this, TABLE.Service));
        tableButtons[3].addClickListener(new ControlButtonBehavior(this, TABLE.Room));
        tableButtons[4].addClickListener(new ControlButtonBehavior(this, TABLE.Occupation));
        tableButtons[5].addClickListener(new ControlButtonBehavior(this, TABLE.Currency));
        tableButtons[6].addClickListener(new ControlButtonBehavior(this, TABLE.Employee));
        tableButtons[7].addClickListener(new ControlButtonBehavior(this, TABLE.RoomType));
        tableButtons[8].addClickListener(new ControlButtonBehavior(this, TABLE.Archive));
        tableButtons[9].addClickListener(new ControlButtonBehavior(this, TABLE.Bill));
    }

    private List<Label> createNews() {
        try {
            return buildNews();
        } catch (DAOException e) {
            e.printStackTrace();
            popUp.showError("Something went wrong");
            return new ArrayList<>();
        }
    }

    private List<Label> buildNews() throws DAOException {
        List<Label> labels = new ArrayList<>();
        int reservations = manager.getNumberOfReservations();
        int clients = manager.getNumberOfClients();
        int roomNumber = manager.getNumberOfRooms();
        int serviceTypeNumber = manager.getNumberOfServiceTypes();
        int numberClientsToCheckIn = manager.getNumberOfClientsToCheckIn();
        int numberClientsToCheckOut = manager.getNumberOfClientsToCheckOut();
        labels.add(new Label("Ogólna ilość rezerwacji: " + reservations));
        labels.add(new Label("Ilość zarejestrowanych gości: " + clients));
        labels.add(new Label(roomNumber + " pokoi, z czego wolnych i  zajętych."));
        labels.add(new Label("Ilość dostąpnych usług: " + serviceTypeNumber));
        labels.add(new Label("W tym miesiącu oczekujemy na " + numberClientsToCheckIn + ", a żegnamy " + numberClientsToCheckOut + " gości"));
        return labels;
    }

    public void refreshTable(Table table) {
        tableLayout.removeComponent(this.table);
        tableLayout.addComponent(table);
        this.table = table;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}

