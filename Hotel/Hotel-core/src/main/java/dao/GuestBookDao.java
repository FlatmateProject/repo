package dao;

import common.ArrayObtained;
import dictionary.TABLE;
import dto.guestBook.RecreationServiceData;
import exception.DAOException;

import java.util.List;

public interface GuestBookDao {

    List<? extends ArrayObtained> getDataFromTable(TABLE table, String conditions) throws DAOException;

    List<RecreationServiceData> getServiceByReservationId(long reservationId) throws DAOException;
}
