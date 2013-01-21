package gui.guestBook;

import dto.SimpleNameData;
import org.springframework.stereotype.Component;
import service.GuestBook;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

@Component
public class CompanyPanel extends JPanel {

    private final GuestBook guestBook;


    private final JLabel[] clientLabel = new JLabel[13];
    private final JTextField[] clientData = new JTextField[13];
    private final JTextArea clientNotes = new JTextArea();
    private final JTable[] table = new JTable[3];
    private final JScrollPane[] scrollPane = new JScrollPane[3];
    private final JButton[] buttons = new JButton[4];
    private final Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));
    private final Color bgColor = new Color(224, 230, 233);
    private final Color buttonColor = new Color(174, 205, 214);
    private MouseListener tableMLCustomer;
    private MouseListener tableMLCompany;
    private MouseListener table2MLCustomer;
    private MouseListener table2MLCompany;

    public CompanyPanel(GuestBook guestBook) {
        this.guestBook = guestBook;
        create();
        addEvents();
    }


    private void create() {
        setBounds(0, 0, 800, 600);
        setBackground(bgColor);
        setLayout(null);
        setVisible(true);
        int i = 0;

        List<SimpleNameData> columns = guestBook.getLabels("firmy");
        Constructorr.createClientForm(this, columns, clientLabel, clientData);
        Constructorr.createButtons(this, buttons);

        table[0] = guestBook.createTable("firmy", "");
        table[0].addMouseListener(tableMLCompany);

        scrollPane[0] = new JScrollPane(table[0]);
        scrollPane[0].setBorder(border);

        scrollPane[1] = new JScrollPane();
        scrollPane[1].setBorder(border);
        scrollPane[2] = new JScrollPane();
        scrollPane[2].setBorder(border);

        add(scrollPane[0]);
    }

    private void addEvents() {
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String s2 = "";
                for (int i = 0; i < 11; i++) {
                    if (i < 10 && !clientData[i].getText().isEmpty()) {
                        if (!s2.isEmpty()) {
                            s2 = s2 + " and ";
                        }
                        s2 = s2 + clientLabel[i].getText() + "=" + "\""
                                + clientData[i].getText() + "\"";
                    } else if (i >= 10 && !clientNotes.getText().isEmpty()) {
                        if (!s2.isEmpty()) {
                            s2 = s2 + " and ";
                        }
                        s2 = s2 + clientLabel[i].getText() + "=" + "\""
                                + clientNotes.getText() + "\"";
                    }
                }
                if (!s2.isEmpty()) {
                    table[0] = guestBook.createTable("firmy", " where " + s2);
                    table[0].addMouseListener(tableMLCompany);
                    scrollPane[0].setViewportView(table[0]);
                }
            }
        });
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String l[] = new String[10];
                String d[] = new String[10];
                for (int i = 0; i < 10; i++) {
                    l[i] = clientLabel[i].getText();
                    d[i] = clientData[i].getText();
                    // log.info(clientLabel[i].getText() + " " +
                    // clientData[i].getText());
                }
                if (guestBook.updateClientData(l, d)) {
                    table[0] = guestBook.createTable("firmy", "");
                    table[0].addMouseListener(tableMLCompany);
                    scrollPane[0].setViewportView(table[0]);
                } else {
                    JOptionPane.showMessageDialog(null, "B��d aktualizacji! Sprawd� dane!", "UWAGA!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    clientData[i].setText("");
                    clientNotes.setText("");
                    table[0] = guestBook.createTable("firmy", "");
                    table[0].addMouseListener(tableMLCompany);
                    scrollPane[0].setViewportView(table[0]);
                }
            }
        });

        addMoreEvents();
    }

    private void addMoreEvents() {
        tableMLCustomer = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    table[1] = guestBook.createTable(
                            "rezerwacje",
                            "where IDK_PESEL="
                                    + table[0].getValueAt(
                                    table[0].getSelectedRow(), 0));
                    table[1].addMouseListener(table2MLCustomer);
                    scrollPane[1].setViewportView(table[1]);
                    add(scrollPane[1]);

                    for (int i = 0; i < 11; i++) {
                        if (i < 10)
                            clientData[i].setText((String) table[0]
                                    .getValueAt(table[0].getSelectedRow(), i));
                        else
                            clientNotes.setText((String) table[0].getValueAt(
                                    table[0].getSelectedRow(), i));
                    }
                } catch (Exception e) {
//                    log.info("Brak danych!");
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        };
        tableMLCompany = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                table[1] = guestBook.createTable("rezerwacje", "where IDF_KRS="
                        + table[0].getValueAt(table[0].getSelectedRow(), 0));
                table[1].addMouseListener(table2MLCompany);
                scrollPane[1].setViewportView(table[1]);
                add(scrollPane[1]);

                for (int i = 0; i < 11; i++) {
                    if (i < 10)
                        clientData[i].setText((String) table[0].getValueAt(
                                table[0].getSelectedRow(), i));
                    else
                        clientNotes.setText((String) table[0].getValueAt(
                                table[0].getSelectedRow(), i));
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        };
        table2MLCustomer = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                table[2] = guestBook.createTable(
                        "uslugi",
                        ", rekreacja where rekreacja.id_rez ="
                                + table[1].getValueAt(
                                table[1].getSelectedRow(), 0)
                                + " and rekreacja.id_uslugi = uslugi.id_uslugi");
                scrollPane[2].setViewportView(table[2]);
                add(scrollPane[2]);
                clientLabel[11] = new JLabel("US�UGI");
                clientLabel[11].setBounds(510, 21, 100, 20);
                add(clientLabel[11]);
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }
        };
        table2MLCompany = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                table[2] = guestBook.createTable(
                        "uslugi",
                        ", rekreacja where rekreacja.id_rez ="
                                + table[1].getValueAt(
                                table[1].getSelectedRow(), 0)
                                + " and rekreacja.id_uslugi = uslugi.id_uslugi");
                scrollPane[2].setViewportView(table[2]);
                add(scrollPane[2]);
                clientLabel[11] = new JLabel("US�UGI");
                clientLabel[11].setBounds(510, 21, 100, 20);
                add(clientLabel[11]);
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }
        };
    }

    public void resizeGuestBook(int width, int height) {
        scrollPane[0].setBounds(20, 300, width - 50, height / 2 - 190);
        scrollPane[1].setBounds(20, scrollPane[0].getY() + scrollPane[0].getHeight() + 5, width - 50, height / 2 - 190);
        scrollPane[2].setBounds(510, 41, width - 540, 187);
    }

    @Override
    public void setSize(int width, int height) {
        resizeGuestBook(width, height);
        super.setSize(width, height);
    }
}
