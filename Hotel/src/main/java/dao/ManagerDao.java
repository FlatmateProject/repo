package dao;

import common.tableBuilder.ArrayObtained;
import dto.ColumnData;
import exception.DAOException;
import service.dictionary.TABLE;

import java.util.List;

public interface ManagerDao {

    List<ColumnData> showColumnsForTable(TABLE table) throws DAOException;

    List<? extends ArrayObtained> getDataFromTable(TABLE table) throws DAOException;

    int getCountForTable(String tableName) throws DAOException;
}
