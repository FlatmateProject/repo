package gui;

import common.adapter.MouseListenerAdapter;
import common.tableBuilder.TableResult;
import dto.cantor.CustomerData;
import org.apache.log4j.Logger;
import service.GuestBook;
import service.Manager;
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
    private String manName = "klienci";
    private JLabel manLabel[];
    private JTextField manData[];

    private final Color bgColor = new Color(224, 230, 233);

    private final MouseListener tableMouseListener = createTableMouseListener();

    private final Color buttonColor = new Color(174, 205, 214);

    private final Manager manager;

    private GuestBook guestBook;

    public ManagerPanel(Manager manager, Calendar calendar) {
        this.calendar = calendar;
        this.manager = manager;
        create();
//        addEvents();
    }

    private void create() {
        setBackground(bgColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        tableButtons = new JButton[5];
        tableButtons[0] = createButton("Dodaj");
        tableButtons[1] = createButton("Usuń");
        tableButtons[2] = createButton("Edytuj");
        tableButtons[3] = createButton("Szukaj");
        tableButtons[4] = createButton("Wyczyść");

        actionButtons = new JButton[10];
        actionButtons[0] = createButton("Klienci");
        actionButtons[1] = createButton("Firmy");
        actionButtons[2] = createButton("Usługi");
        actionButtons[3] = createButton("Pokoje");
        actionButtons[4] = createButton("Stanowiska");
        actionButtons[5] = createButton("Waluty");
        actionButtons[6] = createButton("Pracownicy");
        actionButtons[7] = createButton("Klasy");
        actionButtons[8] = createButton("Archiwum");
        actionButtons[9] = createButton("Rachunki");

        news = new JList(createNews());
        news.setBackground(bgColor);

        table = manGenTable("klienci");
        table.addMouseListener(tableMouseListener);
        scrollPanel = new JScrollPane(table);
        add(scrollPanel);

        dataPanel = createDataPanel("klienci");
        add(dataPanel);
        add(news);
    }

    private JButton createButton(String buttonLabel) {
        JButton button = new JButton(buttonLabel);
        button.setBackground(buttonColor);
        add(button);
        return button;
    }

    private String[] createNews() {
        return new String[]{
                "Ogólna ilość rezerwacji: " + manager.getCount("rezerwacje"),
                "Ilość zarejestrowanych gości: " + manager.getCount("klienci"),
                manager.getCount("pokoje") + " pokoi, z czego wolnych i  zajętych.",
                "Ilość dostąpnych usług: " + manager.getCount("uslugi"),
                "W tym miesiącu oczekujemy na " + manager.getCount("rezerwacje where month(data_z) = " + (calendar.get(Calendar.MONTH) + 1)) + ", a żegnamy " + manager.getCount("rezerwacje where month(data_w) = " + (calendar.get(Calendar.MONTH) + 1)) + " gości"};
    }


    private void addEvents() {
        tableButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String l[] = new String[manData.length];
                String d[] = new String[manData.length];
                for (int i = 0; i < manData.length; i++) {
                    l[i] = manLabel[i].getText();
                    d[i] = manData[i].getText();
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
                    if (i == manData.length - 1) {
                        if (!manager.insertData(manName, l, d, manData.length)) {
                            JOptionPane.showMessageDialog(null, "B��dne ID lub taki klient ju� istnieje!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        table = manGenTable(manName);
                        table.addMouseListener(tableMouseListener);
                        scrollPanel.setViewportView(table);
                    }
                }
            }
        });
        tableButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!manData[0].getText().isEmpty()) {
                    String l = manLabel[0].getText();
                    String d = manData[0].getText();
                    if (!manager.deleteData(manName, l, d)) {
                        JOptionPane.showMessageDialog(null, "Nie mo�na usun�� tego wiersza!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        table = manGenTable(manName);
                        table.addMouseListener(tableMouseListener);
                        scrollPanel.setViewportView(table);
                    }
                }
            }
        });
        tableButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String l[] = new String[manData.length];
                String d[] = new String[manData.length];
                for (int i = 0; i < manData.length; i++) {
                    l[i] = manLabel[i].getText();
                    d[i] = manData[i].getText();
                }
                manager.updateData(manName, l, d, manData.length);
                table = manGenTable(manName);
                table.addMouseListener(tableMouseListener);
                scrollPanel.setViewportView(table);
            }
        });
        tableButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String s2 = "";
                for (int i = 0; i < manData.length; i++) {
                    if (!manData[i].getText().isEmpty()) {
                        if (!s2.isEmpty()) {
                            s2 = s2 + " and ";
                        }
                        s2 = s2 + manLabel[i].getText() + "=" + "\"" + manData[i].getText() + "\"";
                    }
                }
                if (!s2.isEmpty()) {
                    table = createTable(guestBook.createTable(manName, " where " + s2, CustomerData.class));
                    table.addMouseListener(tableMouseListener);
                    scrollPanel.setViewportView(table);
                }
            }
        });
        tableButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JTextField aManData : manData) {
                    aManData.setText("");
                    table = createTable(guestBook.createTable(manName, "", CustomerData.class));
                    table.addMouseListener(tableMouseListener);
                    scrollPanel.setViewportView(table);
                }
            }
        });

        actionButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "klienci";
                manAction(manName);
            }
        });
        actionButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "firmy";
                manAction(manName);
            }
        });
        actionButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "uslugi";
                manAction(manName);
            }
        });
        actionButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "pokoje";
                manAction(manName);
            }
        });
        actionButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "stanowiska";
                manAction(manName);
            }
        });
        actionButtons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "waluty";
                manAction(manName);
            }
        });
        actionButtons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "pracownicy";
                manAction(manName);
            }
        });
        actionButtons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "klasy";
                manAction(manName);
            }
        });
        actionButtons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "archiwum";
                manAction(manName);
            }
        });
        actionButtons[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manName = "rachunki";
                manAction(manName);
            }
        });
    }

    private JTable manGenTable(String name) {
        String manData[][] = manager.createTable(name);
        String columnNames[] = new String[manData[0].length];
        Object rowData[][] = new Object[manData.length - 1][manData[0].length];

        for (int i = 0; i < manData.length; i++) {
            for (int j = 0; j < manData[i].length; j++) {
                if (i == 0) {
                    columnNames[j] = manData[i][j];
                } else {
                    rowData[i - 1][j] = manData[i][j];
                }
            }
        }
        return new JTable(rowData, columnNames);
    }

    private JPanel createDataPanel(String name) {
        String cols[] = manager.getColumns(name);
        int colCount = (cols != null ? cols.length : 0);
        manLabel = new JLabel[colCount];
        manData = new JTextField[colCount];

        JPanel manDataPan = new JPanel();
        manDataPan.setLayout(null);
        manDataPan.setBounds(0, 0, 340, (colCount + 1) * 20);
        manDataPan.setBackground(bgColor);

        int manX = 30, manY = 20;
        if (cols == null) {
            return manDataPan;
        }
        for (int i = 0; i < cols.length; i++) {
            manLabel[i] = new JLabel(cols[i]);
            if (i == 0)
                manY = 20;
            else
                manY = 20 * (i + 1);
            manLabel[i].setBounds(manX, manY, 150, 20);

            manData[i] = new JTextField();
            manData[i].setBounds(manLabel[i].getX() + 150, manLabel[i].getY(),
                    150, 19);
            // manData[i].setBorder(border);

            manDataPan.add(manLabel[i]);
            manDataPan.add(manData[i]);
        }
        return manDataPan;
    }

    private void manAction(String manName) {
        table = manGenTable(manName);
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
                for (int i = 0; i < manData.length; i++) {
                    manData[i].setText((String) table.getValueAt(table.getSelectedRow(), i));
                }
            }
        };
    }

    public void resizeManager(int width, int height) {
        scrollPanel.setBounds(20, 300, width - 50, height - 470);

        tableButtons[0].setBounds(scrollPanel.getX(),
                scrollPanel.getY() - 35, 150, 25);
        for (int i = 1; i < tableButtons.length; i++) {
            tableButtons[i].setBounds(tableButtons[i - 1].getX() + 160,
                    scrollPanel.getY() - 35, 150, 25);
        }
        tableButtons[1].setBounds(tableButtons[0].getX() + 160,
                scrollPanel.getY() - 35, 150, 25);
        tableButtons[2].setBounds(tableButtons[1].getX() + 160,
                scrollPanel.getY() - 35, 150, 25);
        tableButtons[3].setBounds(tableButtons[2].getX() + 160,
                scrollPanel.getY() - 35, 150, 25);

        int tmp, manX = tableButtons[2].getX(), manY = 20;
        tmp = manX;
        actionButtons[0].setBounds(manX, manY, 150, 30);
        for (int i = 1; i < actionButtons.length; i++) {
            manX += 160;
            if (i % 2 == 0) {
                manX = tmp;
                manY += 31;
            }
            actionButtons[i].setBounds(manX, manY, 150, 30);
        }

        news.setBounds(20, scrollPanel.getY() + scrollPanel.getHeight()
                + 10, width - 50, 100);
    }

    @Override
    public void setSize(int width, int height) {
        resizeManager(width, height);
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
}
