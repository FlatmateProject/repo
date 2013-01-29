package dao.impl;

import common.tableBuilder.ArrayObtained;
import dao.GuestBookDao;
import dto.SimpleNameData;
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
    public void updateClientData(String[] labels, String[] data) throws DAOException {
        String query = "update hotel.klienci set ";
        for (int i = 1; i < 10; i++) {
            if (i != 1) {
                query += ", ";
            }
            if (!data[i].isEmpty()) {
                query += labels[i] + " = \"" + data[i] + "\"";
            } else {
                query += labels[i] + " = :OLD." + labels[i];
            }
        }
        query += " where " + labels[0] + " = \"" + data[0] + "\"";
        session.update(query);
    }
}
