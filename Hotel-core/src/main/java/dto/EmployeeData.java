package dto;

import common.tableBuilder.ArrayObtained;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class EmployeeData implements ArrayObtained {

    private long pesel;

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
        return new Object[]{pesel, name, family, password, county, city, street, block, flat, phone, nip, idOccupation};
    }

    public long getPesel() {
        return pesel;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getPassword() {
        return password;
    }

    public String getCounty() {
        return county;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getBlock() {
        return block;
    }

    public long getFlat() {
        return flat;
    }

    public long getPhone() {
        return phone;
    }

    public long getNip() {
        return nip;
    }

    public long getIdOccupation() {
        return idOccupation;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        EmployeeData that = (EmployeeData) object;
        return new EqualsBuilder()
                .append(pesel, that.pesel)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(pesel)
                .toHashCode();
    }
}
