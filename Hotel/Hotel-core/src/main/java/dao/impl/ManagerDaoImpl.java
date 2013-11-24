package dao.impl;

import common.ArrayObtained;
import dao.ManagerDao;
import dictionary.TABLE;
import exception.DAOException;
import session.SimpleSession;

import java.util.List;

public class ManagerDaoImpl implements ManagerDao {

    private final SimpleSession session;

    public ManagerDaoImpl(SimpleSession session) {
        this.session = session;
    }

    @Override
    public List<? extends ArrayObtained> getDataFromTable(TABLE table) throws DAOException {
        String query = "select * from hotel." + table;
        return session.executeQuery(query, table.getTableDtoClass());
    }

    @Override
    public int getCountForTable(String tableName) throws DAOException {
        String query = "select count(*) from " + tableName;
        Long count = (Long) session.simpleResult(query);
        return count != null ? count.intValue() : 0;
    }
}
