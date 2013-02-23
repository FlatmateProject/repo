package dao;

import common.tableBuilder.ArrayObtained;
import dictionary.TABLE;
import dto.ColumnData;
import exception.DAOException;

import java.util.List;

public interface ManagerDao {

    List<ColumnData> showColumnsForTable(TABLE table) throws DAOException;

    List<? extends ArrayObtained> getDataFromTable(TABLE table) throws DAOException;

    int getCountForTable(String tableName) throws DAOException;
}
