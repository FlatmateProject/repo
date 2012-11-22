package dto.cantor;

import org.fest.util.Arrays;
import service.cantor.ArrayObtained;

public class CurrencyData implements ArrayObtained {

    private long currencyId;

    private String name;

    private long salePrice;

    private long buyPrice;

    private long quantity;

    public Object[] getArray() {
        return Arrays.array(currencyId, name, salePrice, buyPrice, quantity);
    }

}
