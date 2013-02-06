package dao.impl;

import dao.ServiceDao;
import exception.DAOException;
import service.dictionary.TABLE;
import session.SimpleSession;

public class ServiceDaoImpl implements ServiceDao {

    private final SimpleSession session;

    public ServiceDaoImpl(SimpleSession session) {
        this.session = session;
    }

    @Override
    public void updateClientData(TABLE table, String[] labels, String[] data) throws DAOException {
        int count = 0;
        String query = "update hotel." + table + " set ";
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
