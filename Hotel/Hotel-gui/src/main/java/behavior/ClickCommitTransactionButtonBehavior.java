package behavior;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import common.TableContent;
import entity.CurrencyExchangeData;
import exception.CantorTransactionCanceledException;
import model.ExchangeCalculationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.cantor.CantorMoneyExchanger;
import service.cantor.CantorTableCreator;
import view.CantorPanel;
import view.PopUpComponent;
import view.common.TableUIBuilder;

@Component
public class ClickCommitTransactionButtonBehavior implements Button.ClickListener {

    @Autowired
    private CantorTableCreator creator;

    @Autowired
    private CantorMoneyExchanger cantor;

    @Autowired
    private ExchangeCalculationModel exchangeCalculationModel;

    @Autowired
    private PopUpComponent popUp;

    private CantorPanel cantorPanel;

    public void buttonClick(Button.ClickEvent event) {
        if (isCostInputEmpty()) {
            popUp.showError("Brak należnej kwoty");
        } else if (isNotSelectedCustomer()) {
            popUp.showError("Nie zaznaczono klienta");
        } else {
            commitMoneyExchangeTransaction();
        }
    }

    private boolean isCostInputEmpty() {
        return cantorPanel.getCostInput().isEmpty();
    }

    private boolean isNotSelectedCustomer() {
        return cantorPanel.getClientTableSelectedRow() == null;
    }

    private void commitMoneyExchangeTransaction() {
        if (cantor.isTransactionPossible(exchangeCalculationModel.getCurrencyExchangeData())) {
            executeTransactionWithUIUpdate();
        } else {
            popUp.showError("Nieprawidłowe dane");
        }
    }

    private void executeTransactionWithUIUpdate() {
        try {
            exchangeMoney();
            refreshView();
        } catch (CantorTransactionCanceledException e) {
            e.printStackTrace();
            popUp.showError("Transakcja wymiany nie powiodla sie");
        }
    }

    private void exchangeMoney() throws CantorTransactionCanceledException {
        String client = cantorPanel.getClient();
        CurrencyExchangeData currencyExchangeData = exchangeCalculationModel.getCurrencyExchangeData();
        currencyExchangeData.forClient(client);
        cantor.exchangeMoney(currencyExchangeData);
    }

    private void refreshView() {
        TableContent currencyTableContent = creator.createCurrencyTable();
        Table currencyTable = TableUIBuilder.table()
                .withTitle("Waluty")
                .withContent(currencyTableContent)
                .withSelection()
                .build();
        cantorPanel.refreshView(currencyTable);
    }

    public void setCantorPanel(CantorPanel cantorPanel) {
        this.cantorPanel = cantorPanel;
    }

    public void setCreator(CantorTableCreator creator) {
        this.creator = creator;
    }

    public void setCantor(CantorMoneyExchanger cantor) {
        this.cantor = cantor;
    }

    public void setExchangeCalculationModel(ExchangeCalculationModel exchangeCalculationModel) {
        this.exchangeCalculationModel = exchangeCalculationModel;
    }

    public void setPopUp(PopUpComponent popUp) {
        this.popUp = popUp;
    }
}