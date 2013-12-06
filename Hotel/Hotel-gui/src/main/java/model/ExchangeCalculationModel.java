package model;

import entity.CurrencyExchangeData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ExchangeCalculationModel {

    private CurrencyExchangeData currencyExchangeData;

    public CurrencyExchangeData getCurrencyExchangeData() {
        return currencyExchangeData;
    }

    public void setCurrencyExchangeData(CurrencyExchangeData currencyExchangeData) {
        this.currencyExchangeData = currencyExchangeData;
    }
}
