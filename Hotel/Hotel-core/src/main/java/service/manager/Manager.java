package service.manager;

import common.tableBuilder.ArrayObtained;
import common.tableBuilder.TableContent;
import dao.ManagerDao;
import dictionary.TABLE;
import dto.ColumnData;
import exception.DAOException;

import java.util.Calendar;
import java.util.List;

public class Manager {

    private final ManagerDao managerDao;

    private Calendar calendar;

    public Manager(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    public List<ColumnData> getColumns(TABLE table) throws DAOException {
        return managerDao.showColumnsForTable(table);
    }

    public TableContent createTable(TABLE table) {
        try {
            List<ColumnData> columns = managerDao.showColumnsForTable(table);
            List<? extends ArrayObtained> data = managerDao.getDataFromTable(table);
            return TableContent.store(columns, data);
        } catch (Exception e) {
            e.printStackTrace();
            return TableContent.EMPTY;
        }
    }

    public int getNumberOfReservations() throws DAOException {
        return getCount("rezerwacje");
    }

    public int getNumberOfClients() throws DAOException {
        return getCount("klienci");
    }

    private int getCount(String tableName) throws DAOException {
        return managerDao.getCountForTable(tableName);
    }

    public int getNumberOfRooms() throws DAOException {
        return getCount("pokoje");
    }

    public int getNumberOfServiceTypes() throws DAOException {
        return getCount("uslugi");
    }

    public int getNumberOfClientsToCheckIn() throws DAOException {
        return getCount("rezerwacje where month(data_z) = " + (calendar.get(Calendar.MONTH) + 1));
    }

    public int getNumberOfClientsToCheckOut() throws DAOException {
        return getCount("rezerwacje where month(data_w) = " + (calendar.get(Calendar.MONTH) + 1));
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
