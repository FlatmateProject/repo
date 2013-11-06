package model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import service.cantor.ExchangeCalculation;

@Component
@Scope("singleton")
public class ExchangeCalculationModel {

    private ExchangeCalculation exchangeCalculation;

    public ExchangeCalculation getExchangeCalculation() {
        return exchangeCalculation;
    }

    public void setExchangeCalculation(ExchangeCalculation exchangeCalculation) {
        this.exchangeCalculation = exchangeCalculation;
    }
}
