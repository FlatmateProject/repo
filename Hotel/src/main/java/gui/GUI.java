package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import dao.Singleton;


public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static {
		Singleton.getInstance();
	}

	private JTabbedPane tabbedPane = new JTabbedPane();

	private JPanel guessPanel;

	private JPanel schegulerPanel;

	private JPanel cantorPanel;

	private JPanel receptionPanel;

	private JPanel rezervationPanel;

	private JPanel statisticPanel;

	private JPanel managerPanel;

	private JPanel employeeManagerPanel;

	public GUI() {
		super();
		setupLookAndFeel();
		initializeWindow();
		createGUI();
		setVisible(true);
	}

	private void createGUI() {
		cantorPanel = new CantorPanel();
		add(cantorPanel);
		schegulerPanel = new SchedulerPanel();
		add(schegulerPanel);
		receptionPanel = new ReceptionPanel();
		add(receptionPanel);
		rezervationPanel = new RezervationPanel();
		add(rezervationPanel);
		managerPanel = new ManagerPanel();
		add(managerPanel);
		guessPanel = new GuestBookPanel();
		add(guessPanel);
		statisticPanel = new StatisticPanel();
		add(statisticPanel);
		employeeManagerPanel = new EmployeeManagerPanel();
		add(employeeManagerPanel);

		tabbedPane.setBounds(0, 0, getWidth(), getHeight());

		tabbedPane.addTab("Cantor", cantorPanel);
		tabbedPane.addTab("Menad�er personelu", employeeManagerPanel);
		tabbedPane.addTab("Statystyka", statisticPanel);
		tabbedPane.addTab("Grafik", schegulerPanel);
		tabbedPane.addTab("Recepcja", receptionPanel);
		tabbedPane.addTab("Rezerwacje", rezervationPanel);
		tabbedPane.addTab("Ksi�ga go�ci", guessPanel);
		tabbedPane.addTab("Manager", managerPanel);
		add(tabbedPane);
	}

	private void initializeWindow() {
		setTitle("Hotel");
		setBounds(0, 0, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1024, 768));
	}

	private void setupLookAndFeel() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.err.println("Błąd :: LOOK AND FEEL");
		}
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}
}