package gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import spring.ApplicationConfiguration;

import javax.swing.*;
import java.awt.*;

@Component
public class Gui extends JFrame {

    private static final long serialVersionUID = 1L;

    @Autowired
    private StatisticPanel statisticPanel;

    @Autowired
    private CantorPanel cantorPanel;

    @Autowired
    private SchedulerPanel schedulerPanel;

    @Autowired
    private ReceptionPanel receptionPanel;

    @Autowired
    private ReservationPanel reservationPanel;

    @Autowired
    private ManagerPanel managerPanel;

    @Autowired
    private GuestBookPanel guessBookPanel;

    @Autowired
    private EmployeeManagerPanel employeeManagerPanel;

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
        tabbedPane.addTab("Cantor", cantorPanel);
        tabbedPane.addTab("Grafik", schedulerPanel);
        tabbedPane.addTab("Recepcja", receptionPanel);
        tabbedPane.addTab("Rezerwacje", reservationPanel);
        tabbedPane.addTab("Manager", managerPanel);
        tabbedPane.addTab("Ksi�ga go�ci", guessBookPanel);
        tabbedPane.addTab("Statystyka", statisticPanel);
        tabbedPane.addTab("Menad�er personelu", employeeManagerPanel);
        tabbedPane.setBounds(0, 0, getWidth(), getHeight());
        add(tabbedPane);
    }

    public void setStatisticPanel(StatisticPanel statisticPanel) {
        this.statisticPanel = statisticPanel;
    }

    public void setCantorPanel(CantorPanel cantorPanel) {
        this.cantorPanel = cantorPanel;
    }

    public void setSchedulerPanel(SchedulerPanel schedulerPanel) {
        this.schedulerPanel = schedulerPanel;
    }

    public void setReceptionPanel(ReceptionPanel receptionPanel) {
        this.receptionPanel = receptionPanel;
    }

    public void setReservationPanel(ReservationPanel reservationPanel) {
        this.reservationPanel = reservationPanel;
    }

    public void setManagerPanel(ManagerPanel managerPanel) {
        this.managerPanel = managerPanel;
    }

    public void setGuessBookPanel(GuestBookPanel guessBookPanel) {
        this.guessBookPanel = guessBookPanel;
    }

    public void setEmployeeManagerPanel(EmployeeManagerPanel employeeManagerPanel) {
        this.employeeManagerPanel = employeeManagerPanel;
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
                context.getBean(Gui.class);
            }
        });
    }
}