package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import service.GraphDraw;
import service.dictionary.MONTH;
import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;
import dao.Singleton;

public class StatisticPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton exec;
	private JLabel titleLabel;
	private JLabel typeLabel;
	private JLabel subLabel;
	private JLabel monthLabel;
	private JLabel yearLabel;
	private JLabel monthLabelFrom;
	private JLabel yearLabelFrom;
	private JLabel monthLabelTo;
	private JLabel yearLabelTo;
	private JLabel classLabel;
	private JLabel serveLabel;
	private JComboBox chooseType;
	private JComboBox chooseSubFinance;
	private JComboBox chooseSubHotel;
	private JComboBox chooseMonth;
	private JComboBox chooseYear;
	private JComboBox chooseMonth2;
	private JComboBox chooseYear2;
	private JComboBox chooseClass;
	private JComboBox chooseServe;
	private JScrollPane raportScroll;
	private JTextPane raportText;
	private GraphDraw graphDraw;
	private Singleton db = Singleton.getInstance();

	private Statistic statistic;

	public StatisticPanel() {
		create();
		addEvents();
	}

	private void create() {
		Color color = new Color(224, 230, 233);
		Font font = new Font("arial", Font.ROMAN_BASELINE, 15);

		graphDraw = new GraphDraw();
		statistic = new Statistic();


		setBounds(0, 0, 900, 650);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(color);
		setVisible(true);

		exec = new JButton("Wykonaj");
		exec.setBounds(380, 60, 100, 60);
		exec.setFont(font);
		exec.setVisible(true);
		add(exec);

		chooseType = new JComboBox();
		chooseType.setBounds(20, 60, 230, 20);
		chooseType.addItem("hotelowe");
		chooseType.addItem("finansowe");
		chooseType.setSelectedIndex(0);
		add(chooseType);

		chooseSubHotel = new JComboBox();
		chooseSubHotel.setBounds(20, 100, 230, 20);
		chooseSubHotel.addItem(RAPORT_KIND.HOTEL_CLASS_ROOM);
		chooseSubHotel.addItem(RAPORT_KIND.HOTEL_ROOM);
		chooseSubHotel.addItem(RAPORT_KIND.HOTEL_SERVICES);
		chooseSubHotel.addItem(RAPORT_KIND.HOTEL_SERVICE);
		chooseSubHotel.setSelectedIndex(0);
		add(chooseSubHotel);

		chooseMonth = new JComboBox();
		chooseMonth.addItem(MONTH.January);
		chooseMonth.addItem(MONTH.February);
		chooseMonth.addItem(MONTH.March);
		chooseMonth.addItem(MONTH.April);
		chooseMonth.addItem(MONTH.May);
		chooseMonth.addItem(MONTH.June);
		chooseMonth.addItem(MONTH.July);
		chooseMonth.addItem(MONTH.August);
		chooseMonth.addItem(MONTH.September);
		chooseMonth.addItem(MONTH.October);
		chooseMonth.addItem(MONTH.November);
		chooseMonth.addItem(MONTH.December);
		chooseMonth.setSelectedIndex(0);
		add(chooseMonth);

		chooseMonth2 = new JComboBox();
		chooseMonth2.setBounds(260, 60, 100, 20);
		chooseMonth2.addItem(MONTH.January);
		chooseMonth2.addItem(MONTH.February);
		chooseMonth2.addItem(MONTH.March);
		chooseMonth2.addItem(MONTH.April);
		chooseMonth2.addItem(MONTH.May);
		chooseMonth2.addItem(MONTH.June);
		chooseMonth2.addItem(MONTH.July);
		chooseMonth2.addItem(MONTH.August);
		chooseMonth2.addItem(MONTH.September);
		chooseMonth2.addItem(MONTH.October);
		chooseMonth2.addItem(MONTH.November);
		chooseMonth2.addItem(MONTH.December);
		chooseMonth2.setSelectedIndex(0);
		chooseMonth2.setVisible(false);
		add(chooseMonth2);

		chooseSubFinance = new JComboBox();
		chooseSubFinance.setBounds(20, 100, 230, 20);
		chooseSubFinance.addItem(RAPORT_KIND.FINANCE_MONTH);
		chooseSubFinance.addItem(RAPORT_KIND.FINANCE_YEAR);
		chooseSubFinance.setSelectedIndex(0);
		chooseSubFinance.setVisible(false);
		add(chooseSubFinance);

		chooseYear = new JComboBox();
		chooseYear.setBounds(260, 60, 100, 20);
		chooseYear.addItem("2010");
		chooseYear.addItem("2011");
		chooseYear.addItem("2012");
		chooseYear.addItem("2013");
		chooseYear.addItem("2014");
		chooseYear.addItem("2015");
		chooseYear.addItem("2016");
		chooseYear.setSelectedIndex(0);
		add(chooseYear);

		chooseYear2 = new JComboBox();
		chooseYear2.setBounds(260, 100, 100, 20);
		chooseYear2.addItem("2010");
		chooseYear2.addItem("2011");
		chooseYear2.addItem("2012");
		chooseYear2.addItem("2013");
		chooseYear2.addItem("2014");
		chooseYear2.addItem("2015");
		chooseYear2.addItem("2016");
		chooseYear2.setSelectedIndex(0);
		chooseYear2.setVisible(false);
		add(chooseYear2);

		createComboBoxStat();

		font = new Font("arial", Font.BOLD, 20);
		titleLabel = new JLabel("Statystyki");
		titleLabel.setBounds(20, 5, 100, 20);
		titleLabel.setFont(font);
		add(titleLabel);

		font = new Font("arial", Font.ROMAN_BASELINE, 15);
		typeLabel = new JLabel("Wybierz rodzaj staStatystyk");
		typeLabel.setBounds(20, 45, 200, 15);
		typeLabel.setFont(font);
		add(typeLabel);

		subLabel = new JLabel("Wybierz jedna");
		subLabel.setBounds(20, 85, 100, 15);
		subLabel.setFont(font);
		add(subLabel);

		monthLabel = new JLabel("Wybierz miesi�c");
		monthLabel.setBounds(260, 85, 200, 15);
		monthLabel.setFont(font);
		add(monthLabel);

		yearLabel = new JLabel("Wybierz rok");
		yearLabel.setBounds(260, 45, 100, 15);
		yearLabel.setFont(font);
		add(yearLabel);

		classLabel = new JLabel("Wybierz klas� pokuju");
		classLabel.setBounds(500, 45, 200, 15);
		classLabel.setFont(font);
		classLabel.setVisible(false);
		add(classLabel);

		serveLabel = new JLabel("Wybierz typ us�ugi");
		serveLabel.setBounds(500, 45, 200, 15);
		serveLabel.setFont(font);
		serveLabel.setVisible(false);
		add(serveLabel);

		monthLabelFrom = new JLabel("Miesi�cy od");
		monthLabelFrom.setBounds(260, 45, 200, 15);
		monthLabelFrom.setFont(font);
		monthLabelFrom.setVisible(false);
		add(monthLabelFrom);

		yearLabelFrom = new JLabel("Lata od");
		yearLabelFrom.setBounds(260, 45, 200, 15);
		yearLabelFrom.setFont(font);
		yearLabelFrom.setVisible(false);
		add(yearLabelFrom);

		monthLabelTo = new JLabel("do");
		monthLabelTo.setBounds(260, 85, 200, 15);
		monthLabelTo.setFont(font);
		monthLabelTo.setVisible(false);
		add(monthLabelTo);

		yearLabelTo = new JLabel("do");
		yearLabelTo.setBounds(260, 85, 200, 15);
		yearLabelTo.setFont(font);
		yearLabelTo.setVisible(false);
		add(yearLabelTo);

		raportText = new JTextPane();
		raportText.setBounds(20, 130, 360, 444);
		raportText.setFont(font);

		raportScroll = new JScrollPane(raportText);
		raportScroll.setVisible(false);
		add(raportScroll);

		graphDraw.setBackground(Color.WHITE);
		graphDraw.setVisible(false);
		add(graphDraw);
	}

	private void createComboBoxStat() {

		chooseClass = new JComboBox();
		chooseServe = new JComboBox();
		chooseClass.setBounds(500, 60, 230, 20);
		chooseServe.setBounds(500, 60, 230, 20);
		chooseClass.setVisible(false);
		chooseServe.setVisible(false);
		fillBox();
	}

	private void fillBox() {
		try {
			ResultSet reSet = db.query("SELECT opis FROM klasy");
			if (reSet != null) {

				while (reSet.next()){
					chooseClass.addItem(reSet.getString(1));
				}
				chooseClass.setSelectedIndex(-1);//should be 0
				add(chooseClass);
			}
			reSet = db.query("SELECT typ FROM uslugi GROUP BY typ");
			if (reSet != null) {
				while (reSet.next())
					chooseServe.addItem(reSet.getString(1));
				chooseServe.setSelectedIndex(-1);//should be 0
				add(chooseServe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addEvents() {
		exec.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				graphDraw.setVisible(false);
				StatisticRaport raport = null;
				if (chooseType.getSelectedIndex() == 0){
					raport = statistic.hotel(//
								(RAPORT_KIND) chooseSubHotel.getSelectedItem(),
								chooseMonth.getSelectedIndex(),
								chooseYear.getSelectedIndex() + 2010,
								(String) chooseClass.getSelectedItem(),
								(String) chooseServe.getSelectedItem());
				}else{
					raport = statistic.finance(//
							(RAPORT_KIND) chooseSubFinance.getSelectedItem(),
							chooseMonth2.getSelectedIndex() + 1,
							chooseMonth.getSelectedIndex() + 1,
							chooseYear.getSelectedIndex() + 2010,
							chooseYear2.getSelectedIndex() + 2010);
				}
				graphDraw.setArray(raport.getArrayResult());
				raportText.setText(raport.getTextResult());
				graphDraw.setVisible(true);
				raportScroll.setVisible(true);
			}
		});
		chooseType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chooseType.getSelectedIndex() == 0) {
					yearLabel.setLocation(260, 45);
					yearLabel.setVisible(true);
					chooseYear.setLocation(260, 60);
					chooseYear.setVisible(true);
					chooseYear2.setVisible(false);
					chooseSubHotel.setVisible(true);
					chooseSubFinance.setVisible(false);
					chooseMonth.setVisible(true);
					chooseMonth2.setVisible(false);
					monthLabel.setVisible(true);
					monthLabelFrom.setVisible(false);
					yearLabelFrom.setVisible(false);
					monthLabelTo.setVisible(false);
					yearLabelTo.setVisible(false);
					chooseSubHotel.setSelectedIndex(0);
				} else {
					chooseClass.setVisible(false);
					chooseServe.setVisible(false);
					serveLabel.setVisible(false);
					classLabel.setVisible(false);
					chooseSubHotel.setVisible(false);
					chooseSubFinance.setVisible(true);
					yearLabel.setLocation(500, 45);
					chooseYear.setLocation(500, 60);
					chooseMonth.setVisible(true);
					chooseMonth2.setVisible(true);
					chooseYear.setVisible(true);
					chooseYear2.setVisible(false);
					monthLabel.setVisible(false);
					monthLabelFrom.setVisible(true);
					monthLabelTo.setVisible(true);
					chooseSubFinance.setSelectedIndex(0);
				}
			}
		});
		chooseSubHotel.addItemListener(new ItemListener() {
			int i = 0;

			public void itemStateChanged(ItemEvent arg0) {
				if ((i = chooseSubHotel.getSelectedIndex()) == 1) {
					chooseClass.setVisible(true);
					classLabel.setVisible(true);
					chooseServe.setVisible(false);
					serveLabel.setVisible(false);
				} else if (i == 3) {
					chooseClass.setVisible(false);
					classLabel.setVisible(false);
					chooseServe.setVisible(true);
					serveLabel.setVisible(true);
				} else {
					chooseClass.setVisible(false);
					classLabel.setVisible(false);
					chooseServe.setVisible(false);
					serveLabel.setVisible(false);
				}
			}
		});
		chooseSubFinance.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chooseSubFinance.getSelectedIndex() == 0) {
					chooseYear.setLocation(500, 60);
					yearLabel.setVisible(true);
					chooseMonth.setVisible(true);
					chooseMonth2.setVisible(true);
					chooseYear.setVisible(true);
					chooseYear2.setVisible(false);
					monthLabelFrom.setVisible(true);
					monthLabelTo.setVisible(true);
					yearLabelFrom.setVisible(false);
					yearLabelTo.setVisible(false);
				} else {
					yearLabel.setVisible(false);
					chooseYear.setLocation(260, 60);
					chooseMonth.setVisible(false);
					chooseMonth2.setVisible(false);
					chooseYear.setVisible(true);
					chooseYear2.setVisible(true);
					monthLabelFrom.setVisible(false);
					monthLabelTo.setVisible(false);
					yearLabelFrom.setVisible(true);
					yearLabelTo.setVisible(true);
				}
			}
		});
	}

	public void resizeStatistic(int width, int height) {
		int x = 0, y = 0, hh = 0, ww = 0, w = 0, h = 0;
		w = width - 8;
		h = height - 33;
		setBounds(0, 0, w, h);
		x = 20;
		y = 130;
		w -= 2 * x;
		hh = (int) (h - y - x);
		ww = (int) (0.4 * w);
		raportScroll.setBounds(x, y, ww, hh);
		x += ww;
		ww = (int) (0.59 * w);
		graphDraw.setBounds(x, y, ww, hh);
	}

	@Override
	public void setSize(int width, int height) {
		resizeStatistic(width, height);
		super.setSize(width, height);
	}
}
