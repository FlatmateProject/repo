package service;


import common.tableBuilder.TableBuilder;
import common.tableBuilder.TableResult;
import dao.GuestBookDao;
import dto.SimpleNameData;
import dto.cantor.CustomerData;
import exception.DAOException;

import javax.swing.*;
import java.util.List;

public class GuestBook {

    private final GuestBookDao guestBookDao;

    public GuestBook(GuestBookDao guestBookDao) {
        this.guestBookDao = guestBookDao;
    }

    public List<SimpleNameData> getLabels(String tableName) {
        List<SimpleNameData> emptyColumns = SimpleNameData.arrayOfMe("", 13);
        try {
            List<SimpleNameData> columns = guestBookDao.showColumnsForTable(tableName);
            if (columns.isEmpty()) {
                return emptyColumns;
            }
            return columns;
        } catch (Exception e) {
            e.printStackTrace();
            return emptyColumns;
        }
    }

    public JTable createTable(String tableName, String conditions, Class dtoClass) {
        try {
            List<SimpleNameData> columns = guestBookDao.showColumnsForTable(tableName);
            List<CustomerData> data = guestBookDao.getDataFromTable(tableName, conditions, dtoClass);
            TableResult result = TableBuilder.table().columns(columns).data(data).build();
            JTable table = new JTable(result.getRowsData(), result.getColumnNames());
            table.setFillsViewportHeight(true);
            return table;
        } catch (Exception e) {
            e.printStackTrace();
            return new JTable(TableResult.EMPTY_DATA, TableResult.EMPTY_COLUMN);
        }
    }

    public JTable createTable(String tableName, String conditions) {
        return createTable(tableName, conditions, CustomerData.class);
    }

    public Boolean updateClientData(String labels[], String data[]) {
        try {
            guestBookDao.updateClientData(labels, data);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
