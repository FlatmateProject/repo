package view;

import behavior.ClickCalculateExchangeButtonBehavior;
import behavior.ClickCommitTransactionButtonBehavior;
import behavior.ClickSearchClientButtonBehavior;
import com.vaadin.data.Property;
import com.vaadin.ui.*;
import common.TableContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.cantor.CURRENCY;
import service.cantor.CantorTableCreator;
import view.common.TableUIBuilder;

import java.text.NumberFormat;

@Component
public class CantorPanel extends TabComponent {

    private final CURRENCY DEFAULT_SELECTED_CURRENCY = CURRENCY.EUR;
    private TextField amountInput;
    private ComboBox oldCurrencyBox;
    private ComboBox newCurrencyBox;
    private Button calculateExchangeButton;
    private Button searchClientButton;
    private Button commitTransactionButton;
    private TextField costInput;
    private TextField clientIdInput;
    private Table currencyTable;
    private Table clientTable;

    private VerticalLayout currencyTableLayout;

    private VerticalLayout clientTableLayout;

    @Autowired
    private CantorTableCreator creator;

    @Autowired
    private ClickSearchClientButtonBehavior clickSearchClientButtonBehavior;

    @Autowired
    private ClickCalculateExchangeButtonBehavior clickCalculateExchangeButtonBehavior;

    @Autowired
    private ClickCommitTransactionButtonBehavior clickCommitTransactionButtonBehavior;

    @Override
    public void create() {
        oldCurrencyBox = new ComboBox("Waluta wymieniana", CURRENCY.asList());
        oldCurrencyBox.setNullSelectionAllowed(false);
        oldCurrencyBox.setValue(DEFAULT_SELECTED_CURRENCY);

        newCurrencyBox = new ComboBox("Nowa waluta", CURRENCY.asList());
        newCurrencyBox.setNullSelectionAllowed(false);
        newCurrencyBox.setValue(DEFAULT_SELECTED_CURRENCY);

        amountInput = new TextField("Kwota");
        calculateExchangeButton = new Button("Przelicz");
        costInput = new TextField("Do zapłaty");

        clientIdInput = new TextField("PESEL/KRS Klienta");
        searchClientButton = new Button("Szukaj");

        commitTransactionButton = new Button("Dokonaj transakcji");

        TableContent currencyTableContent = creator.createCurrencyTable();
        currencyTable = TableUIBuilder.table()
                .withTitle("Waluty")
                .withContent(currencyTableContent)
                .withSelection()
                .build();

        clientTable = new Table();

        VerticalLayout verticalLeft = new VerticalLayout();
        verticalLeft.setMargin(true);
        verticalLeft.addComponent(oldCurrencyBox);
        verticalLeft.addComponent(newCurrencyBox);
        verticalLeft.addComponent(amountInput);
        verticalLeft.addComponent(calculateExchangeButton);
        verticalLeft.addComponent(costInput);
        verticalLeft.addComponent(clientIdInput);
        verticalLeft.addComponent(searchClientButton);
        verticalLeft.addComponent(commitTransactionButton);

        currencyTableLayout = new VerticalLayout();
        currencyTableLayout.addComponent(currencyTable);

        clientTableLayout = new VerticalLayout();

        VerticalLayout verticalRight = new VerticalLayout();
        verticalRight.setMargin(true);
        verticalRight.addComponent(currencyTableLayout);
        verticalRight.addComponent(clientTableLayout);

        HorizontalLayout horizontal = new HorizontalLayout();
        verticalRight.setMargin(true);
        horizontal.addComponent(verticalLeft);
        horizontal.addComponent(verticalRight);
        setCompositionRoot(horizontal);
    }

    @Override
    public void addEvents() {
        clickSearchClientButtonBehavior.setCantorPanel(this);
        searchClientButton.addClickListener(clickSearchClientButtonBehavior);

        clickCalculateExchangeButtonBehavior.setCantorPanel(this);
        calculateExchangeButton.addClickListener(clickCalculateExchangeButtonBehavior);

        clickCommitTransactionButtonBehavior.setCantorPanel(this);
        commitTransactionButton.addClickListener(clickCommitTransactionButtonBehavior);
    }

    public void refreshCustomerTable(Table customerTable) {
        clientTableLayout.removeComponent(clientTable);
        clientTable = customerTable;
        clientTableLayout.addComponent(clientTable);
    }

    public void refreshCompanyTable(Table companyTable) {
        clientTableLayout.removeComponent(clientTable);
        clientTable = companyTable;
        clientTableLayout.addComponent(clientTable);
    }

    public void refreshView(Table currencyTable) {
        clientIdInput.setValue("");
        oldCurrencyBox.setValue(DEFAULT_SELECTED_CURRENCY);
        newCurrencyBox.setValue(DEFAULT_SELECTED_CURRENCY);
        costInput.setValue("");
        currencyTableLayout.removeComponent(this.currencyTable);
        this.currencyTable = currencyTable;
        currencyTableLayout.addComponent(currencyTable);
    }

    public String getClientId() {
        return clientIdInput.getValue();
    }

    public String getAmount() {
        return amountInput.getValue();
    }

    public void updateCostInputText(float cost) {
        costInput.setValue(Double.toString(round(cost)));
    }

    public CURRENCY getOldCurrency() {
        return (CURRENCY) oldCurrencyBox.getValue();
    }

    public CURRENCY getNewCurrency() {
        return (CURRENCY) newCurrencyBox.getValue();
    }

    public String getCostInput() {
        return costInput.getValue();
    }

    public Object getClientTableSelectedRow() {
        return clientTable.getValue();
    }

    public String getClient() {
        Object selectedRowIndex = getClientTableSelectedRow();
        Property property = clientTable.getItem(selectedRowIndex).getItemProperty(1);
        return String.valueOf(property.getValue());
    }

    private double round(double digit) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return Double.parseDouble((numberFormat.format(digit)).replaceAll(",", ".").replaceAll("", ""));
    }

    public void setCreator(CantorTableCreator creator) {
        this.creator = creator;
    }

    public void setClickSearchClientButtonBehavior(ClickSearchClientButtonBehavior clickSearchClientButtonBehavior) {
        this.clickSearchClientButtonBehavior = clickSearchClientButtonBehavior;
    }

    public void setClickCalculateExchangeButtonBehavior(ClickCalculateExchangeButtonBehavior clickCalculateExchangeButtonBehavior) {
        this.clickCalculateExchangeButtonBehavior = clickCalculateExchangeButtonBehavior;
    }

    public void setClickCommitTransactionButtonBehavior(ClickCommitTransactionButtonBehavior clickCommitTransactionButtonBehavior) {
        this.clickCommitTransactionButtonBehavior = clickCommitTransactionButtonBehavior;
    }
}
