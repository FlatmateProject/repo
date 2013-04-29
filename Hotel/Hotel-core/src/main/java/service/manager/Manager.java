package service.manager;

import common.tableBuilder.ArrayObtained;
import common.tableBuilder.TableContent;
import common.tableBuilder.TableContentBuilder;
import dao.ManagerDao;
import dictionary.TABLE;
import dto.ColumnData;
import exception.DAOException;

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

    public TableContent createTable(TABLE table) {
        try {
            List<ColumnData> columns = managerDao.showColumnsForTable(table);
            List<? extends ArrayObtained> data = managerDao.getDataFromTable(table);
            return TableContentBuilder.table().columns(columns).data(data).build();
        } catch (Exception e) {
            e.printStackTrace();
            return TableContent.EMPTY;
        }
    }
}
