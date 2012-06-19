package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

import repo.Cantor;

public class CantorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	float results[];
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
	private Color bgColor = new Color(224, 230, 233);
	private Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));
	private Color buttonColor = new Color(174, 205, 214);
	
	private Cantor cantor = new Cantor();
	
	public CantorPanel() {
		createCantor();
		addEvent();
	}

	private void createCantor() {
		setBounds(0, 0, getWidth(), getHeight());
		setLayout(null);
//		addComponentListener(this);
		setMinimumSize(new Dimension(800, 600));
		setBackground(bgColor);
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
		add(canPrice);
		add(canAmountJta);
		add(canCostJta);
		add(canPESJta);
		add(canTable);
		add(canClientTable);
		canPrice = new JLabel("Kwota transakcji:");
		canCostLabel = new JLabel("Do zap�aty:");
		add(canCostLabel);
		add(canBuyLabel);
		add(canCurrL1);
		add(canCurrL2);
		add(canClientL);
		add(canPrice);
		add(canPay);
		canCurrBox1 = new JComboBox(canCurr);
		canCurrBox2 = new JComboBox(canCurr);
		add(canCurrBox1);
		add(canCurrBox2);
		canResButton = new JButton("Przelicz");
		canPESButton = new JButton("Szukaj");
		canDoIt = new JButton("Dokonaj transakcji");
		canResButton.setBackground(buttonColor);
		canPESButton.setBackground(buttonColor);
		canDoIt.setBackground(buttonColor);
		add(canResButton);
		add(canPESButton);
		add(canDoIt);
		canTable = cantor.createCurrTable();
		canScrollPane = new JScrollPane(canTable);
		canScrollPane.setBorder(border);
		canScrollPane.setViewportView(canTable);
		add(canScrollPane);
		canScrollClientPane = new JScrollPane(canClientTable);
		canScrollClientPane.setBorder(border);
		canScrollClientPane.setViewportView(canClientTable);
		add(canScrollClientPane);
		setVisible(true);
	}
	
	private void addEvent() {

		canPESButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (canPESJta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(getParent(),
							"Nie podano numeru PESEL/KRS");
				} else {
					if (!cantor.isPesel(canPESJta.getText())
							&& !cantor.isKRS(canPESJta.getText()))
						JOptionPane.showMessageDialog(getParent(),
								"Nieprawid�owy PESEL/KRS");
					else if (cantor.isPesel(canPESJta.getText())) {
						canClientTable = cantor
								.createClientTable(" where IDK_PESEL="
										+ canPESJta.getText());
						if (canClientTable.getRowCount() < 1)
							JOptionPane.showMessageDialog(getParent(),
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
					JOptionPane.showMessageDialog(getParent(), "Nie podano kwoty");
				else {
					for (int i = 0; i < canAmountJta.getText().length(); ++i) {
						if (canAmountJta.getText().charAt(i) < '0'
								|| canAmountJta.getText().charAt(i) > '9') {
							ifNumber = false;
						}
					}
					if (!ifNumber)
						JOptionPane.showMessageDialog(getParent(),
								"Podaj prawidlowa ilosc waluty");
					else {
						if (canCurrBox1.getSelectedItem().toString() == canCurrBox2
								.getSelectedItem().toString())
							JOptionPane.showMessageDialog(getParent(),
									"Nie mo�na wymienia� na t� sam� walut�");
						else {
							results = cantor.changeCalc(canCurrBox1
									.getSelectedItem().toString(), canCurrBox2
									.getSelectedItem().toString(), Float
									.valueOf(canAmountJta.getText())
									.floatValue());
							canCostJta.setText(Double.toString(round(results[1], 2)));

						}
					}
				}
			}

		});

		canDoIt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (canCostJta.getText().isEmpty()) {
					JOptionPane.showMessageDialog(getParent(),
							"Brak nale�nej kwoty");
				} else if (canClientTable.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(getParent(),
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
						JOptionPane.showMessageDialog(getParent(),
								"Dokonano transakcji");
						canPESJta.setText("");
						canCurrBox1.setSelectedIndex(0);
						canCurrBox2.setSelectedIndex(0);
						canCostJta.setText("");
						canTable = cantor.createCurrTable();
						canScrollPane.setViewportView(canTable);
						canScrollPane.repaint();
					} else
						JOptionPane.showMessageDialog(getParent(),
								"Nieprawid�owe dane");
					;
				}
			}
		});
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
	
	private void resizeCantor(int width, int height) {
		canEWidth = (width - 200) / 3;
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
	
	@Override
	public void setSize(int width, int height) {
		resizeCantor(width, height);
		super.setSize(width, height);
	}
}
