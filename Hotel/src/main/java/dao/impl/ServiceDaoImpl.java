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
    public void updateData(TABLE table, String[] labels, String[] data) throws DAOException {
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
    public boolean deleteData(TABLE table, String label, String data) throws DAOException {
        String query = "delete from hotel." + table + " where " + label + " = " + data;
        session.update(query);
        return true;
    }
}
