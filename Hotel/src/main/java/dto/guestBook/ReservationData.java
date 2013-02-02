package dto.guestBook;

import common.tableBuilder.ArrayObtained;

import java.util.GregorianCalendar;

public class ReservationData implements ArrayObtained {
    private long idRez;

    private long idPesel;

    private long idKrs;

    private long idService;

    private long idRoom;

    private long type;

    private GregorianCalendar checkInData;

    private GregorianCalendar checkOutData;

    @Override
    public Object[] getArray() {
        return new Object[]{idRez, idPesel, idKrs, idService, idRoom, type, checkInData, checkOutData};
    }
}
