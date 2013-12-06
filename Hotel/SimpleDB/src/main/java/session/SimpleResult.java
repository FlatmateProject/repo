package session;

import exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleResult {

    private final ResultSet resultSet;

    private SimpleResult(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public static SimpleResult on(ResultSet resultSet) {
        return new SimpleResult(resultSet);
    }

    public Object transformToObject() throws DAOException {
        try {
            return resultSet.next() ? resultSet.getObject(1) : 0;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
