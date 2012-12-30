package gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.ApplicationConfiguration;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

    private final ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

    @Autowired
    private JPanel statisticPanel = context.getBean(StatisticPanel.class);

    @Autowired
    private JPanel cantorPanel = context.getBean(CantorPanel.class);

    @Autowired
    private JPanel schedulerPanel = context.getBean(SchedulerPanel.class);

    @Autowired
    private JPanel receptionPanel = context.getBean(ReceptionPanel.class);

    @Autowired
    private JPanel reservationPanel = context.getBean(ReservationPanel.class);

    @Autowired
    private JPanel managerPanel = context.getBean(ManagerPanel.class);

    @Autowired
    private JPanel guessBookPanel = context.getBean(GuestBookPanel.class);

    @Autowired
    private JPanel employeeManagerPanel = context.getBean(EmployeeManagerPanel.class);

    private GUI() {
		super();
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


	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}
}