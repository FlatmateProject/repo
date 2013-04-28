package gui;

import common.adapter.MouseListenerAdapter;
import dictionary.MONTH;
import service.Reservation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import static validation.ValidationUtils.isNotNumber;

public class ReservationPanel extends JPanel {

    private final JTextField[] rezJta = new JTextField[11];
    private JTextField rezCenaJta = new JTextField();
    private final JLabel[] rezGuestLabel = new JLabel[11];
    private final JLabel[] rezCompLabel = new JLabel[11];
    private final JLabel[] rezRoomLabel = new JLabel[5];
    private JCheckBox rezIfExistC = new JCheckBox();
    private JCheckBox rezIfExistF = new JCheckBox();
    private final String[] rezDays = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    private final String[] rezYears = {"2010", "2011", "2012", "2013", "2015", "2016"};
    private JLabel rezDayLabel = new JLabel();
    private final JComboBox[] rezDateBox = new JComboBox[3];
    private JComboBox<String> rezDayBox = new JComboBox<String>();
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
    private JList<String> rezRoomList;
    private final JComboBox rezCatBox = new JComboBox();
    private JScrollPane rezServPane1;
    private String rezServListLabel[];
    private JList<String> rezServList;
    private DefaultListModel<String> listMod;
    private MouseListener rezMous;
    private final Color buttonColor = new Color(174, 205, 214);

    private final Color bgColor2 = new Color(227, 239, 243);
    private final Border border = BorderFactory.createLineBorder(new Color(60, 124,
            142));
    private final Color bgColor = new Color(224, 230, 233);

    private final Reservation reservation;

    public ReservationPanel(Reservation reservation) {
        this.reservation = reservation;
        create();
        addEventa();
    }

    private static final long serialVersionUID = 1L;

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(getParent(), message, "B��d", JOptionPane.ERROR_MESSAGE);
    }

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
            showMessageDialog("Brak klas pokoi");
        } else {
            rezClasPane = new JScrollPane(rezClasTable);
            rezClasPane.setBorder(border);
            add(rezClasPane);
        }

        rezServTable = reservation.createServTable("");
        if (rezServTable == null) {
            showMessageDialog("Brak us�ug");
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

        for (JLabel aRezCompLabel1 : rezCompLabel) {
            add(aRezCompLabel1);
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
        for (JLabel aRezRoomLabel : rezRoomLabel) {
            add(aRezRoomLabel);
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

        rezDateBox[0] = new JComboBox<String>(rezDays);
        rezDateBox[1] = new JComboBox<MONTH>(MONTH.values());
        rezDateBox[2] = new JComboBox<String>(rezYears);
        rezDayBox = new JComboBox<String>(rezDays);
        add(rezDayBox);
        for (int j = 0; j < 3; j++) {
            add(rezDateBox[j]);
        }
        rezRoomListLabel = reservation.createRoomList(0, "2010-01-01", "2011-01-01");
        rezRoomList = new JList<String>(rezRoomListLabel);
        if (rezServListLabel == null) {
            rezServListLabel = new String[30];
            rezServListLabel[0] = "Brak wybranych us�ug";
        }
        listMod = new DefaultListModel<String>();
        listMod.addElement("Wybierz us�ugi");

        rezServList = new JList<String>(listMod);
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
        rezMous = new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                listMod.addElement(rezServTable.getValueAt(rezServTable.getSelectedRow(), 1).toString());
                rezServPane1.setViewportView(rezServList);
                rezServPane1.repaint();
            }
        };
    }

    private void addEventa() {

        rezButton[0].addMouseListener(new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (rezJta[0].getText().isEmpty())
                    showMessageDialog("Podaj parametry");
                else {
                    if (rezClient.isSelected()) {
                        if (reservation.isNotPesel(rezJta[0].getText()))
                            showMessageDialog("�le wprowadzono PESEL");
                        else {
                            rezTable = reservation.createTable(true,
                                    Long.parseLong(rezJta[0].getText()));
                            if (rezTable == null) {
                                showMessageDialog("Nie ma takiego klienta");
                                for (JTextField aRezJta : rezJta) {
                                    aRezJta.setText("");
                                }

                            } else {
                                for (int i = 1; i < rezJta.length; i++) {
                                    rezJta[1].setText((String) rezTable.getValueAt(0, i));
                                }
                            }
                        }
                    } else if (reservation.isNotKRS(rezJta[0].getText()))
                        showMessageDialog("�le wprowadzono KRS");
                    else {
                        rezTable = reservation.createTable(false, Long.parseLong(rezJta[0].getText()));
                        if (rezTable == null)
                            showMessageDialog("Nie ma takiej firmy");
                        else {
                            for (int i = 1; i < rezJta.length; i++) {
                                rezJta[1].setText((String) rezTable.getValueAt(0, i));
                            }
                        }
                    }
                }

            }

        });
        rezClient.addMouseListener(new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
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

        });

        rezCat1.addMouseListener(new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                rezServTable = reservation.createServTable("rekreacja");
                rezServTable.addMouseListener(rezMous);
                if (rezServTable == null) {
                    showMessageDialog("Brak us�ug");
                } else {
                    rezServPane.setViewportView(rezServTable);
                    rezServPane.repaint();
                }

            }

        });

        rezCat2.addMouseListener(new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                rezServTable = reservation.createServTable("gastronomia");
                rezServTable.addMouseListener(rezMous);
                if (rezServTable == null) {
                    showMessageDialog("Brak us�ug");
                } else {
                    rezServPane.setViewportView(rezServTable);
                    rezServPane.repaint();
                }

            }
        });

        rezCat3.addMouseListener(new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                rezServTable = reservation.createServTable("biznes");
                rezServTable.addMouseListener(rezMous);
                if (rezServTable == null) {
                    showMessageDialog("Brak us�ug");
                } else {
                    rezServPane.setViewportView(rezServTable);
                    rezServPane.repaint();
                }

            }

        });

        rezCompany.addMouseListener(new MouseListenerAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        });

        rezClasTable.addMouseListener(new MouseListenerAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if ((rezRoomListLabel = reservation.createRoomList(
                            Integer.parseInt(rezClasTable.getValueAt(rezClasTable.getSelectedRow(), 0).toString()),
                            rezYears[rezDateBox[2].getSelectedIndex()]
                                    + "-"
                                    + rezDateBox[1].getSelectedItem()
                                    + "-"
                                    + rezDays[rezDateBox[0].getSelectedIndex()], reservation.addDate(
                            rezYears[rezDateBox[2].getSelectedIndex()]
                                    + "-"
                                    + rezDateBox[1].getSelectedIndex()
                                    + "-"
                                    + rezDays[rezDateBox[0].getSelectedIndex()], rezDayBox
                            .getSelectedIndex() + 1))) == null) {
                        rezRoomListLabel = new String[1];
                        rezRoomListLabel[0] = "Brak wolnych pokoi";
                    } else {
                        rezRoomList = new JList<String>(rezRoomListLabel);
                        rezRoomPane.setViewportView(rezRoomList);
                        rezRoomPane.repaint();
                    }
                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

            }

        });

        rezServTable.addMouseListener(new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                listMod.addElement(rezServTable.getValueAt(
                        rezServTable.getSelectedRow(), 1).toString());
                rezServPane1.setViewportView(rezServList);
                rezServPane1.repaint();
            }
        });

        rezServList.addMouseListener(new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (listMod.isEmpty()) {

                } else {
                    listMod.removeElementAt(rezServList.getSelectedIndex());
                    rezServPane1.setViewportView(rezServList);
                    rezServPane1.repaint();
                }
            }

        });

        rezButton[3].addMouseListener(new MouseListenerAdapter() {
            int monthInt;
            String monthStr;
            String[] servTab;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (rezRoomList.getSelectedIndex() < 0)
                    showMessageDialog("Nie wybrano pokoju");
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
                                servTab[i] = rezServList.getSelectedValue();

                            }
                            if (rezRoomList.getSelectedValue().equals("Brak danych")) {
                                showMessageDialog("Niepoprawna data");
                            } else
                                rezCenaJta.setText(String.valueOf(reservation.calculate(
                                        Integer.parseInt(rezRoomList
                                                .getSelectedValue()),
                                        rezDayBox.getSelectedIndex() + 1,
                                        rezServList.getModel().getSize(),
                                        servTab)));
                            rezCenaJta.setEditable(false);
                        } else
                            showMessageDialog("Niepoprawna data");

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }

        });

        rezButton[2].addMouseListener(new MouseListenerAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
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
                        showMessageDialog("Wprowad� kompletne dane");
                    else {
                        if (reservation.isNotPesel(rezJta[0].getText())) {
                            showMessageDialog(
                                    "Niepoprawny PESEL");
                            err = false;
                        } else {
                            if (isNotNumber(rezJta[6].getText())) {
                                showMessageDialog(
                                        "Niepoprawny numer lokalu");
                                err = false;
                            } else {
                                if (isNotNumber(rezJta[8].getText())) {
                                    showMessageDialog(
                                            "Niepoprawny numer telefonu");
                                    err = false;
                                } else if (reservation.isValidNip(rezJta[9].getText())) {
                                    showMessageDialog(
                                            "Niepoprawny NIP");
                                    err = false;
                                } else {
                                    if (rezCenaJta.getText().isEmpty()) {
                                        showMessageDialog(
                                                "Nie przeliczono pobytu");
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
                                showMessageDialog("Dodano Klienta");
                                try {
                                    reservation.doRezerv(
                                            true,
                                            Long.parseLong(rezJta[0].getText()),
                                            Integer.parseInt(rezRoomList
                                                    .getSelectedValue()),

                                            rezYears[rezDateBox[2]
                                                    .getSelectedIndex()]
                                                    + "-"
                                                    + rezDateBox[1].getSelectedIndex()
                                                    + "-"
                                                    + rezDays[rezDateBox[0]
                                                    .getSelectedIndex()],
                                            reservation.addDate(
                                                    rezYears[rezDateBox[2]
                                                            .getSelectedIndex()]
                                                            + "-"
                                                            + rezDateBox[1].getSelectedIndex()
                                                            + "-"
                                                            + rezDays[rezDateBox[0]
                                                            .getSelectedIndex()],
                                                    rezDayBox
                                                            .getSelectedIndex() + 1));
                                    rezRoomListLabel = reservation.createRoomList(0,
                                            "2010-01-01", "2011-01-01");
                                    rezRoomList = new JList<String>(rezRoomListLabel);
                                    rezRoomPane.setViewportView(rezRoomList);
                                    rezRoomPane.repaint();
                                } catch (ParseException e1) {
                                    e1.printStackTrace();
                                }
                            } else {
                                showMessageDialog("Nie zaznaczono,�e klient istnieje");
                            }
                        } else {
                            try {
                                if (reservation.doRezerv(
                                        true,
                                        Long.parseLong(rezJta[0].getText()),
                                        Integer.parseInt(rezRoomList
                                                .getSelectedValue()),
                                        rezYears[rezDateBox[2]
                                                .getSelectedIndex()]
                                                + "-"
                                                + rezDateBox[1].getSelectedIndex()
                                                + "-"
                                                + rezDays[rezDateBox[0]
                                                .getSelectedIndex()],
                                        reservation.addDate(
                                                rezYears[rezDateBox[2]
                                                        .getSelectedIndex()]
                                                        + "-"
                                                        + rezDateBox[1].getSelectedIndex()
                                                        + "-"
                                                        + rezDays[rezDateBox[0]
                                                        .getSelectedIndex()],
                                                rezDayBox.getSelectedIndex() + 1)))
                                    showMessageDialog("Nie dokonano rezerwacji");
                                else
                                    showMessageDialog("Dokonano rezerwacji");
                                rezRoomListLabel = reservation.createRoomList(0,
                                        "2010-01-01", "2011-01-01");
                                rezRoomList = new JList<String>(rezRoomListLabel);
                                rezRoomPane.setViewportView(rezRoomList);
                                rezRoomPane.repaint();

                            } catch (NumberFormatException e1) {
                                e1.printStackTrace();
                            } catch (ParseException e1) {
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
                        showMessageDialog(
                                "Wprowad� kompletne dane");
                    else {
                        if (reservation.isNotKRS(rezJta[0].getText())) {
                            showMessageDialog(
                                    "Niepoprawny KRS");
                            err = false;
                        } else {
                            if (isNotNumber(rezJta[5].getText())) {
                                showMessageDialog(
                                        "Niepoprawny numer lokalu");
                                err = false;
                            } else {
                                if (isNotNumber(rezJta[8].getText())) {
                                    showMessageDialog(
                                            "Niepoprawny numer telefonu");
                                    err = false;
                                } else {
                                    if (isNotNumber(rezJta[7].getText())) {
                                        showMessageDialog(
                                                "Niepoprawny REGON");
                                        err = false;
                                    } else {
                                        if (isNotNumber(rezJta[8].getText())) {
                                            showMessageDialog(
                                                    "Niepoprawny NIP");
                                            err = false;
                                        } else if (reservation.isValidNip(rezJta[9].getText())) {
                                            showMessageDialog(
                                                    "Niepoprawny numer telefonu");
                                            err = false;
                                        } else {
                                            if (rezCenaJta.getText().isEmpty()) {
                                                showMessageDialog(
                                                        "Nie przeliczono pobytu");
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
                                showMessageDialog("Dodano Firme");
                                try {
                                    reservation.doRezerv(
                                            false,
                                            Long.parseLong(rezJta[0].getText()),
                                            Integer.parseInt(rezRoomList
                                                    .getSelectedValue()),
                                            rezYears[rezDateBox[2]
                                                    .getSelectedIndex()]
                                                    + "-"
                                                    + rezDateBox[1].getSelectedIndex()
                                                    + "-"
                                                    + rezDays[rezDateBox[0]
                                                    .getSelectedIndex()],
                                            reservation.addDate(
                                                    rezYears[rezDateBox[2]
                                                            .getSelectedIndex()]
                                                            + "-"
                                                            + rezDateBox[1].getSelectedIndex()
                                                            + "-"
                                                            + rezDays[rezDateBox[0]
                                                            .getSelectedIndex()],
                                                    rezDayBox
                                                            .getSelectedIndex() + 1));
                                } catch (ParseException e1) {
                                    e1.printStackTrace();
                                }
                            } else {
                                showMessageDialog("Nie zaznaczono,�e firma istnieje");
                            }
                        } else {
                            try {
                                if (reservation.doRezerv(
                                        false,
                                        Long.parseLong(rezJta[0].getText()),
                                        Integer.parseInt(rezRoomList
                                                .getSelectedValue()),
                                        rezYears[rezDateBox[2].getSelectedIndex()]
                                                + "-"
                                                + rezDateBox[1].getSelectedIndex()
                                                + "-"
                                                + rezDays[rezDateBox[0].getSelectedIndex()],
                                        reservation.addDate(
                                                rezYears[rezDateBox[2].getSelectedIndex()]
                                                        + "-"
                                                        + rezDateBox[1].getSelectedIndex()
                                                        + "-"
                                                        + rezDays[rezDateBox[0].getSelectedIndex()],
                                                rezDayBox.getSelectedIndex() + 1)))
                                    showMessageDialog("Nie dokonano rezerwacji");
                                else
                                    showMessageDialog("Dokonano rezerwacji");
                                rezRoomListLabel = reservation.createRoomList(0,
                                        "2010-01-01", "2011-01-01");
                                rezRoomList = new JList<String>(rezRoomListLabel);
                                rezRoomPane.setViewportView(rezRoomList);
                                rezRoomPane.repaint();

                            } catch (NumberFormatException e1) {
                                e1.printStackTrace();
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    private void resizeRezervation(int width) {
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
        resizeRezervation(width);
        super.setSize(width, height);
    }
}
