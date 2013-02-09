package service.manager;

import common.tableBuilder.ArrayObtained;
import common.tableBuilder.TableBuilder;
import common.tableBuilder.TableResult;
import dao.ManagerDao;
import dto.ColumnData;
import exception.DAOException;
import service.dictionary.TABLE;

import java.util.List;

public class Manager {

    private final ManagerDao managerDao;

    public Manager(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public int getCount(String tableName) throws DAOException {
        return managerDao.getCountForTable(tableName);
    }

    public List<ColumnData> getColumns(TABLE table) throws DAOException {
        return managerDao.showColumnsForTable(table);
    }

    public TableResult createTable(TABLE table) {
        try {
            List<ColumnData> columns = managerDao.showColumnsForTable(table);
            List<? extends ArrayObtained> data = managerDao.getDataFromTable(table);
            return TableBuilder.table().columns(columns).data(data).build();
        } catch (Exception e) {
            e.printStackTrace();
            return TableResult.EMPTY;
        }
    }
}
