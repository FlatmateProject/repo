package dto.guestBook;

import common.tableBuilder.ArrayObtained;
import org.joda.time.LocalDate;

public class ReservationData implements ArrayObtained {
    private long idRez;

    private long idPesel;

    private long idKrs;

    private long idService;

    private long idRoom;

    private long type;

    private LocalDate checkInData;

    private LocalDate checkOutData;

    @Override
    public Object[] getArray() {
        return new Object[]{idRez, idPesel, idKrs, idService, idRoom, type, checkInData, checkOutData};
    }
}
