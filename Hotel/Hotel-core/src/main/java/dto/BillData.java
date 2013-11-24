package dto;

import common.ArrayObtained;
import org.joda.time.LocalDate;

public class BillData implements ArrayObtained {

    private long idBill;

    private long idReservation;

    private LocalDate date;

    private String idr;

    private float tax;

    private float amount;

    private String name;

    @Override
    public Object[] getArray() {
        return new Object[]{idBill, idReservation, date, idr, tax, amount, name};
    }
}
