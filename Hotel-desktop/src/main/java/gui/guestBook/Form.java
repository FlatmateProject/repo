package gui.guestBook;

import javax.swing.*;

public class Form {
    private JLabel[] clientLabel;

    private JTextField[] clientData;

    private Form(JLabel[] clientLabel, JTextField[] clientData) {
        this.clientLabel = clientLabel;
        this.clientData = clientData;
    }

    public static Form create(JLabel[] clientLabel, JTextField[] clientData) {
        return new Form(clientLabel, clientData);
    }

    public JLabel[] getClientLabels() {
        return clientLabel;
    }

    public JTextField[] getClientData() {
        return clientData;
    }
}
