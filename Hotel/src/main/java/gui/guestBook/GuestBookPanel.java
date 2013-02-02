package gui.guestBook;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class GuestBookPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel customerPanel;

    private JPanel companyPanel;

    private JButton customerButton;

    private JButton companyButton;

    private final Color bgColor = new Color(224, 230, 233);


    public GuestBookPanel(JPanel customerPanel, JPanel companyPanel) {
        super();
        this.customerPanel = customerPanel;
        this.companyPanel = companyPanel;
        create();
        addEvents();
    }

    private void create() {
        setBounds(0, 0, 1200, 600);
        setBackground(bgColor);
        setLayout(null);
        setVisible(true);

        add(customerPanel);
        companyPanel.setVisible(false);
        add(companyPanel);

        Color buttonColor = new Color(174, 205, 214);
        customerButton = new JButton("Klient");
        customerButton.setBounds(0, 0, 600, 20);
        customerButton.setBackground(buttonColor);
        add(customerButton);

        companyButton = new JButton("Firma");
        companyButton.setBounds(600, 0, 600, 20);
        companyButton.setBackground(buttonColor);
        add(companyButton);

    }

    private void addEvents() {
        companyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                companyPanel.setVisible(true);
                customerPanel.setVisible(false);
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                companyPanel.setVisible(false);
                customerPanel.setVisible(true);
            }
        });
    }

    @Override
    public void setSize(int width, int height) {
    }

    public void setCustomerPanel(JPanel customerPanel) {
        this.customerPanel = customerPanel;
    }

    public void setCompanyPanel(JPanel companyPanel) {
        this.companyPanel = companyPanel;
    }
}
