package service;


import common.tableBuilder.ArrayObtained;
import common.tableBuilder.TableBuilder;
import common.tableBuilder.TableResult;
import dao.GuestBookDao;
import dto.SimpleNameData;
import exception.DAOException;
import exception.IncorrectDataException;

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

    public <T extends ArrayObtained> TableResult createTable(String tableName, String conditions, Class<T> dtoClass) {
        try {
            List<SimpleNameData> columns = guestBookDao.showColumnsForTable(tableName);
            List<T> data = guestBookDao.getDataFromTable(tableName, conditions, dtoClass);
            return TableBuilder.table().columns(columns).data(data).build();
        } catch (Exception e) {
            e.printStackTrace();
            return TableResult.EMPTY;
        }
    }

    public void updateClientData(String labels[], String data[]) throws IncorrectDataException {
        try {
            guestBookDao.updateClientData(labels, data);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IncorrectDataException();
        }
    }
}
