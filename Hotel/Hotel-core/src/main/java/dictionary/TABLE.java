package dictionary;

import common.tableBuilder.ArrayObtained;
import dto.*;
import entity.CompanyData;
import entity.CurrencyData;
import entity.CustomerData;
import entity.ReservationData;

public enum TABLE {

    Customer("klienci", CustomerData.class, new String[]{"IDK_PESEL", "IMIE", "NAZWISKO", "WOJEWODZTWO", "MIASTO", "ULICA", "BLOK", "NR_LOKALU", "STATUS", "UWAGI", "TELEFON", "NIP"}),
    Company("firmy", CompanyData.class, new String[]{"IDF_KRS", "NAZWA", "WOJEWODZTWO", "MIASTO", "ULICA", "BLOK", "NR_LOKALU", "STATUS", "UWAGI", "REGON", "NIP", "TELEFON", "DATA_ZALOZENIA"}),
    Service("uslugi", ServiceData.class, new String[]{}),
    Room("pokoje", RoomData.class, new String[]{}),
    Occupation("stanowiska", OccupationData.class, new String[]{}),
    Currency("waluty", CurrencyData.class, new String[]{"ID_WALUTY", "NAZWA", "CENA_SP", "CENA_KU", "ILOSC"}),
    Employee("pracownicy", EmployeeData.class, new String[]{}),
    RoomType("klasy", RoomTypeData.class, new String[]{}),
    Bill("rachunki", BillData.class, new String[]{}),
    Archive("archiwum", ArchiveData.class, new String[]{}),
    Reservation("rezerwacje", ReservationData.class, new String[]{});

    private String tableName;

    private Class<? extends ArrayObtained> tableDtoClass;

    private String[] columns;

    private TABLE(String tableName, Class<? extends ArrayObtained> tableDtoClass, String[] columns) {
        this.tableName = tableName;
        this.tableDtoClass = tableDtoClass;
        this.columns = columns;
    }

    public Class<? extends ArrayObtained> getTableDtoClass() {
        return tableDtoClass;
    }

    public String getTableName() {
        return tableName;
    }

    public String[] getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        return getTableName();
    }
}
