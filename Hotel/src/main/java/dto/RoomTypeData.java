package dto;

import common.tableBuilder.ArrayObtained;

public class RoomTypeData implements ArrayObtained {

    private long idRoomClass;

    private long numberOccupiedPersons;

    private float price;

    private float description;

    @Override
    public Object[] getArray() {
        return new Object[]{idRoomClass, numberOccupiedPersons, price, description};
    }
}
