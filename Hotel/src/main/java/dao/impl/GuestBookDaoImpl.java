package dao.impl;

import common.tableBuilder.ArrayObtained;
import dao.GuestBookDao;
import dto.SimpleNameData;
import dto.guestBook.ServiceData;
import exception.DAOException;
import session.SimpleSession;

import java.util.List;

public class GuestBookDaoImpl implements GuestBookDao {

    private final SimpleSession session;

    public GuestBookDaoImpl(SimpleSession session) {
        this.session = session;
    }

    @Override
    public List<SimpleNameData> showColumnsForTable(String table) throws DAOException {
        String query = "show columns from hotel." + table;
        return session.executeQuery(query, SimpleNameData.class);
    }

    @Override
    public <T extends ArrayObtained> List<T> getDataFromTable(String table, String conditions, Class<T> customerDataClass) throws DAOException {
        String query = "select * from hotel." + table + " " + conditions;
        return session.executeQuery(query, customerDataClass);
    }

    @Override
    public List<ServiceData> getServiceByReservationId(long reservationId) throws DAOException {
        String query = "select uslugi.ID_USLUGI, NAZWA, CENA, TYP, CZAS from hotel.uslugi, rekreacja where rekreacja.id_rez ="
                + reservationId + " and rekreacja.id_uslugi = uslugi.id_uslugi";
        return session.executeQuery(query, ServiceData.class);
    }

    @Override
    public void updateClientData(String[] labels, String[] data) throws DAOException {
        int count = 0;
        String query = "update hotel.klienci set ";
        for (int i = 1; i < data.length; i++) {
            if (!data[i].isEmpty()) {
                if (count > 0) {
                    query += ", ";
                }
                query += labels[i] + " = \"" + data[i] + "\"";
                count++;
            }
        }
        query += " where " + labels[0] + " = \"" + data[0] + "\"";
        session.update(query);
    }
}
