package dictionary;

import common.ArrayObtained;
import dto.*;
import entity.*;

public enum TABLE {

    Customer("klienci", CustomerData.class, new String[]{"IDK_PESEL", "IMIE", "NAZWISKO", "WOJEWODZTWO", "MIASTO", "ULICA", "BLOK", "NR_LOKALU", "STATUS", "UWAGI", "TELEFON", "NIP"}),
    Company("firmy", CompanyData.class, new String[]{"IDF_KRS", "NAZWA", "WOJEWODZTWO", "MIASTO", "ULICA", "BLOK", "NR_LOKALU", "STATUS", "UWAGI", "REGON", "NIP", "TELEFON", "DATA_ZALOZENIA"}),
    Service("uslugi", ServiceData.class, new String[]{"ID_USLUGI", "NAZWA", " CENA", "TYP"}),
    Room("pokoje", RoomData.class, new String[]{"ID_POKOJU", "ID_KLASY", "IDK_PESEL", "IDF_KRS"}),
    Occupation("stanowiska", OccupationData.class, new String[]{"ID_STANOWISKA", "NAZWA", "PODSTAWA", "PREMIA "}),
    Currency("waluty", CurrencyData.class, new String[]{"ID_WALUTY", "NAZWA", "CENA_SP", "CENA_KU", "ILOSC"}),
    Employee("pracownicy", EmployeeData.class, new String[]{"IDP_PESEL", "IMIE", "NAZWISKO", "HASLO", "WOJEWODZTWO", "MIASTO", "ULICA", "BLOK", "NR_LOKALU", "TELEFON", "NIP", "ID_STANOWISKA"}),
    RoomType("klasy", RoomTypeData.class, new String[]{"ID_KLASY", "IL_OSOB", "CENA", "OPIS"}),
    Bill("rachunki", BillData.class, new String[]{"ID_RACHUNKU", "ID_REZ", "DATA", "IDR", "PODATEK", "WARTOSC", "NAZWA"}),
    Archive("archiwum", ArchiveData.class, new String[]{"ID_ARCHIVE"}),
    Reservation("rezerwacje", ReservationData.class, new String[]{"ID_REZ", "IDK_PESEL", "IDF_KRS", "ID_USLUGI", "ID_POKOJU", "TYP", "DATA_Z", "DATA_W"});

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
