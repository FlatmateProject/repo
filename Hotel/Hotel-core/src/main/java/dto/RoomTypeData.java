package dto;

import common.ArrayObtained;

public class RoomTypeData implements ArrayObtained {

    private long idRoomClass;

    private long numberOccupiedPersons;

    private float price;

    private String description;

    @Override
    public Object[] getArray() {
        return new Object[]{idRoomClass, numberOccupiedPersons, price, description};
    }
}
