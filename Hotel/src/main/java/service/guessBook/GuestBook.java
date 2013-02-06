package service.guessBook;


import common.tableBuilder.ArrayObtained;
import common.tableBuilder.TableBuilder;
import common.tableBuilder.TableResult;
import dao.GuestBookDao;
import dto.ColumnData;
import dto.guestBook.RecreationServiceData;
import dto.guestBook.ReservationData;
import service.dictionary.TABLE;

import java.util.List;

public class GuestBook {

    private final GuestBookDao guestBookDao;

    public GuestBook(GuestBookDao guestBookDao) {
        this.guestBookDao = guestBookDao;
    }

    public List<ColumnData> getLabels(TABLE tableName) {
        List<ColumnData> emptyColumns = ColumnData.arrayOfMe("", 13);
        try {
            List<ColumnData> columns = guestBookDao.showColumnsForTable(tableName.getTableName());
            if (columns.isEmpty()) {
                return emptyColumns;
            }
            return columns;
        } catch (Exception e) {
            e.printStackTrace();
            return emptyColumns;
        }
    }

    public TableResult createReservationTable(String primaryId, long clientId) {
        try {
            List<ColumnData> columns = guestBookDao.showColumnsForTable("rezerwacje");
            List<ReservationData> data = guestBookDao.getReservationsByClientId(primaryId, clientId);
            return TableBuilder.table().columns(columns).data(data).build();
        } catch (Exception e) {
            e.printStackTrace();
            return TableResult.EMPTY;
        }
    }

    public TableResult createTable(TABLE tableName, String conditions) {
        try {
            List<ColumnData> columns = guestBookDao.showColumnsForTable(tableName.getTableName());
            List<? extends ArrayObtained> data = guestBookDao.getDataFromTable(tableName, conditions);
            return TableBuilder.table().columns(columns).data(data).build();
        } catch (Exception e) {
            e.printStackTrace();
            return TableResult.EMPTY;
        }
    }

    public TableResult createRecreationTable(long idReservation) {
        try {
            List<ColumnData> columns = guestBookDao.showColumnsForTable("uslugi");
            List<RecreationServiceData> data = guestBookDao.getServiceByReservationId(idReservation);
            return TableBuilder
                    .table()
                    .columns(columns)
                    .appendColumn("CZAS")
                    .data(data)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return TableResult.EMPTY;
        }
    }
}
