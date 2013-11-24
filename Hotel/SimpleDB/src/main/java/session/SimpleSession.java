package session;

import exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public class SimpleSession {

    private Singleton singleton;

    public SimpleSession(SimpleDataSource simpleDataSource) {
        singleton = simpleDataSource.establishConnection();
    }

    public Object simpleResult(String query) throws DAOException {
        return SimpleResult.on(resultSet(query)).transformToObject();
    }

    public <T> T uniqueResult(String query, Class<T> dtoClass) throws DAOException {
        return Transformer.on(resultSet(query)).transformToObjectOf(dtoClass);
    }

    public <T> List<T> executeQuery(String query, Class<T> dtoClass) throws DAOException {
        return Transformer.on(resultSet(query)).transformToListOf(dtoClass);
    }

    private ResultSet resultSet(String query) throws DAOException {
        return singleton.query(query);
    }

    public void update(String query) throws DAOException {
        singleton.update(query);
    }
}
