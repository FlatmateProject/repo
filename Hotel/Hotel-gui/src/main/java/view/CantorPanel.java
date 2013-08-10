package view;


import com.vaadin.data.Property;
import com.vaadin.ui.*;
import common.tableBuilder.TableContent;
import exception.CantorTransactionCanceledException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.cantor.CURRENCY;
import service.cantor.CantorMoneyExchanger;
import service.cantor.CantorTableCreator;
import service.cantor.ExchangeCalculation;
import view.common.TableUIBuilder;

import java.text.NumberFormat;

import static com.vaadin.ui.Button.ClickEvent;
import static com.vaadin.ui.Button.ClickListener;
import static validation.BusinessValidation.isKRS;
import static validation.BusinessValidation.isPesel;
import static validation.UIValidation.isNotNumber;

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
    private TextField customerIdInput;
    private Table currencyTable;
    private Table clientTable;

    private VerticalLayout currencyTableLayout;

    private VerticalLayout clientTableLayout;

    @Autowired
    private CantorTableCreator creator;

    @Autowired
    private CantorMoneyExchanger cantor;

    private ExchangeCalculation exchangeCalculation;


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

        customerIdInput = new TextField("PESEL/KRS Klienta");
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
        verticalLeft.addComponent(customerIdInput);
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
        searchClientButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                String customerId = customerIdInput.getValue();
                if (isCustomerIdEmpty()) {
                    Notification.show("Nie podano numeru PESEL/KRS");
                } else if (isPesel(customerId)) {
                    clientTableLayout.removeComponent(clientTable);
                    clientTable = createCustomerTable();
                    clientTableLayout.addComponent(clientTable);
                } else if (isKRS(customerId)) {
                    clientTableLayout.removeComponent(clientTable);
                    clientTable = createCompanyTable();
                    clientTableLayout.addComponent(clientTable);
                } else {
                    Notification.show("Nieprawidłowy PESEL/KRS");
                }
            }

            private boolean isCustomerIdEmpty() {
                return customerIdInput.getValue().isEmpty();
            }

            private Table createCustomerTable() {
                long pesel = Long.parseLong(customerIdInput.getValue());
                TableContent customerTableContent = creator.createCustomerTable(pesel);
                return TableUIBuilder.table()
                        .withTitle("Klienci indywidualni")
                        .withContent(customerTableContent)
                        .withSelection()
                        .build();
            }

            private Table createCompanyTable() {
                long krs = Long.parseLong(customerIdInput.getValue());
                TableContent companyTableContent = creator.createCompanyTable(krs);
                return TableUIBuilder.table()
                        .withTitle("Firmy")
                        .withContent(companyTableContent)
                        .withSelection()
                        .build();
            }
        });

        calculateExchangeButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (isAmountInputEmpty()) {
                    Notification.show("Nie podano kwoty");
                } else if (isNotNumber(amountInput.getValue())) {
                    Notification.show("Podaj prawidlowa ilosc waluty");
                } else if (areSelectedTheSameCurrency()) {
                    Notification.show("Nie można wymieniać na tą samą walutę");
                } else {
                    exchangeCalculation = calculateExchange();
                    updateCostInputText();
                }
            }

            private boolean isAmountInputEmpty() {
                return amountInput.getValue().isEmpty();
            }

            private boolean areSelectedTheSameCurrency() {
                return oldCurrencyBox.getValue() == newCurrencyBox.getValue();
            }

            private ExchangeCalculation calculateExchange() {
                float amount = getAmount();
                CURRENCY oldCurrency = (CURRENCY) oldCurrencyBox.getValue();
                CURRENCY newCurrency = (CURRENCY) newCurrencyBox.getValue();
                return cantor.calculateExchange(oldCurrency, newCurrency, amount);
            }

            private Float getAmount() {
                return Float.valueOf(amountInput.getValue());
            }

            private void updateCostInputText() {
                costInput.setValue(Double.toString(round(exchangeCalculation.getCost())));
            }
        });

        commitTransactionButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (isCostInputEmpty()) {
                    Notification.show("Brak należnej kwoty");
                } else if (isNotSelectedCustomer()) {
                    Notification.show("Nie zaznaczono klienta");
                } else {
                    commitMoneyExchangeTransaction();
                }
            }

            private boolean isCostInputEmpty() {
                return costInput.getValue().isEmpty();
            }

            private boolean isNotSelectedCustomer() {
                return clientTable.getValue() == null;
            }

            private void commitMoneyExchangeTransaction() {
                if (cantor.isTransactionPossible(exchangeCalculation)) {
                    executeTransactionWithUIUpdate();
                } else {
                    Notification.show("Nieprawidłowe dane");
                }
            }

            private void executeTransactionWithUIUpdate() {
                try {
                    exchangeMoney();
                    refreshView();
                } catch (CantorTransactionCanceledException e) {
                    e.printStackTrace();
                    Notification.show("Transakcja wymiany nie powiodla sie");
                }
            }

            private void exchangeMoney() throws CantorTransactionCanceledException {
                String client = getClient();
                exchangeCalculation.forClient(client);
                cantor.exchangeMoney(exchangeCalculation);
            }

            private void refreshView() {
                customerIdInput.setValue("");
                oldCurrencyBox.setValue(DEFAULT_SELECTED_CURRENCY);
                newCurrencyBox.setValue(DEFAULT_SELECTED_CURRENCY);
                costInput.setValue("");
                currencyTableLayout.removeComponent(currencyTable);
                TableContent currencyTableContent = creator.createCurrencyTable();
                currencyTable = TableUIBuilder.table()
                        .withTitle("Waluty")
                        .withContent(currencyTableContent)
                        .withSelection()
                        .build();
                currencyTableLayout.addComponent(currencyTable);
            }

            private String getClient() {
                Object selectedRowIndex = clientTable.getValue();
                Property property = clientTable.getItem(selectedRowIndex).getItemProperty(1);
                return String.valueOf(property.getValue());
            }
        });
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

    public void setCantor(CantorMoneyExchanger cantor) {
        this.cantor = cantor;
    }
}