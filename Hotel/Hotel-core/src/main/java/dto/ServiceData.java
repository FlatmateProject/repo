package dto;

import common.tableBuilder.ArrayObtained;

public class ServiceData implements ArrayObtained {

    private long idService;

    private String name;

    private float price;

    private String type;

    @Override
    public Object[] getArray() {
        return new Object[]{idService, name, price, type};
    }
}
