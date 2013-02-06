package dto;

import common.tableBuilder.ArrayObtained;

public class EmployeeData implements ArrayObtained {

    private long idPesel;

    private String name;

    private String family;

    private String password;

    private String county;

    private String city;

    private String street;

    private String block;

    private long flat;

    private long phone;

    private long nip;

    private long idOccupation;

    @Override
    public Object[] getArray() {
        return new Object[]{idPesel, name, family, password, county, city, street, block, flat, phone, nip, idOccupation};
    }
}
