package service.cantor;

public class ExchangeCalculation {

    private CURRENCY sellingCurrency;

    private CURRENCY buyingCurrency;

    private float amount;

    private float cost;

    private float gain;

    public static final ExchangeCalculation ZERO = new ExchangeCalculation(0.0f, 0.0f, 0.0f);

    private ExchangeCalculation(float amount, float cost, float gain) {
        this.amount = amount;
        this.cost = cost;
        this.gain = gain;
    }

    public static ExchangeCalculation save(CURRENCY sellingCurrency, CURRENCY buyingCurrency, float amount, float cost, float gain) {
        ExchangeCalculation exchangeCalculation = new ExchangeCalculation(amount, cost, gain);
        exchangeCalculation.sellingCurrency = sellingCurrency;
        exchangeCalculation.buyingCurrency = buyingCurrency;
        return exchangeCalculation;
    }

    public CURRENCY getSellingCurrency() {
        return sellingCurrency;
    }

    public CURRENCY getBuyingCurrency() {
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
}
