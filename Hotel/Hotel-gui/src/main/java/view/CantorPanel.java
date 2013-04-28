package view;


import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.*;
import dao.CantorDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.cantor.CantorMoneyExchanger;
import service.cantor.CantorTableCreator;
import service.cantor.ExchangeCalculation;

@Component
public class CantorPanel extends TabComponent {

    private static final Logger log = Logger.getLogger(CantorPanel.class);

    private TextField amountInput;
    private Label buyLabel;
    private Label oldCurrencyLabel;
    private Label newCurrencyLabel;
    private Label clientLabel;

    private Label priceLabel;
    private ComboBox oldCurrencyBox;
    private ComboBox newCurrencyBox;
    private Button calculateExchangeButton;
    private Button searchClientButton;
    private Button commitTransactionButton;
    private TextField costInput;
    private TextField peselOrKrsInput;
    private Label costLabel;
    private Label payLabel;
    private Table currencyTable;
    private Table clientTable;
//    private ScrollPane scrollPane;
//    private ScrollPane scrollClientPane;

    private final Color bgColor = new Color(224, 230, 233);
    //    private final Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));
    private final Color buttonColor = new Color(174, 205, 214);

    @Autowired
    private CantorTableCreator creator;

    @Autowired
    private CantorDao cantorDao;

    @Autowired
    private CantorMoneyExchanger cantor;

    private ExchangeCalculation exchangeCalculation;

    @Override
    public void create() {
        VerticalLayout vertical = new VerticalLayout();
        peselOrKrsInput = new TextField("PESEL/KRS Klienta");
        peselOrKrsInput.setWidth(100.0f, Unit.PIXELS);
        peselOrKrsInput.setHeight(20.0f, Unit.PIXELS);

//        currencyTable = createTable(creator.createCurrencyTable());
//        scrollPane = new JScrollPane();
//        scrollPane.setBorder(border);
//        scrollPane.setViewportView(currencyTable);
//
//        scrollClientPane = new JScrollPane();
//        scrollClientPane.setBorder(border);
//
//        currencyTable = new JTable();
//        clientTable = new JTable();
//        buyLabel = new JLabel("Dostepność oraz kursy walut");
//        oldCurrencyLabel = new JLabel("Waluta wymieniana");
//        newCurrencyLabel = new JLabel("Waluta ");
//        clientLabel = new JLabel("PESEL/KRS Klienta");
//        payLabel = new JLabel();
//        amountInput = new JTextField("");
//        costInput = new JTextField("");
//
//        amountInput.setBorder(border);
//        costInput.setBorder(border);
//        peselOrKrsInput.setBorder(border);
//
//        priceLabel = new JLabel("Kwota transakcji:");
//        costLabel = new JLabel("Do zapłaty:");
//
//        oldCurrencyBox = new JComboBox<CURRENCY>(CURRENCY.values());
//        newCurrencyBox = new JComboBox<CURRENCY>(CURRENCY.values());
//        calculateExchangeButton = new JButton("Przelicz");
//        searchClientButton = new JButton("Szukaj");
//        commitTransactionButton = new JButton("Dokonaj transakcji");
//        calculateExchangeButton.setBackground(buttonColor);
//        searchClientButton.setBackground(buttonColor);
//        commitTransactionButton.setBackground(buttonColor);

        vertical.addComponent(peselOrKrsInput);
//        add(priceLabel);
//        add(amountInput);
//        add(costInput);
//        add(currencyTable);
//        add(clientTable);
//        add(costLabel);
//        add(buyLabel);
//        add(oldCurrencyLabel);
//        add(newCurrencyLabel);
//        add(clientLabel);
//        add(priceLabel);
//        add(payLabel);
//        add(oldCurrencyBox);
//        add(newCurrencyBox);
//        add(calculateExchangeButton);
//        add(searchClientButton);
//        add(commitTransactionButton);
//        add(scrollPane);
//        add(scrollClientPane);
        setCompositionRoot(vertical);
    }

//    private void resizeCantor(int width) {
//        int cantorEWidth = (width - 200) / 3;
//        int cantorJtaHeight = 18;
//        int cantorYList = 300;
//        int cantorBWidth = 100;
//        int cantorBHeight = 25;
//        payLabel.setBounds(10, 10, 150, cantorJtaHeight);
//        oldCurrencyLabel.setBounds(10, 10, 150, 18);
//        scrollPane.setBounds(oldCurrencyLabel.getX() + oldCurrencyLabel.getWidth() + 40, cantorYList - 270, 500, 230);
//        buyLabel.setBounds(scrollPane.getX(), scrollPane.getY() - 20, cantorEWidth, 18);
//        oldCurrencyBox.setBounds(10, oldCurrencyLabel.getY() + 20, 100, 20);
//        newCurrencyLabel.setBounds(10, oldCurrencyBox.getY() + 20, cantorEWidth, 18);
//        newCurrencyBox.setBounds(oldCurrencyBox.getX(), newCurrencyLabel.getY() + 20, 100, 20);
//        priceLabel.setBounds(newCurrencyBox.getX(), newCurrencyBox.getY() + 30, 150, cantorJtaHeight);
//        amountInput.setBounds(priceLabel.getX(), priceLabel.getY() + 20, 100, cantorJtaHeight);
//        calculateExchangeButton.setBounds(amountInput.getX(), amountInput.getY() + 22, cantorBWidth, cantorBHeight);
//        costLabel.setBounds(calculateExchangeButton.getX(), calculateExchangeButton.getY() + 28, 150, cantorJtaHeight);
//        costInput.setBounds(costLabel.getX(), costLabel.getY() + 22, 100, cantorJtaHeight);
//        clientLabel.setBounds(10, costInput.getY() + 80, cantorEWidth, 18);
//        peselOrKrsInput.setBounds(clientLabel.getX(), clientLabel.getY() + 22, 100, cantorJtaHeight);
//        searchClientButton.setBounds(peselOrKrsInput.getX(), peselOrKrsInput.getY() + 22, cantorBWidth, cantorBHeight);
//        scrollClientPane.setBounds(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 30, 550, 230);
//        commitTransactionButton.setBounds(costInput.getX(), costInput.getY() + 300, cantorBWidth + 50, cantorBHeight);
//    }

    @Override
    public void addEvents() {
    }

    public void setCreator(CantorTableCreator creator) {
        this.creator = creator;
    }

    public void setCantorDao(CantorDao cantorDao) {
        this.cantorDao = cantorDao;
    }

    public void setCantor(CantorMoneyExchanger cantor) {
        this.cantor = cantor;
    }
}
