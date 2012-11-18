package dto.cantor;

import org.fest.util.Arrays;
import service.cantor.GetArray;

public class CurrencyData implements GetArray {

    private long currencyId;

    private String name;

    private long salePrice;

    private long buyPrice;

    private long quantity;

    public Object[] getArray() {
        return Arrays.array(currencyId, name, salePrice, buyPrice, quantity);
    }

}
