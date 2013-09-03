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

import java.util.Calendar;
import java.util.List;

import static com.vaadin.ui.Button.ClickEvent;
import static com.vaadin.ui.Button.ClickListener;

@Component
public class ManagerPanel extends TabComponent {
    private static final Logger log = Logger.getLogger(ManagerPanel.class);

    private DataPanel dataPanel;

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
    private Calendar calendar;

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

        dataPanel = createDataPanel(TABLE.Customer);

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
                .build();
        tableLayout.addComponent(table);
//        createNewsList(progressiveY);

        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(tableButtonsLayout);
        vertical.addComponent(dataPanel);
        vertical.addComponent(controlButtonsLayout);
        vertical.addComponent(tableLayout);
//        vertical.addComponent(news);
        vertical.setMargin(true);
        setCompositionRoot(vertical);

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

//    private void createNewsList(int progressiveY) {
//        List<String> news = new List<>(/*createNews()*/);
//        add(news);
//    }

//    private String[] createNews() {
//        try {
//            return buildNews();
//        } catch (DAOException e) {
//            e.printStackTrace();
//            showErrorMessage("Something went wrong");
//            return new String[]{"ERROR"};
//        }
//    }
//
//    private String[] buildNews() throws DAOException {
//        int reservations = manager.getCount("rezerwacje");
//        int clients = manager.getCount("klienci");
//        int roomNumber = manager.getCount("pokoje");
//        int serviceTypeNumber = manager.getCount("uslugi");
//        int numberClientsToCheckIn = manager.getCount("rezerwacje where month(data_z) = " + (calendar.get(Calendar.MONTH) + 1));
//        int numberClientsToCheckOut = manager.getCount("rezerwacje where month(data_w) = " + (calendar.get(Calendar.MONTH) + 1));
//        return new String[]{
//                "Ogólna ilość rezerwacji: " + reservations,
//                "Ilość zarejestrowanych gości: " + clients,
//                roomNumber + " pokoi, z czego wolnych i  zajętych.",
//                "Ilość dostąpnych usług: " + serviceTypeNumber,
//                "W tym miesiącu oczekujemy na " + numberClientsToCheckIn + ", a żegnamy " + numberClientsToCheckOut + " gości"
//        };
//    }

    private DataPanel createDataPanel(TABLE name) {
        try {
            List<ColumnData> columns = manager.getColumns(name);
            return DataPanel.create(columns);
        } catch (DAOException e) {
            Notification.show("Something went wrong");
            return DataPanel.empty();
        }
    }

    private ItemClickEvent.ItemClickListener createTableMouseListener() {
        return new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                int numberOfColumn = table.getVisibleColumns().length;
                int numberOfInputFields = dataPanel.getNumberOfFields();
                if (numberOfColumn == numberOfInputFields) {
                    fillInputs();
                }
            }

            private void fillInputs() {
                Object selectedRowIndex = table.getValue();
                log.info("selectedRowIndex " + selectedRowIndex);
                Item selectedRow = table.getItem(selectedRowIndex);
                log.info("selectedRow " + selectedRow);
                for (int i = 1; i <= dataPanel.getNumberOfFields(); i++) {
                    Property property = selectedRow.getItemProperty(i);
                    String value = String.valueOf(property.getValue());
                    dataPanel.updateField(i, value);
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
        }
    }


    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
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

