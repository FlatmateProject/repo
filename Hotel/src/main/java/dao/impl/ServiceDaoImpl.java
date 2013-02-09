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
    public void updateData(TABLE table, String conditions) throws DAOException {
        String query = "update hotel." + table + " set " + conditions;
        session.update(query);
    }

    @Override
    public boolean insertData(TABLE table, String[] labels, String[] data) throws DAOException {
        String query = "insert into hotel." + table + "(";
        int length = data.length;
        for (int i = 0; i < length; i++) {
            if (i == 0 && data[i].isEmpty()) {
                return false;
            } else if (!data[i].isEmpty()) {
                if (i != 0)
                    query += ", ";
                query += labels[i];
            }
        }
        query += ") values(";
        for (int i = 0; i < length; i++) {
            if (i == 0 && data[i].isEmpty()) {
                return false;
            } else if (!data[i].isEmpty()) {
                if (i != 0)
                    query += ", ";
                query += "\"" + data[i] + "\"";
            }
        }
        query += ");";
        session.update(query);
        return true;
    }

    @Override
    public void deleteData(TABLE table, String primaryKey, long id) throws DAOException {
        String query = "delete from hotel." + table + " where " + primaryKey + " = " + id;
        session.update(query);
    }
}
