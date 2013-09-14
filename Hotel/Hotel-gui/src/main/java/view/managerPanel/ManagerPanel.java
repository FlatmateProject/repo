package view.managerPanel;


import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import common.tableBuilder.TableContent;
import dictionary.TABLE;
import dto.ColumnData;
import exception.DAOException;
import exception.IncorrectDataException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.AddService;
import service.DeleteService;
import service.UpdateService;
import service.guessBook.GuestBook;
import service.manager.Manager;
import view.TabComponent;
import view.common.TableUIBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.vaadin.ui.Button.ClickEvent;
import static com.vaadin.ui.Button.ClickListener;

@Component
public class ManagerPanel extends TabComponent {

    private static final Logger log = Logger.getLogger(ManagerPanel.class);

    private DataPanel dataPanel = DataPanel.empty();

    private Button[] tableButtons;

    private Table table;

    private TABLE currentlySelectedTable = TABLE.Customer;

    private final ItemClickEvent.ItemClickListener tableMouseListener = createTableMouseListener();

    private Button addButton;

    private Button deleteButton;

    private Button updateButton;

    private Button searchButton;

    private Button cleanButton;

    private VerticalLayout tableLayout;

    @Autowired
    private Manager manager;

    @Autowired
    private GuestBook guestBook;

    @Autowired
    private AddService addService;

    @Autowired
    private UpdateService updateService;

    @Autowired
    private DeleteService deleteService;

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

        createDataPanel(TABLE.Customer);

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
                .withClickListener(tableMouseListener)
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

    private void createDataPanel(TABLE table) {
        try {
            List<ColumnData> columns = manager.getColumns(table);
            dataPanel.createDataPanel(columns);
        } catch (DAOException e) {
            Notification.show("Something went wrong");
        }
    }

    @Override
    public void addEvents() {
        addButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (dataPanel.areValidFields()) {
                    addRow();
                }
            }

            private void addRow() {
                try {
                    String labels[] = dataPanel.getLabels();
                    String data[] = dataPanel.getData();
                    addService.insertData(currentlySelectedTable, labels, data);
                    tableLayout.removeComponent(table);
                    TableContent tableContent = manager.createTable(currentlySelectedTable);
                    table = TableUIBuilder.table()
                            .withTitle(currentlySelectedTable)
                            .withContent(tableContent)
                            .withSelection()
                            .build();
                    tableLayout.addComponent(table);
                } catch (IncorrectDataException e) {
                    Notification.show("Wrong ID or given ID already exists!");
                }
            }


        });
        deleteButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    deleteRow();
                } catch (IncorrectDataException e) {
                    Notification.show("Error during deleting the row!");
                }
            }

            private void deleteRow() throws IncorrectDataException {
                long id = dataPanel.getIdValue();
                if (id > 0) {
                    String primaryKey = dataPanel.getPrimaryKey();
                    deleteService.deleteData(currentlySelectedTable, primaryKey, id);
                    tableLayout.removeComponent(table);
                    TableContent tableContent = manager.createTable(currentlySelectedTable);
                    table = TableUIBuilder.table()
                            .withTitle(currentlySelectedTable)
                            .withContent(tableContent)
                            .withSelection()
                            .build();
                    tableLayout.addComponent(table);
                }
            }
        });
        updateButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                try {
                    String[] labels = dataPanel.getLabels();
                    String[] data = dataPanel.getData();
                    updateService.updateClientData(currentlySelectedTable, labels, data);
                    tableLayout.removeComponent(table);
                    TableContent tableContent = manager.createTable(currentlySelectedTable);
                    table = TableUIBuilder.table()
                            .withTitle(currentlySelectedTable)
                            .withContent(tableContent)
                            .withSelection()
                            .build();
                    tableLayout.addComponent(table);
                } catch (IncorrectDataException e) {
                    Notification.show("Update error! Check correctness of data!");
                }
            }

        });
        searchButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                String[] labels = dataPanel.getLabels();
                String[] data = dataPanel.getData();
                tableLayout.removeComponent(table);
                TableContent tableContent = guestBook.createTable(currentlySelectedTable, labels, data);
                table = TableUIBuilder.table()
                        .withTitle(currentlySelectedTable)
                        .withContent(tableContent)
                        .withSelection()
                        .build();
                tableLayout.addComponent(table);
            }
        });
        cleanButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                dataPanel.cleanFields();
                tableLayout.removeComponent(table);
                TableContent tableContent = guestBook.createTable(currentlySelectedTable);
                table = TableUIBuilder.table()
                        .withTitle(currentlySelectedTable)
                        .withContent(tableContent)
                        .withSelection()
                        .build();
                tableLayout.addComponent(table);
            }
        });
        tableButtons[0].addClickListener(new ControlButtonClickListener(TABLE.Customer));
        tableButtons[1].addClickListener(new ControlButtonClickListener(TABLE.Company));
        tableButtons[2].addClickListener(new ControlButtonClickListener(TABLE.Service));
        tableButtons[3].addClickListener(new ControlButtonClickListener(TABLE.Room));
        tableButtons[4].addClickListener(new ControlButtonClickListener(TABLE.Occupation));
        tableButtons[5].addClickListener(new ControlButtonClickListener(TABLE.Currency));
        tableButtons[6].addClickListener(new ControlButtonClickListener(TABLE.Employee));
        tableButtons[7].addClickListener(new ControlButtonClickListener(TABLE.RoomType));
        tableButtons[8].addClickListener(new ControlButtonClickListener(TABLE.Archive));
        tableButtons[9].addClickListener(new ControlButtonClickListener(TABLE.Bill));
    }


    private List<Label> createNews() {
        try {
            return buildNews();
        } catch (DAOException e) {
            e.printStackTrace();
            Notification.show("Something went wrong");
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


    private ItemClickEvent.ItemClickListener createTableMouseListener() {
        return new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                int numberOfColumn = event.getItem().getItemPropertyIds().size();
                int numberOfInputFields = dataPanel.getNumberOfFields();
                if (numberOfColumn == numberOfInputFields) {
                    fillInputs(event.getItem());
                }
            }

            private void fillInputs(Item selectedRow) {
                log.info("selectedRow " + selectedRow);
                for (int i = 1; i <= dataPanel.getNumberOfFields(); i++) {
                    Property property = selectedRow.getItemProperty(i);
                    String value = String.valueOf(property.getValue());
                    dataPanel.updateField(i - 1, value);
                }
            }
        };
    }

    private class ControlButtonClickListener implements ClickListener {

        private TABLE usedTable;

        ControlButtonClickListener(TABLE usedTable) {
            this.usedTable = usedTable;
        }

        @Override
        public void buttonClick(ClickEvent event) {
            currentlySelectedTable = usedTable;
            tableLayout.removeComponent(table);
            TableContent tableContent = manager.createTable(currentlySelectedTable);
            table = TableUIBuilder.table()
                    .withTitle(currentlySelectedTable)
                    .withContent(tableContent)
                    .withSelection()
                    .withClickListener(tableMouseListener)
                    .build();
            tableLayout.addComponent(table);
            createDataPanel(currentlySelectedTable);
        }
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setGuestBook(GuestBook guestBook) {
        this.guestBook = guestBook;
    }

    public void setAddService(AddService addService) {
        this.addService = addService;
    }

    public void setUpdateService(UpdateService updateService) {
        this.updateService = updateService;
    }

    public void setDeleteService(DeleteService deleteService) {
        this.deleteService = deleteService;
    }
}

