package gui;

import dao.CantorDao;
import exception.CantorTransactionCanceledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.cantor.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import static validation.ValidationUtils.*;

@Component
public class CantorPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private ExchangeCalculation exchangeCalculation;

    private JTextField amountInput;
    private JLabel buyLabel;
    private JLabel oldCurrencyLabel;
    private JLabel newCurrencyLabel;
    private JLabel clientLabel;

    private JLabel priceLabel;
    private JComboBox oldCurrencyBox;
    private JComboBox newCurrencyBox;
    private JButton calculateExchangeButton;
    private JButton searchClientButton;
    private JButton commitTransactionButton;
    private JTextField costInput;
    private JTextField peselOrKrsInput;
    private JLabel costLabel;
    private JLabel payLabel;
    private JTable currencyTable;
    private JTable clientTable;
    private JScrollPane scrollPane;
    private JScrollPane scrollClientPane;

    private final Color bgColor = new Color(224, 230, 233);
    private final Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));
    private final Color buttonColor = new Color(174, 205, 214);

    @Autowired
    private final CantorDao cantorDao;

    @Autowired
    private CantorMoneyExchanger cantor;

    @Autowired
    private final CantorTableCreator creator;

    public CantorPanel(CantorDao cantorDao, CantorTableCreator creator) {
        this.cantorDao = cantorDao;
        this.creator = creator;
        create();
        addEvents();
    }

    private void create() {
        setBounds(0, 0, getWidth(), getHeight());
        setLayout(null);
        setMinimumSize(new Dimension(800, 600));
        setBackground(bgColor);
        setVisible(true);

        peselOrKrsInput = new JTextField();

        currencyTable = createTable(creator.createCurrencyTable());
        scrollPane = new JScrollPane();
        scrollPane.setBorder(border);
        scrollPane.setViewportView(currencyTable);

        scrollClientPane = new JScrollPane();
        scrollClientPane.setBorder(border);

        currencyTable = new JTable();
        clientTable = new JTable();
        buyLabel = new JLabel("Dostepność oraz kursy walut");
        oldCurrencyLabel = new JLabel("Waluta wymieniana");
        newCurrencyLabel = new JLabel("Waluta ");
        clientLabel = new JLabel("PESEL/KRS Klienta");
        payLabel = new JLabel();
        amountInput = new JTextField("");
        costInput = new JTextField("");

        amountInput.setBorder(border);
        costInput.setBorder(border);
        peselOrKrsInput.setBorder(border);

        priceLabel = new JLabel("Kwota transakcji:");
        costLabel = new JLabel("Do zapłaty:");

        oldCurrencyBox = new JComboBox<CURRENCY>(CURRENCY.values());
        newCurrencyBox = new JComboBox<CURRENCY>(CURRENCY.values());
        calculateExchangeButton = new JButton("Przelicz");
        searchClientButton = new JButton("Szukaj");
        commitTransactionButton = new JButton("Dokonaj transakcji");
        calculateExchangeButton.setBackground(buttonColor);
        searchClientButton.setBackground(buttonColor);
        commitTransactionButton.setBackground(buttonColor);

        resizeCantor(getWidth());

        add(peselOrKrsInput);
        add(priceLabel);
        add(amountInput);
        add(costInput);
        add(currencyTable);
        add(clientTable);
        add(costLabel);
        add(buyLabel);
        add(oldCurrencyLabel);
        add(newCurrencyLabel);
        add(clientLabel);
        add(priceLabel);
        add(payLabel);
        add(oldCurrencyBox);
        add(newCurrencyBox);
        add(calculateExchangeButton);
        add(searchClientButton);
        add(commitTransactionButton);
        add(scrollPane);
        add(scrollClientPane);
    }

    private void addEvents() {

        searchClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = peselOrKrsInput.getText();
                if (isPeselOrKrsInputEmpty()) {
                    showMessageDialog("Nie podano numeru PESEL/KRS");
                } else if (isPesel(text)) {
                        createCustomerTable();
                    } else if (isKRS(text)) {
                        createCompanyTable();
                    } else {
                        showMessageDialog("Nieprawidłowy PESEL/KRS");
                    }
            }

            private boolean isPeselOrKrsInputEmpty() {
                return peselOrKrsInput.getText().isEmpty();
            }

            private void createCustomerTable() {
                long pesel = Long.parseLong(peselOrKrsInput.getText());
                clientTable = createTable(creator.createCustomerTable(pesel));
                scrollClientPane.setViewportView(clientTable);
                scrollClientPane.repaint();
            }

            private void createCompanyTable() {
                long krs = Long.parseLong(peselOrKrsInput.getText());
                clientTable = createTable(creator.createCompanyTable(krs));
                scrollClientPane.setViewportView(clientTable);
                scrollClientPane.repaint();
            }

        });

        calculateExchangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isAmountInputEmpty()) {
                    showMessageDialog("Nie podano kwoty");
                } else if (isNotNumber(amountInput.getText())) {
                    showMessageDialog("Podaj prawidlowa ilosc waluty");
                } else if (areSelectedTheSameCurrency()) {
                    showMessageDialog("Nie można wymieniać na tą samą walutę");
                } else {
                    calculateExchange();
                    updateCostInputText();
                }
            }

            private boolean isAmountInputEmpty() {
                return amountInput.getText().isEmpty();
            }

            private boolean areSelectedTheSameCurrency() {
                return oldCurrencyBox.getSelectedItem() == newCurrencyBox.getSelectedItem();
            }

            private void calculateExchange() {
                float amount = getAmount();
                exchangeCalculation = cantor.calculateExchange(
                        (CURRENCY) oldCurrencyBox.getSelectedItem(),
                        (CURRENCY) newCurrencyBox.getSelectedItem(),
                        amount);
            }

            private Float getAmount() {
                return Float.valueOf(amountInput.getText());
            }

            private void updateCostInputText() {
                costInput.setText(Double.toString(round(exchangeCalculation.getCost())));
            }
        });

        commitTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCostInputEmpty()) {
                    showMessageDialog("Brak należnej kwoty");
                } else if (isNotSelectedCustomer()) {
                    showMessageDialog("Nie zaznaczono klienta");
                } else {
                    commitMoneyExchangeTransaction();
                }
            }

            private boolean isCostInputEmpty() {
                return costInput.getText().isEmpty();
            }

            private boolean isNotSelectedCustomer() {
                return clientTable.getSelectedRow() < 0;
            }

            private void commitMoneyExchangeTransaction() {
                if (cantor.isTransactionPossible(exchangeCalculation)) {
                    exchangeMoney();
                    refreshView();
                } else {
                    showMessageDialog("Nieprawidłowe dane");
                }
            }

            private void exchangeMoney() {
                try {
                    String client = getClient();
                    exchangeCalculation.forClient(client);
                    cantor.exchangeMoney(exchangeCalculation);
                } catch (CantorTransactionCanceledException e) {
                    showMessageDialog("Transakcja wymiany nie powiodla sie");
                    e.printStackTrace();
                }
            }

            private String getClient() {
                return clientTable.getValueAt(clientTable.getSelectedRow(), 0).toString();
            }

            private void refreshView() {
                peselOrKrsInput.setText("");
                oldCurrencyBox.setSelectedIndex(0);
                newCurrencyBox.setSelectedIndex(0);
                costInput.setText("");
                currencyTable = createTable(creator.createCurrencyTable());
                scrollPane.setViewportView(currencyTable);
                scrollPane.repaint();
            }
        });
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(getParent(), message);
    }

    private JTable createTable(CantorTableResult result) {
        JTable table = new JTable(result.getRowsData(), result.getColumnNames());
        table.setFillsViewportHeight(true);
        return table;
    }

    private double round(double d) {
        NumberFormat nf = java.text.NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return Double.parseDouble((nf.format(d)).replaceAll(",", ".").replaceAll("", ""));
    }

    private void resizeCantor(int width) {
        int cantorEWidth = (width - 200) / 3;
        int cantorJtaHeight = 18;
        int cantorYList = 300;
        int cantorBWidth = 100;
        int cantorBHeight = 25;
        payLabel.setBounds(10, 10, 150, cantorJtaHeight);
        oldCurrencyLabel.setBounds(10, 10, 150, 18);
        scrollPane.setBounds(oldCurrencyLabel.getX() + oldCurrencyLabel.getWidth() + 40, cantorYList - 270, 500, 230);
        buyLabel.setBounds(scrollPane.getX(), scrollPane.getY() - 20, cantorEWidth, 18);
        oldCurrencyBox.setBounds(10, oldCurrencyLabel.getY() + 20, 100, 20);
        newCurrencyLabel.setBounds(10, oldCurrencyBox.getY() + 20, cantorEWidth, 18);
        newCurrencyBox.setBounds(oldCurrencyBox.getX(), newCurrencyLabel.getY() + 20, 100, 20);
        priceLabel.setBounds(newCurrencyBox.getX(), newCurrencyBox.getY() + 30, 150, cantorJtaHeight);
        amountInput.setBounds(priceLabel.getX(), priceLabel.getY() + 20, 100, cantorJtaHeight);
        calculateExchangeButton.setBounds(amountInput.getX(), amountInput.getY() + 22, cantorBWidth, cantorBHeight);
        costLabel.setBounds(calculateExchangeButton.getX(), calculateExchangeButton.getY() + 28, 150, cantorJtaHeight);
        costInput.setBounds(costLabel.getX(), costLabel.getY() + 22, 100, cantorJtaHeight);
        clientLabel.setBounds(10, costInput.getY() + 80, cantorEWidth, 18);
        peselOrKrsInput.setBounds(clientLabel.getX(), clientLabel.getY() + 22, 100, cantorJtaHeight);
        searchClientButton.setBounds(peselOrKrsInput.getX(), peselOrKrsInput.getY() + 22, cantorBWidth, cantorBHeight);
        scrollClientPane.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 30, 550, 230);
        commitTransactionButton.setBounds(costInput.getX(), costInput.getY() + 300, cantorBWidth + 50, cantorBHeight);
    }

    @Override
    public void setSize(int width, int height) {
        resizeCantor(width);
        super.setSize(width, height);
    }

    public void setCantor(CantorMoneyExchanger cantor) {
        this.cantor = cantor;
    }
}
