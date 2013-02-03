package dao;

import common.tableBuilder.ArrayObtained;
import dto.ColumnData;
import dto.guestBook.ServiceData;
import exception.DAOException;

import java.util.List;

public interface GuestBookDao {

    List<ColumnData> showColumnsForTable(String table) throws DAOException;

    <T extends ArrayObtained> List<T> getDataFromTable(String table, String conditions, Class<T> customerDataClass) throws DAOException;

    void updateClientData(String[] labels, String[] data) throws DAOException;

    List<ServiceData> getServiceByReservationId(long reservationId) throws DAOException;

}
