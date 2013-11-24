package entity;

import common.tableBuilder.ArrayObtained;

import javax.persistence.*;

@Entity
@Table(name = "firmy")
public class CompanyData implements ArrayObtained {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idf_krs")
    private long krsId;

    @Column(name = "nazwa")
    private String name;

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

    @Column(name = "regon")
    private long regon;

    @Column(name = "nip")
    private long nip;

    @Column(name = "telefon")
    private long phone;

    @Column(name = "data_zalozenia")
    private long createDate;

    @Override
    public Object[] getArray() {
        return new Object[]{krsId, name, county, city, street, block, flat, status, notes, regon, nip, phone, createDate};
    }
}
