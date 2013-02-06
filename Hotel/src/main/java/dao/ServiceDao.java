package dao;

import exception.DAOException;
import service.dictionary.TABLE;

public interface ServiceDao {

    void updateClientData(TABLE table, String[] labels, String[] data) throws DAOException;
}
