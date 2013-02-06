package dao;

import common.tableBuilder.ArrayObtained;
import dto.ColumnData;
import dto.guestBook.RecreationServiceData;
import dto.guestBook.ReservationData;
import exception.DAOException;
import service.dictionary.TABLE;

import java.util.List;

public interface GuestBookDao {

    List<ColumnData> showColumnsForTable(String table) throws DAOException;

    List<? extends ArrayObtained> getDataFromTable(TABLE table, String conditions) throws DAOException;

    List<ReservationData> getReservationsByClientId(String primaryId, long clientId) throws DAOException;

    List<RecreationServiceData> getServiceByReservationId(long reservationId) throws DAOException;

}
