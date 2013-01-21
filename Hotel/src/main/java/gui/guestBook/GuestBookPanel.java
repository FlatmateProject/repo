package gui.guestBook;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import service.GuestBook;

import javax.swing.*;
import java.awt.*;

@Component
public class GuestBookPanel extends JPanel {

    private static final Logger log = Logger.getLogger(GuestBookPanel.class);

    private static final long serialVersionUID = 1L;

    private JPanel customerPanel;

    private JPanel companyPanel;

    private final Color bgColor = new Color(224, 230, 233);

    private final GuestBook guestBook;

    public GuestBookPanel(GuestBook guestBook) {
        this.guestBook = guestBook;
        create();
    }


    private void create() {
        setBounds(0, 0, 800, 600);
        setBackground(bgColor);
        setLayout(null);
        setVisible(true);

        customerPanel = new CustomerPanel(guestBook);
        add(customerPanel);

        companyPanel = new CompanyPanel(guestBook);
        add(companyPanel);

    }

    @Override
    public void setSize(int width, int height) {
    }
}
