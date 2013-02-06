package service.manager;

import common.tableBuilder.ArrayObtained;
import common.tableBuilder.TableBuilder;
import common.tableBuilder.TableResult;
import dao.ManagerDao;
import dao.impl.Singleton;
import dto.*;
import exception.DAOException;
import org.apache.log4j.Logger;
import service.dictionary.TABLE;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {


    private static final Logger log = Logger.getLogger(Manager.class);

    private Singleton singleton;

    private ResultSet rset1;

    private final ManagerDao managerDao;

    private Map<String, Class> map = new HashMap<String, Class>();

    public Manager(ManagerDao managerDao) {
        this.managerDao = managerDao;
        map.put("Klienci", CustomerData.class);
        map.put("Firmy", CompanyData.class);
        map.put("Usługi", ServiceData.class);
        map.put("Pokoje", RoomData.class);
        map.put("Stanowiska", OccupationData.class);
        map.put("Waluty", Currency.class);
        map.put("Pracownicy", EmployeeData.class);
        map.put("Klasy", RoomTypeData.class);
        map.put("Rachunki", BillData.class);
        map.put("Archiwum", ArchiveData.class);
    }

    public int getCount(String tableName) throws DAOException {
        return managerDao.getCountForTable(tableName);
    }

    public Boolean deleteData(TABLE table, String l, String d) {
        String q = "delete from hotel." + table + " where " + l + " = " + d;
        return singleton.update(q);
    }

    public Boolean insertData(TABLE table, String l[], String d[], int size) {
        String q = "insert into hotel." + table + "(";
        for (int i = 0; i < size; i++) {
            if (i == 0 && d[i].isEmpty()) {
                return false;
            } else if (!d[i].isEmpty()) {
                if (i != 0)
                    q += ", ";
                q += l[i];
            }
        }
        q += ") values(";
        for (int i = 0; i < size; i++) {
            if (i == 0 && d[i].isEmpty()) {
                return false;
            } else if (!d[i].isEmpty()) {
                if (i != 0)
                    q += ", ";
                q += "\"" + d[i] + "\"";
            }
        }
        q += ");";
        log.info(q);
        return singleton.update(q);
    }

    public Boolean checkTable(String table, String l, String d) {
        String q = "select count(*) from " + table + " where " + l + " = " + d;
        rset1 = singleton.query(q);
        try {
            rset1.next();
            return rset1.getString(1).equals("0");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Co� zepsu�e�!", "UWAGA!",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public String[] getColumns(TABLE table) throws DAOException {
        int i = 0;
        List<ColumnData> columns = managerDao.showColumnsForTable(table);
        String[] labels = new String[columns.size()];
        for (ColumnData column : columns) {
            labels[i] = column.getName();
        }
        return labels;
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

    public void setSingleton(Singleton singleton) {
        this.singleton = singleton;
    }
}
