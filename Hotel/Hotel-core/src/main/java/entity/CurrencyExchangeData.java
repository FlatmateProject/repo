package entity;

import javax.persistence.*;
import java.util.Date;

import static validation.BusinessValidation.isKRS;
import static validation.BusinessValidation.isPesel;

@Entity
@Table(name = "kantor")
public class CurrencyExchangeData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_trans")
    private long transactionId;

    @Column(name = "idk_pesel")
    private long peselId;

    @Column(name = "idf_krs")
    private long krsId;

    @Column(name = "data")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "w_ku")
    private CurrencyData buyingCurrency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "w_sp")
    private CurrencyData sellingCurrency;

    @Column(name = "ilosc")
    private final float amount;

    @Column(name = "wartosc")
    private final float cost;

    @Column(name = "zysk")
    private final float gain;

    public static final CurrencyExchangeData ZERO = new CurrencyExchangeData(0.0f, 0.0f, 0.0f);

    private CurrencyExchangeData(float amount, float cost, float gain) {
        this.amount = amount;
        this.cost = cost;
        this.gain = gain;
    }

    public static CurrencyExchangeData save(CurrencyData sellingCurrency, CurrencyData buyingCurrency, float amount, float cost, float gain) {
        CurrencyExchangeData currencyExchangeData = new CurrencyExchangeData(amount, cost, gain);
        currencyExchangeData.sellingCurrency = sellingCurrency;
        currencyExchangeData.buyingCurrency = buyingCurrency;
        currencyExchangeData.date = new Date();
        return currencyExchangeData;
    }

    public CurrencyData getSellingCurrency() {
        return sellingCurrency;
    }

    public CurrencyData getBuyingCurrency() {
        return buyingCurrency;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public Date getDate() {
        return date;
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
        if (peselId != 0) {
            return peselId;
        } else {
            return krsId;
        }
    }

    public boolean isCustomer() {
        return peselId != 0;
    }

    public void forClient(String clientId) {
        if (isPesel(clientId)) {
            this.peselId = Long.parseLong(clientId);
        }
        if (isKRS(clientId)) {
            this.krsId = Long.parseLong(clientId);
        }
    }

    public boolean isCompany() {
        return krsId != 0;
    }
}
