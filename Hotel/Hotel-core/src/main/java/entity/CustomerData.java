package entity;

import common.tableBuilder.ArrayObtained;

import javax.persistence.*;

@Entity
@Table(name = "klienci")
public class CustomerData implements ArrayObtained {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idk_pesel")
    private long peselId;

    @Column(name = "imie")
    private String name;

    @Column(name = "nazwisko")
    private String family;

    @Column(name = "wojewodztwo")
    private String county;

    @Column(name = "miasto")
    private String city;

    @Column(name = "ulica")
    private String street;

    @Column(name = "blok")
    private String block;

    @Column(name = "nr_lokalu")
    private long flat;

    @Column(name = "status")
    private String status;

    @Column(name = "uwagi")
    private String notes;

    @Column(name = "telefon")
    private long phone;

    @Column(name = "nip")
    private long nip;

    @Override
    public Object[] getArray() {
        return new Object[]{peselId, name, family, county, city, street, block, flat, status, notes, phone, nip};
    }
}
