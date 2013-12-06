package entity;

import common.ArrayObtained;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rezerwacje")
public class ReservationData implements ArrayObtained {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rez")
    private long reservationId;

    @Column(name = "idk_pesel")
    private long peselId;

    @Column(name = "idf_krs")
    private long krsId;

    @Column(name = "ID_USLUGI")
    private long serviceId;

    @Column(name = "ID_POKOJU")
    private long roomId;

    @Column(name = "TYP")
    private long type;

    @Column(name = "DATA_Z")
    private Date checkInData;

    @Column(name = "DATA_W")
    private Date checkOutData;

    public ReservationData() {
    }

    @Override
    public Object[] getArray() {
        return new Object[]{reservationId, peselId, krsId, serviceId, roomId, type, checkInData, checkOutData};
    }

    public long getReservationId() {
        return reservationId;
    }

    public long getPeselId() {
        return peselId;
    }

    public long getKrsId() {
        return krsId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getType() {
        return type;
    }

    public Date getCheckInData() {
        return checkInData;
    }

    public Date getCheckOutData() {
        return checkOutData;
    }
}
