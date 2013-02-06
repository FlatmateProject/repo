package gui;

import common.adapter.MouseListenerAdapter;
import common.tableBuilder.TableResult;
import exception.DAOException;
import exception.IncorrectDataException;
import org.apache.log4j.Logger;
import service.UpdateService;
import service.dictionary.TABLE;
import service.guessBook.GuestBook;
import service.manager.Manager;
import validation.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

public class ManagerPanel extends JPanel {

    private static final Logger log = Logger.getLogger(ManagerPanel.class);

    private final Calendar calendar;

    private JPanel dataPanel;

    private JButton actionButtons[];

    private JButton tableButtons[];

    private JList news;

    private JScrollPane scrollPanel = new JScrollPane();

    private JTable table;

    private TABLE tableName = TABLE.Customer;

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

    private UpdateService updateService;

    private GuestBook guestBook;

    public ManagerPanel(Manager manager, Calendar calendar) {
        this.calendar = calendar;
        this.manager = manager;
        create();
//        addEvents();
    }

    private void create() {
        setBackground(bgColor);
        setLayout(null);


        tableButtons = new JButton[5];
        addButton = createButton("Dodaj", new Point(0, 10));
        deleteButton = createButton("Usuń", new Point(100, 10));
        updateButton = createButton("Edytuj", new Point(200, 10));
        searchButton = createButton("Szukaj", new Point(300, 10));
        cleanButton = createButton("Wyczyść", new Point(400, 10));

        actionButtons = new JButton[10];
        actionButtons[0] = createButton("Klienci", new Point(0, 40));
        actionButtons[1] = createButton("Firmy", new Point(100, 40));
        actionButtons[2] = createButton("Usługi", new Point(200, 40));
        actionButtons[3] = createButton("Pokoje", new Point(300, 40));
        actionButtons[4] = createButton("Stanowiska", new Point(400, 40));
        actionButtons[5] = createButton("Waluty", new Point(500, 40));
        actionButtons[6] = createButton("Pracownicy", new Point(600, 40));
        actionButtons[7] = createButton("Klasy", new Point(700, 40));
        actionButtons[8] = createButton("Archiwum", new Point(800, 40));
        actionButtons[9] = createButton("Rachunki", new Point(900, 40));

//        news = new JList(createNews());
//        news.setBackground(bgColor);
//
//        table = createReservationTable("klienci");
//        table.addMouseListener(tableMouseListener);
//        scrollPanel = new JScrollPane(table);
//        add(scrollPanel);
//
//        dataPanel = createDataPanel("klienci");
//        add(dataPanel);
//        add(news);
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
            return new String[]{
                    "Ogólna ilość rezerwacji: " + manager.getCount("rezerwacje"),
                    "Ilość zarejestrowanych gości: " + manager.getCount("klienci"),
                    manager.getCount("pokoje") + " pokoi, z czego wolnych i  zajętych.",
                    "Ilość dostąpnych usług: " + manager.getCount("uslugi"),
                    "W tym miesiącu oczekujemy na " + manager.getCount("rezerwacje where month(data_z) = " + (calendar.get(Calendar.MONTH) + 1)) + ", a żegnamy " + manager.getCount("rezerwacje where month(data_w) = " + (calendar.get(Calendar.MONTH) + 1)) + " gości"};
        } catch (DAOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Something went wrong", "ATTENTION!", JOptionPane.ERROR_MESSAGE);
            return new String[]{"ERROR"};
        }
    }


    private void addEvents() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String l[] = new String[dataFields.length];
                String d[] = new String[dataFields.length];
                for (int i = 0; i < dataFields.length; i++) {
                    l[i] = labels[i].getText();
                    d[i] = dataFields[i].getText();
                    if (!d[i].isEmpty()) {
                        if ((l[i].equals("IDK_PESEL") || l[i]
                                .equals("IDP_PESEL"))
                                && !ValidationUtils.isPesel(d[i])) {
                            JOptionPane.showMessageDialog(null,
                                    "B��dny PESEL!", "UWAGA!",
                                    JOptionPane.ERROR_MESSAGE);
                            break;
                        } else if (l[i].equals("IDF_KRS")
                                && !ValidationUtils.isKRS(d[i])) {
                            JOptionPane.showMessageDialog(null, "B��dny KRS!",
                                    "UWAGA!", JOptionPane.ERROR_MESSAGE);
                            break;
                        } else if ((l[i].equals("DATA_Z")
                                || l[i].equals("DATA_W") || l[i].equals("DATA"))
                                && ValidationUtils.isNotDate(d[i])) {
                            JOptionPane.showMessageDialog(null, "B��dna data!",
                                    "UWAGA!", JOptionPane.ERROR_MESSAGE);
                            break;
                        } else if ((l[i].equals("CENA_SP")
                                || l[i].equals("CENA_KU")
                                || l[i].equals("ILOSC")
                                || l[i].equals("WARTOSC")
                                || l[i].equals("PODATEK")
                                || l[i].equals("IL_OSOB")
                                || l[i].equals("ID_STANOWISKA")
                                || l[i].equals("TELEFON")
                                || l[i].equals("NIP")
                                || l[i].equals("NR_LOKALU")
                                || l[i].equals("REGON")
                                || l[i].equals("CENA")
                                || l[i].equals("PODSTAWA")
                                || l[i].equals("PREMIA")
                                || l[i].equals("ID_POKOJU")
                                || l[i].equals("ID_KLASY")
                                || l[i].equals("ID_REZ")
                                || l[i].equals("ID_USLUGI"))
                                && ValidationUtils.isNotNumber(d[i])) {
                            JOptionPane.showMessageDialog(null, "B��dna liczba!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                    if (i == dataFields.length - 1) {
                        if (!manager.insertData(tableName, l, d, dataFields.length)) {
                            JOptionPane.showMessageDialog(null, "B��dne ID lub taki klient ju� istnieje!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        table = createTable(tableName);
                        table.addMouseListener(tableMouseListener);
                        scrollPanel.setViewportView(table);
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!dataFields[0].getText().isEmpty()) {
                    String l = labels[0].getText();
                    String d = dataFields[0].getText();
                    if (!manager.deleteData(tableName, l, d)) {
                        JOptionPane.showMessageDialog(null, "Nie mo�na usun�� tego wiersza!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        table = createTable(tableName);
                        table.addMouseListener(tableMouseListener);
                        scrollPanel.setViewportView(table);
                    }
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String[] labels = getLabels();
                    String[] data = getData();
                    updateService.updateClientData(tableName, labels, data);
                    table = createTable(tableName);
                    table.addMouseListener(tableMouseListener);
                    scrollPanel.setViewportView(table);
                } catch (IncorrectDataException e) {
                    JOptionPane.showMessageDialog(null, "Blad aktualizacji! Sprawdz dane!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
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
                String array[] = new String[length];
                for (int i = 0; i < length; i++) {
                    array[i] = dataFields[i].getText();
                }
                return array;
            }

        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String s2 = "";
                for (int i = 0; i < dataFields.length; i++) {
                    if (!dataFields[i].getText().isEmpty()) {
                        if (!s2.isEmpty()) {
                            s2 = s2 + " and ";
                        }
                        s2 = s2 + labels[i].getText() + "=" + "\"" + dataFields[i].getText() + "\"";
                    }
                }
                if (!s2.isEmpty()) {
                    table = createTable(guestBook.createTable(tableName, " where " + s2));
                    table.addMouseListener(tableMouseListener);
                    scrollPanel.setViewportView(table);
                }
            }
        });
        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JTextField field : dataFields) {
                    field.setText("");
                    TableResult tableResult = guestBook.createTable(tableName, "");
                    table = createTable(tableResult);
                    table.addMouseListener(tableMouseListener);
                    scrollPanel.setViewportView(table);
                }
            }
        });

        actionButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.Customer;
                manAction(tableName);
            }
        });
        actionButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.Company;
                manAction(tableName);
            }
        });
        actionButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.Service;
                manAction(tableName);
            }
        });
        actionButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.Room;
                manAction(tableName);
            }
        });
        actionButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.Occupation;
                manAction(tableName);
            }
        });
        actionButtons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.Currency;
                manAction(tableName);
            }
        });
        actionButtons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.Employee;
                manAction(tableName);
            }
        });
        actionButtons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.RoomType;
                manAction(tableName);
            }
        });
        actionButtons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.Archive;
                manAction(tableName);
            }
        });
        actionButtons[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                tableName = TABLE.Bill;
                manAction(tableName);
            }
        });
    }

    private JTable createTable(TABLE tableName) {
        TableResult result = manager.createTable(tableName);
        JTable table = new JTable(result.getRowsData(), result.getColumnNames());
        table.setFillsViewportHeight(true);
        return table;
    }

    private JPanel createDataPanel(TABLE name) {
        String cols[] = getColumns(name);
        int colCount = (cols != null ? cols.length : 0);
        labels = new JLabel[colCount];
        dataFields = new JTextField[colCount];

        JPanel manDataPan = new JPanel();
        manDataPan.setLayout(null);
        manDataPan.setBounds(0, 0, 340, (colCount + 1) * 20);
        manDataPan.setBackground(bgColor);

        int manX = 30, manY = 20;
        if (cols == null) {
            return manDataPan;
        }
        for (int i = 0; i < cols.length; i++) {
            labels[i] = new JLabel(cols[i]);
            if (i == 0)
                manY = 20;
            else
                manY = 20 * (i + 1);
            labels[i].setBounds(manX, manY, 150, 20);

            dataFields[i] = new JTextField();
            dataFields[i].setBounds(labels[i].getX() + 150, labels[i].getY(),
                    150, 19);
            // dataFields[i].setBorder(border);

            manDataPan.add(labels[i]);
            manDataPan.add(dataFields[i]);
        }
        return manDataPan;
    }

    private String[] getColumns(TABLE name) {
        try {
            return manager.getColumns(name);
        } catch (DAOException e) {
            JOptionPane.showMessageDialog(null, "Co� zepsu�e�!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private void manAction(TABLE manName) {
        table = createTable(manName);
        table.addMouseListener(tableMouseListener);
        scrollPanel.setViewportView(table);
        remove(dataPanel);
        add(createDataPanel(manName));
        repaint();
        revalidate();
    }

    private MouseListenerAdapter createTableMouseListener() {
        return new MouseListenerAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                for (int i = 0; i < dataFields.length; i++) {
                    dataFields[i].setText((String) table.getValueAt(table.getSelectedRow(), i));
                }
            }
        };
    }


    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    private JTable createTable(TableResult result) {
        JTable table = new JTable(result.getRowsData(), result.getColumnNames());
        table.setFillsViewportHeight(true);
        return table;
    }

    public void setGuestBook(GuestBook guestBook) {
        this.guestBook = guestBook;
    }

    public void setUpdateService(UpdateService updateService) {
        this.updateService = updateService;
    }
}
