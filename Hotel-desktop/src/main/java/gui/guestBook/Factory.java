package gui.guestBook;

import dto.ColumnData;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class Factory {

    private static int startY;

    private static String[] buttonLabels = {"Szukaj", "Aktualizuj", "Wyczyść"};

    public static JButton[] createButtons(JPanel panel) {
        Color buttonColor = new Color(174, 205, 214);
        JButton[] buttons = new JButton[3];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setBounds(20 + i * 140, startY, 120, 25);
            buttons[i].setBackground(buttonColor);
            buttons[i].setText(buttonLabels[i]);
            panel.add(buttons[i]);
        }
        return buttons;
    }

    public static Form createClientForm(JPanel panel, List<ColumnData> columns) {
        int i = 0;
        Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));
        int size = columns.size();
        JLabel[] clientLabel = new JLabel[size];
        JTextField[] clientData = new JTextField[size];
        for (ColumnData column : columns) {
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
        return Form.create(clientLabel, clientData);
    }
}
