package dictionary;

import common.tableBuilder.ArrayObtained;
import dto.*;

public enum TABLE {

    Customer("klienci", CustomerData.class),
    Company("firmy", CompanyData.class),
    Service("uslugi", ServiceData.class),
    Room("pokoje", RoomData.class),
    Occupation("stanowiska", OccupationData.class),
    Currency("waluty", CurrencyData.class),
    Employee("pracownicy", EmployeeData.class),
    RoomType("klasy", RoomTypeData.class),
    Bill("rachunki", BillData.class),
    Archive("archiwum", ArchiveData.class);

    private String tableName;

    private Class<? extends ArrayObtained> tableDtoClass;

    TABLE(String tableName, Class<? extends ArrayObtained> tableDtoClass) {
        this.tableName = tableName;
        this.tableDtoClass = tableDtoClass;
    }

    public Class<? extends ArrayObtained> getTableDtoClass() {
        return tableDtoClass;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public String toString() {
        return getTableName();
    }
}
