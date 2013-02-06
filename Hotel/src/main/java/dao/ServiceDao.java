package dao;

import exception.DAOException;
import service.dictionary.TABLE;

public interface ServiceDao {

    void updateData(TABLE table, String[] labels, String[] data) throws DAOException;

    boolean insertData(TABLE table, String[] labels, String[] data) throws DAOException;

    boolean deleteData(TABLE table, String label, String data) throws DAOException;
}
