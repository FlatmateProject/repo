package entity.reservation;

import common.tableBuilder.ArrayObtained;

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
    private long idService;

    @Column(name = "ID_POKOJU")
    private long idRoom;

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
        return new Object[]{reservationId, peselId, krsId, idService, idRoom, type, checkInData, checkOutData};
    }
}
