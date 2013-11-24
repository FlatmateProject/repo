package service.guessBook;

import common.tableBuilder.ArrayObtained;
import common.tableBuilder.TableContent;
import dao.GuestBookDao;
import dictionary.TABLE;
import dto.ColumnData;
import dto.guestBook.RecreationServiceData;
import entity.ReservationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ReservationRepository;

import java.util.List;

@Component
public class GuestBook {

    @Autowired
    private GuestBookDao guestBookDao;

    @Autowired
    private ReservationRepository reservationRepository;

    private String[] labels;

    private String[] data;

    public List<ColumnData> getLabels(TABLE table) {
        List<ColumnData> emptyColumns = ColumnData.arrayOfMe("", 13);
        try {
            List<ColumnData> columns = TableContent.asList(table);
            if (columns.isEmpty()) {
                return emptyColumns;
            }
            return columns;
        } catch (Exception e) {
            e.printStackTrace();
            return emptyColumns;
        }
    }

    public TableContent createReservationTableForCustomer(long clientId) {
        try {
            List<ColumnData> columns = TableContent.asList(TABLE.Reservation);
            List<ReservationData> data = reservationRepository.findByPeselId(clientId);
            return TableContent.store(columns, data);
        } catch (Exception e) {
            e.printStackTrace();
            return TableContent.EMPTY;
        }
    }

    public TableContent createReservationTableForCompany(long clientId) {
        try {
            List<ColumnData> columns = TableContent.asList(TABLE.Reservation);
            List<ReservationData> data = reservationRepository.findByKrsId(clientId);
            return TableContent.store(columns, data);
        } catch (Exception e) {
            e.printStackTrace();
            return TableContent.EMPTY;
        }
    }

    public TableContent createRecreationTable(long idReservation) {
        try {
            List<ColumnData> columns = TableContent.asList(TABLE.Service);
            List<RecreationServiceData> data = guestBookDao.getServiceByReservationId(idReservation);
            columns.add(new ColumnData("CZAS"));
            return TableContent.store(columns, data);
        } catch (Exception e) {
            e.printStackTrace();
            return TableContent.EMPTY;
        }
    }

    public TableContent createTable(TABLE tableName) {
        String emptyConditions = "";
        return createTableWithConditions(tableName, emptyConditions);
    }

    public TableContent createTable(TABLE table, String[] labels, String[] data) {
        this.labels = labels;
        this.data = data;
        String conditions = createConditions();
        if (!conditions.isEmpty()) {
            conditions = " where " + conditions;
            return createTableWithConditions(table, conditions);
        }
        return TableContent.EMPTY;
    }

    private TableContent createTableWithConditions(TABLE table, String conditions) {
        try {
            List<ColumnData> columns = TableContent.asList(table);
            List<? extends ArrayObtained> data = guestBookDao.getDataFromTable(table, conditions);
            return TableContent.store(columns, data);
        } catch (Exception e) {
            e.printStackTrace();
            return TableContent.EMPTY;
        }
    }

    private String createConditions() {
        String conditions = "";
        for (int i = 0; i < data.length; i++) {
            if (!isNotClientDataEmpty(i)) {
                if (!conditions.isEmpty()) {
                    conditions = conditions + " and ";
                }
                conditions = conditions + addCondition(i);
            }
        }
        return conditions;
    }

    private String addCondition(int i) {
        return String.format("%s=\"%s\"", labels[i], data[i]);
    }

    private boolean isNotClientDataEmpty(int i) {
        return data[i].isEmpty();
    }
}
