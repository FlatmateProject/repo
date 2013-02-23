package dto;

import common.tableBuilder.ArrayObtained;

public class CompanyData implements ArrayObtained {

    private long idKrs;

    private String name;

    private String county;

    private String city;

    private String street;

    private String block;

    private long flat;

    private String status;

    private String notes;

    private long regon;

    private long nip;

    private long phone;

    private long createDate;


    @Override
    public Object[] getArray() {
        return new Object[]{idKrs, name, county, city, street, block, flat, status, notes, regon, nip, phone, createDate};
    }
}
