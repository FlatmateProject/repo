package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import service.GuestBook;
import service.Singleton;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private GuestBook gue = new GuestBook();
	
	private JTabbedPane jtp = new JTabbedPane();
	private JPanel guePanelCl;
	private JPanel schPanel;
	private JPanel canPanel;
	private JPanel recPanel;
	private JPanel rezPanel;
	private JPanel staPanel;
	private JPanel manPanel;
	private JPanel mgpPanel;

	public GUI() {
		super();
		setupLookAndFeel();
		createGUI();
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}
	
	private void createGUI() {
		setTitle("Hotel");
		setBounds(0, 0, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1024, 768));

		Singleton.getInstance();

		canPanel = new CantorPanel();
		add(canPanel);
		schPanel = new SchedulePanel();
		add(schPanel);
		recPanel = new ReceptionPanel();
		add(recPanel);
//		rezPanel = new RezervationPanel();
//		add(rezPanel);
		manPanel = new ManagerPanel(gue);
		add(manPanel);
		guePanelCl = new GuestBookPanel();
		add(guePanelCl);
		staPanel = new StatisticPanel();
		add(staPanel);
		mgpPanel = new EmployeeManagerPanel();
		add(mgpPanel);
		
		jtp.setBounds(0, 0, getWidth(), getHeight());
		
		jtp.addTab("Cantor", canPanel);
		jtp.addTab("Menad�er personelu", mgpPanel);
		jtp.addTab("Statystyka", staPanel);	
		jtp.addTab("Grafik", schPanel);	
		jtp.addTab("Recepcja", recPanel);
		//jtp.addTab("Rezerwacje", rezPanel);
		jtp.addTab("Ksi�ga go�ci", guePanelCl);
		jtp.addTab("Manager", manPanel);
		add(jtp);
		
		setVisible(true);
	}

	private void setupLookAndFeel() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			System.err.println("Błąd :: LOOK AND FEEL");
		}
	}
}