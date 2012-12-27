package dto.cantor;

import org.fest.util.Arrays;
import service.cantor.ArrayObtained;
import service.cantor.CURRENCY;

public class CurrencyData implements ArrayObtained {

    private long currencyId;

    private String name;

    private float salePrice;

    private float buyPrice;

    private float quantity;

    public CurrencyData() {
    }

    public CurrencyData(String name) {
        this.name = name;
    }

    public Object[] getArray() {
        return Arrays.array(currencyId, name, salePrice, buyPrice, quantity);
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public String getName() {
        return name;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public float getQuantity() {
        return quantity;
    }

    public void increaseQuantity(float amount) {
        quantity = quantity + amount;
    }

    public void decreaseQuantity(float amount) {
        quantity = quantity - amount;
    }

    public CURRENCY asEnum() {
        return CURRENCY.valueOf(name);
    }

}
