package dao;

import exception.DAOException;
import service.dictionary.TABLE;

public interface ServiceDao {

    void updateData(TABLE table, String conditions) throws DAOException;

    boolean insertData(TABLE table, String[] labels, String[] data) throws DAOException;

    void deleteData(TABLE table, String primaryKey, long id) throws DAOException;
}
