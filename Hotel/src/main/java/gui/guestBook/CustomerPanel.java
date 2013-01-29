package gui.guestBook;

import common.adapter.MouseListenerAdapter;
import dto.SimpleNameData;
import dto.cantor.CustomerData;
import service.GuestBook;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

class CustomerPanel extends JPanel {

    private final GuestBook guestBook;

    private JLabel[] clientLabel;

    private JTextField[] clientData;

    private final JTextArea clientNotes = new JTextArea();

    private final JTable[] table = new JTable[3];

    private JScrollPane dataTableScrollPane;

    private JScrollPane reservationTableScrollPane;

    private JScrollPane serviceTableScrollPane;

    private JTable reservationTable;

    private JTable serviceTable;

    private JTable dataTable;

    private JButton searchButton;

    private JButton cleanButton;

    private JButton updateButton;


    private final Color bgColor = new Color(224, 230, 233);

    private MouseListener tableML;

    private MouseListener table2ML;

    public CustomerPanel(GuestBook guestBook) {
        this.guestBook = guestBook;
        create();
        addEvents();
    }

    private void create() {
        Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));

        setBounds(0, 20, 1200, 580);
        setBackground(bgColor);
        setLayout(null);

        List<SimpleNameData> columns = guestBook.getLabels("klienci");
        Form clientForm = Factory.createClientForm(this, columns);
        clientLabel = clientForm.getClientLabels();
        clientData = clientForm.getClientData();
        JButton[] buttons = Factory.createButtons(this);
        searchButton = buttons[0];
        updateButton = buttons[1];
        cleanButton = buttons[2];

        dataTable = guestBook.createTable("klienci", "", CustomerData.class);
        dataTable.addMouseListener(tableML);

        dataTableScrollPane = new JScrollPane(dataTable);
        dataTableScrollPane.setBorder(border);
        dataTableScrollPane.setBounds(0, 380, 1200, 200);
        add(dataTableScrollPane);

        reservationTableScrollPane = new JScrollPane();
        reservationTableScrollPane.setBounds(0, 380, 1200, 200);
        reservationTableScrollPane.setBorder(border);
        reservationTableScrollPane.setVisible(false);
        add(reservationTableScrollPane);

        serviceTableScrollPane = new JScrollPane();
        serviceTableScrollPane.setBounds(0, 380, 1200, 200);
        serviceTableScrollPane.setBorder(border);
        serviceTableScrollPane.setVisible(false);
        add(serviceTableScrollPane);

        setVisible(true);
    }

    private void addEvents() {
        tableML = new MouseListenerAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    reservationTable = guestBook.createTable("rezerwacje", "where IDK_PESEL=" + dataTable.getValueAt(dataTable.getSelectedRow(), 0), CustomerData.class);
                    reservationTable.addMouseListener(table2ML);
                    reservationTableScrollPane.setViewportView(reservationTable);
                    add(reservationTableScrollPane);

                    for (int i = 0; i < 11; i++) {
                        if (i < 10) {
                            clientData[i].setText((String) dataTable.getValueAt(dataTable.getSelectedRow(), i));
                        } else {
                            clientNotes.setText((String) dataTable.getValueAt(dataTable.getSelectedRow(), i));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        table2ML = new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                serviceTable = guestBook.createTable("uslugi", ", rekreacja where rekreacja.id_rez =" + reservationTable.getValueAt(reservationTable.getSelectedRow(), 0) + " and rekreacja.id_uslugi = uslugi.id_uslugi", CustomerData.class);
                serviceTableScrollPane.setViewportView(serviceTable);
                add(serviceTableScrollPane);
                clientLabel[11] = new JLabel("US�UGI");
                clientLabel[11].setBounds(510, 21, 100, 20);
                add(clientLabel[11]);
            }
        };

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataTable = guestBook.createTable("klienci", "", CustomerData.class);
                dataTable.addMouseListener(tableML);
                dataTableScrollPane.setViewportView(dataTable);
                cleanFields();
            }

            private void cleanFields() {
                clientNotes.setText("");
                for (int i = 0; i < clientData.length; i++) {
                    clientData[i].setText("");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String conditions = "";
                for (int i = 0; i < clientData.length; i++) {
                    if (!isNotClientDataEmpty(i)) {
                        if (!conditions.isEmpty()) {
                            conditions = conditions + " and ";
                        }
                        conditions = conditions + clientLabel[i].getText() + "=" + "\"" + clientData[i].getText() + "\"";
                    }
                }
                if (!conditions.isEmpty()) {
                    dataTable = guestBook.createTable("klienci", " where " + conditions, CustomerData.class);
                    dataTable.addMouseListener(tableML);
                    dataTableScrollPane.setViewportView(dataTable);
                }
            }

            private boolean isNotClientDataEmpty(int i) {
                return clientData[i].getText().isEmpty();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String l[] = new String[10];
                String d[] = new String[10];
                for (int i = 0; i < 10; i++) {
                    l[i] = clientLabel[i].getText();
                    d[i] = clientData[i].getText();
                    // clientData[i].getText());
                }
                if (guestBook.updateClientData(l, d)) {
                    dataTable = guestBook.createTable("klienci", "", CustomerData.class);
                    dataTable.addMouseListener(tableML);
                    dataTableScrollPane.setViewportView(dataTable);
                } else {
                    JOptionPane.showMessageDialog(null, "B��d aktualizacji! Sprawd� dane!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void resizeGuestBook(int width, int height) {
        dataTableScrollPane.setBounds(20, 300, width - 50, height / 2 - 190);
        reservationTableScrollPane.setBounds(20, dataTableScrollPane.getY() + dataTableScrollPane.getHeight() + 5, width - 50, height / 2 - 190);
        serviceTableScrollPane.setBounds(510, 41, width - 540, 187);
    }

    @Override
    public void setSize(int width, int height) {
        resizeGuestBook(width, height);
        super.setSize(width, height);
    }
}
