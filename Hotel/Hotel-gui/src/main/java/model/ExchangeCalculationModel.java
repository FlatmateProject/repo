package model;

import entity.ExchangeCalculationData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ExchangeCalculationModel {

    private ExchangeCalculationData exchangeCalculationData;

    public ExchangeCalculationData getExchangeCalculationData() {
        return exchangeCalculationData;
    }

    public void setExchangeCalculationData(ExchangeCalculationData exchangeCalculationData) {
        this.exchangeCalculationData = exchangeCalculationData;
    }
}
