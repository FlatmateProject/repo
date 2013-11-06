package behavior;

import com.vaadin.ui.Button;
import model.ExchangeCalculationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.cantor.CURRENCY;
import service.cantor.CantorMoneyExchanger;
import service.cantor.ExchangeCalculation;
import view.CantorPanel;
import view.PopUpComponent;

import static validation.UIValidation.isNotNumber;

@Component
public class ClickCalculateExchangeButtonBehavior implements Button.ClickListener {

    @Autowired
    private CantorMoneyExchanger cantor;

    @Autowired
    private ExchangeCalculationModel exchangeCalculationModel;

    @Autowired
    private PopUpComponent popUp;

    private CantorPanel cantorPanel;

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (isAmountInputEmpty()) {
            popUp.showError("Nie podano kwoty");
        } else if (isNotNumber(cantorPanel.getAmount())) {
            popUp.showError("Podaj prawidlowa ilosc waluty");
        } else if (areSelectedTheSameCurrency()) {
            popUp.showError("Nie można wymieniać na tą samą walutę");
        } else {
            ExchangeCalculation exchangeCalculation = calculateExchange();
            cantorPanel.updateCostInputText(exchangeCalculation.getCost());
            exchangeCalculationModel.setExchangeCalculation(exchangeCalculation);
        }
    }

    private boolean isAmountInputEmpty() {
        return cantorPanel.getAmount().isEmpty();
    }

    private boolean areSelectedTheSameCurrency() {
        return cantorPanel.getOldCurrency() == cantorPanel.getNewCurrency();
    }

    private ExchangeCalculation calculateExchange() {
        float amount = getAmount();
        CURRENCY oldCurrency = cantorPanel.getOldCurrency();
        CURRENCY newCurrency = cantorPanel.getNewCurrency();
        return cantor.calculateExchange(oldCurrency, newCurrency, amount);
    }

    private Float getAmount() {
        return Float.valueOf(cantorPanel.getAmount());
    }

    public void setCantorPanel(CantorPanel cantorPanel) {
        this.cantorPanel = cantorPanel;
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