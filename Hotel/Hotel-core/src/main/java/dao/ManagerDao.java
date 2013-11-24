package dao;

import common.tableBuilder.ArrayObtained;
import dictionary.TABLE;
import exception.DAOException;

import java.util.List;

public interface ManagerDao {

    List<? extends ArrayObtained> getDataFromTable(TABLE table) throws DAOException;

    int getCountForTable(String tableName) throws DAOException;
}
