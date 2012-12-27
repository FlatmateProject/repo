package service.cantor;

import dto.cantor.CurrencyData;

import static validation.ValidationUtils.isKRS;
import static validation.ValidationUtils.isPesel;

public class ExchangeCalculation {

    private CurrencyData sellingCurrency;

    private CurrencyData buyingCurrency;

    private final float amount;

    private final float cost;

    private final float gain;

    private long clientId;

    private boolean customer;

    private boolean company;

    public static final ExchangeCalculation ZERO = new ExchangeCalculation(0.0f, 0.0f, 0.0f);

    private ExchangeCalculation(float amount, float cost, float gain) {
        this.amount = amount;
        this.cost = cost;
        this.gain = gain;
    }

    public static ExchangeCalculation save(CurrencyData sellingCurrency, CurrencyData buyingCurrency, float amount, float cost, float gain) {
        ExchangeCalculation exchangeCalculation = new ExchangeCalculation(amount, cost, gain);
        exchangeCalculation.sellingCurrency = sellingCurrency;
        exchangeCalculation.buyingCurrency = buyingCurrency;
        return exchangeCalculation;
    }

    public CurrencyData getSellingCurrency() {
        return sellingCurrency;
    }

    public CurrencyData getBuyingCurrency() {
        return buyingCurrency;
    }

    public float getAmount() {
        return amount;
    }

    public float getCost() {
        return cost;
    }

    public float getGain() {
        return gain;
    }

    public long getClientId() {
        return clientId;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void forClient(String clientId) {
        this.customer = isPesel(clientId);
        this.company = isKRS(clientId);
        this.clientId = Long.parseLong(clientId);
    }

    public boolean isCompany() {
        return company;
    }
}
