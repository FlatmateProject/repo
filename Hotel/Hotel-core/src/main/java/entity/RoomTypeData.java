package entity;

import common.ArrayObtained;

import javax.persistence.*;

@Entity
@Table(name = "klasy")
public class RoomTypeData implements ArrayObtained {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_KLASY")
    private long roomTypeId;

    @Column(name = "IL_OSOB")
    private long capacity;

    @Column(name = "CENA")
    private float price;

    @Column(name = "OPIS")
    private String description;

    @Override
    public Object[] getArray() {
        return new Object[]{roomTypeId, capacity, price, description};
    }
}
