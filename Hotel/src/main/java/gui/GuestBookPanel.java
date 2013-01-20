package gui;

import dto.SimpleNameData;
import org.apache.log4j.Logger;
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
public class GuestBookPanel extends JPanel {

    private static final Logger log = Logger.getLogger(GuestBookPanel.class);

    private static final long serialVersionUID = 1L;

    private final GuestBook guestBook;

    private JPanel panelCo;
    private final JLabel[] gueClientLabel = new JLabel[12];
    private final JTextField[] gueClientData = new JTextField[12];
    private final JTextArea gueClientNotes = new JTextArea();
    private final JTable[] gueTable = new JTable[3];
    private final JScrollPane[] gueScrollPane = new JScrollPane[3];
    private final JButton[] buttons = new JButton[4];
    private final Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));
    private final Color bgColor = new Color(224, 230, 233);
    private final Color buttonColor = new Color(174, 205, 214);
    private MouseListener gueTableMLCl;
    private MouseListener gueTableMLCo;
    private MouseListener gueTable2MLCl;
    private MouseListener gueTable2MLCo;

    public GuestBookPanel(GuestBook guestBook) {
        this.guestBook = guestBook;
        create();
        addEvents();
    }


    private void create() {

        setBounds(0, 0, getWidth(), getHeight());
        setBackground(bgColor);
        setLayout(null);
        setVisible(true);


        int i = 0;
        List<SimpleNameData> columns = guestBook.getLabels("klienci");
        for (SimpleNameData column : columns) {
            gueClientLabel[i] = new JLabel(column.getName());
            gueClientLabel[i].setBounds(30, (i + 1) * 21, 160, 20);
            gueClientData[i] = new JTextField();
            gueClientData[i].setBounds(150, (i + 1) * 21, 150, 18);
            gueClientData[i].setBorder(border);
            add(gueClientData[i]);
            add(gueClientLabel[i]);
            i++;
        }

        add(gueClientNotes);

        String[] labels = {"Szukaj", "Aktualizuj", "Firmy", "Wyczyść"};

        for (i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            buttons[i].setBounds(20 + i * 140, 280, 120, 25);
            buttons[i].setBackground(buttonColor);
            buttons[i].setText(labels[i]);
            add(buttons[i]);
        }

        gueTable[0] = guestBook.createTable("klienci", "");
        gueTable[0].addMouseListener(gueTableMLCl);

        gueScrollPane[0] = new JScrollPane(gueTable[0]);
        gueScrollPane[0].setBorder(border);

        gueScrollPane[1] = new JScrollPane();
        gueScrollPane[1].setBorder(border);
        gueScrollPane[2] = new JScrollPane();
        gueScrollPane[2].setBorder(border);

        add(gueScrollPane[0]);
    }

    private void addEventsForButtons() {
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String s2 = "";
                for (int i = 0; i < 11; i++) {
                    if (i < 10 && !gueClientData[i].getText().isEmpty()) {
                        if (!s2.isEmpty()) {
                            s2 = s2 + " and ";
                        }
                        s2 = s2 + gueClientLabel[i].getText() + "=" + "\"" + gueClientData[i].getText() + "\"";
                    } else if (i >= 10 && !gueClientNotes.getText().isEmpty()) {
                        if (!s2.isEmpty()) {
                            s2 = s2 + " and ";
                        }
                        s2 = s2 + gueClientLabel[i].getText() + "=" + "\"" + gueClientNotes.getText() + "\"";
                    }
                }
                if (!s2.isEmpty()) {
                    gueTable[0] = guestBook.createTable("klienci", " where " + s2);
                    gueTable[0].addMouseListener(gueTableMLCl);
                    gueScrollPane[0].setViewportView(gueTable[0]);
                }
            }
        });
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String l[] = new String[10];
                String d[] = new String[10];
                for (int i = 0; i < 10; i++) {
                    l[i] = gueClientLabel[i].getText();
                    d[i] = gueClientData[i].getText();
                    // log.info(gueClientLabel[i].getText() + " " +
                    // gueClientData[i].getText());
                }
                if (guestBook.updateClientData(l, d)) {
                    gueTable[0] = guestBook.createTable("klienci", "");
                    gueTable[0].addMouseListener(gueTableMLCl);
                    gueScrollPane[0].setViewportView(gueTable[0]);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "B��d aktualizacji! Sprawd� dane!", "UWAGA!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
//				jtp.remove(guePanelCl);
                createGuestBookCo();
//				jtp.addTab("Ksi�ga Go�ci", panelCo);
//				jtp.setSelectedIndex(jtp.getComponentCount() - 1);
                resizeGuestBook(getWidth(), getHeight());
            }
        });
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    gueClientData[i].setText("");
                    gueClientNotes.setText("");
                    gueTable[0] = guestBook.createTable("klienci", "");
                    gueTable[0].addMouseListener(gueTableMLCl);
                    gueScrollPane[0].setViewportView(gueTable[0]);
                }
            }
        });
    }

    private void createGuestBookCo() {
        panelCo = new JPanel();
        panelCo.setBounds(0, 0, getWidth(), getHeight());
        panelCo.setBackground(bgColor);
        panelCo.setLayout(null);
        int i = 0;
        List<SimpleNameData> firms = guestBook.getLabels("firmy");
        for (SimpleNameData columns : firms) {
            if (i < 10) {
                gueClientLabel[i] = new JLabel(columns.getName());
                gueClientLabel[i].setBounds(30, (i + 1) * 21, 150, 20);

                gueClientData[i] = new JTextField();
                gueClientData[i].setBounds(140, (i + 1) * 21, 150, 18);
                gueClientData[i].setBorder(border);

                panelCo.add(gueClientData[i]);
            } else {
                gueClientLabel[i] = new JLabel(columns.getName());
                gueClientLabel[i].setBounds(300, 21, 70, 20);
            }
            panelCo.add(gueClientLabel[i]);
            i++;
        }

        gueClientNotes.setBorder(border);
        gueClientNotes.setBounds(gueClientLabel[10].getX(), 41, 200, 187);

        panelCo.add(gueClientNotes);

        for (i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            if (i == 0)
                buttons[i].setBounds(20, 250, 100, 25);
            else
                buttons[i].setBounds(buttons[i - 1].getX() + 110, 250, 100,
                        25);
            buttons[i].setBackground(buttonColor);
            panelCo.add(buttons[i]);
        }
        buttons[0].setText("Szukaj");
        buttons[1].setText("Aktualizuj");
        buttons[2].setText("Klienci");
        buttons[3].setText("Wyczy��");

        gueTable[0] = guestBook.createTable("firmy", "");
        gueTable[0].addMouseListener(gueTableMLCo);

        gueScrollPane[0] = new JScrollPane(gueTable[0]);
        gueScrollPane[0].setBorder(border);

        gueScrollPane[1] = new JScrollPane();
        gueScrollPane[1].setBorder(border);
        gueScrollPane[2] = new JScrollPane();
        gueScrollPane[2].setBorder(border);

        panelCo.add(gueScrollPane[0]);

        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String s2 = "";
                for (int i = 0; i < 11; i++) {
                    if (i < 10 && !gueClientData[i].getText().isEmpty()) {
                        if (!s2.isEmpty()) {
                            s2 = s2 + " and ";
                        }
                        s2 = s2 + gueClientLabel[i].getText() + "=" + "\""
                                + gueClientData[i].getText() + "\"";
                    } else if (i >= 10 && !gueClientNotes.getText().isEmpty()) {
                        if (!s2.isEmpty()) {
                            s2 = s2 + " and ";
                        }
                        s2 = s2 + gueClientLabel[i].getText() + "=" + "\""
                                + gueClientNotes.getText() + "\"";
                    }
                }
                if (!s2.isEmpty()) {
                    gueTable[0] = guestBook.createTable("firmy", " where " + s2);
                    gueTable[0].addMouseListener(gueTableMLCo);
                    gueScrollPane[0].setViewportView(gueTable[0]);
                }
            }
        });
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String l[] = new String[10];
                String d[] = new String[10];
                for (int i = 0; i < 10; i++) {
                    l[i] = gueClientLabel[i].getText();
                    d[i] = gueClientData[i].getText();
                    // log.info(gueClientLabel[i].getText() + " " +
                    // gueClientData[i].getText());
                }
                if (guestBook.updateClientData(l, d)) {
                    gueTable[0] = guestBook.createTable("firmy", "");
                    gueTable[0].addMouseListener(gueTableMLCo);
                    gueScrollPane[0].setViewportView(gueTable[0]);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "B��d aktualizacji! Sprawd� dane!", "UWAGA!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
//				jtp.remove(panelCo);
//				createGuestBook();
//				jtp.addTab("Ksi�ga Go�ci", this);
//				jtp.setSelectedIndex(jtp.getComponentCount() - 1);
                resizeGuestBook(getWidth(), getHeight());
            }
        });
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    gueClientData[i].setText("");
                    gueClientNotes.setText("");
                    gueTable[0] = guestBook.createTable("firmy", "");
                    gueTable[0].addMouseListener(gueTableMLCo);
                    gueScrollPane[0].setViewportView(gueTable[0]);
                }
            }
        });
    }


    private void addEvents() {
        addEventsForButtons();


        gueTableMLCl = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    gueTable[1] = guestBook.createTable(
                            "rezerwacje",
                            "where IDK_PESEL="
                                    + gueTable[0].getValueAt(
                                    gueTable[0].getSelectedRow(), 0));
                    gueTable[1].addMouseListener(gueTable2MLCl);
                    gueScrollPane[1].setViewportView(gueTable[1]);
                    add(gueScrollPane[1]);

                    for (int i = 0; i < 11; i++) {
                        if (i < 10)
                            gueClientData[i].setText((String) gueTable[0]
                                    .getValueAt(gueTable[0].getSelectedRow(), i));
                        else
                            gueClientNotes.setText((String) gueTable[0].getValueAt(
                                    gueTable[0].getSelectedRow(), i));
                    }
                } catch (Exception e) {
                    log.info("Brak danych!");
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
        gueTableMLCo = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                gueTable[1] = guestBook.createTable("rezerwacje", "where IDF_KRS="
                        + gueTable[0].getValueAt(gueTable[0].getSelectedRow(), 0));
                gueTable[1].addMouseListener(gueTable2MLCo);
                gueScrollPane[1].setViewportView(gueTable[1]);
                panelCo.add(gueScrollPane[1]);

                for (int i = 0; i < 11; i++) {
                    if (i < 10)
                        gueClientData[i].setText((String) gueTable[0].getValueAt(
                                gueTable[0].getSelectedRow(), i));
                    else
                        gueClientNotes.setText((String) gueTable[0].getValueAt(
                                gueTable[0].getSelectedRow(), i));
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
        gueTable2MLCl = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                gueTable[2] = guestBook.createTable(
                        "uslugi",
                        ", rekreacja where rekreacja.id_rez ="
                                + gueTable[1].getValueAt(
                                gueTable[1].getSelectedRow(), 0)
                                + " and rekreacja.id_uslugi = uslugi.id_uslugi");
                gueScrollPane[2].setViewportView(gueTable[2]);
                add(gueScrollPane[2]);
                gueClientLabel[11] = new JLabel("US�UGI");
                gueClientLabel[11].setBounds(510, 21, 100, 20);
                add(gueClientLabel[11]);
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
        gueTable2MLCo = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                gueTable[2] = guestBook.createTable(
                        "uslugi",
                        ", rekreacja where rekreacja.id_rez ="
                                + gueTable[1].getValueAt(
                                gueTable[1].getSelectedRow(), 0)
                                + " and rekreacja.id_uslugi = uslugi.id_uslugi");
                gueScrollPane[2].setViewportView(gueTable[2]);
                panelCo.add(gueScrollPane[2]);
                gueClientLabel[11] = new JLabel("US�UGI");
                gueClientLabel[11].setBounds(510, 21, 100, 20);
                add(gueClientLabel[11]);
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
        gueScrollPane[0].setBounds(20, 300, width - 50,
                height / 2 - 190);
        gueScrollPane[1].setBounds(20, gueScrollPane[0].getY()
                + gueScrollPane[0].getHeight() + 5, width - 50,
                height / 2 - 190);
        gueScrollPane[2].setBounds(510, 41, width - 540, 187);
    }

    @Override
    public void setSize(int width, int height) {
        resizeGuestBook(width, height);
        super.setSize(width, height);
    }
}
