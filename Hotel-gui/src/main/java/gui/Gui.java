package gui;

import gui.guestBook.GuestBookPanel;
import gui.statistic.StatisticPanel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.WebApplicationConfiguration;

import javax.swing.*;
import java.awt.*;


public class Gui extends JFrame {

    private static final long serialVersionUID = 1L;

    private StatisticPanel statisticPanel;

    private CantorPanel cantorPanel;

    private ManagerPanel managerPanel;

    private GuestBookPanel guessBookPanel;

    public Gui() {
        super();
    }

    public void construct() {
        setupLookAndFeel();
        initializeWindow();
        createGUI();
        setVisible(true);
    }

    private void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.err.println("Błąd :: LOOK AND FEEL");
        }
    }

    private void initializeWindow() {
        setTitle("Hotel");
        setBounds(0, 0, 1024, 768);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1024, 768));
    }

    private void createGUI() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Manager", managerPanel);
        tabbedPane.addTab("Cantor", cantorPanel);
        tabbedPane.addTab("Księga gości", guessBookPanel);
        tabbedPane.addTab("Statystyka", statisticPanel);
        tabbedPane.setBounds(0, 0, getWidth(), getHeight());
        add(tabbedPane);
    }

    public void setStatisticPanel(StatisticPanel statisticPanel) {
        this.statisticPanel = statisticPanel;
    }

    public void setCantorPanel(CantorPanel cantorPanel) {
        this.cantorPanel = cantorPanel;
    }

    public void setManagerPanel(ManagerPanel managerPanel) {
        this.managerPanel = managerPanel;
    }

    public void setGuessBookPanel(GuestBookPanel guessBookPanel) {
        this.guessBookPanel = guessBookPanel;
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ApplicationContext context = new AnnotationConfigApplicationContext(WebApplicationConfiguration.class);
                Gui gui = context.getBean(Gui.class);
                gui.construct();
            }
        });
    }
}