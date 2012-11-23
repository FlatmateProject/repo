package service.cantor;

public class ExchangeCalculation {

    private float amount;

    private float cost;

    private float gain;

    public static final ExchangeCalculation ZERO = new ExchangeCalculation(0.0f, 0.0f, 0.0f);

    private ExchangeCalculation(float amount, float cost, float gain) {
        this.amount = amount;
        this.cost = cost;
        this.gain = gain;
    }

    public static ExchangeCalculation save(float amount, float cost, float gain) {
        return new ExchangeCalculation(amount, cost, gain);
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
