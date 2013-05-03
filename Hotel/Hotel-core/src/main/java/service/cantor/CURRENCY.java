package service.cantor;

import java.util.Arrays;
import java.util.List;

public enum CURRENCY {
    EUR, USD, GBP, JPY, CHF, CAD, AUD, BRL, CZK, SEK, CNY, RUB, PLN;

    public static List<CURRENCY> asList() {
        return Arrays.asList(values());
    }

}
