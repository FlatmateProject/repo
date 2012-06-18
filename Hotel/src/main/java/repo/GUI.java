package repo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class GUI extends JFrame implements ComponentListener {

	private static final long serialVersionUID = 1L;
	private JTabbedPane jtp = new JTabbedPane();
	private Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));
	private int k = 50;
	private Color bgColor2 = new Color(227, 239, 243);
	private Color bgColor = new Color(224, 230, 233);
	private Color buttonColor = new Color(174, 205, 214);
	private Color callendarColor = new Color(255, 255, 255);

	private Statistic sta;
	private Schedule sch = new Schedule();
	private GuestBook gue = new GuestBook();
	private Kantor cantor = new Kantor();
	private Reception recept = new Reception();
	private Rezervation rezerv = new Rezervation();

	// GuestBook
	private JPanel guePanelCl;
	private JPanel guePanelCo;
	private JLabel gueClientLabel[] = new JLabel[12];
	private JTextField gueClientData[] = new JTextField[11];
	private JTextArea gueClientNotes = new JTextArea();
	private JTable gueTable[] = new JTable[3];
	private JScrollPane gueScrollPane[] = new JScrollPane[3];
	private JButton gueButton[] = new JButton[4];
	private MouseListener gueTableMLCl = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {
				gueTable[1] = gue.createTable(
						"rezerwacje",
						"where IDK_PESEL="
								+ gueTable[0].getValueAt(
										gueTable[0].getSelectedRow(), 0));
				gueTable[1].addMouseListener(gueTable2MLCl);
				gueScrollPane[1].setViewportView(gueTable[1]);
				guePanelCl.add(gueScrollPane[1]);

				for (int i = 0; i < 11; i++) {
					if (i < 10)
						gueClientData[i].setText((String) gueTable[0]
								.getValueAt(gueTable[0].getSelectedRow(), i));
					else
						gueClientNotes.setText((String) gueTable[0].getValueAt(
								gueTable[0].getSelectedRow(), i));
				}
			} catch (Exception e) {
				System.out.println("Brak danych!");
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	};
	private MouseListener gueTableMLCo = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			gueTable[1] = gue.createTable("rezerwacje", "where IDF_KRS="
					+ gueTable[0].getValueAt(gueTable[0].getSelectedRow(), 0));
			gueTable[1].addMouseListener(gueTable2MLCo);
			gueScrollPane[1].setViewportView(gueTable[1]);
			guePanelCo.add(gueScrollPane[1]);

			for (int i = 0; i < 11; i++) {
				if (i < 10)
					gueClientData[i].setText((String) gueTable[0].getValueAt(
							gueTable[0].getSelectedRow(), i));
				else
					gueClientNotes.setText((String) gueTable[0].getValueAt(
							gueTable[0].getSelectedRow(), i));
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	};
	private MouseListener gueTable2MLCl = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			gueTable[2] = gue.createTable(
					"uslugi",
					", rekreacja where rekreacja.id_rez ="
							+ gueTable[1].getValueAt(
									gueTable[1].getSelectedRow(), 0)
							+ " and rekreacja.id_uslugi = uslugi.id_uslugi");
			gueScrollPane[2].setViewportView(gueTable[2]);
			guePanelCl.add(gueScrollPane[2]);
			gueClientLabel[11] = new JLabel("US�UGI");
			gueClientLabel[11].setBounds(510, 21, 100, 20);
			guePanelCl.add(gueClientLabel[11]);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
	};
	private MouseListener gueTable2MLCo = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			gueTable[2] = gue.createTable(
					"uslugi",
					", rekreacja where rekreacja.id_rez ="
							+ gueTable[1].getValueAt(
									gueTable[1].getSelectedRow(), 0)
							+ " and rekreacja.id_uslugi = uslugi.id_uslugi");
			gueScrollPane[2].setViewportView(gueTable[2]);
			guePanelCo.add(gueScrollPane[2]);
			gueClientLabel[11] = new JLabel("US�UGI");
			gueClientLabel[11].setBounds(510, 21, 100, 20);
			guePanelCl.add(gueClientLabel[11]);
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
	};

	// Schedule
	private int d, m, y;
	private JPanel schPanel = new JPanel();
	private Calendar schCalendar = GregorianCalendar.getInstance();
	private JButton schDayButton[] = new JButton[31];
	private JButton schNext, schPrev;
	private JLabel schDayLabel[] = new JLabel[7];
	private JLabel schMonthLabel = new JLabel();
	private JScrollPane schScrollPane;
	private JTable schTable;
	private String schDow[] = { "Pn", "Wt", "�r", "Cz", "Pt", "So", "Nd" };
	private int schDom[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private String schMoy[] = { "Stycze�", "Luty", "Marzec", "Kwiecie�", "Maj",
			"Czerwiec", "Lipiec", "Sierpie�", "Wrzesie�", "Pa�dziernik",
			"Listopad", "Grudzie�" };

	// Cantor
	float results[];
	private JPanel canPanel = new JPanel();
	private JTextField canAmountJta = new JTextField();
	private JLabel canBuyLabel = new JLabel();
	private JLabel canCurrL1 = new JLabel();
	private JLabel canCurrL2 = new JLabel();
	private JLabel canClientL = new JLabel();

	private JLabel canPrice = new JLabel();
	private JComboBox canCurrBox1 = new JComboBox();
	private JComboBox canCurrBox2 = new JComboBox();
	private String canCurr[] = { "USD", "EUR", "GBP", "JPY", "CHF", "CAD",
			"AUD", "BRL", "CZK", "SEK", "CNY", "RUB", "PLN" };
	private JButton canResButton = new JButton();
	private JButton canPESButton = new JButton();
	private JButton canDoIt = new JButton();
	private JTextField canCostJta = new JTextField();
	private JTextField canPESJta = new JTextField();
	private JLabel canCostLabel = new JLabel();
	private JLabel canPay = new JLabel();
	private JTable canTable = new JTable();
	private JTable canClientTable = new JTable();
	private JScrollPane canScrollPane;
	private JScrollPane canScrollClientPane;
	private int canYList, canEWidth, canJtaHeight, canBWidth, canBHeight;
	float many;

	// Reception
	private JPanel recPanel = new JPanel();
	private JTextField recJta[] = new JTextField[3];
	private JTextField recCenaJta = new JTextField();
	private JLabel recGuestLabel[] = new JLabel[3];
	private JLabel recRezLabel = new JLabel();
	private JLabel recDateLabel = new JLabel();
	private JLabel recDayLabel = new JLabel();
	private JLabel recPrice = new JLabel();
	private JLabel recPay = new JLabel();
	private JLabel recBil = new JLabel();
	private ButtonGroup recPayGroup = new ButtonGroup();
	private ButtonGroup recBilGroup = new ButtonGroup();
	private JRadioButton recPayButton[] = new JRadioButton[2];
	private JRadioButton recBilButton[] = new JRadioButton[2];
	private JButton recButton[] = new JButton[5];
	private JTable recTable = new JTable();
	private JScrollPane recScrollPane;
	private int yJta, recYList, recEWidth, recJtaHeight, recBWidth, recBHeight;

	// Rezervation
	private JPanel rezPanel = new JPanel();
	private JTextField rezJta[] = new JTextField[11];
	private JTextField rezCenaJta = new JTextField();
	private JLabel rezGuestLabel[] = new JLabel[11];
	private JLabel rezCompLabel[] = new JLabel[11];
	private JLabel rezRoomLabel[] = new JLabel[5];
	private JCheckBox rezIfExistC = new JCheckBox();
	private JCheckBox rezIfExistF = new JCheckBox();
	private String rezDays[] = { "01", "02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
			"19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
			"30", "31" };
	private String months[] = { "01", "02", "03", "04", "05", "06", "07", "08",
			"09", "10", "11", "12" };
	private String rezYears[] = { "2010", "2011", "2012", "2013", "2015",
			"2016" };
	private JLabel rezDateLabel = new JLabel();
	private JLabel rezDayLabel = new JLabel();
	private JComboBox rezDateBox[] = new JComboBox[3];
	private JComboBox rezDayBox = new JComboBox();
	private JLabel rezPrice = new JLabel();
	private JButton rezButton[] = new JButton[5];
	private int rezXJta, rezYJta, rezYList, rezEWidth, rezJtaHeight, bWidth,
			bHeight;
	private JTable rezTable = new JTable();
	private JRadioButton rezClient;
	private JRadioButton rezCompany;
	private JRadioButton rezCat1;
	private JRadioButton rezCat2;
	private JRadioButton rezCat3;
	private ButtonGroup rezRadioGroup;
	private ButtonGroup rezCatGroup;
	private JTable rezClasTable;
	private JScrollPane rezClasPane;
	private JScrollPane rezRoomPane;
	private JTable rezServTable;
	private JScrollPane rezServPane;
	private String rezRoomListLabel[];
	private JList rezRoomList;
	private JComboBox rezCatBox = new JComboBox();
	private JScrollPane rezServPane1;
	private String rezServListLabel[];
	private JList rezServList;
	private DefaultListModel listMod;
	private MouseListener rezMous;

	// Logowanie
	private Login log;
	private JFrame logFrame;
	private JPanel logPanel;
	private JLabel logLabel[] = new JLabel[3];
	private JTextField logLogin;
	private JPasswordField logPass;
	private JButton logSubmit;

	// statistic
	private JPanel staPanel;
	private JButton staExec;
	private JLabel staTitleLabel;
	private JLabel staTypeLabel;
	private JLabel staSubLabel;
	private JLabel staMonthLabel;
	private JLabel staYearLabel;
	private JLabel staMonthLabelFrom;
	private JLabel staYearLabelFrom;
	private JLabel staMonthLabelTo;
	private JLabel staYearLabelTo;
	private JLabel staClassLabel;
	private JLabel staServeLabel;
	private JComboBox staChooseType;
	private JComboBox staChooseSubFin;
	private JComboBox staChooseSubHot;
	private JComboBox staChooseMonth;
	private JComboBox staChooseYear;
	private JComboBox staChooseMonth2;
	private JComboBox staChooseYear2;
	private JComboBox staChooseClass;
	private JComboBox staChooseServe;
	private JScrollPane staRaportScroll;
	private JTextPane staRaportText;
	private GraphDraw staMem;
	private Singleton db;

	// Manager
	private Manager man = new Manager();
	private JPanel manPanel;
	private JPanel manDataPan;
	private JButton manButton[];
	private JButton manButton2[];
	private JList manNews;
	private JScrollPane manScrollPane = new JScrollPane();
	private JTable manTable;
	private String manName = "klienci";
	private JLabel manLabel[];
	private JTextField manData[];
	private MouseListener manTableML = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {
				for (int i = 0; i < manData.length; i++) {
					manData[i].setText((String) manTable.getValueAt(
							manTable.getSelectedRow(), i));
				}
			} catch (Exception e) {
				System.out.println("Brak danych!");
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	};

	// Personnel manager
	private JPanel mgpPanel;
	private JPanel mgpServe[];
	private JComboBox mgpChooseType;
	private JComboBox mgpChooseAdd;
	private JComboBox mgpChooseDel;
	private JComboBox mgpChooseMonth;
	private JComboBox mgpChooseEmployee;
	private JLabel mgpTitle;
	private JLabel mgpTypeLabel;
	private JLabel mgpMonthLabel;
	private JLabel mgpCalMonthLabel;
	private JLabel mgpSubTitle[];
	private JLabel mgpAddLabel[];
	private JLabel mgpDelLabel[];
	private JLabel mgpDayLabel[];
	private JTextField mgpAddEmploy[];
	private JTextField mgpDelEmploy[];
	private JButton mgpAdd;
	private JButton mgpFind;
	private JButton mgpDel;
	private JButton mgpCreat;
	private JButton mgpDays[];
	private JButton mgpPrev;
	private JButton mgpNext;
	private JTable mgpTable;
	private JScrollPane mgpSchedScroll;
	private JScrollPane mgpRaportScroll;
	private JScrollPane mgpTableScroll;
	private JList mgpSchedText;
	private JTextPane mgpRaportText;

	private PersonnelManager mgp;

	private String mgpColsName[];
	private String mgpMatrix[][];
	private boolean mgpIndel = false;
	private final String[] dayOfWeek = { "Pn", "Wt", "�r", "Cz", "Pt", "So",
			"Nd" };
	private final String[] monthOfYear = { "Stycze�", "Luty", "Marzec",
			"Kwiecie�", "Maj", "Czerwiec", "Lipiec", "Sierpie�", "Wrzesie�",
			"Pa�dziernik", "Listopad", "Grudzie�" };
	private final int[] dayInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 };
	private int mgpDay = schCalendar.get(Calendar.DAY_OF_MONTH);
	private int mgpMonth = schCalendar.get(Calendar.MONTH);
	private int mgpYear = schCalendar.get(Calendar.YEAR);
	private int mgpClickCount = 0;

	private void createComboBoxStat() {
		try {
			staChooseClass = new JComboBox();
			staChooseServe = new JComboBox();
			staChooseClass.setBounds(500, 60, 230, 20);
			staChooseServe.setBounds(500, 60, 230, 20);
			staChooseClass.setVisible(false);
			staChooseServe.setVisible(false);
			ResultSet reSet = db.query("SELECT opis FROM klasy");
			if (reSet != null) {
				while (reSet.next())
					staChooseClass.addItem(reSet.getString(1));
				staChooseClass.setSelectedIndex(0);
				staPanel.add(staChooseClass);
			}
			reSet = db.query("SELECT typ FROM uslugi GROUP BY typ");
			if (reSet != null) {
				while (reSet.next())
					staChooseServe.addItem(reSet.getString(1));
				staChooseServe.setSelectedIndex(0);
				staPanel.add(staChooseServe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createManagerPPanel() throws SQLException {
		int i = 0;
		ResultSet rset = null;
		Font font = null;

		mgp = new PersonnelManager();

		mgpPanel = new JPanel();
		mgpPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
		mgpPanel.setLayout(null);
		mgpPanel.setBackground(bgColor);
		mgpPanel.setVisible(true);

		font = new Font("arial", Font.BOLD, 20);
		mgpTitle = new JLabel("Manager personalny");
		mgpTitle.setBounds(20, 5, 300, 20);
		mgpTitle.setFont(font);
		mgpPanel.add(mgpTitle);

		mgpServe = new JPanel[4];
		mgpSubTitle = new JLabel[4];
		mgpSubTitle[0] = new JLabel("Grafik misi�czny");
		mgpSubTitle[1] = new JLabel("Dodaj pracownika");
		mgpSubTitle[2] = new JLabel("Usu� pracownika");
		mgpSubTitle[3] = new JLabel("Raport p�ac");
		font = new Font("arial", Font.BOLD, 15);
		for (i = 0; i < mgpServe.length; i++) {
			mgpServe[i] = new JPanel();
			mgpServe[i].setLayout(null);
			mgpServe[i].setBackground(bgColor);
			mgpServe[i].setVisible(false);
			mgpSubTitle[i].setBounds(20, 5, 300, 20);
			mgpSubTitle[i].setFont(font);
			mgpServe[i].add(mgpSubTitle[i]);
			mgpPanel.add(mgpServe[i]);
		}
		mgpServe[0].setVisible(true);

		mgpAddEmploy = new JTextField[8];
		mgpDelEmploy = new JTextField[4];
		mgpAddLabel = new JLabel[9];
		mgpDelLabel = new JLabel[4];
		mgpColsName = new String[4];
		rset = db.query("SHOW COLUMNS FROM pracownicy");
		System.out.println("SHOW COLUMNS FROM pracownicy");
		if (rset != null) {
			for (i = 0; i < 8 && rset.next(); i++) {
				mgpAddEmploy[i] = new JTextField();
				mgpAddLabel[i] = new JLabel(rset.getString(1));
				mgpAddLabel[i].setBounds(20, 40 + i * 25, 100, 20);
				mgpAddEmploy[i].setBounds(130, 40 + i * 25, 200, 20);
				mgpServe[1].add(mgpAddEmploy[i]);
				mgpServe[1].add(mgpAddLabel[i]);
			}
			rset.beforeFirst();
			for (i = 0; i < 3 && rset.next(); i++) {
				mgpDelEmploy[i] = new JTextField();
				mgpDelLabel[i] = new JLabel(rset.getString(1));
				mgpColsName[i] = rset.getString(1);
				mgpDelEmploy[i].setBounds(130, 40 + i * 25, 200, 20);
				mgpDelLabel[i].setBounds(20, 40 + i * 25, 100, 20);
				mgpServe[2].add(mgpDelLabel[i]);
				mgpServe[2].add(mgpDelEmploy[i]);
			}
		}
		mgpAddLabel[8] = new JLabel("STANOWISKO");
		mgpAddLabel[8].setBounds(20, 40 + 8 * 25, 100, 20);
		mgpDelLabel[3] = new JLabel("STANOWISKO");
		mgpDelLabel[3].setBounds(20, 40 + 3 * 25, 100, 20);
		mgpServe[1].add(mgpAddLabel[8]);
		mgpServe[2].add(mgpDelLabel[3]);
		mgpColsName[3] = "STANOWISKO";
		mgpChooseAdd = new JComboBox();
		mgpChooseAdd.setBounds(130, 40 + 8 * 25, 200, 20);
		mgpChooseDel = new JComboBox();
		mgpChooseDel.setBounds(130, 40 + 3 * 25, 200, 20);
		rset = db.query("SELECT nazwa FROM stanowiska");
		if (rset != null) {
			mgpChooseDel.addItem("");
			while (rset.next()) {
				mgpChooseAdd.addItem(rset.getString(1));
				mgpChooseDel.addItem(rset.getString(1));
			}
			mgpChooseAdd.setSelectedIndex(0);
			mgpChooseDel.setSelectedIndex(0);
			mgpServe[1].add(mgpChooseAdd);
			mgpServe[2].add(mgpChooseDel);
		}

		mgpSchedText = new JList();
		mgpSchedScroll = new JScrollPane(mgpSchedText);
		mgpServe[0].add(mgpSchedScroll);

		rset = db
				.query("SELECT imie, nazwisko, idp_pesel FROM pracownicy WHERE id_stanowiska=1");
		System.out
				.println("SELECT imie, nazwisko, idp_pesel FROM pracownicy WHERE id_stanowiska=1");
		mgpChooseEmployee = new JComboBox();
		if (rset != null) {
			System.out.println("in costam lipa");
			mgpChooseEmployee.setBounds(0, 0, 120, 20);
			while (rset.next()) {
				mgpChooseEmployee.addItem(rset.getString(1) + " "
						+ rset.getString(2) + " " + rset.getString(3));
				System.out.println(rset.getString(1) + " " + rset.getString(2));
			}
			mgpChooseEmployee.setVisible(false);
			mgpSchedText.add(mgpChooseEmployee);
		}
		font = new Font("arial", Font.ROMAN_BASELINE, 15);
		mgpAdd = new JButton("Dodaj");
		mgpAdd.setBounds(340, 40, 100, 40);
		mgpAdd.setFont(font);
		mgpServe[1].add(mgpAdd);

		mgpFind = new JButton("Znajdz");
		mgpFind.setBounds(340, 40, 100, 40);
		mgpFind.setFont(font);
		mgpServe[2].add(mgpFind);

		mgpCreat = new JButton("Wykonaj");
		mgpCreat.setBounds(160, 40, 100, 40);
		mgpCreat.setFont(font);
		mgpServe[3].add(mgpCreat);

		mgpDel = new JButton("Usu�");
		mgpDel.setFont(font);

		// mgpDel.setBounds(505,165,107,40);
		mgpDel.setVisible(false);
		mgpServe[2].add(mgpDel);

		mgpPrev = new JButton("Poprzedni");
		mgpServe[0].add(mgpPrev);

		mgpNext = new JButton("Nast�pny");
		mgpServe[0].add(mgpNext);

		mgpTableScroll = new JScrollPane();
		// mgpTableScroll.setBounds(20,165,473,382);
		mgpTableScroll.setVisible(false);
		mgpServe[2].add(mgpTableScroll);

		mgpTypeLabel = new JLabel("Wybirz czynno��");
		mgpTypeLabel.setBounds(20, 40, 300, 20);
		mgpTypeLabel.setFont(font);
		mgpChooseType = new JComboBox();
		mgpChooseType.setBounds(20, 60, 230, 20);
		// ///////////////////------------b
		mgpChooseType.addItem("Tw�rz grafik");
		mgpChooseType.addItem("Usu� grafik");
		mgpChooseType.addItem("Pokaz grafik");
		mgpChooseType.addItem("Dodaj pracownika");
		mgpChooseType.addItem("Usu� pracownika");
		mgpChooseType.addItem("Raport p�ac");
		mgpChooseType.setSelectedIndex(2);
		// ///////////////////------------e
		mgpPanel.add(mgpChooseType);
		mgpPanel.add(mgpTypeLabel);

		mgpMonthLabel = new JLabel("Wybirz miesi�c");
		mgpMonthLabel.setBounds(20, 40, 300, 20);
		mgpMonthLabel.setFont(font);
		mgpChooseMonth = new JComboBox();
		mgpChooseMonth.setBounds(20, 60, 130, 20);
		mgpChooseMonth.addItem("Stycze�");
		mgpChooseMonth.addItem("Luty");
		mgpChooseMonth.addItem("Marzec");
		mgpChooseMonth.addItem("Kwiecie�");
		mgpChooseMonth.addItem("Maj");
		mgpChooseMonth.addItem("Czerwiec");
		mgpChooseMonth.addItem("Lipiec");
		mgpChooseMonth.addItem("Sierpie�");
		mgpChooseMonth.addItem("Wrzesie�");
		mgpChooseMonth.addItem("Pa�dziernik");
		mgpChooseMonth.addItem("Listopad");
		mgpChooseMonth.addItem("Grudzie�");
		mgpChooseMonth.setSelectedIndex(0);
		mgpServe[3].add(mgpChooseMonth);
		mgpServe[3].add(mgpMonthLabel);

		mgpRaportText = new JTextPane();
		mgpRaportScroll = new JScrollPane(mgpRaportText);
		mgpServe[3].add(mgpRaportScroll);
		mgpRaportScroll.setBounds(20, 90, 592, 457);
		mgpRaportText.setSize(592, 457);

		mgpCalMonthLabel = new JLabel();
		mgpServe[0].add(mgpCalMonthLabel);
		mgpDays = new JButton[31];
		for (i = 0; i < mgpDays.length; i++) {
			mgpDays[i] = new JButton(String.valueOf(i + 1));
			mgpDays[i].setBackground(Color.WHITE);
			mgpServe[0].add(mgpDays[i]);
		}
		mgpDayLabel = new JLabel[7];
		for (i = 0; i < mgpDayLabel.length; i++) {
			mgpDayLabel[i] = new JLabel(dayOfWeek[i]);
			mgpServe[0].add(mgpDayLabel[i]);
		}

		// add(mgpPanel);
	}

	public void createStatistic() throws SQLException {
		Color color = new Color(0, 150, 0);
		Font font = new Font("arial", Font.ROMAN_BASELINE, 15);

		staMem = new GraphDraw();
		sta = new Statistic();

		staPanel = new JPanel();
		staPanel.setBounds(0, 0, 900, 650);
		staPanel.setLayout(null);
		staPanel.setBackground(color);
		staPanel.setVisible(true);

		staExec = new JButton("Wykonaj");
		staExec.setBounds(380, 60, 100, 60);
		staExec.setFont(font);
		staExec.setVisible(true);
		staPanel.add(staExec);

		staChooseType = new JComboBox();
		staChooseType.setBounds(20, 60, 230, 20);
		staChooseType.addItem("hotelowe");
		staChooseType.addItem("finansowe");
		staChooseType.setSelectedIndex(0);
		staPanel.add(staChooseType);

		staChooseSubHot = new JComboBox();
		staChooseSubHot.setBounds(20, 100, 230, 20);
		staChooseSubHot.addItem("Raportu z wykorzystania klas pokoi");
		staChooseSubHot.addItem("Raportu z wykorzystania pokoi w klasie");
		staChooseSubHot.addItem("Raportu z wykorzystania typ�w us�ug");
		staChooseSubHot.addItem("Raportu z wybranej uslugi");
		staChooseSubHot.setSelectedIndex(0);
		staPanel.add(staChooseSubHot);

		staChooseMonth = new JComboBox();
		staChooseMonth.setBounds(260, 100, 100, 20);
		staChooseMonth.addItem("Stycze�");
		staChooseMonth.addItem("Luty");
		staChooseMonth.addItem("Marzec");
		staChooseMonth.addItem("Kwiecie�");
		staChooseMonth.addItem("Maj");
		staChooseMonth.addItem("Czerwiec");
		staChooseMonth.addItem("Lipiec");
		staChooseMonth.addItem("Sierpie�");
		staChooseMonth.addItem("Wrzesie�");
		staChooseMonth.addItem("Pa�dziernik");
		staChooseMonth.addItem("Listopad");
		staChooseMonth.addItem("Grudzie�");
		staChooseMonth.setSelectedIndex(0);
		staPanel.add(staChooseMonth);

		staChooseMonth2 = new JComboBox();
		staChooseMonth2.setBounds(260, 60, 100, 20);
		staChooseMonth2.addItem("Stycze�");
		staChooseMonth2.addItem("Luty");
		staChooseMonth2.addItem("Marzec");
		staChooseMonth2.addItem("Kwiecie�");
		staChooseMonth2.addItem("Maj");
		staChooseMonth2.addItem("Czerwiec");
		staChooseMonth2.addItem("Lipiec");
		staChooseMonth2.addItem("Sierpie�");
		staChooseMonth2.addItem("Wrzesie�");
		staChooseMonth2.addItem("Pa�dziernik");
		staChooseMonth2.addItem("Listopad");
		staChooseMonth2.addItem("Grudzie�");
		staChooseMonth2.setSelectedIndex(0);
		staChooseMonth2.setVisible(false);
		staPanel.add(staChooseMonth2);

		staChooseSubFin = new JComboBox();
		staChooseSubFin.setBounds(20, 100, 230, 20);
		staChooseSubFin.addItem("Bilansu z miesi�cy");
		staChooseSubFin.addItem("Bilansu z lat");
		staChooseSubFin.setSelectedIndex(0);
		staChooseSubFin.setVisible(false);
		staPanel.add(staChooseSubFin);

		staChooseYear = new JComboBox();
		staChooseYear.setBounds(260, 60, 100, 20);
		staChooseYear.addItem("2010");
		staChooseYear.addItem("2011");
		staChooseYear.addItem("2012");
		staChooseYear.addItem("2013");
		staChooseYear.addItem("2014");
		staChooseYear.addItem("2015");
		staChooseYear.addItem("2016");
		staChooseYear.setSelectedIndex(0);
		staPanel.add(staChooseYear);

		staChooseYear2 = new JComboBox();
		staChooseYear2.setBounds(260, 100, 100, 20);
		staChooseYear2.addItem("2010");
		staChooseYear2.addItem("2011");
		staChooseYear2.addItem("2012");
		staChooseYear2.addItem("2013");
		staChooseYear2.addItem("2014");
		staChooseYear2.addItem("2015");
		staChooseYear2.addItem("2016");
		staChooseYear2.setSelectedIndex(0);
		staChooseYear2.setVisible(false);
		staPanel.add(staChooseYear2);

		createComboBoxStat();

		font = new Font("arial", Font.BOLD, 20);
		staTitleLabel = new JLabel("Statystyki");
		staTitleLabel.setBounds(20, 5, 100, 20);
		staTitleLabel.setFont(font);
		staPanel.add(staTitleLabel);

		font = new Font("arial", Font.ROMAN_BASELINE, 15);
		staTypeLabel = new JLabel("Wybierz rodzaj staStatystyk");
		staTypeLabel.setBounds(20, 45, 200, 15);
		staTypeLabel.setFont(font);
		staPanel.add(staTypeLabel);

		staSubLabel = new JLabel("Wybierz jedna");
		staSubLabel.setBounds(20, 85, 100, 15);
		staSubLabel.setFont(font);
		staPanel.add(staSubLabel);

		staMonthLabel = new JLabel("Wybierz miesi�c");
		staMonthLabel.setBounds(260, 85, 200, 15);
		staMonthLabel.setFont(font);
		staPanel.add(staMonthLabel);

		staYearLabel = new JLabel("Wybierz rok");
		staYearLabel.setBounds(260, 45, 100, 15);
		staYearLabel.setFont(font);
		staPanel.add(staYearLabel);

		staClassLabel = new JLabel("Wybierz klas� pokuju");
		staClassLabel.setBounds(500, 45, 200, 15);
		staClassLabel.setFont(font);
		staClassLabel.setVisible(false);
		staPanel.add(staClassLabel);

		staServeLabel = new JLabel("Wybierz typ us�ugi");
		staServeLabel.setBounds(500, 45, 200, 15);
		staServeLabel.setFont(font);
		staServeLabel.setVisible(false);
		staPanel.add(staServeLabel);

		staMonthLabelFrom = new JLabel("Miesi�cy od");
		staMonthLabelFrom.setBounds(260, 45, 200, 15);
		staMonthLabelFrom.setFont(font);
		staMonthLabelFrom.setVisible(false);
		staPanel.add(staMonthLabelFrom);

		staYearLabelFrom = new JLabel("Lata od");
		staYearLabelFrom.setBounds(260, 45, 200, 15);
		staYearLabelFrom.setFont(font);
		staYearLabelFrom.setVisible(false);
		staPanel.add(staYearLabelFrom);

		staMonthLabelTo = new JLabel("do");
		staMonthLabelTo.setBounds(260, 85, 200, 15);
		staMonthLabelTo.setFont(font);
		staMonthLabelTo.setVisible(false);
		staPanel.add(staMonthLabelTo);

		staYearLabelTo = new JLabel("do");
		staYearLabelTo.setBounds(260, 85, 200, 15);
		staYearLabelTo.setFont(font);
		staYearLabelTo.setVisible(false);
		staPanel.add(staYearLabelTo);

		staRaportText = new JTextPane();
		staRaportText.setBounds(20, 130, 360, 444);
		staRaportText.setFont(font);

		staRaportScroll = new JScrollPane(staRaportText);
		staRaportScroll.setVisible(false);
		staPanel.add(staRaportScroll);

		staMem.setBackground(Color.WHITE);
		staMem.setVisible(false);
		staPanel.add(staMem);

		add(staPanel);
	}

	private JTable manGenTable(String name) {
		String manData[][] = man.createTable(name);
		String columnNames[] = new String[manData[0].length];
		Object rowData[][] = new Object[manData.length - 1][manData[0].length];

		for (int i = 0; i < manData.length; i++) {
			for (int j = 0; j < manData[i].length; j++) {
				if (i == 0) {
					columnNames[j] = manData[i][j];
				} else {
					rowData[i - 1][j] = manData[i][j];
				}
			}
		}
		return new JTable(rowData, columnNames);
	}

	private void manAction(String manName) {
		manTable = manGenTable(manName);
		manTable.addMouseListener(manTableML);
		manScrollPane.setViewportView(manTable);
		manPanel.remove(manDataPan);
		manPanel.add(createDataPanel(manName));
		manPanel.repaint();
		manPanel.revalidate();
	}

	private void createManager() {
		manPanel = new JPanel();
		manPanel.setBackground(bgColor);
		manPanel.setLayout(null);

		manButton = new JButton[10];
		manButton2 = new JButton[5];

		manButton2[0] = new JButton("Dodaj");
		manButton2[1] = new JButton("Usu�");
		manButton2[2] = new JButton("Edytuj");
		manButton2[3] = new JButton("Szukaj");
		manButton2[4] = new JButton("Wyczy��");

		manButton[0] = new JButton("Klienci");
		manButton[1] = new JButton("Firmy");
		manButton[2] = new JButton("Us�ugi");
		manButton[3] = new JButton("Pokoje");
		manButton[4] = new JButton("Stanowiska");
		manButton[5] = new JButton("Waluty");
		manButton[6] = new JButton("Pracownicy");
		manButton[7] = new JButton("Klasy");
		manButton[8] = new JButton("Archiwum");
		manButton[9] = new JButton("Rachunki");

		manButton2[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String l[] = new String[manData.length];
				String d[] = new String[manData.length];
				for (int i = 0; i < manData.length; i++) {
					l[i] = manLabel[i].getText();
					d[i] = manData[i].getText();
					if (!d[i].isEmpty()) {
						if ((l[i].equals("IDK_PESEL") || l[i]
								.equals("IDP_PESEL"))
								&& !Validation.isPesel(d[i])) {
							JOptionPane.showMessageDialog(null,
									"B��dny PESEL!", "UWAGA!",
									JOptionPane.ERROR_MESSAGE);
							break;
						} else if (l[i].equals("IDF_KRS")
								&& !Validation.isKRS(d[i])) {
							JOptionPane.showMessageDialog(null, "B��dny KRS!",
									"UWAGA!", JOptionPane.ERROR_MESSAGE);
							break;
						} else if ((l[i].equals("DATA_Z")
								|| l[i].equals("DATA_W") || l[i].equals("DATA"))
								&& !Validation.isDate(d[i])) {
							JOptionPane.showMessageDialog(null, "B��dna data!",
									"UWAGA!", JOptionPane.ERROR_MESSAGE);
							break;
						} else if ((l[i].equals("CENA_SP")
								|| l[i].equals("CENA_KU")
								|| l[i].equals("ILOSC")
								|| l[i].equals("WARTOSC")
								|| l[i].equals("PODATEK")
								|| l[i].equals("IL_OSOB")
								|| l[i].equals("ID_STANOWISKA")
								|| l[i].equals("TELEFON") || l[i].equals("NIP")
								|| l[i].equals("NR_LOKALU")
								|| l[i].equals("REGON") || l[i].equals("CENA")
								|| l[i].equals("PODSTAWA")
								|| l[i].equals("PREMIA")
								|| l[i].equals("ID_POKOJU")
								|| l[i].equals("ID_KLASY")
								|| l[i].equals("ID_REZ") || l[i]
									.equals("ID_USLUGI"))
								&& !Validation.isNumber(d[i])) {
							JOptionPane.showMessageDialog(null,
									"B��dna liczba!", "UWAGA!",
									JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					if (i == manData.length - 1) {
						if (!man.insertData(manName, l, d, manData.length)) {
							JOptionPane.showMessageDialog(null,
									"B��dne ID lub taki klient ju� istnieje!",
									"UWAGA!", JOptionPane.ERROR_MESSAGE);
							break;
						}
						manTable = manGenTable(manName);
						manTable.addMouseListener(manTableML);
						manScrollPane.setViewportView(manTable);
					}
				}
			}
		});
		manButton2[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!manData[0].getText().isEmpty()) {
					String l = new String(manLabel[0].getText());
					String d = new String(manData[0].getText());
					if (!man.deleteData(manName, l, d)) {
						JOptionPane.showMessageDialog(null,
								"Nie mo�na usun�� tego wiersza!", "UWAGA!",
								JOptionPane.ERROR_MESSAGE);
					} else {
						manTable = manGenTable(manName);
						manTable.addMouseListener(manTableML);
						manScrollPane.setViewportView(manTable);
					}
				}
			}
		});
		manButton2[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String l[] = new String[manData.length];
				String d[] = new String[manData.length];
				for (int i = 0; i < manData.length; i++) {
					l[i] = manLabel[i].getText();
					d[i] = manData[i].getText();
				}
				man.updateData(manName, l, d, manData.length);
				manTable = manGenTable(manName);
				manTable.addMouseListener(manTableML);
				manScrollPane.setViewportView(manTable);
			}
		});
		manButton2[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s2 = "";
				for (int i = 0; i < manData.length; i++) {
					if (!manData[i].getText().isEmpty()) {
						if (!s2.isEmpty()) {
							s2 = s2 + " and ";
						}
						s2 = s2 + manLabel[i].getText() + "=" + "\""
								+ manData[i].getText() + "\"";
					}
				}
				if (!s2.isEmpty()) {
					manTable = gue.createTable(manName, " where " + s2);
					manTable.addMouseListener(manTableML);
					manScrollPane.setViewportView(manTable);
				}
			}
		});
		manButton2[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < manData.length; i++) {
					manData[i].setText("");
					manTable = gue.createTable(manName, "");
					manTable.addMouseListener(manTableML);
					manScrollPane.setViewportView(manTable);
				}
			}
		});

		manButton[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "klienci";
				manAction(manName);
			}
		});
		manButton[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "firmy";
				manAction(manName);
			}
		});
		manButton[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "uslugi";
				manAction(manName);
			}
		});
		manButton[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "pokoje";
				manAction(manName);
			}
		});
		manButton[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "stanowiska";
				manAction(manName);
			}
		});
		manButton[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "waluty";
				manAction(manName);
			}
		});
		manButton[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "pracownicy";
				manAction(manName);
			}
		});
		manButton[7].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "klasy";
				manAction(manName);
			}
		});
		manButton[8].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "archiwum";
				manAction(manName);
			}
		});
		manButton[9].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				manName = "rachunki";
				manAction(manName);
			}
		});

		String news[] = {
				"Og�lna ilo�� rezerwacji: " + man.getCount("rezerwacje"),
				"Ilo�� zarejestrowanych go�ci: " + man.getCount("klienci"),
				man.getCount("pokoje") + " pokoi, z czego "
						+ man.getCount("pokoje where id_rez is null")
						+ " wolnych i "
						+ man.getCount("pokoje where id_rez is not null")
						+ " zaj�tych.",
				"Ilo�� dost�pnych us�ug: " + man.getCount("uslugi"),
				"W tym miesi�cu oczekujemy na "
						+ man.getCount("rezerwacje where month(data_z) = "
								+ (schCalendar.get(Calendar.MONTH) + 1))
						+ ", a �egnamy "
						+ man.getCount("rezerwacje where month(data_w) = "
								+ (schCalendar.get(Calendar.MONTH) + 1))
						+ " go�ci" };
		manNews = new JList(news);
		manNews.setBackground(bgColor);

		manTable = manGenTable("klienci");
		manTable.addMouseListener(manTableML);
		manScrollPane = new JScrollPane(manTable);

		createDataPanel("klienci");
		manPanel.add(manDataPan);

		manPanel.add(manScrollPane);
		manPanel.add(manNews);

		for (int i = 0; i < manButton.length; i++) {
			manButton[i].setBackground(buttonColor);
			manPanel.add(manButton[i]);
		}
		for (int i = 0; i < manButton2.length; i++) {
			manButton2[i].setBackground(buttonColor);
			manPanel.add(manButton2[i]);
		}
	}

	private JPanel createDataPanel(String name) {
		String cols[] = man.getColumns(name);
		int colCount = cols.length;
		manLabel = new JLabel[colCount];
		manData = new JTextField[colCount];

		manDataPan = new JPanel();
		manDataPan.setLayout(null);
		manDataPan.setBounds(0, 0, 340, (colCount + 1) * 20);
		manDataPan.setBackground(bgColor);

		int manX = 30, manY = 20;

		for (int i = 0; i < cols.length; i++) {
			manLabel[i] = new JLabel(cols[i]);
			if (i == 0)
				manY = 20;
			else
				manY = 20 * (i + 1);
			manLabel[i].setBounds(manX, manY, 150, 20);

			manData[i] = new JTextField();
			manData[i].setBounds(manLabel[i].getX() + 150, manLabel[i].getY(),
					150, 19);
			// manData[i].setBorder(border);

			manDataPan.add(manLabel[i]);
			manDataPan.add(manData[i]);
		}
		return manDataPan;
	}

	public void createLogin() {
		log = new Login();
		logFrame = new JFrame("Logowanie");
		logFrame.setLayout(null);
		logFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logFrame.setBounds(0, 0, 320, 180);
		logFrame.setLocationRelativeTo(null);

		logPanel = new JPanel();
		logPanel.setLayout(null);
		logPanel.setBounds(0, 0, logFrame.getWidth(), logFrame.getHeight());
		logPanel.setBackground(bgColor2);

		logLabel[0] = new JLabel("LOGIN:");
		logLabel[0].setBounds(20, 50, 50, 20);

		logLabel[1] = new JLabel("HAS�O:");
		logLabel[1].setBounds(logLabel[0].getX(), logLabel[0].getY() + 21, 50,
				20);

		logLogin = new JTextField();
		logLogin.setBorder(border);
		logLogin.setBounds(logLabel[0].getX() + 55, logLabel[0].getY() + 2,
				200, 18);
		logPanel.add(logLogin);

		logPass = new JPasswordField();
		logPass.setBorder(border);
		logPass.setBounds(logLabel[0].getX() + 55, logLabel[1].getY() + 2, 200,
				18);
		logPanel.add(logPass);

		logSubmit = new JButton("Zaloguj");
		logSubmit.setBackground(buttonColor);
		logSubmit.setBorder(border);
		logSubmit.setBounds(logPass.getX() + logPass.getWidth() - 100,
				logPass.getY() + 25, 100, 25);
		logSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (log.check(logLogin.getText(), logPass.getPassword())) {
					logLabel[2].setVisible(false);
					createGUI();
					logFrame.dispose();
				} else {
					logLabel[2].setVisible(true);
				}
			}
		});
		logPanel.add(logSubmit);

		logLabel[2] = new JLabel("B��dny login lub has�o!");
		logLabel[2].setForeground(Color.red);
		logLabel[2].setBounds(logLabel[0].getX(), logLabel[0].getY() - 25, 200,
				20);
		logLabel[2].setVisible(false);

		for (int i = 0; i < 3; i++) {
			logLabel[i].setHorizontalAlignment(SwingConstants.RIGHT);
			logPanel.add(logLabel[i]);
		}

		logFrame.add(logPanel);
		logFrame.setVisible(true);
	}

	public void schInitCalendar(int day, int month, int year) {
		int tmp, dayTmp, dX, h = 0;

		schCalendar.set(Calendar.YEAR, year);
		schCalendar.set(Calendar.MONTH, month);
		schCalendar.set(Calendar.DAY_OF_MONTH, day);

		dX = getWidth() / 2 - 182;
		h = 70;
		dayTmp = schCalendar.get(Calendar.DAY_OF_MONTH);
		schCalendar.set(Calendar.DAY_OF_MONTH, 0);
		tmp = schCalendar.get(Calendar.DAY_OF_WEEK) - 1;

		dX += tmp * 51;
		for (int i = 0, k = 51; i < 31; i++, dX += k, tmp++) {
			if (i != 0 && tmp % 7 == 0) {
				h += 31;
				dX = getWidth() / 2 - 182;
			}
			schDayButton[i].setBounds(dX, h, 50, 30);
			if (i < schDom[month]) {
				schDayButton[i].setVisible(true);
			} else {
				schDayButton[i].setVisible(false);
			}
		}
		dX = getWidth() / 2 - 175;
		for (int i = 0; i < schDayLabel.length; i++, dX += 51) {
			schDayLabel[i].setBounds(dX + 10, schDayButton[0].getY() - 18, 50,
					18);
		}
		schMonthLabel.setText(schMoy[month] + " " + year);
		schMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		schMonthLabel.setBounds((getWidth() - 100) / 2,
				schDayLabel[0].getY() - 30, 100, 20);
		dX = getWidth() / 2 - 75;
		schPrev.setBounds(schDayLabel[0].getX() - 18, schMonthLabel.getY(),
				100, 20);
		schNext.setBounds(schDayLabel[6].getX() - 68, schMonthLabel.getY(),
				100, 20);
		schCalendar.set(Calendar.DAY_OF_MONTH, dayTmp);
		schDayButton[day - 1].requestFocus();

		schScrollPane.setBounds(20, 300, getWidth() - 50, getHeight() - 400);
	}

	public void createSchedule() {

		d = schCalendar.get(Calendar.DAY_OF_MONTH);
		m = schCalendar.get(Calendar.MONTH);
		y = schCalendar.get(Calendar.YEAR);

		schPanel.setBackground(bgColor);
		schPanel.setBounds(0, 0, getWidth(), getHeight());
		schPanel.setLayout(null);

		for (int i = 0; i < schDayButton.length; i++) {
			schDayButton[i] = new JButton(String.valueOf(i + 1));
			schDayButton[i].setBackground(callendarColor);
			schDayButton[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JButton tmp = (JButton) arg0.getSource();
					schTable = sch.getTable(Integer.valueOf(tmp.getText()),
							m + 1, y);
					schScrollPane.setViewportView(schTable);
				}
			});
			schPanel.add(schDayButton[i]);
		}
		for (int i = 0; i < schDayLabel.length; i++) {
			schDayLabel[i] = new JLabel(schDow[i]);
			schPanel.add(schDayLabel[i]);
		}
		schPanel.add(schMonthLabel);
		schNext = new JButton("Nast�pny");
		schPrev = new JButton("Poprzedni");
		schPanel.add(schPrev);
		schPanel.add(schNext);

		schTable = sch.getTable(0, m + 1, y);
		schScrollPane = new JScrollPane(schTable);
		schScrollPane.setBorder(border);
		schPanel.add(schScrollPane);

		schPanel.setVisible(true);
	}

	private void createGuestBookCo() {
		guePanelCo = new JPanel();
		guePanelCo.setBounds(0, 0, getWidth(), getHeight());
		guePanelCo.setBackground(bgColor);
		guePanelCo.setLayout(null);

		for (int i = 0; i < 11; i++) {
			if (i < 10) {
				gueClientLabel[i] = new JLabel(gue.getLabel("firmy")[i]);
				gueClientLabel[i].setBounds(30, (i + 1) * 21, 150, 20);

				gueClientData[i] = new JTextField();
				gueClientData[i].setBounds(140, (i + 1) * 21, 150, 18);
				gueClientData[i].setBorder(border);

				guePanelCo.add(gueClientData[i]);
			} else {
				gueClientLabel[i] = new JLabel(gue.getLabel("firmy")[i]);
				gueClientLabel[i].setBounds(300, 21, 70, 20);
			}
			guePanelCo.add(gueClientLabel[i]);
		}

		gueClientNotes.setBorder(border);
		gueClientNotes.setBounds(gueClientLabel[10].getX(), 41, 200, 187);

		guePanelCo.add(gueClientNotes);

		for (int i = 0; i < 4; i++) {
			gueButton[i] = new JButton();
			if (i == 0)
				gueButton[i].setBounds(20, 250, 100, 25);
			else
				gueButton[i].setBounds(gueButton[i - 1].getX() + 110, 250, 100,
						25);
			gueButton[i].setBackground(buttonColor);
			guePanelCo.add(gueButton[i]);
		}
		gueButton[0].setText("Szukaj");
		gueButton[1].setText("Aktualizuj");
		gueButton[2].setText("Klienci");
		gueButton[3].setText("Wyczy��");

		gueTable[0] = gue.createTable("firmy", "");
		gueTable[0].addMouseListener(gueTableMLCo);

		gueScrollPane[0] = new JScrollPane(gueTable[0]);
		gueScrollPane[0].setBorder(border);

		gueScrollPane[1] = new JScrollPane();
		gueScrollPane[1].setBorder(border);
		gueScrollPane[2] = new JScrollPane();
		gueScrollPane[2].setBorder(border);

		guePanelCo.add(gueScrollPane[0]);

		gueButton[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s2 = "";
				for (int i = 0; i < 11; i++) {
					if (i < 10 && !gueClientData[i].getText().isEmpty()) {
						if (!s2.isEmpty()) {
							s2 = s2 + " and ";
						}
						s2 = s2 + gueClientLabel[i].getText() + "=" + "\""
								+ gueClientData[i].getText() + "\"";
					} else if (i >= 10 && !gueClientNotes.getText().isEmpty()) {
						if (!s2.isEmpty()) {
							s2 = s2 + " and ";
						}
						s2 = s2 + gueClientLabel[i].getText() + "=" + "\""
								+ gueClientNotes.getText() + "\"";
					}
				}
				if (!s2.isEmpty()) {
					gueTable[0] = gue.createTable("firmy", " where " + s2);
					gueTable[0].addMouseListener(gueTableMLCo);
					gueScrollPane[0].setViewportView(gueTable[0]);
				}
			}
		});
		gueButton[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String l[] = new String[10];
				String d[] = new String[10];
				for (int i = 0; i < 10; i++) {
					l[i] = gueClientLabel[i].getText();
					d[i] = gueClientData[i].getText();
					// System.out.println(gueClientLabel[i].getText() + " " +
					// gueClientData[i].getText());
				}
				if (gue.updateClientData(l, d)) {
					gueTable[0] = gue.createTable("firmy", "");
					gueTable[0].addMouseListener(gueTableMLCo);
					gueScrollPane[0].setViewportView(gueTable[0]);
				} else {
					JOptionPane.showMessageDialog(null,
							"B��d aktualizacji! Sprawd� dane!", "UWAGA!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gueButton[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jtp.remove(guePanelCo);
				createGuestBook();
				jtp.addTab("Ksi�ga Go�ci", guePanelCl);
				jtp.setSelectedIndex(jtp.getComponentCount() - 1);
				resizeGuestBook();
			}
		});
		gueButton[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 10; i++) {
					gueClientData[i].setText("");
					gueClientNotes.setText("");
					gueTable[0] = gue.createTable("firmy", "");
					gueTable[0].addMouseListener(gueTableMLCo);
					gueScrollPane[0].setViewportView(gueTable[0]);
				}
			}
		});
	}

	private void createGuestBook() {
		guePanelCl = new JPanel();
		guePanelCl.setBounds(0, 0, getWidth(), getHeight());
		guePanelCl.setBackground(bgColor);
		guePanelCl.setLayout(null);

		for (int i = 0; i < 11; i++) {
			if (i < 10) {
				gueClientLabel[i] = new JLabel(gue.getLabel("klienci")[i]);
				gueClientLabel[i].setBounds(30, (i + 1) * 21, 150, 20);

				gueClientData[i] = new JTextField();
				gueClientData[i].setBounds(140, (i + 1) * 21, 150, 18);
				gueClientData[i].setBorder(border);

				guePanelCl.add(gueClientData[i]);
			} else {
				gueClientLabel[i] = new JLabel(gue.getLabel("klienci")[i]);
				gueClientLabel[i].setBounds(300, 21, 70, 20);
			}
			guePanelCl.add(gueClientLabel[i]);
		}

		gueClientNotes.setBorder(border);
		gueClientNotes.setBounds(gueClientLabel[10].getX(), 41, 200, 187);

		guePanelCl.add(gueClientNotes);

		for (int i = 0; i < 4; i++) {
			gueButton[i] = new JButton();
			if (i == 0)
				gueButton[i].setBounds(20, 250, 100, 25);
			else
				gueButton[i].setBounds(gueButton[i - 1].getX() + 110, 250, 100,
						25);
			gueButton[i].setBackground(buttonColor);
			guePanelCl.add(gueButton[i]);
		}
		gueButton[0].setText("Szukaj");
		gueButton[1].setText("Aktualizuj");
		gueButton[2].setText("Firmy");
		gueButton[3].setText("Wyczy��");

		gueTable[0] = gue.createTable("klienci", "");
		gueTable[0].addMouseListener(gueTableMLCl);

		gueScrollPane[0] = new JScrollPane(gueTable[0]);
		gueScrollPane[0].setBorder(border);

		gueScrollPane[1] = new JScrollPane();
		gueScrollPane[1].setBorder(border);
		gueScrollPane[2] = new JScrollPane();
		gueScrollPane[2].setBorder(border);

		gueButton[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String s2 = "";
				for (int i = 0; i < 11; i++) {
					if (i < 10 && !gueClientData[i].getText().isEmpty()) {
						if (!s2.isEmpty()) {
							s2 = s2 + " and ";
						}
						s2 = s2 + gueClientLabel[i].getText() + "=" + "\""
								+ gueClientData[i].getText() + "\"";
					} else if (i >= 10 && !gueClientNotes.getText().isEmpty()) {
						if (!s2.isEmpty()) {
							s2 = s2 + " and ";
						}
						s2 = s2 + gueClientLabel[i].getText() + "=" + "\""
								+ gueClientNotes.getText() + "\"";
					}
				}
				if (!s2.isEmpty()) {
					gueTable[0] = gue.createTable("klienci", " where " + s2);
					gueTable[0].addMouseListener(gueTableMLCl);
					gueScrollPane[0].setViewportView(gueTable[0]);
				}
			}
		});
		gueButton[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String l[] = new String[10];
				String d[] = new String[10];
				for (int i = 0; i < 10; i++) {
					l[i] = gueClientLabel[i].getText();
					d[i] = gueClientData[i].getText();
					// System.out.println(gueClientLabel[i].getText() + " " +
					// gueClientData[i].getText());
				}
				if (gue.updateClientData(l, d)) {
					gueTable[0] = gue.createTable("klienci", "");
					gueTable[0].addMouseListener(gueTableMLCl);
					gueScrollPane[0].setViewportView(gueTable[0]);
				} else {
					JOptionPane.showMessageDialog(null,
							"B��d aktualizacji! Sprawd� dane!", "UWAGA!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		gueButton[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jtp.remove(guePanelCl);
				createGuestBookCo();
				jtp.addTab("Ksi�ga Go�ci", guePanelCo);
				jtp.setSelectedIndex(jtp.getComponentCount() - 1);
				resizeGuestBook();
			}
		});
		gueButton[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 10; i++) {
					gueClientData[i].setText("");
					gueClientNotes.setText("");
					gueTable[0] = gue.createTable("klienci", "");
					gueTable[0].addMouseListener(gueTableMLCl);
					gueScrollPane[0].setViewportView(gueTable[0]);
				}
			}
		});
		guePanelCl.add(gueScrollPane[0]);
	}

	private void createRezervation() {
		rezPanel.setBounds(0, 0, getWidth(), getHeight());
		rezPanel.setLayout(null);
		rezPanel.addComponentListener(this);
		rezPanel.setBackground(bgColor);
		rezClient = new JRadioButton("Klient", true);
		rezCompany = new JRadioButton("Firma", false);
		rezCat1 = new JRadioButton("Rekreacja", true);
		rezCat2 = new JRadioButton("Gastronomia", false);
		rezCat3 = new JRadioButton("Biznes", false);
		rezRadioGroup = new ButtonGroup();
		rezCatGroup = new ButtonGroup();
		rezRadioGroup.add(rezClient);
		rezRadioGroup.add(rezCompany);
		rezCatGroup.add(rezCat1);
		rezCatGroup.add(rezCat2);
		rezCatGroup.add(rezCat3);
		rezGuestLabel[0] = new JLabel("PESEL");
		rezGuestLabel[1] = new JLabel("Imie");
		rezGuestLabel[2] = new JLabel("Nazwisko");
		rezGuestLabel[3] = new JLabel("Wojewodztwo");
		rezGuestLabel[4] = new JLabel("Miasto");
		rezGuestLabel[5] = new JLabel("Ulica");
		rezGuestLabel[6] = new JLabel("Nr lokalu");
		rezGuestLabel[7] = new JLabel("Status");
		rezGuestLabel[8] = new JLabel("Telefon");
		rezGuestLabel[9] = new JLabel("NIP");
		rezGuestLabel[10] = new JLabel("Uwagi");

		rezCompLabel[0] = new JLabel("KRS");
		rezCompLabel[1] = new JLabel("Nazwa");
		rezCompLabel[2] = new JLabel("Wojew�dztwo");
		rezCompLabel[3] = new JLabel("Miasto");
		rezCompLabel[4] = new JLabel("Ulica");
		rezCompLabel[5] = new JLabel("Nr lokalu");
		rezCompLabel[6] = new JLabel("Status");
		rezCompLabel[7] = new JLabel("REGON");
		rezCompLabel[8] = new JLabel("NIP");
		rezCompLabel[9] = new JLabel("Telefon");
		rezCompLabel[10] = new JLabel("Uwagi");

		rezClasTable = new JTable();
		rezClasTable = rezerv.createClasTable();
		if (rezClasTable == null) {
			JOptionPane.showMessageDialog(rezPanel, "Brak klas pokoi", "B��d",
					1);
		} else {
			rezClasPane = new JScrollPane(rezClasTable);
			rezClasPane.setBorder(border);
			rezPanel.add(rezClasPane);
		}

		rezServTable = new JTable();
		rezServTable = rezerv.createServTable("");
		if (rezServTable == null) {
			JOptionPane.showMessageDialog(rezPanel, "Brak us�ug", "B��d", 1);
			rezServPane = new JScrollPane(null);
			rezServPane.setBorder(border);
			rezPanel.add(rezServPane);
		} else {
			rezServPane = new JScrollPane(rezServTable);
			rezServPane.setBorder(border);
			rezPanel.add(rezServPane);
		}

		for (int i = 0; i < rezGuestLabel.length; i++) {
			rezJta[i] = new JTextField();
			rezJta[i].setBorder(border);
			rezPanel.add(rezGuestLabel[i]);
			rezPanel.add(rezJta[i]);
		}

		for (int i = 0; i < rezCompLabel.length; i++) {
			rezPanel.add(rezCompLabel[i]);
		}

		for (int i = 0; i < rezCompLabel.length; i++) {
			rezCompLabel[i].setVisible(false);
		}
		rezRoomLabel[0] = new JLabel("Klasa pokoju");
		rezRoomLabel[1] = new JLabel("Wolne pokoje");
		rezRoomLabel[2] = new JLabel("Wybrane us�ugi");
		rezRoomLabel[3] = new JLabel("Rezerwacja od:");
		rezRoomLabel[4] = new JLabel("Wybrane us�ugi");
		rezPrice = new JLabel("��czny koszt rezerwacji");
		for (int i = 0; i < rezRoomLabel.length; i++) {
			rezPanel.add(rezRoomLabel[i]);
		}
		rezCenaJta = new JTextField("");
		rezCenaJta.setBorder(border);
		rezPanel.add(rezClient);
		rezPanel.add(rezCompany);
		rezPanel.add(rezCat1);
		rezPanel.add(rezCat2);
		rezPanel.add(rezCat3);
		rezPanel.add(rezPrice);
		rezPanel.add(rezCenaJta);

		rezButton[0] = new JButton("Sprawd�");
		rezButton[1] = new JButton("Melduj");
		rezButton[2] = new JButton("Rezerwuj");
		rezButton[3] = new JButton("Przelicz");
		for (int i = 0; i < 4; i++) {
			rezButton[i].setBackground(buttonColor);
			rezPanel.add(rezButton[i]);
		}

		rezDateLabel = new JLabel("Rozpocz�cie pobytu: ");
		rezDayLabel = new JLabel("Doby: ");
		rezPanel.add(rezDateLabel);
		rezPanel.add(rezDayLabel);

		rezDateBox[0] = new JComboBox(rezDays);
		rezDateBox[1] = new JComboBox(months);
		rezDateBox[2] = new JComboBox(rezYears);
		rezDayBox = new JComboBox(rezDays);
		rezPanel.add(rezDayBox);
		for (int j = 0; j < 3; j++) {
			rezPanel.add(rezDateBox[j]);
		}
		rezRoomListLabel = rezerv.createRoomList(0, "2010-01-01", "2011-01-01");
		rezRoomList = new JList(rezRoomListLabel);
		if (rezServListLabel == null) {
			rezServListLabel = new String[30];
			rezServListLabel[0] = "Brak wybranych us�ug";
		}
		listMod = new DefaultListModel();
		listMod.addElement("Wybierz us�ugi");
		rezServList = new JList(listMod);
		listMod.removeElement("Wybierz us�ugi");
		rezRoomPane = new JScrollPane(rezRoomList);
		rezRoomPane.setBorder(border);
		rezPanel.add(rezRoomPane);
		rezPanel.add(rezCatBox);
		rezServPane1 = new JScrollPane(rezServList);
		rezServPane1.setBorder(border);
		rezIfExistC = new JCheckBox("Klient istnieje");
		rezIfExistF = new JCheckBox("Firma istnieje");
		rezPanel.add(rezIfExistC);
		rezPanel.add(rezIfExistF);
		rezPanel.add(rezServPane1);
		rezPanel.setVisible(true);
		rezMous = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				listMod.addElement(rezServTable.getValueAt(
						rezServTable.getSelectedRow(), 1).toString());
				rezServPane1.setViewportView(rezServList);
				rezServPane1.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		};

	}

	private void createReception() {
		recPanel.setBounds(0, 0, getWidth(), getHeight());
		recPanel.setLayout(null);
		recPanel.addComponentListener(this);
		recPanel.setBackground(bgColor);
		recTable = new JTable();
		recGuestLabel[0] = new JLabel("ID Rezerwacji");
		recGuestLabel[1] = new JLabel("PESEL/KRS");
		recGuestLabel[2] = new JLabel("Data wymeldowania (RRRR-MM-DD)");

		for (int i = 0; i < 3; i++) {
			recJta[i] = new JTextField();
			recJta[i].setBorder(border);
			recPanel.add(recGuestLabel[i]);
			recPanel.add(recJta[i]);
		}
		recButton[0] = new JButton("Szukaj");
		recButton[1] = new JButton("Przelicz");
		recButton[2] = new JButton("Usu�");
		recButton[3] = new JButton("Wymelduj");
		recButton[4] = new JButton("Dokonaj wp�aty");
		for (int j = 0; j < 5; j++) {
			recButton[j].setBackground(buttonColor);
			recPanel.add(recButton[j]);
		}
		recRezLabel = new JLabel("Lista rezerwacji");
		recCenaJta = new JTextField("");
		recCenaJta.setBorder(border);
		recPanel.add(recPrice);
		recPanel.add(recCenaJta);
		recPrice = new JLabel("Kwota do zap�aty:");
		recPay = new JLabel("Forma p�atno�ci");
		recBil = new JLabel("Rodzaj rachunku");
		recPanel.add(recRezLabel);
		recCenaJta = new JTextField("");
		recCenaJta.setBorder(border);
		recPanel.add(recTable);
		recPanel.add(recPrice);
		recPanel.add(recPay);
		recPanel.add(recBil);
		recPanel.add(recCenaJta);
		recDateLabel = new JLabel("Rozpocz�cie pobytu: ");
		recDayLabel = new JLabel("Doby: ");
		recPayButton[0] = new JRadioButton("Got�wka");
		recPayButton[1] = new JRadioButton("Karta p�atnicza");
		recBilButton[0] = new JRadioButton("Paragon");
		recBilButton[1] = new JRadioButton("Faktura");
		recPanel.add(recPayButton[0]);
		recPanel.add(recPayButton[1]);
		recPanel.add(recBilButton[0]);
		recPanel.add(recBilButton[1]);
		recPayGroup = new ButtonGroup();
		recBilGroup = new ButtonGroup();
		recPayGroup.add(recPayButton[0]);
		recPayGroup.add(recPayButton[1]);
		recBilGroup.add(recBilButton[0]);
		recBilGroup.add(recBilButton[1]);
		recTable = recept.createTable("");
		recScrollPane = new JScrollPane(recTable);
		recScrollPane.setBorder(border);
		recScrollPane.setViewportView(recTable);
		recPanel.add(recScrollPane);
	}

	public static double round(double d, int ic) {
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
		nf.setMaximumFractionDigits(ic);
		nf.setMinimumFractionDigits(ic);
		System.out.println((nf.format(d)).replaceAll(",", ".").replaceAll("",
				""));
		return Double.parseDouble((nf.format(d)).replaceAll(",", ".")
				.replaceAll("", ""));
	}

	private void createCantor() {
		canPanel.setBounds(0, 0, getWidth(), getHeight());
		canPanel.setLayout(null);
		canPanel.addComponentListener(this);
		canPanel.setMinimumSize(new Dimension(800, 600));
		canPanel.setBackground(bgColor);
		canTable = new JTable();
		canClientTable = new JTable();
		canBuyLabel = new JLabel("Dostepno�� oraz kursy walut");
		canCurrL1 = new JLabel("Waluta wymieniana");
		canCurrL2 = new JLabel("Waluta ");
		canClientL = new JLabel("PESEL/KRS Klienta");
		canAmountJta = new JTextField("");
		canCostJta = new JTextField("");
		canPESJta = new JTextField("");
		canAmountJta.setBorder(border);
		canCostJta.setBorder(border);
		canPESJta.setBorder(border);
		canPanel.add(canPrice);
		canPanel.add(canAmountJta);
		canPanel.add(canCostJta);
		canPanel.add(canPESJta);
		canPanel.add(canTable);
		canPanel.add(canClientTable);
		canPrice = new JLabel("Kwota transakcji:");
		canCostLabel = new JLabel("Do zap�aty:");
		canPanel.add(canCostLabel);
		canPanel.add(canBuyLabel);
		canPanel.add(canCurrL1);
		canPanel.add(canCurrL2);
		canPanel.add(canClientL);
		canPanel.add(canPrice);
		canPanel.add(canPay);
		canCurrBox1 = new JComboBox(canCurr);
		canCurrBox2 = new JComboBox(canCurr);
		canPanel.add(canCurrBox1);
		canPanel.add(canCurrBox2);
		canResButton = new JButton("Przelicz");
		canPESButton = new JButton("Szukaj");
		canDoIt = new JButton("Dokonaj transakcji");
		canResButton.setBackground(buttonColor);
		canPESButton.setBackground(buttonColor);
		canDoIt.setBackground(buttonColor);
		canPanel.add(canResButton);
		canPanel.add(canPESButton);
		canPanel.add(canDoIt);
		canTable = cantor.createCurrTable();
		canScrollPane = new JScrollPane(canTable);
		canScrollPane.setBorder(border);
		canScrollPane.setViewportView(canTable);
		canPanel.add(canScrollPane);
		canScrollClientPane = new JScrollPane(canClientTable);
		canScrollClientPane.setBorder(border);
		canScrollClientPane.setViewportView(canClientTable);
		canPanel.add(canScrollClientPane);
	}

	private void createGUI() {
		setTitle("Hotel");
		setBounds(0, 0, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(1024, 768));

		Singleton.getInstance();

		createCantor();
		createSchedule();
		createReception();
		createRezervation();
		createManager();
		createGuestBook();
		try {
			createStatistic();
			createManagerPPanel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jtp.setBounds(0, 0, getWidth(), getHeight());
		jtp.addTab("Kantor", canPanel);
		jtp.addTab("Grafik", schPanel);
		jtp.addTab("Recepcja", recPanel);
		jtp.addTab("Rezerwacje", rezPanel);
		jtp.addTab("Ksi�ga go�ci", guePanelCl);
		jtp.addTab("Manager", manPanel);
		jtp.addTab("Statystyka", staPanel);
		jtp.addTab("Menad�er personelu", mgpPanel);

		add(jtp);

		addEvent();
		addStatEvent();
		addManagerPEvent();

		setVisible(true);
	}

	public GUI() {
		super();
		db = Singleton.getInstance();
		// createLogin();
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} catch (Exception e) {
			System.err.println("B��d :: LOOK AND FEEL");
		}
		createGUI();
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI();
			}
		});
	}

	private void addManagerPEvent() {
		mgpSchedText.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int a = (e.getY() / 18) * 18;
				int i = mgpSchedText.getSelectedIndex();
				String s = (String) mgpSchedText.getSelectedValue();
				if (i > 0 && s.charAt(0) == '[') {
					mgpChooseEmployee.setBounds(0, a, 250, 18);
					System.out.println(e.getX() + " " + a);
					mgpChooseEmployee.setVisible(true);
				} else
					mgpChooseEmployee.setVisible(false);
			}
		});

		// ///////////////-------------------b
		mgpChooseType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				boolean ret = false;
				int i = 0;
				mgpChooseEmployee.setVisible(false);
				if ((i = mgpChooseType.getSelectedIndex()) == 0) {
					if (++mgpClickCount == 2) {
						mgpClickCount = 0;
						ret = mgp.createSchedule(mgpMonth + 1);
						System.out.println((mgpMonth + 1));
						if (ret)
							JOptionPane.showMessageDialog(mgpPanel,
									"Stworzono grafik");
						else
							JOptionPane.showMessageDialog(mgpPanel,
									"Grafik na ten miesiac juz istnieje");
						mgpChooseType.setSelectedIndex(2);
					}
				} else if (i == 1) {
					if (++mgpClickCount == 2) {
						mgpClickCount = 0;
						ret = mgp.delSchedule(mgpMonth + 1);
						if (ret)
							JOptionPane.showMessageDialog(mgpPanel,
									"Usunieto grafik");
						else
							JOptionPane.showMessageDialog(mgpPanel,
									"Grafik na ten miesiac nie istnieje");
						mgpChooseType.setSelectedIndex(2);
					}
				} else {
					for (i = 0; i < mgpServe.length; i++)
						if (mgpChooseType.getSelectedIndex() == (i + 2))
							mgpServe[i].setVisible(true);
						else
							mgpServe[i].setVisible(false);
				}
			}
		});

		// ////////////////////////-------------------e
		for (int i = 0; i < 31; i++)
			mgpDays[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					JButton tmp = (JButton) e.getSource();
					System.out.println("dzien: " + tmp.getText());
					mgpDay = Integer.valueOf(tmp.getText());
					mgpSchedText.setListData(mgp.getDaySchedule(mgpYear + "/"
							+ (mgpMonth + 1) + "/" + mgpDay));
					mgpChooseEmployee.setVisible(false);
				}
			});
		mgpNext.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (++mgpMonth > 11) {
					mgpYear++;
					mgpMonth = 0;
				}
				resizePersonelMenager();
			}
		});
		mgpPrev.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (--mgpMonth < 0) {
					mgpYear--;
					mgpMonth = 11;
				}
				resizePersonelMenager();
			}
		});
		// ///////////////////-------------b
		mgpAdd.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				boolean ret = false;
				int i = 0;
				String msg = "";
				if (!Validation.isPesel(mgpAddEmploy[0].getText()))
					msg += "Pole PESEL ma nieprawid�ow� warto��\n";
				for (i = 1; i < 6; i++)
					if (mgpAddEmploy[i].getText().equals(""))
						msg += "Nie wype�ni�es pola "
								+ mgpAddLabel[i].getText() + "\n";
				if (!Validation.isNumber(mgpAddEmploy[7].getText()))
					msg += "Pole NR_LOKALU ma nieprawid�ow� warto��\n";
				if (msg == "") {
					ret = mgp.addEmployee(mgpAddEmploy[0].getText(),
							mgpAddEmploy[1].getText(),
							mgpAddEmploy[2].getText(),
							mgpAddEmploy[3].getText(),
							mgpAddEmploy[4].getText(),
							mgpAddEmploy[5].getText(),
							mgpAddEmploy[6].getText(),
							mgpAddEmploy[7].getText(),
							(String) mgpChooseAdd.getSelectedItem());
					if (ret) {
						if (mgpChooseAdd.getSelectedIndex() == 0) {
							mgpChooseEmployee.addItem(mgpAddEmploy[1].getText()
									+ " " + mgpAddEmploy[2].getText() + " "
									+ mgpAddEmploy[0].getText());

							System.out.println("ok dziala: "
									+ mgpAddEmploy[1].getText() + " "
									+ mgpAddEmploy[2].getText() + " "
									+ mgpAddEmploy[0].getText());
						} else
							System.out.println("lipa "
									+ mgpChooseAdd.getSelectedIndex());
					} else
						JOptionPane.showMessageDialog(mgpServe[1],
								"Osoba o podanych danych jest ju� w systemie");

				} else
					JOptionPane.showMessageDialog(mgpServe[1], "B��d!\n" + msg);
			}
		});
		// /////////////////////////////////////----e
		// //////////////-----------------------b
		mgpChooseEmployee.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (++mgpClickCount == 2) {
					if (!mgpIndel) {
						mgpClickCount = 0;
						String s = (String) mgpSchedText.getSelectedValue();
						String z = (String) mgpChooseEmployee.getSelectedItem();
						int ind = z.lastIndexOf(" ");
						System.out.println("index: "
								+ mgpSchedText.getSelectedIndex());
						mgpChooseEmployee.setVisible(false);
						System.out.println("pesel: "
								+ z.substring(ind, z.length()));
						System.out.println("dane: " + z.substring(0, ind));
						mgpSchedText.setListData(mgp.updateSchedule(
								mgpSchedText.getSelectedIndex(),
								z.substring(0, ind), mgpYear + "/"
										+ (mgpMonth + 1) + "/" + mgpDay,
								z.substring(ind + 1, z.length()),
								s.substring(1, s.indexOf(" "))));

						mgpSchedText.repaint();
					} else
						mgpIndel = false;
				}
			}
		});
		// //////////////////////////////----------------e
		// ///////////////////------------b
		mgpDel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = mgpTable.getSelectedRow();

				boolean ret = false;
				String s = "";
				if (i != -1) {
					System.out.println("table index: " + i);
					s = (String) mgpTable.getValueAt(i, 1) + " "
							+ (String) mgpTable.getValueAt(i, 2) + " "
							+ (String) mgpTable.getValueAt(i, 0);
					System.out.println("table selected item: " + s);
					ret = mgp.delEmployee((String) mgpTable.getValueAt(i, 0));
					mgpIndel = true;
					if (ret) {
						// mgpTable.removeRowSelectionInterval(i,i);
						// mgpServe[2].remove(mgpTableScroll);

					}
					for (i = 0; i < mgpChooseEmployee.getItemCount(); i++) {
						if (mgpChooseEmployee.getItemAt(i).equals(s)) {
							System.out.println("is equla: " + i);
							mgpChooseEmployee.removeItemAt(i);
						}

					}
					mgpTableScroll.repaint();
				}
			}
		});
		// ///////////////////////////-----------------e
		mgpFind.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = (mgpServe[2].getWidth() - 40);
				mgpMatrix = mgp.findEmployee(mgpDelEmploy[0].getText(),
						mgpDelEmploy[1].getText(), mgpDelEmploy[2].getText(),
						(String) mgpChooseDel.getSelectedItem());
				mgpServe[2].remove(mgpTableScroll);
				mgpTable = new JTable(mgpMatrix, mgpColsName);
				mgpTable.setFillsViewportHeight(true);
				mgpTableScroll = new JScrollPane(mgpTable);
				mgpTableScroll.setBounds(20, 165, (int) (i * 0.8),
						mgpServe[2].getHeight() - 175);
				mgpTable.setSize((int) (i * 0.8), mgpServe[2].getHeight() - 175);
				mgpServe[2].add(mgpTableScroll);
				mgpServe[2].repaint();
				if (mgp.getRowCount() > 0)
					mgpDel.setVisible(true);
				if (!mgpDelEmploy[0].getText().equals("")
						&& !Validation.isPesel(mgpDelEmploy[0].getText()))
					JOptionPane.showMessageDialog(mgpServe[1],
							"Pole PESEL ma nieprawid�ow� warto��\n");
			}
		});
		mgpCreat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mgpRaportText.setText("Raport z zarobk�w za miesi�c "
						+ mgpChooseMonth.getSelectedItem()
						+ "\n"
						+ mgp.getPaysRaport(mgpChooseMonth.getSelectedIndex() + 1));
				mgpRaportText.setVisible(true);

			}
		});
	}

	private void addStatEvent() {
		staExec.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				staMem.setVisible(false);
				if (staChooseType.getSelectedIndex() == 0)
					sta.hotel(staChooseSubHot.getSelectedIndex(),
							staChooseMonth.getSelectedIndex() + 1,
							staChooseYear.getSelectedIndex() + 2010,
							(String) staChooseClass.getSelectedItem(),
							(String) staChooseServe.getSelectedItem());
				else
					sta.finance(staChooseSubFin.getSelectedIndex(),
							staChooseMonth2.getSelectedIndex() + 1,
							staChooseMonth.getSelectedIndex() + 1,
							staChooseYear.getSelectedIndex() + 2010,
							staChooseYear2.getSelectedIndex() + 2010);
				staMem.setArray(sta.getArrayResult());
				staRaportText.setText(sta.getTextResult());
				staMem.setVisible(true);
				staRaportScroll.setVisible(true);
			}
		});
		staChooseType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (staChooseType.getSelectedIndex() == 0) {
					staYearLabel.setLocation(260, 45);
					staYearLabel.setVisible(true);
					staChooseYear.setLocation(260, 60);
					staChooseYear.setVisible(true);
					staChooseYear2.setVisible(false);
					staChooseSubHot.setVisible(true);
					staChooseSubFin.setVisible(false);
					staChooseMonth.setVisible(true);
					staChooseMonth2.setVisible(false);
					staMonthLabel.setVisible(true);
					staMonthLabelFrom.setVisible(false);
					staYearLabelFrom.setVisible(false);
					staMonthLabelTo.setVisible(false);
					staYearLabelTo.setVisible(false);
					staChooseSubHot.setSelectedIndex(0);
				} else {
					staChooseClass.setVisible(false);
					staChooseServe.setVisible(false);
					staServeLabel.setVisible(false);
					staClassLabel.setVisible(false);
					staChooseSubHot.setVisible(false);
					staChooseSubFin.setVisible(true);
					staYearLabel.setLocation(500, 45);
					staChooseYear.setLocation(500, 60);
					staChooseMonth.setVisible(true);
					staChooseMonth2.setVisible(true);
					staChooseYear.setVisible(true);
					staChooseYear2.setVisible(false);
					staMonthLabel.setVisible(false);
					staMonthLabelFrom.setVisible(true);
					staMonthLabelTo.setVisible(true);
					staChooseSubFin.setSelectedIndex(0);
				}
			}
		});
		staChooseSubHot.addItemListener(new ItemListener() {
			int i = 0;

			public void itemStateChanged(ItemEvent arg0) {
				if ((i = staChooseSubHot.getSelectedIndex()) == 1) {
					staChooseClass.setVisible(true);
					staClassLabel.setVisible(true);
					staChooseServe.setVisible(false);
					staServeLabel.setVisible(false);
				} else if (i == 3) {
					staChooseClass.setVisible(false);
					staClassLabel.setVisible(false);
					staChooseServe.setVisible(true);
					staServeLabel.setVisible(true);
				} else {
					staChooseClass.setVisible(false);
					staClassLabel.setVisible(false);
					staChooseServe.setVisible(false);
					staServeLabel.setVisible(false);
				}
			}
		});
		staChooseSubFin.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (staChooseSubFin.getSelectedIndex() == 0) {
					staChooseYear.setLocation(500, 60);
					staYearLabel.setVisible(true);
					staChooseMonth.setVisible(true);
					staChooseMonth2.setVisible(true);
					staChooseYear.setVisible(true);
					staChooseYear2.setVisible(false);
					staMonthLabelFrom.setVisible(true);
					staMonthLabelTo.setVisible(true);
					staYearLabelFrom.setVisible(false);
					staYearLabelTo.setVisible(false);
				} else {
					staYearLabel.setVisible(false);
					staChooseYear.setLocation(260, 60);
					staChooseMonth.setVisible(false);
					staChooseMonth2.setVisible(false);
					staChooseYear.setVisible(true);
					staChooseYear2.setVisible(true);
					staMonthLabelFrom.setVisible(false);
					staMonthLabelTo.setVisible(false);
					staYearLabelFrom.setVisible(true);
					staYearLabelTo.setVisible(true);
				}
			}
		});
	}

	void addEvent() {

		canPESButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (canPESJta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(canPanel,
							"Nie podano numeru PESEL/KRS");
				} else {
					if (!cantor.isPesel(canPESJta.getText())
							&& !cantor.isKRS(canPESJta.getText()))
						JOptionPane.showMessageDialog(canPanel,
								"Nieprawid�owy PESEL/KRS");
					else if (cantor.isPesel(canPESJta.getText())) {
						canClientTable = cantor
								.createClientTable(" where IDK_PESEL="
										+ canPESJta.getText());
						if (canClientTable.getRowCount() < 1)
							JOptionPane.showMessageDialog(canPanel,
									"Brak klienta w bazie");
						System.out.println(canClientTable.getRowCount());
						canScrollClientPane.setViewportView(canClientTable);
						canScrollClientPane.repaint();
					} else if (cantor.isKRS(canPESJta.getText())) {
						canClientTable = cantor.createCompTable(canPESJta
								.getText());
						canScrollClientPane.setViewportView(canClientTable);
						canScrollClientPane.repaint();

					}
				}
			}
		});

		canResButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean ifNumber = true;
				if (canAmountJta.getText().isEmpty())
					JOptionPane.showMessageDialog(canPanel, "Nie podano kwoty");
				else {
					for (int i = 0; i < canAmountJta.getText().length(); ++i) {
						if (canAmountJta.getText().charAt(i) < '0'
								|| canAmountJta.getText().charAt(i) > '9') {
							ifNumber = false;
						}
					}
					if (!ifNumber)
						JOptionPane.showMessageDialog(canPanel,
								"Podaj prawidlowa ilosc waluty");
					else {
						if (canCurrBox1.getSelectedItem().toString() == canCurrBox2
								.getSelectedItem().toString())
							JOptionPane.showMessageDialog(canPanel,
									"Nie mo�na wymienia� na t� sam� walut�");
						else {
							results = cantor.changeCalc(canCurrBox1
									.getSelectedItem().toString(), canCurrBox2
									.getSelectedItem().toString(), Float
									.valueOf(canAmountJta.getText())
									.floatValue());
							canCostJta.setText(Double.toString(round(
									results[1], 2)));

						}
					}
				}
			}

		});

		canDoIt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (canCostJta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(canPanel,
							"Brak nale�nej kwoty");
				} else if (canClientTable.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(canPanel,
							"Nie zaznaczono klienta");
				} else {
					if (cantor.changeMoney(
							cantor.isPesel(canPESJta.getText()),
							canClientTable.getValueAt(
									canClientTable.getSelectedRow(), 0)
									.toString(), cantor.ShowDate(), canCurrBox1
									.getSelectedItem().toString(), canCurrBox2
									.getSelectedItem().toString(), results[0],
							results[1], results[2])) {
						JOptionPane.showMessageDialog(canPanel,
								"Dokonano transakcji");
						canPESJta.setText("");
						canCurrBox1.setSelectedIndex(0);
						canCurrBox2.setSelectedIndex(0);
						canCostJta.setText("");
						canTable = cantor.createCurrTable();
						canScrollPane.setViewportView(canTable);
						canScrollPane.repaint();
					} else
						JOptionPane.showMessageDialog(canPanel,
								"Nieprawid�owe dane");
					;
				}
			}
		});

		recButton[0].addMouseListener(new MouseListener() {
			boolean pes = false, krs = false, id = false, date = false;
			String sel = "";

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				if (recJta[0].getText().isEmpty()
						&& recJta[1].getText().isEmpty()
						&& recJta[2].getText().isEmpty())
					JOptionPane.showMessageDialog(recPanel, "Podaj parametry",
							"B��d", 0);
				else {
					if (!recept.isNumber(recJta[0].getText())) {
						id = false;
						JOptionPane.showMessageDialog(recPanel,
								"Nieprawidlowy numer rezerwacji",
								"Nieuznany parametr", 0);
					} else if (recept.isNumber(recJta[0].getText())
							&& !recJta[0].getText().isEmpty()) {
						id = true;
						sel = sel + " where ID_REZ=" + recJta[0].getText();
					}
					if (!recJta[1].getText().isEmpty()) {
						pes = recept.isPesel(recJta[1].getText());
						krs = recept.isKRS(recJta[1].getText());
						if (!pes && !krs)
							JOptionPane.showMessageDialog(recPanel,
									"Nieprawidlowy PESEL/KRS",
									"Nieuznany parametr", 0);
						else if (!pes && krs) {
							if (id) {
								sel = sel + " AND";
								sel = sel + " IDF_KRS=" + recJta[1];
							} else {
								sel = sel + " where IDF_KRS=" + recJta[1];
							}
						} else if (pes && !krs) {
							if (id) {
								sel = sel + " AND";
								sel = sel + " IDK_PESEL=" + recJta[1].getText();
							} else {
								sel = sel + " where IDK_PESEL="
										+ recJta[1].getText();
							}
						}
					}
					if (!recJta[2].getText().isEmpty()) {
						date = recept.isDate(recJta[2].getText());
						if (!date) {
							JOptionPane.showMessageDialog(recPanel,
									"Nieprawid�owa data", "Nieuznany parametr",
									0);
							return;

						} else if (id || pes || krs) {
							sel = sel + " AND";
							sel = sel + " DATA_W=" + "'" + recJta[2].getText()
									+ "'";
						} else {
							sel = sel + " where DATA_W=" + "'"
									+ recJta[2].getText() + "'";
						}
					}
				}
				recTable = recept.createTable(sel);
				recScrollPane.setViewportView(recTable);
				recScrollPane.repaint();
				id = false;
				pes = false;
				krs = false;
				date = false;
				sel = "";

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		recButton[4].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				String form, name;
				float tax, much = 0;
				DateFormat dfY = new SimpleDateFormat("yyyy");
				DateFormat dfM = new SimpleDateFormat("MM");
				DateFormat dfD = new SimpleDateFormat("dd");
				Calendar calendar = Calendar.getInstance();
				String sYear = dfY.format(calendar.getTime());
				String sMonth = dfM.format(calendar.getTime());
				String sDay = dfD.format(calendar.getTime());
				String date1;

				date1 = sYear + "-" + sMonth + "-" + sDay;
				if (recTable.getSelectedColumnCount() < 1) {
					JOptionPane.showMessageDialog(recPanel,
							"Nie zaznaczono rezerwacji", "B��d", 0);
				} else if (recTable.getValueAt(0, 0) == "Brak danych") {
					JOptionPane.showMessageDialog(recPanel, "Brak danych",
							"B��d", 0);
				} else {
					if (recCenaJta.getText().isEmpty())
						JOptionPane.showMessageDialog(recPanel,
								"Nie przeliczono kwoty", "B��d", 0);
					else {
						if (recPayButton[0].isSelected())
							form = "Got�wka";
						else
							form = "Karta";
						if (recBilButton[0].isSelected())
							name = "Paragon";
						else
							name = "Faktura";

						much = Float.parseFloat(recCenaJta.getText());
						tax = (float) (much * 0.22);
						if (recept.pay(Integer.parseInt(recTable.getValueAt(
								recTable.getSelectedRow(), 0).toString()),
								date1, form, tax, much, name))
							JOptionPane.showMessageDialog(recPanel,
									"Dokonano zap�aty", "Informacja", 1);
						else
							JOptionPane.showMessageDialog(recPanel,
									"Zap�ata zosta�a dokonana wcze�niej",
									"B��d", 0);
					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		recButton[1].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				int row = -1;
				float cost;
				row = recTable.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(recPanel,
							"Nie zaznaczono zadnej kolumny",
							"Brak zaznaczenia", 1);
				else {
					if (recTable.getValueAt(0, 0) == "Brak danych") {
						JOptionPane.showMessageDialog(recPanel, "Brak danych!");
					} else {
						if ((cost = recept.calculate(Integer.parseInt(recTable
								.getValueAt(recTable.getSelectedRow(), 0)
								.toString()))) < 1)
							JOptionPane.showMessageDialog(recPanel,
									"Pobyt jeszcze si� nie rozpocz��");
						else {
							recCenaJta.setText(Float.toString(cost));
							row = -1;
						}
					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		recButton[2].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				int row = -1;
				row = recTable.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(recPanel,
							"Nie zaznaczono zadnej kolumny",
							"Brak zaznaczenia", 1);
				else {
					if (recTable.getValueAt(0, 0) == "Brak danych") {
						JOptionPane.showMessageDialog(recPanel, "Brak danych!");
					} else {
						recept.deleteRez(Integer.parseInt(recTable.getValueAt(
								recTable.getSelectedRow(), 0).toString()));
						JOptionPane.showMessageDialog(recPanel,
								"Usuni�to rezerwacj�");
						recTable = recept.createTable("");
						recScrollPane.setViewportView(recTable);
						recScrollPane.repaint();

					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});
		recButton[3].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				int row = -1;
				boolean kind;
				long idK = 0;
				// float cost;
				row = recTable.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(recPanel,
							"Nie zaznaczono zadnej kolumny",
							"Brak zaznaczenia", 1);
				else {
					if (recTable.getValueAt(recTable.getSelectedRow(), 0)
							.toString() == "Brak danych")
						JOptionPane.showMessageDialog(recPanel, "Brak danych!",
								"B��d", 1);
					else if (recept.checkPay(Integer.parseInt(recTable
							.getValueAt(recTable.getSelectedRow(), 0)
							.toString()))) {
						if (recTable.getValueAt(recTable.getSelectedRow(), 2) == null) {
							kind = true;
							idK = Long.parseLong(recTable.getValueAt(
									recTable.getSelectedRow(), 1).toString());
						} else {
							kind = false;
							idK = Long.parseLong(recTable.getValueAt(
									recTable.getSelectedRow(), 2).toString());
						}
						recept.archivRez(
								Integer.parseInt(recTable.getValueAt(
										recTable.getSelectedRow(), 0)
										.toString()),
								kind,
								idK,
								Integer.parseInt(recTable.getValueAt(
										recTable.getSelectedRow(), 3)
										.toString()),
								Integer.parseInt(recTable.getValueAt(
										recTable.getSelectedRow(), 4)
										.toString()),
								recTable.getValueAt(recTable.getSelectedRow(),
										5).toString(),
								recTable.getValueAt(recTable.getSelectedRow(),
										6).toString());
						recept.deleteRez(Integer.parseInt(recTable.getValueAt(
								recTable.getSelectedRow(), 0).toString()));
						recTable = recept.createTable("");
						recScrollPane.setViewportView(recTable);
						recScrollPane.repaint();
					} else {
						JOptionPane.showMessageDialog(recPanel,
								"Nie dokonano wp�aty za pobyt", "Brak wp�aty",
								0);
					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		rezButton[0].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (rezJta[0].getText().isEmpty())
					JOptionPane.showMessageDialog(rezPanel, "Podaj parametry",
							"B��d", 0);
				else {
					if (rezClient.isSelected()) {
						if (!rezerv.isPesel(rezJta[0].getText()))
							JOptionPane.showMessageDialog(rezPanel,
									"�le wprowadzono PESEL", "B��d", 0);
						else {
							rezTable = rezerv.createTable(true,
									Long.parseLong(rezJta[0].getText()));
							if (rezTable == null) {
								JOptionPane.showMessageDialog(rezPanel,
										"Nie ma takiego klienta", "Informacja",
										1);
								rezJta[0].setText("");
								rezJta[1].setText("");
								rezJta[2].setText("");
								rezJta[3].setText("");
								rezJta[4].setText("");
								rezJta[5].setText("");
								rezJta[6].setText("");
								rezJta[7].setText("");
								rezJta[8].setText("");
								rezJta[9].setText("");
								rezJta[10].setText("");

							} else {
								rezJta[1].setText((String) rezTable.getValueAt(
										0, 1));
								rezJta[2].setText((String) rezTable.getValueAt(
										0, 2));
								rezJta[3].setText((String) rezTable.getValueAt(
										0, 3));
								rezJta[4].setText((String) rezTable.getValueAt(
										0, 4));
								rezJta[5].setText((String) rezTable.getValueAt(
										0, 5));
								rezJta[6].setText((String) rezTable.getValueAt(
										0, 6));
								rezJta[7].setText((String) rezTable.getValueAt(
										0, 7));
								rezJta[8].setText((String) rezTable.getValueAt(
										0, 8));
								rezJta[9].setText((String) rezTable.getValueAt(
										0, 9));
								rezJta[10].setText((String) rezTable
										.getValueAt(0, 10));
							}
						}
					} else if (!rezerv.isKRS(rezJta[0].getText()))
						JOptionPane.showMessageDialog(rezPanel,
								"�le wprowadzono KRS", "B��d", 0);
					else {
						rezTable = rezerv.createTable(false,
								Long.parseLong(rezJta[0].getText()));
						if (rezTable == null)
							JOptionPane.showMessageDialog(rezPanel,
									"Nie ma takiej firmy", "Informacja", 1);
						else {
							rezJta[1].setText((String) rezTable
									.getValueAt(0, 1));
							rezJta[2].setText((String) rezTable
									.getValueAt(0, 2));
							rezJta[3].setText((String) rezTable
									.getValueAt(0, 3));
							rezJta[4].setText((String) rezTable
									.getValueAt(0, 4));
							rezJta[5].setText((String) rezTable
									.getValueAt(0, 5));
							rezJta[6].setText((String) rezTable
									.getValueAt(0, 6));
							rezJta[7].setText((String) rezTable
									.getValueAt(0, 7));
							rezJta[8].setText((String) rezTable
									.getValueAt(0, 8));
							rezJta[9].setText((String) rezTable
									.getValueAt(0, 9));
							rezJta[10].setText((String) rezTable.getValueAt(0,
									10));
						}
					}
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		rezClient.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = 0; i < rezCompLabel.length; i++) {
					rezCompLabel[i].setVisible(false);
					rezGuestLabel[i].setVisible(true);
					rezIfExistC.setVisible(true);
					rezIfExistF.setSelected(false);
					rezIfExistF.setVisible(false);
					rezJta[0].setText("");
					rezJta[1].setText("");
					rezJta[2].setText("");
					rezJta[3].setText("");
					rezJta[4].setText("");
					rezJta[5].setText("");
					rezJta[6].setText("");
					rezJta[7].setText("");
					rezJta[8].setText("");
					rezJta[9].setText("");
					rezJta[10].setText("");
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		rezCat1.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				rezServTable = rezerv.createServTable("rekreacja");
				rezServTable.addMouseListener(rezMous);
				if (rezServTable == null) {
					JOptionPane.showMessageDialog(rezPanel, "Brak us�ug",
							"B��d", 1);
				} else {
					rezServPane.setViewportView(rezServTable);
					rezServPane.repaint();
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		rezCat2.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				rezServTable = rezerv.createServTable("gastronomia");
				rezServTable.addMouseListener(rezMous);
				if (rezServTable == null) {
					JOptionPane.showMessageDialog(rezPanel, "Brak us�ug",
							"B��d", 1);
				} else {
					rezServPane.setViewportView(rezServTable);
					rezServPane.repaint();
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		rezCat3.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				rezServTable = rezerv.createServTable("biznes");
				rezServTable.addMouseListener(rezMous);
				if (rezServTable == null) {
					JOptionPane.showMessageDialog(rezPanel, "Brak us�ug",
							"B��d", 1);
				} else {
					rezServPane.setViewportView(rezServTable);
					rezServPane.repaint();
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		rezCompany.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = 0; i < rezCompLabel.length; i++) {
					rezGuestLabel[i].setVisible(false);
					rezCompLabel[i].setVisible(true);
					rezIfExistC.setSelected(false);
					rezIfExistC.setVisible(false);
					rezIfExistF.setVisible(true);
					rezJta[0].setText("");
					rezJta[1].setText("");
					rezJta[2].setText("");
					rezJta[3].setText("");
					rezJta[4].setText("");
					rezJta[5].setText("");
					rezJta[6].setText("");
					rezJta[7].setText("");
					rezJta[8].setText("");
					rezJta[9].setText("");
					rezJta[10].setText("");
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		rezClasTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if ((rezRoomListLabel = rezerv.createRoomList(
							Integer.parseInt(rezClasTable.getValueAt(
									rezClasTable.getSelectedRow(), 0)
									.toString()),
							rezYears[rezDateBox[2].getSelectedIndex()]
									.toString()
									+ "-"
									+ months[rezDateBox[1].getSelectedIndex()]
											.toString()
									+ "-"
									+ rezDays[rezDateBox[0].getSelectedIndex()]
											.toString(), rezerv.addDate(
									rezYears[rezDateBox[2].getSelectedIndex()]
											.toString()
											+ "-"
											+ months[rezDateBox[1]
													.getSelectedIndex()]
													.toString()
											+ "-"
											+ rezDays[rezDateBox[0]
													.getSelectedIndex()]
													.toString(), rezDayBox
											.getSelectedIndex() + 1))) == null) {
						rezRoomListLabel = new String[1];
						rezRoomListLabel[0] = "Brak wolnych pokoi";
					} else {
						rezRoomList = new JList(rezRoomListLabel);
						rezRoomPane.setViewportView(rezRoomList);
						rezRoomPane.repaint();
					}
				} catch (NumberFormatException e1) {
				} catch (ParseException e1) {
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		rezServTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				listMod.addElement(rezServTable.getValueAt(
						rezServTable.getSelectedRow(), 1).toString());
				rezServPane1.setViewportView(rezServList);
				rezServPane1.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		rezServList.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (listMod.isEmpty()) {

				} else {
					listMod.removeElementAt(rezServList.getSelectedIndex());
					rezServPane1.setViewportView(rezServList);
					rezServPane1.repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		rezButton[3].addMouseListener(new MouseListener() {
			int monthInt;
			String monthStr;
			String[] servTab;

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (rezRoomList.getSelectedIndex() < 0)
					JOptionPane.showMessageDialog(rezPanel,
							"Nie wybrano pokoju", "B��d", 1);
				else {
					monthInt = rezDateBox[1].getSelectedIndex() + 1;
					if (monthInt < 10)
						monthStr = "0" + String.valueOf(monthInt);
					else
						monthStr = String.valueOf(monthInt);

					try {
						if (rezerv
								.isAfter((rezDateBox[2].getSelectedItem()
										.toString() + "-" + monthStr + "-" + rezDateBox[0]
										.getSelectedItem().toString()))
								&& rezerv.isDate((rezDateBox[2]
										.getSelectedItem().toString()
										+ "-"
										+ monthStr + "-" + rezDateBox[0]
										.getSelectedItem().toString()))) {
							servTab = new String[rezServList.getModel()
									.getSize()];
							for (int i = 0; i < rezServList.getModel()
									.getSize(); i++) {
								rezServList.setSelectedIndex(i);
								servTab[i] = rezServList.getSelectedValue()
										.toString();

							}
							if (rezRoomList.getSelectedValue().toString() == "Brak danych")
								JOptionPane.showMessageDialog(rezPanel,
										"Niepoprawna data", "B��d", 0);
							else
								rezCenaJta.setText(String.valueOf(rezerv.calculate(
										Integer.parseInt(rezRoomList
												.getSelectedValue().toString()),
										rezDayBox.getSelectedIndex() + 1,
										rezServList.getModel().getSize(),
										servTab)));
							rezCenaJta.setEditable(false);
						} else
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawna data", "B��d", 0);

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		rezButton[2].addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				boolean err = true;
				String ch1;
				String ch2;
				if (rezClient.isSelected()) {
					for (int k = 0; k < 11; k++) {
						if (k == 5 || k == 10) {

						} else if (rezJta[k].getText().isEmpty()) {
							err = false;
						}
					}
					if (!err)
						JOptionPane.showMessageDialog(rezPanel,
								"Wprowad� kompletne dane", "B��d", 1);
					else {
						if (!rezerv.isPesel(rezJta[0].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny PESEL", "B��d", 1);
							err = false;
						} else if (!rezerv.isNumber(rezJta[6].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny numer lokalu", "B��d", 1);
							err = false;
						} else if (!rezerv.isNumber(rezJta[8].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny numer telefonu", "B��d", 1);
							err = false;
						} else if (rezerv.isValidNip(rezJta[9].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny NIP", "B��d", 1);
							err = false;
						} else {
							if (rezCenaJta.getText().isEmpty()) {
								JOptionPane.showMessageDialog(rezPanel,
										"Nie przeliczono pobytu", "B��d", 1);
								err = false;
							}
						}
					}
					if (err) {
						if (rezJta[5].getText().isEmpty())
							ch1 = "";
						else
							ch1 = rezJta[5].getText();
						if (rezJta[10].getText().isEmpty())
							ch2 = "";
						else
							ch2 = rezJta[10].getText();

						if (!rezIfExistC.isSelected()) {

							if (rezerv.addClient(
									Long.parseLong(rezJta[0].getText()),
									rezJta[1].getText(), rezJta[2].getText(),
									rezJta[3].getText(), rezJta[4].getText(),
									ch1, Integer.parseInt(rezJta[6].getText()),
									rezJta[7].getText(),
									Long.parseLong(rezJta[8].getText()),
									Long.parseLong(rezJta[9].getText()), ch2)) {
								JOptionPane.showMessageDialog(rezPanel,
										"Dodano Klienta", "Informacja", 1);
								try {
									rezerv.doRezerv(
											true,
											Long.parseLong(rezJta[0].getText()),
											Integer.parseInt(rezRoomList
													.getSelectedValue()
													.toString()),

											rezYears[rezDateBox[2]
													.getSelectedIndex()]
													.toString()
													+ "-"
													+ months[rezDateBox[1]
															.getSelectedIndex()]
															.toString()
													+ "-"
													+ rezDays[rezDateBox[0]
															.getSelectedIndex()]
															.toString(),
											rezerv.addDate(
													rezYears[rezDateBox[2]
															.getSelectedIndex()]
															.toString()
															+ "-"
															+ months[rezDateBox[1]
																	.getSelectedIndex()]
																	.toString()
															+ "-"
															+ rezDays[rezDateBox[0]
																	.getSelectedIndex()]
																	.toString(),
													rezDayBox
															.getSelectedIndex() + 1));
									rezRoomListLabel = rezerv.createRoomList(0,
											"2010-01-01", "2011-01-01");
									rezRoomList = new JList(rezRoomListLabel);
									rezRoomPane.setViewportView(rezRoomList);
									rezRoomPane.repaint();
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(rezPanel,
										"Nie zaznaczono,�e klient istnieje",
										"Klient istnieje", 1);
							}
						} else {
							try {
								if (rezerv.doRezerv(
										true,
										Long.parseLong(rezJta[0].getText()),
										Integer.parseInt(rezRoomList
												.getSelectedValue().toString()),
										rezYears[rezDateBox[2]
												.getSelectedIndex()].toString()
												+ "-"
												+ months[rezDateBox[1]
														.getSelectedIndex()]
														.toString()
												+ "-"
												+ rezDays[rezDateBox[0]
														.getSelectedIndex()]
														.toString(),
										rezerv.addDate(
												rezYears[rezDateBox[2]
														.getSelectedIndex()]
														.toString()
														+ "-"
														+ months[rezDateBox[1]
																.getSelectedIndex()]
																.toString()
														+ "-"
														+ rezDays[rezDateBox[0]
																.getSelectedIndex()]
																.toString(),
												rezDayBox.getSelectedIndex() + 1)))
									JOptionPane.showMessageDialog(rezPanel,
											"Nie dokonano rezerwacji",
											"Brak klienta", 1);
								else
									JOptionPane.showMessageDialog(rezPanel,
											"Dokonano rezerwacji",
											"Informacja", 1);
								rezRoomListLabel = rezerv.createRoomList(0,
										"2010-01-01", "2011-01-01");
								rezRoomList = new JList(rezRoomListLabel);
								rezRoomPane.setViewportView(rezRoomList);
								rezRoomPane.repaint();

							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}

				else if (rezCompany.isSelected()) {
					for (int k = 0; k < 11; k++) {
						if (k == 4 || k == 10) {

						} else if (rezJta[k].getText().isEmpty()) {
							err = false;
						}
					}
					if (!err)
						JOptionPane.showMessageDialog(rezPanel,
								"Wprowad� kompletne dane", "B��d", 0);
					else {
						if (!rezerv.isKRS(rezJta[0].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny KRS", "B��d", 0);
							err = false;
						} else if (!rezerv.isNumber(rezJta[5].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny numer lokalu", "B��d", 0);
							err = false;
						} else if (!rezerv.isNumber(rezJta[8].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny numer telefonu", "B��d", 0);
							err = false;
						} else if (!rezerv.isNumber(rezJta[7].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny REGON", "B��d", 0);
							err = false;
						} else if (!rezerv.isNumber(rezJta[8].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny NIP", "B��d", 0);
							err = false;
						} else if (rezerv.isValidNip(rezJta[9].getText())) {
							JOptionPane.showMessageDialog(rezPanel,
									"Niepoprawny numer telefonu", "B��d", 0);
							err = false;
						} else {
							if (rezCenaJta.getText().isEmpty()) {
								JOptionPane.showMessageDialog(rezPanel,
										"Nie przeliczono pobytu", "B��d", 0);
								err = false;
							}
						}
					}
					if (err) {
						if (rezJta[4].getText().isEmpty())
							ch1 = "";
						else
							ch1 = rezJta[4].getText();
						if (rezJta[10].getText().isEmpty())
							ch2 = "";
						else
							ch2 = rezJta[10].getText();

						if (!rezIfExistF.isSelected()) {

							if (rezerv.addComp(
									Long.parseLong(rezJta[0].getText()),
									rezJta[1].getText(), rezJta[2].getText(),
									rezJta[3].getText(), ch1,
									Integer.parseInt(rezJta[5].getText()),
									rezJta[6].getText(),
									Long.parseLong(rezJta[7].getText()),
									Long.parseLong(rezJta[8].getText()),
									Long.parseLong(rezJta[8].getText()), ch2)) {
								JOptionPane.showMessageDialog(rezPanel,
										"Dodano Firme", "Informacja", 1);
								try {
									rezerv.doRezerv(
											false,
											Long.parseLong(rezJta[0].getText()),
											Integer.parseInt(rezRoomList
													.getSelectedValue()
													.toString()),
											rezYears[rezDateBox[2]
													.getSelectedIndex()]
													.toString()
													+ "-"
													+ months[rezDateBox[1]
															.getSelectedIndex()]
															.toString()
													+ "-"
													+ rezDays[rezDateBox[0]
															.getSelectedIndex()]
															.toString(),
											rezerv.addDate(
													rezYears[rezDateBox[2]
															.getSelectedIndex()]
															.toString()
															+ "-"
															+ months[rezDateBox[1]
																	.getSelectedIndex()]
																	.toString()
															+ "-"
															+ rezDays[rezDateBox[0]
																	.getSelectedIndex()]
																	.toString(),
													rezDayBox
															.getSelectedIndex() + 1));
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(rezPanel,
										"Nie zaznaczono,�e firma istnieje",
										"Firma istnieje", 1);
							}
						} else {
							try {
								if (rezerv.doRezerv(
										false,
										Long.parseLong(rezJta[0].getText()),
										Integer.parseInt(rezRoomList
												.getSelectedValue().toString()),
										rezYears[rezDateBox[2]
												.getSelectedIndex()].toString()
												+ "-"
												+ months[rezDateBox[1]
														.getSelectedIndex()]
														.toString()
												+ "-"
												+ rezDays[rezDateBox[0]
														.getSelectedIndex()]
														.toString(),
										rezerv.addDate(
												rezYears[rezDateBox[2]
														.getSelectedIndex()]
														.toString()
														+ "-"
														+ months[rezDateBox[1]
																.getSelectedIndex()]
																.toString()
														+ "-"
														+ rezDays[rezDateBox[0]
																.getSelectedIndex()]
																.toString(),
												rezDayBox.getSelectedIndex() + 1)))
									JOptionPane.showMessageDialog(rezPanel,
											"Nie dokonano rezerwacji",
											"Brak klienta", 1);
								else
									JOptionPane.showMessageDialog(rezPanel,
											"Dokonano rezerwacji",
											"Informacja", 1);
								rezRoomListLabel = rezerv.createRoomList(0,
										"2010-01-01", "2011-01-01");
								rezRoomList = new JList(rezRoomListLabel);
								rezRoomPane.setViewportView(rezRoomList);
								rezRoomPane.repaint();

							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		schPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (--m < 0) {
					m = 11;
					y--;
				}
				schInitCalendar(d, m, y);
				schTable = sch.getTable(0, m + 1, y);
				schScrollPane.setViewportView(schTable);
			}
		});
		schNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (++m > 11) {
					m = 0;
					y++;
				}
				schInitCalendar(d, m, y);
				schTable = sch.getTable(0, m + 1, y);
				schScrollPane.setViewportView(schTable);
			}
		});
	}

	public void resizeRezervation() {
		rezEWidth = (rezPanel.getWidth() - 200) / 3;
		rezJtaHeight = 18;
		rezXJta = k;
		rezYJta = 50;
		rezYList = 300;
		rezClient.setBounds(10, 5, 65, 20);
		rezCompany.setBounds(75, 5, 70, 20);
		rezClient.setBackground(bgColor2);
		rezCompany.setBackground(bgColor2);
		rezIfExistC.setBounds(rezCompany.getX() + rezCompany.getWidth() + 10,
				rezCompany.getY(), 120, 20);
		rezIfExistC.setBackground(bgColor2);
		rezIfExistC.setVisible(true);
		rezIfExistF.setVisible(false);
		rezIfExistF.setBounds(rezCompany.getX() + rezCompany.getWidth() + 10,
				rezCompany.getY(), 120, 20);
		rezIfExistF.setBackground(bgColor2);
		rezRoomLabel[0].setBounds(15, rezYList - 18, rezEWidth, 18);
		rezClasPane.setBounds(15, rezRoomLabel[0].getY() + 20, 320, 115);
		rezRoomLabel[1].setBounds(rezRoomLabel[0].getX(), rezClasPane.getY()
				+ rezClasPane.getHeight(), rezEWidth, 18);
		rezRoomPane.setBounds(rezRoomLabel[1].getX(),
				rezRoomLabel[1].getY() + 18, 120, 100);
		rezCat1.setBounds(rezRoomLabel[0].getX() + rezClasPane.getWidth() + 20,
				rezRoomLabel[0].getY(), 100, 20);
		rezCat1.setBackground(bgColor2);
		rezCat2.setBounds(rezCat1.getX() + rezCat1.getWidth(), rezCat1.getY(),
				100, 20);
		rezCat2.setBackground(bgColor2);
		rezCat3.setBounds(rezCat2.getX() + rezCat2.getWidth(), rezCat1.getY(),
				100, 20);
		rezCat3.setBackground(bgColor2);
		rezServPane.setBounds(rezCat1.getX(), rezCat1.getY() + 20, 320, 115);
		rezRoomLabel[2].setBounds(rezServPane.getX(), rezServPane.getY()
				+ rezServPane.getHeight() + 5, rezEWidth, 18);

		bWidth = 100;
		bHeight = 25;
		rezButton[0].setBounds(150, 70, bWidth, bHeight);
		rezServPane1.setBounds(rezRoomLabel[2].getX(), rezRoomLabel[2].getY()
				+ rezRoomLabel[2].getHeight() + 5, 100, 90);
		rezRoomLabel[3].setBounds(rezCat3.getX() + rezCat3.getWidth() + 25,
				rezCat3.getY(), rezEWidth, 18);
		rezDateBox[0].setBounds(rezRoomLabel[3].getX(), rezRoomLabel[3].getY()
				+ rezRoomLabel[3].getHeight() + 5, 50, 20);
		rezDateBox[1].setBounds(rezDateBox[0].getX(), rezDateBox[0].getY()
				+ rezDateBox[0].getHeight() + 5, 100, 20);
		rezDateBox[2].setBounds(rezDateBox[1].getX(), rezDateBox[1].getY()
				+ rezDateBox[1].getHeight() + 5, 65, 20);
		rezDayLabel.setBounds(rezDateBox[1].getX(), rezDateBox[2].getY()
				+ rezDateBox[2].getHeight() + 10, 50, rezJtaHeight);
		rezDayBox.setBounds(rezDayLabel.getX(), rezDayLabel.getY()
				+ rezDayLabel.getHeight() + 5, 50, rezJtaHeight);
		rezPrice.setBounds(rezDayBox.getX() - 150, rezDayBox.getY() + 40, 150,
				rezJtaHeight);
		rezCenaJta.setBounds(rezPrice.getX() + rezPrice.getWidth(),
				rezPrice.getY(), 70, rezJtaHeight);
		rezButton[3].setBounds(rezCenaJta.getX() - 80, rezCenaJta.getY() + 25,
				150, rezJtaHeight);
		rezButton[2].setBounds(rezButton[3].getX(), rezButton[3].getY() + 40,
				bWidth, bHeight);

		for (int i = 0; i < 11; i++) {
			if (i != 0 && i % 3 == 0) {
				rezYJta += 50;
				rezXJta = k;
			}
			if (i == 10)
				rezJta[i].setBounds(rezXJta, rezYJta, rezEWidth,
						rezJtaHeight + 60);
			else
				rezJta[i].setBounds(rezXJta, rezYJta, rezEWidth, rezJtaHeight);
			rezGuestLabel[i].setBounds(rezXJta, rezYJta - 18, rezEWidth,
					rezJtaHeight);
			rezCompLabel[i].setBounds(rezXJta, rezYJta - 18, rezEWidth,
					rezJtaHeight);
			rezXJta += k + rezEWidth;
		}
	}

	public void resizeReception() {

		recEWidth = (recPanel.getWidth() - 200) / 3;
		recJtaHeight = 18;
		yJta = 30;
		recYList = 300;
		for (int i = 0; i < 3; i++) {
			if (i != 0 && i % 1 == 0) {
				yJta += 50;
			}
			recJta[i].setBounds(10, yJta, recEWidth - 10, recJtaHeight);
			recGuestLabel[i].setBounds(10, yJta - 18, recEWidth, recJtaHeight);
		}
		recScrollPane.setBounds(recJta[0].getX() + recJta[0].getWidth() + 20,
				recYList - 270, recEWidth + 200, 200);
		recRezLabel.setBounds(recTable.getX(), recTable.getY() - 20, recEWidth,
				18);
		recBWidth = 100;
		recBHeight = 25;
		recButton[0].setBounds(50, 200, recBWidth, recBHeight);
		recButton[1].setBounds(recScrollPane.getX() + recScrollPane.getWidth()
				+ 10, recScrollPane.getY(), recBWidth, recBHeight);
		recButton[2].setBounds(recButton[1].getX(), recButton[1].getY() + 100,
				recBWidth, recBHeight);
		recButton[3].setBounds(recButton[1].getX(), recButton[1].getY() + 170,
				recBWidth, recBHeight);
		recDateLabel.setBounds(k, recYList + 20, recEWidth, 18);
		recDayLabel.setBounds(recDateLabel.getX() + 390, recDateLabel.getY(),
				50, recJtaHeight);
		recPrice.setBounds(recJta[0].getX(), recDateLabel.getY(), 150,
				recJtaHeight);
		recCenaJta.setBounds(recJta[0].getX() + 110, recDateLabel.getY(), 50,
				recJtaHeight);
		recPay.setBounds(recPrice.getX(),
				recCenaJta.getY() + recCenaJta.getHeight() + 10, 150,
				recJtaHeight);
		recBil.setBounds(recPay.getX() + recPay.getWidth() + 30,
				recCenaJta.getY() + recCenaJta.getHeight() + 10, 150,
				recJtaHeight);
		recPayButton[0].setBounds(recPrice.getX(), recCenaJta.getY()
				+ recCenaJta.getHeight() + 40, 150, recJtaHeight);
		recPayButton[1].setBounds(recPrice.getX(), recCenaJta.getY()
				+ recCenaJta.getHeight() + 70, 150, recJtaHeight);
		recBilButton[0].setBounds(
				recPayButton[0].getX() + recPayButton[0].getWidth() + 20,
				recPayButton[0].getY(), 150, recJtaHeight);
		recBilButton[1].setBounds(
				recPayButton[1].getX() + recPayButton[1].getWidth() + 20,
				recPayButton[1].getY(), 150, recJtaHeight);
		recButton[4].setBounds(recPrice.getX(),
				recCenaJta.getY() + recCenaJta.getHeight() + 110,
				recBWidth + 30, recBHeight);
		recBilButton[0].setSelected(true);
		recPayButton[0].setSelected(true);
	}

	public void resizeCantor() {
		canEWidth = (canPanel.getWidth() - 200) / 3;
		canJtaHeight = 18;
		canYList = 300;

		canBWidth = 100;
		canBHeight = 25;
		canPay.setBounds(10, 10, 150, canJtaHeight);
		canCurrL1.setBounds(10, 10, 150, 18);
		canScrollPane.setBounds(canCurrL1.getX() + canCurrL1.getWidth() + 40,
				canYList - 270, 500, 230);
		canBuyLabel.setBounds(canScrollPane.getX(), canScrollPane.getY() - 20,
				canEWidth, 18);
		canCurrBox1.setBounds(10, canCurrL1.getY() + 20, 50, 20);
		canCurrL2.setBounds(10, canCurrBox1.getY() + 20, canEWidth, 18);
		canCurrBox2
				.setBounds(canCurrBox1.getX(), canCurrL2.getY() + 20, 50, 20);
		canPrice.setBounds(canCurrBox2.getX(), canCurrBox2.getY() + 30, 150,
				canJtaHeight);
		canAmountJta.setBounds(canPrice.getX(), canPrice.getY() + 20, 100,
				canJtaHeight);
		canResButton.setBounds(canAmountJta.getX(), canAmountJta.getY() + 22,
				canBWidth, canBHeight);
		canCostLabel.setBounds(canResButton.getX(), canResButton.getY() + 28,
				150, canJtaHeight);
		canCostJta.setBounds(canCostLabel.getX(), canCostLabel.getY() + 22,
				100, canJtaHeight);
		canClientL.setBounds(10, canCostJta.getY() + 80, canEWidth, 18);
		canPESJta.setBounds(canClientL.getX(), canClientL.getY() + 22, 100,
				canJtaHeight);
		canPESButton.setBounds(canPESJta.getX(), canPESJta.getY() + 22,
				canBWidth, canBHeight);
		canScrollClientPane
				.setBounds(canScrollPane.getX(), canScrollPane.getY()
						+ canScrollPane.getHeight() + 30, 550, 230);
		canDoIt.setBounds(canCostJta.getX(), canCostJta.getY() + 300,
				canBWidth + 50, canBHeight);
	}

	public void resizeGuestBook() {
		gueScrollPane[0].setBounds(20, 300, getWidth() - 50,
				getHeight() / 2 - 190);
		gueScrollPane[1].setBounds(20, gueScrollPane[0].getY()
				+ gueScrollPane[0].getHeight() + 5, getWidth() - 50,
				getHeight() / 2 - 190);
		gueScrollPane[2].setBounds(510, 41, getWidth() - 540, 187);
	}

	public void resizeStatistic() {
		int x = 0, y = 0, hh = 0, ww = 0, w = 0, h = 0;
		w = this.getWidth() - 8;
		h = this.getHeight() - 33;
		staPanel.setBounds(0, 0, w, h);
		x = 20;
		y = 130;
		w -= 2 * x;
		hh = (int) (h - y - x);
		ww = (int) (0.4 * w);
		staRaportScroll.setBounds(x, y, ww, hh);
		x += ww;
		ww = (int) (0.59 * w);
		staMem.setBounds(x, y, ww, hh);
		// System.out.println("width: "+staMem.getWidth());
		// System.out.println("height: "+staMem.getHeight());
	}

	public void resizePersonelMenager() {
		int i = 0, k = 0, j = 0, dayNum = 0;
		int tmp = 0, dayTmp = 0, dX = 0;
		int w = this.getWidth() - 8;
		int h = this.getHeight() - 33;
		int ww = 0, hh = 0;
		int m = 0, y = 0;
		int index = mgpChooseType.getSelectedIndex();
		mgpPanel.setBounds(0, 0, w, h);
		ww = w - 260;
		hh = h - 60;
		for (i = 0; i < mgpServe.length; i++)
			mgpServe[i].setBounds(260, 60, ww, hh);
		// System.out.println("ww: "+ww);////////////
		// System.out.println("hh: "+hh); ////////////
		i = (ww - 40);
		mgpDel.setBounds(20 + (int) (i * 0.82), 165, (int) (i * 0.18), 40);
		if (index <= 2) {
			hh = 80;
			y = schCalendar.get(Calendar.YEAR);
			m = schCalendar.get(Calendar.MONTH);
			schCalendar.set(Calendar.YEAR, mgpYear);
			schCalendar.set(Calendar.MONTH, mgpMonth);
			schCalendar.set(Calendar.DAY_OF_MONTH, mgpDay);
			dX = ww / 2 - 182;
			dayTmp = schCalendar.get(Calendar.DAY_OF_MONTH);
			schCalendar.set(Calendar.DAY_OF_MONTH, 0);
			tmp = schCalendar.get(Calendar.DAY_OF_WEEK) - 1;
			dX += tmp * 51;
			dayNum = dayInMonth[mgpMonth];
			if (mgpMonth == 1 && mgpYear % 4 == 0)
				dayNum++;
			for (i = 0, k = 51; i < 31; i++, dX += k, tmp++) {
				if (i != 0 && tmp % 7 == 0) {
					hh += 31;
					dX = ww / 2 - 182;
				}
				mgpDays[i].setBounds(dX, hh, 50, 30);
				if (i < dayNum) {
					mgpDays[i].setVisible(true);
					j = i;
				} else
					mgpDays[i].setVisible(false);
			}
			dX = ww / 2 - 175;
			k = mgpDays[0].getY() - 18;
			for (i = 0; i < mgpDayLabel.length; i++, dX += 51)
				mgpDayLabel[i].setBounds(dX + 10, k, 50, 18);
			mgpCalMonthLabel.setText(monthOfYear[mgpMonth] + " " + mgpYear);// <----
			mgpCalMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
			mgpCalMonthLabel.setBounds((ww - 100) / 2,
					mgpDayLabel[0].getY() - 30, 100, 20);
			dX = ww / 2 - 75;
			mgpPrev.setBounds(mgpDayLabel[0].getX() - 18,
					mgpCalMonthLabel.getY(), 100, 20);
			mgpNext.setBounds(mgpDayLabel[6].getX() - 68,
					mgpCalMonthLabel.getY(), 100, 20);
			schCalendar.set(Calendar.DAY_OF_MONTH, dayTmp);
			schCalendar.set(Calendar.YEAR, y);
			schCalendar.set(Calendar.MONTH, m);
			mgpDays[dayTmp - 1].requestFocus();
			i = (mgpServe[2].getWidth() - 40);
			hh = h - mgpDays[j].getY() - 120;
			mgpSchedScroll.setBounds(20, mgpDays[j].getY() + 50, i, hh);
			mgpSchedText.setSize(i, hh);
		} else if (index == 4) {
			// i=(mgpServe[2].getWidth()-40);
			hh = hh - 175;
			mgpTableScroll.setBounds(20, 165, (int) (i * 0.8), hh);
			if (mgpTable != null)
				mgpTable.setSize((int) (i * 0.8), hh);
		} else if (index == 5) {
			// i=(mgpServe[3].getWidth()-40);
			hh = hh - 100;
			mgpRaportScroll.setBounds(20, 90, i, hh);
			mgpRaportText.setSize(i, hh);
		}
	}

	public void resizeManager() {
		manScrollPane.setBounds(20, 300, getWidth() - 50, getHeight() - 470);

		manButton2[0].setBounds(manScrollPane.getX(),
				manScrollPane.getY() - 35, 150, 25);
		for (int i = 1; i < manButton2.length; i++) {
			manButton2[i].setBounds(manButton2[i - 1].getX() + 160,
					manScrollPane.getY() - 35, 150, 25);
		}
		manButton2[1].setBounds(manButton2[0].getX() + 160,
				manScrollPane.getY() - 35, 150, 25);
		manButton2[2].setBounds(manButton2[1].getX() + 160,
				manScrollPane.getY() - 35, 150, 25);
		manButton2[3].setBounds(manButton2[2].getX() + 160,
				manScrollPane.getY() - 35, 150, 25);

		int tmp, manX = manButton2[2].getX(), manY = 20;
		tmp = manX;
		manButton[0].setBounds(manX, manY, 150, 30);
		for (int i = 1; i < manButton.length; i++) {
			manX += 160;
			if (i % 2 == 0) {
				manX = tmp;
				manY += 31;
			}
			manButton[i].setBounds(manX, manY, 150, 30);
		}

		manNews.setBounds(20, manScrollPane.getY() + manScrollPane.getHeight()
				+ 10, getWidth() - 50, 100);
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {

	}

	@Override
	public void componentResized(ComponentEvent arg0) {

		resizeRezervation();
		resizeReception();
		resizeCantor();
		schInitCalendar(d, m, y);
		resizeGuestBook();
		resizeManager();
		resizeStatistic();
		resizePersonelMenager();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}

}