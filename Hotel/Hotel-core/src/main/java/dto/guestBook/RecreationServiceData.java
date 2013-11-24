package dto.guestBook;

import common.tableBuilder.ArrayObtained;

public class RecreationServiceData implements ArrayObtained {

    private long serviceId;

    private String name;

    private float price;

    private String type;

    private long time;

    @Override
    public Object[] getArray() {
        return new Object[]{serviceId, name, price, type, time};
    }
}
