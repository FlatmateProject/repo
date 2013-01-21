package gui.guestBook;

import dto.SimpleNameData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class Constructorr {

    private static int startY;

    public static void createButtons(JPanel panel, JButton[] buttons) {
        Color buttonColor = new Color(174, 205, 214);
        String[] labels = {"Szukaj", "Aktualizuj", "Firmy", "Wyczyść"};
        for (int i = 0; i < 4; i++) {
            buttons[i] = new JButton();
            buttons[i].setBounds(20 + i * 140, startY, 120, 25);
            buttons[i].setBackground(buttonColor);
            buttons[i].setText(labels[i]);
            panel.add(buttons[i]);
        }
    }

    public static void createClientForm(JPanel panel, List<SimpleNameData> columns, JLabel[] clientLabel, JTextField[] clientData) {
        int i = 0;
        Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));
        for (SimpleNameData column : columns) {
            clientLabel[i] = new JLabel(column.getName());
            clientLabel[i].setBounds(30, (i + 1) * 21, 160, 20);
            clientData[i] = new JTextField();
            clientData[i].setBounds(150, (i + 1) * 21, 150, 18);
            clientData[i].setBorder(border);
            panel.add(clientData[i]);
            panel.add(clientLabel[i]);
            i++;
        }

        startY = clientData[i - 1].getY() + clientData[i - 1].getHeight() + 10;
    }
}
