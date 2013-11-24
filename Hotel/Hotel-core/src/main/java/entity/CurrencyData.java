package entity;

import common.ArrayObtained;
import service.cantor.CURRENCY;

import javax.persistence.*;

@Entity
@Table(name = "waluty")
public class CurrencyData implements ArrayObtained {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_waluty")
    private long currencyId;

    @Column(name = "nazwa")
    private String name;

    @Column(name = "cena_sp")
    private float salePrice;

    @Column(name = "cena_ku")
    private float buyPrice;

    @Column(name = "ilosc")
    private float quantity;

    public CurrencyData() {
    }

    public Object[] getArray() {
        return new Object[]{currencyId, name, salePrice, buyPrice, quantity};
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
