package dao.impl;

import dao.GuestBookDao;
import dto.SimpleNameData;
import exception.DAOException;
import session.SimpleSession;

import java.util.List;

public class GuestBookDaoImpl extends AbstractDao implements GuestBookDao {

    private final SimpleSession session;

    public GuestBookDaoImpl(SimpleSession session) {
        this.session = session;
    }

    @Override
    public List<SimpleNameData> showColumnsForTable(String table) throws DAOException {
        String query = "show columns from hotel." + table;
        return executeQuery(query, SimpleNameData.class);
    }

    @Override
    public <T> List<T> getDataWithTable(String table, String conditions, Class<T> customerDataClass) throws DAOException {
        String query = "select * from hotel." + table + " " + conditions;
        return executeQuery(query, customerDataClass);
    }

}
