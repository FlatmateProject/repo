package dao;

import common.ArrayObtained;
import common.Condition;
import dictionary.TABLE;
import exception.DAOException;

import java.util.List;

public interface ServiceDao {

    void updateData(TABLE table, String conditions) throws DAOException;

    void insertData(TABLE table, String[] labels, String[] data) throws DAOException;

    void deleteData(TABLE table, String primaryKey, long id) throws DAOException;

    List<? extends ArrayObtained> getDataFromTable(TABLE table) throws DAOException;

    int getCountForTable(String tableName) throws DAOException;

    List<? extends ArrayObtained> getDataFromTable(TABLE table, List<Condition> conditions);
}
