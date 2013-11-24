package dto;

import common.ArrayObtained;

public class RoomData implements ArrayObtained {

    private long idRoom;

    private long idRoomClass;

    private long idPesel;

    private long idKra;

    @Override
    public Object[] getArray() {
        return new Object[]{idRoom, idRoomClass, idPesel, idKra};
    }
}
