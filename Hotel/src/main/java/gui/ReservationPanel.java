package gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.Reservation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import static validation.ValidationUtils.isNotNumber;

@Component
public class ReservationPanel extends JPanel {

    private final JTextField[] rezJta = new JTextField[11];
    private JTextField rezCenaJta = new JTextField();
    private final JLabel[] rezGuestLabel = new JLabel[11];
    private final JLabel[] rezCompLabel = new JLabel[11];
    private final JLabel[] rezRoomLabel = new JLabel[5];
    private JCheckBox rezIfExistC = new JCheckBox();
    private JCheckBox rezIfExistF = new JCheckBox();
    private final String[] rezDays = {"01", "02", "03", "04", "05", "06", "07",
            "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
            "30", "31"};
    private final String[] months = {"01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12"};
    private final String[] rezYears = {"2010", "2011", "2012", "2013", "2015",
            "2016"};
    private JLabel rezDayLabel = new JLabel();
    private final JComboBox[] rezDateBox = new JComboBox[3];
    private JComboBox rezDayBox = new JComboBox();
    private JLabel rezPrice = new JLabel();
    private final JButton[] rezButton = new JButton[5];
    private JTable rezTable = new JTable();
    private JRadioButton rezClient;
    private JRadioButton rezCompany;
    private JRadioButton rezCat1;
    private JRadioButton rezCat2;
    private JRadioButton rezCat3;
    private JTable rezClasTable;
    private JScrollPane rezClasPane;
    private JScrollPane rezRoomPane;
    private JTable rezServTable;
    private JScrollPane rezServPane;
    private String rezRoomListLabel[];
    private JList rezRoomList;
    private final JComboBox rezCatBox = new JComboBox();
    private JScrollPane rezServPane1;
    private String rezServListLabel[];
    private JList rezServList;
    private DefaultListModel listMod;
    private MouseListener rezMous;
    private final Color buttonColor = new Color(174, 205, 214);

    private final Color bgColor2 = new Color(227, 239, 243);
    private final Border border = BorderFactory.createLineBorder(new Color(60, 124,
            142));
    private final Color bgColor = new Color(224, 230, 233);

    @Autowired
    private final Reservation reservation;

    public ReservationPanel(Reservation reservation) {
        this.reservation = reservation;
        create();
        addEventa();
    }

    private static final long serialVersionUID = 1L;

    private void create() {
        setBounds(0, 0, getWidth(), getHeight());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // addComponentListener(this);
        setBackground(bgColor);
        rezClient = new JRadioButton("Klient", true);
        rezCompany = new JRadioButton("Firma", false);
        rezCat1 = new JRadioButton("Rekreacja", true);
        rezCat2 = new JRadioButton("Gastronomia", false);
        rezCat3 = new JRadioButton("Biznes", false);
        ButtonGroup rezRadioGroup = new ButtonGroup();
        ButtonGroup rezCatGroup = new ButtonGroup();
        rezRadioGroup.add(rezClient);
        rezRadioGroup.add(rezCompany);
        rezCatGroup.add(rezCat1);
        rezCatGroup.add(rezCat2);
        rezCatGroup.add(rezCat3);
        rezGuestLabel[0] = new JLabel("PESEL");
        rezGuestLabel[1] = new JLabel("Imie");
        rezGuestLabel[2] = new JLabel("Nazwisko");
        rezGuestLabel[3] = new JLabel("Wojewodztwo");
        rezGuestLabel[4] = new JLabel("Miasto");
        rezGuestLabel[5] = new JLabel("Ulica");
        rezGuestLabel[6] = new JLabel("Nr lokalu");
        rezGuestLabel[7] = new JLabel("Status");
        rezGuestLabel[8] = new JLabel("Telefon");
        rezGuestLabel[9] = new JLabel("NIP");
        rezGuestLabel[10] = new JLabel("Uwagi");

        rezCompLabel[0] = new JLabel("KRS");
        rezCompLabel[1] = new JLabel("Nazwa");
        rezCompLabel[2] = new JLabel("Wojew�dztwo");
        rezCompLabel[3] = new JLabel("Miasto");
        rezCompLabel[4] = new JLabel("Ulica");
        rezCompLabel[5] = new JLabel("Nr lokalu");
        rezCompLabel[6] = new JLabel("Status");
        rezCompLabel[7] = new JLabel("REGON");
        rezCompLabel[8] = new JLabel("NIP");
        rezCompLabel[9] = new JLabel("Telefon");
        rezCompLabel[10] = new JLabel("Uwagi");

        rezClasTable = new JTable();
        rezClasTable = reservation.createClasTable();
        if (rezClasTable == null) {
            JOptionPane.showMessageDialog(getParent(), "Brak klas pokoi",
                    "B��d", 1);
        } else {
            rezClasPane = new JScrollPane(rezClasTable);
            rezClasPane.setBorder(border);
            add(rezClasPane);
        }

        rezServTable = reservation.createServTable("");
        if (rezServTable == null) {
            JOptionPane.showMessageDialog(getParent(), "Brak us�ug", "B��d", 1);
            rezServPane = new JScrollPane(null);
            rezServPane.setBorder(border);
            add(rezServPane);
        } else {
            rezServPane = new JScrollPane(rezServTable);
            rezServPane.setBorder(border);
            add(rezServPane);
        }

        for (int i = 0; i < rezGuestLabel.length; i++) {
            rezJta[i] = new JTextField();
            rezJta[i].setBorder(border);
            add(rezGuestLabel[i]);
            add(rezJta[i]);
        }

        for (int i = 0; i < rezCompLabel.length; i++) {
            add(rezCompLabel[i]);
        }

        for (JLabel aRezCompLabel : rezCompLabel) {
            aRezCompLabel.setVisible(false);
        }
        rezRoomLabel[0] = new JLabel("Klasa pokoju");
        rezRoomLabel[1] = new JLabel("Wolne pokoje");
        rezRoomLabel[2] = new JLabel("Wybrane us�ugi");
        rezRoomLabel[3] = new JLabel("Rezerwacja od:");
        rezRoomLabel[4] = new JLabel("Wybrane us�ugi");
        rezPrice = new JLabel("��czny koszt rezerwacji");
        for (int i = 0; i < rezRoomLabel.length; i++) {
            add(rezRoomLabel[i]);
        }
        rezCenaJta = new JTextField("");
        rezCenaJta.setBorder(border);
        add(rezClient);
        add(rezCompany);
        add(rezCat1);
        add(rezCat2);
        add(rezCat3);
        add(rezPrice);
        add(rezCenaJta);

        rezButton[0] = new JButton("Sprawd�");
        rezButton[1] = new JButton("Melduj");
        rezButton[2] = new JButton("Rezerwuj");
        rezButton[3] = new JButton("Przelicz");
        for (int i = 0; i < 4; i++) {
            rezButton[i].setBackground(buttonColor);
            add(rezButton[i]);
        }

        JLabel rezDateLabel = new JLabel("Rozpocz�cie pobytu: ");
        rezDayLabel = new JLabel("Doby: ");
        add(rezDateLabel);
        add(rezDayLabel);

        rezDateBox[0] = new JComboBox(rezDays);
        rezDateBox[1] = new JComboBox(months);
        rezDateBox[2] = new JComboBox(rezYears);
        rezDayBox = new JComboBox(rezDays);
        add(rezDayBox);
        for (int j = 0; j < 3; j++) {
            add(rezDateBox[j]);
        }
        rezRoomListLabel = reservation.createRoomList(0, "2010-01-01", "2011-01-01");
        rezRoomList = new JList(rezRoomListLabel);
        if (rezServListLabel == null) {
            rezServListLabel = new String[30];
            rezServListLabel[0] = "Brak wybranych us�ug";
        }
        listMod = new DefaultListModel();
        listMod.addElement("Wybierz us�ugi");
        rezServList = new JList(listMod);
        listMod.removeElement("Wybierz us�ugi");
        rezRoomPane = new JScrollPane(rezRoomList);
        rezRoomPane.setBorder(border);
        add(rezRoomPane);
        add(rezCatBox);
        rezServPane1 = new JScrollPane(rezServList);
        rezServPane1.setBorder(border);
        rezIfExistC = new JCheckBox("Klient istnieje");
        rezIfExistF = new JCheckBox("Firma istnieje");
        add(rezIfExistC);
        add(rezIfExistF);
        add(rezServPane1);
        setVisible(true);
        rezMous = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                listMod.addElement(rezServTable.getValueAt(
                        rezServTable.getSelectedRow(), 1).toString());
                rezServPane1.setViewportView(rezServList);
                rezServPane1.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        };
    }

    private void addEventa() {

        rezButton[0].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (rezJta[0].getText().isEmpty())
                    JOptionPane.showMessageDialog(getParent(),
                            "Podaj parametry", "B��d", 0);
                else {
                    if (rezClient.isSelected()) {
                        if (reservation.isNotPesel(rezJta[0].getText()))
                            JOptionPane.showMessageDialog(getParent(),
                                    "�le wprowadzono PESEL", "B��d", 0);
                        else {
                            rezTable = reservation.createTable(true,
                                    Long.parseLong(rezJta[0].getText()));
                            if (rezTable == null) {
                                JOptionPane.showMessageDialog(getParent(),
                                        "Nie ma takiego klienta", "Informacja",
                                        1);
                                rezJta[0].setText("");
                                rezJta[1].setText("");
                                rezJta[2].setText("");
                                rezJta[3].setText("");
                                rezJta[4].setText("");
                                rezJta[5].setText("");
                                rezJta[6].setText("");
                                rezJta[7].setText("");
                                rezJta[8].setText("");
                                rezJta[9].setText("");
                                rezJta[10].setText("");

                            } else {
                                rezJta[1].setText((String) rezTable.getValueAt(
                                        0, 1));
                                rezJta[2].setText((String) rezTable.getValueAt(
                                        0, 2));
                                rezJta[3].setText((String) rezTable.getValueAt(
                                        0, 3));
                                rezJta[4].setText((String) rezTable.getValueAt(
                                        0, 4));
                                rezJta[5].setText((String) rezTable.getValueAt(
                                        0, 5));
                                rezJta[6].setText((String) rezTable.getValueAt(
                                        0, 6));
                                rezJta[7].setText((String) rezTable.getValueAt(
                                        0, 7));
                                rezJta[8].setText((String) rezTable.getValueAt(
                                        0, 8));
                                rezJta[9].setText((String) rezTable.getValueAt(
                                        0, 9));
                                rezJta[10].setText((String) rezTable
                                        .getValueAt(0, 10));
                            }
                        }
                    } else if (reservation.isNotKRS(rezJta[0].getText()))
                        JOptionPane.showMessageDialog(getParent(),
                                "�le wprowadzono KRS", "B��d", 0);
                    else {
                        rezTable = reservation.createTable(false,
                                Long.parseLong(rezJta[0].getText()));
                        if (rezTable == null)
                            JOptionPane.showMessageDialog(getParent(),
                                    "Nie ma takiej firmy", "Informacja", 1);
                        else {
                            rezJta[1].setText((String) rezTable
                                    .getValueAt(0, 1));
                            rezJta[2].setText((String) rezTable
                                    .getValueAt(0, 2));
                            rezJta[3].setText((String) rezTable
                                    .getValueAt(0, 3));
                            rezJta[4].setText((String) rezTable
                                    .getValueAt(0, 4));
                            rezJta[5].setText((String) rezTable
                                    .getValueAt(0, 5));
                            rezJta[6].setText((String) rezTable
                                    .getValueAt(0, 6));
                            rezJta[7].setText((String) rezTable
                                    .getValueAt(0, 7));
                            rezJta[8].setText((String) rezTable
                                    .getValueAt(0, 8));
                            rezJta[9].setText((String) rezTable
                                    .getValueAt(0, 9));
                            rezJta[10].setText((String) rezTable.getValueAt(0,
                                    10));
                        }
                    }
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
        rezClient.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < rezCompLabel.length; i++) {
                    rezCompLabel[i].setVisible(false);
                    rezGuestLabel[i].setVisible(true);
                    rezIfExistC.setVisible(true);
                    rezIfExistF.setSelected(false);
                    rezIfExistF.setVisible(false);
                    rezJta[0].setText("");
                    rezJta[1].setText("");
                    rezJta[2].setText("");
                    rezJta[3].setText("");
                    rezJta[4].setText("");
                    rezJta[5].setText("");
                    rezJta[6].setText("");
                    rezJta[7].setText("");
                    rezJta[8].setText("");
                    rezJta[9].setText("");
                    rezJta[10].setText("");
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        rezCat1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                rezServTable = reservation.createServTable("rekreacja");
                rezServTable.addMouseListener(rezMous);
                if (rezServTable == null) {
                    JOptionPane.showMessageDialog(getParent(), "Brak us�ug",
                            "B��d", 1);
                } else {
                    rezServPane.setViewportView(rezServTable);
                    rezServPane.repaint();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        rezCat2.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                rezServTable = reservation.createServTable("gastronomia");
                rezServTable.addMouseListener(rezMous);
                if (rezServTable == null) {
                    JOptionPane.showMessageDialog(getParent(), "Brak us�ug",
                            "B��d", 1);
                } else {
                    rezServPane.setViewportView(rezServTable);
                    rezServPane.repaint();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        rezCat3.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                rezServTable = reservation.createServTable("biznes");
                rezServTable.addMouseListener(rezMous);
                if (rezServTable == null) {
                    JOptionPane.showMessageDialog(getParent(), "Brak us�ug",
                            "B��d", 1);
                } else {
                    rezServPane.setViewportView(rezServTable);
                    rezServPane.repaint();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        rezCompany.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < rezCompLabel.length; i++) {
                    rezGuestLabel[i].setVisible(false);
                    rezCompLabel[i].setVisible(true);
                    rezIfExistC.setSelected(false);
                    rezIfExistC.setVisible(false);
                    rezIfExistF.setVisible(true);
                    rezJta[0].setText("");
                    rezJta[1].setText("");
                    rezJta[2].setText("");
                    rezJta[3].setText("");
                    rezJta[4].setText("");
                    rezJta[5].setText("");
                    rezJta[6].setText("");
                    rezJta[7].setText("");
                    rezJta[8].setText("");
                    rezJta[9].setText("");
                    rezJta[10].setText("");
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        rezClasTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    if ((rezRoomListLabel = reservation.createRoomList(
                            Integer.parseInt(rezClasTable.getValueAt(
                                    rezClasTable.getSelectedRow(), 0)
                                    .toString()),
                            rezYears[rezDateBox[2].getSelectedIndex()]
                                    .toString()
                                    + "-"
                                    + months[rezDateBox[1].getSelectedIndex()]
                                    .toString()
                                    + "-"
                                    + rezDays[rezDateBox[0].getSelectedIndex()]
                                    .toString(), reservation.addDate(
                            rezYears[rezDateBox[2].getSelectedIndex()]
                                    .toString()
                                    + "-"
                                    + months[rezDateBox[1]
                                    .getSelectedIndex()]
                                    .toString()
                                    + "-"
                                    + rezDays[rezDateBox[0]
                                    .getSelectedIndex()]
                                    .toString(), rezDayBox
                            .getSelectedIndex() + 1))) == null) {
                        rezRoomListLabel = new String[1];
                        rezRoomListLabel[0] = "Brak wolnych pokoi";
                    } else {
                        rezRoomList = new JList(rezRoomListLabel);
                        rezRoomPane.setViewportView(rezRoomList);
                        rezRoomPane.repaint();
                    }
                } catch (NumberFormatException e1) {
                } catch (ParseException e1) {
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        rezServTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                listMod.addElement(rezServTable.getValueAt(
                        rezServTable.getSelectedRow(), 1).toString());
                rezServPane1.setViewportView(rezServList);
                rezServPane1.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        rezServList.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (listMod.isEmpty()) {

                } else {
                    listMod.removeElementAt(rezServList.getSelectedIndex());
                    rezServPane1.setViewportView(rezServList);
                    rezServPane1.repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        rezButton[3].addMouseListener(new MouseListener() {
            int monthInt;
            String monthStr;
            String[] servTab;

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (rezRoomList.getSelectedIndex() < 0)
                    JOptionPane.showMessageDialog(getParent(),
                            "Nie wybrano pokoju", "B��d", 1);
                else {
                    monthInt = rezDateBox[1].getSelectedIndex() + 1;
                    if (monthInt < 10)
                        monthStr = "0" + String.valueOf(monthInt);
                    else
                        monthStr = String.valueOf(monthInt);

                    try {
                        if (reservation
                                .isAfter((rezDateBox[2].getSelectedItem()
                                        .toString() + "-" + monthStr + "-" + rezDateBox[0]
                                        .getSelectedItem().toString()))
                                && reservation.isDate((rezDateBox[2]
                                .getSelectedItem().toString()
                                + "-"
                                + monthStr + "-" + rezDateBox[0]
                                .getSelectedItem().toString()))) {
                            servTab = new String[rezServList.getModel()
                                    .getSize()];
                            for (int i = 0; i < rezServList.getModel()
                                    .getSize(); i++) {
                                rezServList.setSelectedIndex(i);
                                servTab[i] = rezServList.getSelectedValue()
                                        .toString();

                            }
                            if (rezRoomList.getSelectedValue().toString() == "Brak danych")
                                JOptionPane.showMessageDialog(getParent(),
                                        "Niepoprawna data", "B��d", 0);
                            else
                                rezCenaJta.setText(String.valueOf(reservation.calculate(
                                        Integer.parseInt(rezRoomList
                                                .getSelectedValue().toString()),
                                        rezDayBox.getSelectedIndex() + 1,
                                        rezServList.getModel().getSize(),
                                        servTab)));
                            rezCenaJta.setEditable(false);
                        } else
                            JOptionPane.showMessageDialog(getParent(),
                                    "Niepoprawna data", "B��d", 0);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        rezButton[2].addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                boolean err = true;
                String ch1;
                String ch2;
                if (rezClient.isSelected()) {
                    for (int k = 0; k < 11; k++) {
                        if (k == 5 || k == 10) {

                        } else if (rezJta[k].getText().isEmpty()) {
                            err = false;
                        }
                    }
                    if (!err)
                        JOptionPane.showMessageDialog(getParent(),
                                "Wprowad� kompletne dane", "B��d", 1);
                    else {
                        if (reservation.isNotPesel(rezJta[0].getText())) {
                            JOptionPane.showMessageDialog(getParent(),
                                    "Niepoprawny PESEL", "B��d", 1);
                            err = false;
                        } else {
                            if (isNotNumber(rezJta[6].getText())) {
                                JOptionPane.showMessageDialog(getParent(),
                                        "Niepoprawny numer lokalu", "B��d", 1);
                                err = false;
                            } else {
                                if (isNotNumber(rezJta[8].getText())) {
                                    JOptionPane.showMessageDialog(getParent(),
                                            "Niepoprawny numer telefonu", "B��d", 1);
                                    err = false;
                                } else if (reservation.isValidNip(rezJta[9].getText())) {
                                    JOptionPane.showMessageDialog(getParent(),
                                            "Niepoprawny NIP", "B��d", 1);
                                    err = false;
                                } else {
                                    if (rezCenaJta.getText().isEmpty()) {
                                        JOptionPane.showMessageDialog(getParent(),
                                                "Nie przeliczono pobytu", "B��d", 1);
                                        err = false;
                                    }
                                }
                            }
                        }
                    }
                    if (err) {
                        if (rezJta[5].getText().isEmpty())
                            ch1 = "";
                        else
                            ch1 = rezJta[5].getText();
                        if (rezJta[10].getText().isEmpty())
                            ch2 = "";
                        else
                            ch2 = rezJta[10].getText();

                        if (!rezIfExistC.isSelected()) {

                            if (reservation.addClient(
                                    Long.parseLong(rezJta[0].getText()),
                                    rezJta[1].getText(), rezJta[2].getText(),
                                    rezJta[3].getText(), rezJta[4].getText(),
                                    ch1, Integer.parseInt(rezJta[6].getText()),
                                    rezJta[7].getText(),
                                    Long.parseLong(rezJta[8].getText()),
                                    Long.parseLong(rezJta[9].getText()), ch2)) {
                                JOptionPane.showMessageDialog(getParent(),
                                        "Dodano Klienta", "Informacja", 1);
                                try {
                                    reservation.doRezerv(
                                            true,
                                            Long.parseLong(rezJta[0].getText()),
                                            Integer.parseInt(rezRoomList
                                                    .getSelectedValue()
                                                    .toString()),

                                            rezYears[rezDateBox[2]
                                                    .getSelectedIndex()]
                                                    .toString()
                                                    + "-"
                                                    + months[rezDateBox[1]
                                                    .getSelectedIndex()]
                                                    .toString()
                                                    + "-"
                                                    + rezDays[rezDateBox[0]
                                                    .getSelectedIndex()]
                                                    .toString(),
                                            reservation.addDate(
                                                    rezYears[rezDateBox[2]
                                                            .getSelectedIndex()]
                                                            .toString()
                                                            + "-"
                                                            + months[rezDateBox[1]
                                                            .getSelectedIndex()]
                                                            .toString()
                                                            + "-"
                                                            + rezDays[rezDateBox[0]
                                                            .getSelectedIndex()]
                                                            .toString(),
                                                    rezDayBox
                                                            .getSelectedIndex() + 1));
                                    rezRoomListLabel = reservation.createRoomList(0,
                                            "2010-01-01", "2011-01-01");
                                    rezRoomList = new JList(rezRoomListLabel);
                                    rezRoomPane.setViewportView(rezRoomList);
                                    rezRoomPane.repaint();
                                } catch (ParseException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(getParent(),
                                        "Nie zaznaczono,�e klient istnieje",
                                        "Klient istnieje", 1);
                            }
                        } else {
                            try {
                                if (reservation.doRezerv(
                                        true,
                                        Long.parseLong(rezJta[0].getText()),
                                        Integer.parseInt(rezRoomList
                                                .getSelectedValue().toString()),
                                        rezYears[rezDateBox[2]
                                                .getSelectedIndex()].toString()
                                                + "-"
                                                + months[rezDateBox[1]
                                                .getSelectedIndex()]
                                                .toString()
                                                + "-"
                                                + rezDays[rezDateBox[0]
                                                .getSelectedIndex()]
                                                .toString(),
                                        reservation.addDate(
                                                rezYears[rezDateBox[2]
                                                        .getSelectedIndex()]
                                                        .toString()
                                                        + "-"
                                                        + months[rezDateBox[1]
                                                        .getSelectedIndex()]
                                                        .toString()
                                                        + "-"
                                                        + rezDays[rezDateBox[0]
                                                        .getSelectedIndex()]
                                                        .toString(),
                                                rezDayBox.getSelectedIndex() + 1)))
                                    JOptionPane.showMessageDialog(getParent(),
                                            "Nie dokonano rezerwacji",
                                            "Brak klienta", 1);
                                else
                                    JOptionPane.showMessageDialog(getParent(),
                                            "Dokonano rezerwacji",
                                            "Informacja", 1);
                                rezRoomListLabel = reservation.createRoomList(0,
                                        "2010-01-01", "2011-01-01");
                                rezRoomList = new JList(rezRoomListLabel);
                                rezRoomPane.setViewportView(rezRoomList);
                                rezRoomPane.repaint();

                            } catch (NumberFormatException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (ParseException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    }
                } else if (rezCompany.isSelected()) {
                    for (int k = 0; k < 11; k++) {
                        if (k == 4 || k == 10) {

                        } else if (rezJta[k].getText().isEmpty()) {
                            err = false;
                        }
                    }
                    if (!err)
                        JOptionPane.showMessageDialog(getParent(),
                                "Wprowad� kompletne dane", "B��d", 0);
                    else {
                        if (reservation.isNotKRS(rezJta[0].getText())) {
                            JOptionPane.showMessageDialog(getParent(),
                                    "Niepoprawny KRS", "B��d", 0);
                            err = false;
                        } else {
                            if (isNotNumber(rezJta[5].getText())) {
                                JOptionPane.showMessageDialog(getParent(),
                                        "Niepoprawny numer lokalu", "B��d", 0);
                                err = false;
                            } else {
                                if (isNotNumber(rezJta[8].getText())) {
                                    JOptionPane.showMessageDialog(getParent(),
                                            "Niepoprawny numer telefonu", "B��d", 0);
                                    err = false;
                                } else {
                                    if (isNotNumber(rezJta[7].getText())) {
                                        JOptionPane.showMessageDialog(getParent(),
                                                "Niepoprawny REGON", "B��d", 0);
                                        err = false;
                                    } else {
                                        if (isNotNumber(rezJta[8].getText())) {
                                            JOptionPane.showMessageDialog(getParent(),
                                                    "Niepoprawny NIP", "B��d", 0);
                                            err = false;
                                        } else if (reservation.isValidNip(rezJta[9].getText())) {
                                            JOptionPane.showMessageDialog(getParent(),
                                                    "Niepoprawny numer telefonu", "B��d", 0);
                                            err = false;
                                        } else {
                                            if (rezCenaJta.getText().isEmpty()) {
                                                JOptionPane.showMessageDialog(getParent(),
                                                        "Nie przeliczono pobytu", "B��d", 0);
                                                err = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (err) {
                        if (rezJta[4].getText().isEmpty())
                            ch1 = "";
                        else
                            ch1 = rezJta[4].getText();
                        if (rezJta[10].getText().isEmpty())
                            ch2 = "";
                        else
                            ch2 = rezJta[10].getText();

                        if (!rezIfExistF.isSelected()) {

                            if (reservation.addComp(
                                    Long.parseLong(rezJta[0].getText()),
                                    rezJta[1].getText(), rezJta[2].getText(),
                                    rezJta[3].getText(), ch1,
                                    Integer.parseInt(rezJta[5].getText()),
                                    rezJta[6].getText(),
                                    Long.parseLong(rezJta[7].getText()),
                                    Long.parseLong(rezJta[8].getText()),
                                    Long.parseLong(rezJta[8].getText()), ch2)) {
                                JOptionPane.showMessageDialog(getParent(),
                                        "Dodano Firme", "Informacja", 1);
                                try {
                                    reservation.doRezerv(
                                            false,
                                            Long.parseLong(rezJta[0].getText()),
                                            Integer.parseInt(rezRoomList
                                                    .getSelectedValue()
                                                    .toString()),
                                            rezYears[rezDateBox[2]
                                                    .getSelectedIndex()]
                                                    .toString()
                                                    + "-"
                                                    + months[rezDateBox[1]
                                                    .getSelectedIndex()]
                                                    .toString()
                                                    + "-"
                                                    + rezDays[rezDateBox[0]
                                                    .getSelectedIndex()]
                                                    .toString(),
                                            reservation.addDate(
                                                    rezYears[rezDateBox[2]
                                                            .getSelectedIndex()]
                                                            .toString()
                                                            + "-"
                                                            + months[rezDateBox[1]
                                                            .getSelectedIndex()]
                                                            .toString()
                                                            + "-"
                                                            + rezDays[rezDateBox[0]
                                                            .getSelectedIndex()]
                                                            .toString(),
                                                    rezDayBox
                                                            .getSelectedIndex() + 1));
                                } catch (ParseException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(getParent(),
                                        "Nie zaznaczono,�e firma istnieje",
                                        "Firma istnieje", 1);
                            }
                        } else {
                            try {
                                if (reservation.doRezerv(
                                        false,
                                        Long.parseLong(rezJta[0].getText()),
                                        Integer.parseInt(rezRoomList
                                                .getSelectedValue().toString()),
                                        rezYears[rezDateBox[2]
                                                .getSelectedIndex()].toString()
                                                + "-"
                                                + months[rezDateBox[1]
                                                .getSelectedIndex()]
                                                .toString()
                                                + "-"
                                                + rezDays[rezDateBox[0]
                                                .getSelectedIndex()]
                                                .toString(),
                                        reservation.addDate(
                                                rezYears[rezDateBox[2]
                                                        .getSelectedIndex()]
                                                        .toString()
                                                        + "-"
                                                        + months[rezDateBox[1]
                                                        .getSelectedIndex()]
                                                        .toString()
                                                        + "-"
                                                        + rezDays[rezDateBox[0]
                                                        .getSelectedIndex()]
                                                        .toString(),
                                                rezDayBox.getSelectedIndex() + 1)))
                                    JOptionPane.showMessageDialog(getParent(),
                                            "Nie dokonano rezerwacji",
                                            "Brak klienta", 1);
                                else
                                    JOptionPane.showMessageDialog(getParent(),
                                            "Dokonano rezerwacji",
                                            "Informacja", 1);
                                rezRoomListLabel = reservation.createRoomList(0,
                                        "2010-01-01", "2011-01-01");
                                rezRoomList = new JList(rezRoomListLabel);
                                rezRoomPane.setViewportView(rezRoomList);
                                rezRoomPane.repaint();

                            } catch (NumberFormatException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (ParseException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });
    }

    private void resizeRezervation(int width, int height) {
        int rezEWidth = (width - 200) / 3;
        int rezJtaHeight = 18;
        int k = 50;
        int rezXJta = k;
        int rezYJta = 50;
        int rezYList = 300;
        rezClient.setBounds(10, 5, 65, 20);
        rezCompany.setBounds(75, 5, 70, 20);
        rezClient.setBackground(bgColor2);
        rezCompany.setBackground(bgColor2);
        rezIfExistC.setBounds(rezCompany.getX() + rezCompany.getWidth() + 10,
                rezCompany.getY(), 120, 20);
        rezIfExistC.setBackground(bgColor2);
        rezIfExistC.setVisible(true);
        rezIfExistF.setVisible(false);
        rezIfExistF.setBounds(rezCompany.getX() + rezCompany.getWidth() + 10,
                rezCompany.getY(), 120, 20);
        rezIfExistF.setBackground(bgColor2);
        rezRoomLabel[0].setBounds(15, rezYList - 18, rezEWidth, 18);
        rezClasPane.setBounds(15, rezRoomLabel[0].getY() + 20, 320, 115);
        rezRoomLabel[1].setBounds(rezRoomLabel[0].getX(), rezClasPane.getY()
                + rezClasPane.getHeight(), rezEWidth, 18);
        rezRoomPane.setBounds(rezRoomLabel[1].getX(),
                rezRoomLabel[1].getY() + 18, 120, 100);
        rezCat1.setBounds(rezRoomLabel[0].getX() + rezClasPane.getWidth() + 20,
                rezRoomLabel[0].getY(), 100, 20);
        rezCat1.setBackground(bgColor2);
        rezCat2.setBounds(rezCat1.getX() + rezCat1.getWidth(), rezCat1.getY(),
                100, 20);
        rezCat2.setBackground(bgColor2);
        rezCat3.setBounds(rezCat2.getX() + rezCat2.getWidth(), rezCat1.getY(),
                100, 20);
        rezCat3.setBackground(bgColor2);
        rezServPane.setBounds(rezCat1.getX(), rezCat1.getY() + 20, 320, 115);
        rezRoomLabel[2].setBounds(rezServPane.getX(), rezServPane.getY()
                + rezServPane.getHeight() + 5, rezEWidth, 18);

        int bWidth = 100;
        int bHeight = 25;
        rezButton[0].setBounds(150, 70, bWidth, bHeight);
        rezServPane1.setBounds(rezRoomLabel[2].getX(), rezRoomLabel[2].getY()
                + rezRoomLabel[2].getHeight() + 5, 100, 90);
        rezRoomLabel[3].setBounds(rezCat3.getX() + rezCat3.getWidth() + 25,
                rezCat3.getY(), rezEWidth, 18);
        rezDateBox[0].setBounds(rezRoomLabel[3].getX(), rezRoomLabel[3].getY()
                + rezRoomLabel[3].getHeight() + 5, 50, 20);
        rezDateBox[1].setBounds(rezDateBox[0].getX(), rezDateBox[0].getY()
                + rezDateBox[0].getHeight() + 5, 100, 20);
        rezDateBox[2].setBounds(rezDateBox[1].getX(), rezDateBox[1].getY()
                + rezDateBox[1].getHeight() + 5, 65, 20);
        rezDayLabel.setBounds(rezDateBox[1].getX(), rezDateBox[2].getY()
                + rezDateBox[2].getHeight() + 10, 50, rezJtaHeight);
        rezDayBox.setBounds(rezDayLabel.getX(), rezDayLabel.getY()
                + rezDayLabel.getHeight() + 5, 50, rezJtaHeight);
        rezPrice.setBounds(rezDayBox.getX() - 150, rezDayBox.getY() + 40, 150,
                rezJtaHeight);
        rezCenaJta.setBounds(rezPrice.getX() + rezPrice.getWidth(),
                rezPrice.getY(), 70, rezJtaHeight);
        rezButton[3].setBounds(rezCenaJta.getX() - 80, rezCenaJta.getY() + 25,
                150, rezJtaHeight);
        rezButton[2].setBounds(rezButton[3].getX(), rezButton[3].getY() + 40,
                bWidth, bHeight);

        for (int i = 0; i < 11; i++) {
            if (i != 0 && i % 3 == 0) {
                rezYJta += 50;
                rezXJta = k;
            }
            if (i == 10)
                rezJta[i].setBounds(rezXJta, rezYJta, rezEWidth,
                        rezJtaHeight + 60);
            else
                rezJta[i].setBounds(rezXJta, rezYJta, rezEWidth, rezJtaHeight);
            rezGuestLabel[i].setBounds(rezXJta, rezYJta - 18, rezEWidth,
                    rezJtaHeight);
            rezCompLabel[i].setBounds(rezXJta, rezYJta - 18, rezEWidth,
                    rezJtaHeight);
            rezXJta += k + rezEWidth;
        }
    }

    @Override
    public void setSize(int width, int height) {
        resizeRezervation(width, height);
        super.setSize(width, height);
    }
}
