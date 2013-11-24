package dao.impl;

import common.tableBuilder.ArrayObtained;
import dao.GuestBookDao;
import dictionary.TABLE;
import dto.guestBook.RecreationServiceData;
import exception.DAOException;
import session.SimpleSession;

import java.util.List;

public class GuestBookDaoImpl implements GuestBookDao {

    private final SimpleSession session;

    public GuestBookDaoImpl(SimpleSession session) {
        this.session = session;
    }

    @Override
    public List<? extends ArrayObtained> getDataFromTable(TABLE table, String conditions) throws DAOException {
        String query = "select * from hotel." + table + " " + conditions;
        return session.executeQuery(query, table.getTableDtoClass());
    }

    @Override
    public List<RecreationServiceData> getServiceByReservationId(long reservationId) throws DAOException {
        String query = "select uslugi.ID_USLUGI, NAZWA, CENA, TYP, CZAS from hotel.uslugi, rekreacja where rekreacja.id_rez ="
                + reservationId + " and rekreacja.id_uslugi = uslugi.id_uslugi";

        return session.executeQuery(query, RecreationServiceData.class);
    }
}
