package service.guessBook;

import common.ArrayObtained;
import common.Condition;
import common.TableContent;
import dao.ServiceDao;
import dictionary.TABLE;
import dto.ColumnData;
import entity.RecreationServiceData;
import entity.ReservationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.RecreationServiceRepository;
import repository.ReservationRepository;

import java.util.Collections;
import java.util.List;

@Component
public class GuestBook {

    @Autowired
    private RecreationServiceRepository recreationServiceRepository;

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private ReservationRepository reservationRepository;

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

    public TableContent createRecreationTable(ReservationData reservation) {
        try {
            List<ColumnData> columns = TableContent.asList(TABLE.Service);
            List<RecreationServiceData> data = recreationServiceRepository.findByReservation(reservation);
            columns.add(new ColumnData("CZAS"));
            return TableContent.store(columns, data);
        } catch (Exception e) {
            e.printStackTrace();
            return TableContent.EMPTY;
        }
    }

    public TableContent createTable(TABLE tableName) {
        return createTableWithConditions(tableName, Collections.<Condition>emptyList());
    }

    public TableContent createTable(TABLE table, List<Condition> conditions) {
        return createTableWithConditions(table, conditions);
    }

    private TableContent createTableWithConditions(TABLE table, List<Condition> conditions) {
        try {
            List<ColumnData> columns = TableContent.asList(table);
            List<? extends ArrayObtained> data = serviceDao.getDataFromTable(table, conditions);
            return TableContent.store(columns, data);
        } catch (Exception e) {
            e.printStackTrace();
            return TableContent.EMPTY;
        }
    }
}
