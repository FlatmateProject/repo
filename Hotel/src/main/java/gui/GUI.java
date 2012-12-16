package gui;

import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

    public GUI() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1024, 768));
	}
	
	private void createGUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel cantorPanel = new CantorPanel();
		add(cantorPanel);
		tabbedPane.addTab("Cantor", cantorPanel);

        JPanel schegulerPanel = new SchedulerPanel();
		add(schegulerPanel);
		tabbedPane.addTab("Grafik", schegulerPanel);

        JPanel receptionPanel = new ReceptionPanel();
		add(receptionPanel);
		tabbedPane.addTab("Recepcja", receptionPanel);

        JPanel rezervationPanel = new RezervationPanel();
		add(rezervationPanel);
		tabbedPane.addTab("Rezerwacje", rezervationPanel);


        JPanel managerPanel = new ManagerPanel();
		add(managerPanel);
		tabbedPane.addTab("Manager", managerPanel);

        JPanel guessPanel = new GuestBookPanel();
		add(guessPanel);
		tabbedPane.addTab("Ksi�ga go�ci", guessPanel);

        JPanel statisticPanel = new StatisticPanel();
		add(statisticPanel);
		tabbedPane.addTab("Statystyka", statisticPanel);

        JPanel employeeManagerPanel = new EmployeeManagerPanel();
		add(employeeManagerPanel);
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