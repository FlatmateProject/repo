package entity;

import common.ArrayObtained;

import javax.persistence.*;

@Entity
@Table(name = "rekreacja")
public class RecreationServiceData implements ArrayObtained {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDK_REKREACJI")
    private long recreationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USLUGI")
    private ServiceData service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_REZ")
    private ReservationData reservation;

    @Column(name = "CZAS")
    private long time;

    @Override
    public Object[] getArray() {
        return new Object[]{recreationId, service.getName(), service.getPrice(), service.getType(), time};
    }

    public long getRecreationId() {
        return recreationId;
    }

    public ServiceData getService() {
        return service;
    }

    public ReservationData getReservation() {
        return reservation;
    }

    public long getTime() {
        return time;
    }
}
