package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import repo.GraphDraw;
import repo.Singleton;
import repo.Statistic;

public class StatisticPanel extends JPanel {

	private static final long serialVersionUID = 1L;

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
	private Singleton db = Singleton.getInstance();

	private Statistic sta;

	public StatisticPanel() {
		createStatistic();
		addStatEvent();
	}

	private void createStatistic() {
		Color color = new Color(224, 230, 233);
		Font font = new Font("arial", Font.ROMAN_BASELINE, 15);

		staMem = new GraphDraw();
		try {
			sta = new Statistic();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setBounds(0, 0, 900, 650);
		setLayout(null);
		setBackground(color);
		setVisible(true);

		staExec = new JButton("Wykonaj");
		staExec.setBounds(380, 60, 100, 60);
		staExec.setFont(font);
		staExec.setVisible(true);
		add(staExec);

		staChooseType = new JComboBox();
		staChooseType.setBounds(20, 60, 230, 20);
		staChooseType.addItem("hotelowe");
		staChooseType.addItem("finansowe");
		staChooseType.setSelectedIndex(0);
		add(staChooseType);

		staChooseSubHot = new JComboBox();
		staChooseSubHot.setBounds(20, 100, 230, 20);
		staChooseSubHot.addItem("Raportu z wykorzystania klas pokoi");
		staChooseSubHot.addItem("Raportu z wykorzystania pokoi w klasie");
		staChooseSubHot.addItem("Raportu z wykorzystania typ�w us�ug");
		staChooseSubHot.addItem("Raportu z wybranej uslugi");
		staChooseSubHot.setSelectedIndex(0);
		add(staChooseSubHot);

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
		add(staChooseMonth);

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
		add(staChooseMonth2);

		staChooseSubFin = new JComboBox();
		staChooseSubFin.setBounds(20, 100, 230, 20);
		staChooseSubFin.addItem("Bilansu z miesi�cy");
		staChooseSubFin.addItem("Bilansu z lat");
		staChooseSubFin.setSelectedIndex(0);
		staChooseSubFin.setVisible(false);
		add(staChooseSubFin);

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
		add(staChooseYear);

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
		add(staChooseYear2);

		createComboBoxStat();

		font = new Font("arial", Font.BOLD, 20);
		staTitleLabel = new JLabel("Statystyki");
		staTitleLabel.setBounds(20, 5, 100, 20);
		staTitleLabel.setFont(font);
		add(staTitleLabel);

		font = new Font("arial", Font.ROMAN_BASELINE, 15);
		staTypeLabel = new JLabel("Wybierz rodzaj staStatystyk");
		staTypeLabel.setBounds(20, 45, 200, 15);
		staTypeLabel.setFont(font);
		add(staTypeLabel);

		staSubLabel = new JLabel("Wybierz jedna");
		staSubLabel.setBounds(20, 85, 100, 15);
		staSubLabel.setFont(font);
		add(staSubLabel);

		staMonthLabel = new JLabel("Wybierz miesi�c");
		staMonthLabel.setBounds(260, 85, 200, 15);
		staMonthLabel.setFont(font);
		add(staMonthLabel);

		staYearLabel = new JLabel("Wybierz rok");
		staYearLabel.setBounds(260, 45, 100, 15);
		staYearLabel.setFont(font);
		add(staYearLabel);

		staClassLabel = new JLabel("Wybierz klas� pokuju");
		staClassLabel.setBounds(500, 45, 200, 15);
		staClassLabel.setFont(font);
		staClassLabel.setVisible(false);
		add(staClassLabel);

		staServeLabel = new JLabel("Wybierz typ us�ugi");
		staServeLabel.setBounds(500, 45, 200, 15);
		staServeLabel.setFont(font);
		staServeLabel.setVisible(false);
		add(staServeLabel);

		staMonthLabelFrom = new JLabel("Miesi�cy od");
		staMonthLabelFrom.setBounds(260, 45, 200, 15);
		staMonthLabelFrom.setFont(font);
		staMonthLabelFrom.setVisible(false);
		add(staMonthLabelFrom);

		staYearLabelFrom = new JLabel("Lata od");
		staYearLabelFrom.setBounds(260, 45, 200, 15);
		staYearLabelFrom.setFont(font);
		staYearLabelFrom.setVisible(false);
		add(staYearLabelFrom);

		staMonthLabelTo = new JLabel("do");
		staMonthLabelTo.setBounds(260, 85, 200, 15);
		staMonthLabelTo.setFont(font);
		staMonthLabelTo.setVisible(false);
		add(staMonthLabelTo);

		staYearLabelTo = new JLabel("do");
		staYearLabelTo.setBounds(260, 85, 200, 15);
		staYearLabelTo.setFont(font);
		staYearLabelTo.setVisible(false);
		add(staYearLabelTo);

		staRaportText = new JTextPane();
		staRaportText.setBounds(20, 130, 360, 444);
		staRaportText.setFont(font);

		staRaportScroll = new JScrollPane(staRaportText);
		staRaportScroll.setVisible(false);
		add(staRaportScroll);

		staMem.setBackground(Color.WHITE);
		staMem.setVisible(false);
		add(staMem);
	}

	private void createComboBoxStat() {

		staChooseClass = new JComboBox();
		staChooseServe = new JComboBox();
		staChooseClass.setBounds(500, 60, 230, 20);
		staChooseServe.setBounds(500, 60, 230, 20);
		staChooseClass.setVisible(false);
		staChooseServe.setVisible(false);
		fillBox();
	}

	private void fillBox() {
		try {
			ResultSet reSet = db.query("SELECT opis FROM klasy");
			if (reSet != null) {

				while (reSet.next())
					staChooseClass.addItem(reSet.getString(1));

				staChooseClass.setSelectedIndex(0);
				add(staChooseClass);
			}
			reSet = db.query("SELECT typ FROM uslugi GROUP BY typ");
			if (reSet != null) {
				while (reSet.next())
					staChooseServe.addItem(reSet.getString(1));
				staChooseServe.setSelectedIndex(0);
				add(staChooseServe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		staRaportScroll.setBounds(x, y, ww, hh);
		x += ww;
		ww = (int) (0.59 * w);
		staMem.setBounds(x, y, ww, hh);
		// System.out.println("width: "+staMem.getWidth());
		// System.out.println("height: "+staMem.getHeight());
	}

	@Override
	public void setSize(int width, int height) {
		resizeStatistic(width, height);
		super.setSize(width, height);
	}
}
