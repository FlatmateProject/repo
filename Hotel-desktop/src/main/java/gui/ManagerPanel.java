package gui;

import common.adapter.MouseListenerAdapter;
import common.tableBuilder.TableResult;
import dictionary.TABLE;
import dto.ColumnData;
import exception.DAOException;
import exception.IncorrectDataException;
import org.apache.log4j.Logger;
import service.AddService;
import service.DeleteService;
import service.UpdateService;
import service.guessBook.GuestBook;
import service.manager.Manager;
import validation.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class ManagerPanel extends JPanel {

    private static final Logger log = Logger.getLogger(ManagerPanel.class);

    private final Calendar calendar;

    private JPanel dataPanel;

    private JButton tableButtons[];

    private JScrollPane scrollPanel;

    private JTable table;

    private TABLE currentTable = TABLE.Customer;

    private JLabel labels[];

    private JTextField dataFields[];

    private final Color bgColor = new Color(224, 230, 233);

    private final MouseListener tableMouseListener = createTableMouseListener();

    private final Color buttonColor = new Color(174, 205, 214);

    private JButton addButton;

    private JButton deleteButton;

    private JButton updateButton;

    private JButton searchButton;

    private JButton cleanButton;

    private final Manager manager;

    private GuestBook guestBook;

    private AddService addService;

    private UpdateService updateService;

    private DeleteService deleteService;


    public ManagerPanel(Manager manager, Calendar calendar) {
        this.calendar = calendar;
        this.manager = manager;
        create();
        addEvents();
    }

    private void create() {
        setBackground(bgColor);
        setLayout(null);
        int progressiveY = 20;
        tableButtons = new JButton[10];
        tableButtons[0] = createButton("Klienci", new Point(0, progressiveY));
        tableButtons[1] = createButton("Firmy", new Point(100, progressiveY));
        tableButtons[2] = createButton("Usługi", new Point(200, progressiveY));
        tableButtons[3] = createButton("Pokoje", new Point(300, progressiveY));
        tableButtons[4] = createButton("Stanowiska", new Point(400, progressiveY));
        tableButtons[5] = createButton("Waluty", new Point(500, progressiveY));
        tableButtons[6] = createButton("Pracownicy", new Point(600, progressiveY));
        tableButtons[7] = createButton("Klasy", new Point(700, progressiveY));
        tableButtons[8] = createButton("Archiwum", new Point(800, progressiveY));
        tableButtons[9] = createButton("Rachunki", new Point(900, progressiveY));
        progressiveY = progressiveY + 20;
        dataPanel = createDataPanel(TABLE.Customer);
        add(dataPanel);
        progressiveY = progressiveY + dataPanel.getHeight() + 20;
        addButton = createButton("Dodaj", new Point(0, progressiveY));
        deleteButton = createButton("Usuń", new Point(100, progressiveY));
        updateButton = createButton("Edytuj", new Point(200, progressiveY));
        searchButton = createButton("Szukaj", new Point(300, progressiveY));
        cleanButton = createButton("Wyczyść", new Point(400, progressiveY));
        progressiveY = progressiveY + 40;
        scrollPanel = new JScrollPane();
        scrollPanel.setBounds(0, progressiveY, 1200, 220);
        add(scrollPanel);
        progressiveY = progressiveY + 240;
        TableResult tableResult = manager.createTable(TABLE.Customer);
        table = createTable(tableResult);
        createNewsList(progressiveY);
    }

    private void createNewsList(int progressiveY) {
        JList<String> news = new JList<String>(createNews());
        news.setBounds(0, progressiveY, 1200, 100);
        news.setBackground(bgColor);
        add(news);
    }

    private JButton createButton(String buttonLabel, Point point) {
        JButton button = new JButton(buttonLabel);
        button.setBounds(new Rectangle(point, new Dimension(100, 20)));
        button.setBackground(buttonColor);
        add(button);
        return button;
    }

    private String[] createNews() {
        try {
            return buildNews();
        } catch (DAOException e) {
            e.printStackTrace();
            showErrorMessage("Something went wrong");
            return new String[]{"ERROR"};
        }
    }

    private String[] buildNews() throws DAOException {
        int reservations = manager.getCount("rezerwacje");
        int clients = manager.getCount("klienci");
        int roomNumber = manager.getCount("pokoje");
        int serviceTypeNumber = manager.getCount("uslugi");
        int numberClientsToCheckIn = manager.getCount("rezerwacje where month(data_z) = " + (calendar.get(Calendar.MONTH) + 1));
        int numberClientsToCheckOut = manager.getCount("rezerwacje where month(data_w) = " + (calendar.get(Calendar.MONTH) + 1));
        return new String[]{
                "Ogólna ilość rezerwacji: " + reservations,
                "Ilość zarejestrowanych gości: " + clients,
                roomNumber + " pokoi, z czego wolnych i  zajętych.",
                "Ilość dostąpnych usług: " + serviceTypeNumber,
                "W tym miesiącu oczekujemy na " + numberClientsToCheckIn + ", a żegnamy " + numberClientsToCheckOut + " gości"
        };
    }


    private void addEvents() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (areValidFields()) {
                    addRow();
                }
            }

            private void addRow() {
                try {
                    String labels[] = getLabels();
                    String data[] = getData();
                    addService.insertData(currentTable, labels, data);
                    TableResult tableResult = manager.createTable(currentTable);
                    table = createTable(tableResult);
                } catch (IncorrectDataException e) {
                    showErrorMessage("Wrong ID or given ID already exists!");
                }
            }

            private boolean areValidFields() {
                for (int i = 0; i < dataFields.length; i++) {
                    String fieldText = dataFields[i].getText();
                    if (fieldText.isEmpty() && isNotValidField(labels[i].getText(), fieldText)) {
                        return false;
                    }
                }
                return true;
            }

            private boolean isNotValidField(String label, String field) {
                List<String> fieldsShouldBeNumbers = Arrays.asList(
                        "CENA_SP", "CENA_KU", "ILOSC", "WARTOSC", "PODATEK", "IL_OSOB",
                        "ID_STANOWISKA", "TELEFON", "NIP", "NR_LOKALU", "REGON", "CENA",
                        "PODSTAWA", "PREMIA", "ID_POKOJU", "ID_KLASY", "ID_REZ", "ID_USLUGI"
                );
                List<String> fieldsShouldBePesel = Arrays.asList("IDK_PESEL", "IDP_PESEL");
                List<String> fieldsShouldBeData = Arrays.asList("DATA_Z", "DATA_W", "DATA");
                if (fieldsShouldBePesel.contains(label) && !ValidationUtils.isPesel(field)) {
                    showErrorMessage("Wrong PESEL!");
                    return true;
                }
                if (label.equals("IDF_KRS") && !ValidationUtils.isKRS(field)) {
                    showErrorMessage("Wrong KRS!");
                    return true;
                }
                if (fieldsShouldBeData.contains(label) && ValidationUtils.isNotDate(field)) {
                    showErrorMessage("Wrong date!");
                    return true;
                }
                if (fieldsShouldBeNumbers.contains(label) && ValidationUtils.isNotNumber(field)) {
                    showErrorMessage("Wrong number!");
                    return true;
                }
                return false;
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    deleteRow();
                } catch (IncorrectDataException e) {
                    showErrorMessage("Error during deleting the row!");
                }
            }

            private void deleteRow() throws IncorrectDataException {
                long id = getId();
                if (id > 0) {
                    String primaryKey = getPrimaryKey();
                    deleteService.deleteData(currentTable, primaryKey, id);
                    TableResult tableResult = manager.createTable(currentTable);
                    table = createTable(tableResult);
                }
            }

            private String getPrimaryKey() {
                return labels[0].getText();
            }

            private long getId() {
                try {
                    return Long.parseLong(dataFields[0].getText());
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String[] labels = getLabels();
                    String[] data = getData();
                    updateService.updateClientData(currentTable, labels, data);
                    TableResult tableResult = manager.createTable(currentTable);
                    table = createTable(tableResult);
                } catch (IncorrectDataException e) {
                    showErrorMessage("Update error! Check correctness of data!");
                }
            }

        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String[] labels = getLabels();
                String[] data = getData();
                TableResult tableResult = guestBook.createTable(currentTable, labels, data);
                table = createTable(tableResult);

            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanFields();
                TableResult tableResult = guestBook.createTable(currentTable);
                table = createTable(tableResult);
            }

            private void cleanFields() {
                for (JTextField field : dataFields) {
                    field.setText("");
                }
            }
        });

        tableButtons[0].addActionListener(new TableButtonClickListener(TABLE.Customer));
        tableButtons[1].addActionListener(new TableButtonClickListener(TABLE.Company));
        tableButtons[2].addActionListener(new TableButtonClickListener(TABLE.Service));
        tableButtons[3].addActionListener(new TableButtonClickListener(TABLE.Room));
        tableButtons[4].addActionListener(new TableButtonClickListener(TABLE.Occupation));
        tableButtons[5].addActionListener(new TableButtonClickListener(TABLE.Currency));
        tableButtons[6].addActionListener(new TableButtonClickListener(TABLE.Employee));
        tableButtons[7].addActionListener(new TableButtonClickListener(TABLE.RoomType));
        tableButtons[8].addActionListener(new TableButtonClickListener(TABLE.Archive));
        tableButtons[9].addActionListener(new TableButtonClickListener(TABLE.Bill));
    }

    private JTable createTable(TableResult tableResult) {
        JTable table = new JTable(tableResult.getRowsData(), tableResult.getColumnNames());
        table.setFillsViewportHeight(true);
        table.addMouseListener(tableMouseListener);
        scrollPanel.setViewportView(table);
        return table;
    }

    private JPanel createDataPanel(TABLE name) {
        try {
            List<ColumnData> columns = manager.getColumns(name);
            int length = columns.size();
            labels = new JLabel[length];
            dataFields = new JTextField[length];
            JPanel manDataPan = emptyPanel((length + 1) * 20);
            int manX = 30;
            int i = 0;
            for (ColumnData columnData : columns) {
                int manY = 20 + 20 * i;
                labels[i] = new JLabel(columnData.getName());
                labels[i].setBounds(manX, manY, 150, 20);
                dataFields[i] = new JTextField();
                dataFields[i].setBounds(labels[i].getX() + 150, labels[i].getY(), 150, 19);
                manDataPan.add(labels[i]);
                manDataPan.add(dataFields[i]);
                i++;
            }
            return manDataPan;
        } catch (DAOException e) {
            showErrorMessage("Something went wrong");
            return emptyPanel(40);
        }
    }

    private JPanel emptyPanel(int height) {
        JPanel manDataPan = new JPanel();
        manDataPan.setLayout(null);
        manDataPan.setBounds(0, 40, 340, height);
        manDataPan.setBackground(bgColor);
        return manDataPan;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "ATTENTION!", JOptionPane.ERROR_MESSAGE);
    }


    private MouseListenerAdapter createTableMouseListener() {
        return new MouseListenerAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int numberOfColumn = table.getColumnCount();
                int numberOfInputFields = dataFields.length;
                if (numberOfColumn == numberOfInputFields) {
                    fillInputs();
                }
            }

            private void fillInputs() {
                int selectedRow = table.getSelectedRow();
                for (int i = 0; i < dataFields.length; i++) {
                    Object cell = table.getValueAt(selectedRow, i);
                    dataFields[i].setText(cell.toString());
                }
            }
        };
    }

    private String[] getLabels() {
        int length = labels.length;
        String[] array = new String[length];
        for (int i = 0; i < length; i++) {
            array[i] = labels[i].getText();
        }
        return array;
    }

    private String[] getData() {
        int length = dataFields.length;
        String data[] = new String[length];
        for (int i = 0; i < length; i++) {
            data[i] = dataFields[i].getText();
        }
        return data;
    }


    class TableButtonClickListener implements ActionListener {

        private TABLE usedTable;

        TableButtonClickListener(TABLE usedTable) {
            this.usedTable = usedTable;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            currentTable = usedTable;
            TableResult tableResult = manager.createTable(usedTable);
            table = createTable(tableResult);
            remove(dataPanel);
            dataPanel = createDataPanel(usedTable);
            add(dataPanel);
            repaint();
            revalidate();
        }
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
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
