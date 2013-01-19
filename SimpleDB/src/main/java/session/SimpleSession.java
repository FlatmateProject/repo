package session;

import exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public class SimpleSession {

    private Singleton singleton;

    public SimpleSession(DataSource dataSource) {
        singleton = Singleton.getInstance(dataSource);
    }

    public Object simpleResult(String query) throws DAOException {
        return readOneColumnOfFirstRow(query);
    }

    public <T> T uniqueResult(String query, Class<T> dtoClass) throws DAOException {
        ResultSet resultSet = singleton.query(query);
        return Transformer.on(resultSet).transformTo(dtoClass);
    }

    public <T> List<T> executeQuery(String query, Class<T> dtoClass) throws DAOException {
        ResultSet resultSet = singleton.query(query);
        return Transformer.on(resultSet).transformToListOf(dtoClass);
    }

    public void query(String query) {
        singleton.update(query);
    }
    private Object readOneColumnOfFirstRow(String query) throws DAOException {
        try {
            ResultSet resultSet = singleton.query(query);
            return resultSet.next() ? resultSet.getObject(1) : 0;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }


}
