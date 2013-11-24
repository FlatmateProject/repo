package dto;

import common.ArrayObtained;

public class OccupationData implements ArrayObtained {

    private long idOccupation;

    private String name;

    private float baseSalary;

    private float extraSalary;

    @Override
    public Object[] getArray() {
        return new Object[]{idOccupation, name, baseSalary, extraSalary};
    }
}
