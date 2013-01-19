package service;


import common.tableBuilder.TableBuilder;
import common.tableBuilder.TableResult;
import dao.GuestBookDao;
import dao.impl.Singleton;
import dto.SimpleNameData;
import dto.cantor.CustomerData;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.List;

public class GuestBook {

    private static final Logger log = Logger.getLogger(GuestBook.class);

    private Singleton sing;

    private ResultSet rset1, rset2;

    private final GuestBookDao guestBookDao;

    public GuestBook(GuestBookDao guestBookDao) {
        this.guestBookDao = guestBookDao;
    }

    public String[] getLabels(String tableName) {
        String[] emptyColumns = {"", "", "", "", "", "", "", "", "", "", "", "", ""};
        try {
            List<SimpleNameData> columns = guestBookDao.showColumnsForTable(tableName);
            if (columns.isEmpty()) {
                return emptyColumns;
            }
            return (String[]) columns.toArray();
        } catch (Exception e) {
            e.printStackTrace();
            return emptyColumns;
        }
    }

    public JTable createTable(String tableName, String conditions, Class dtoClass) {
        try {
            List<SimpleNameData> columns = guestBookDao.showColumnsForTable(tableName);
            List<CustomerData> data = guestBookDao.getDataWithTable(tableName, conditions, dtoClass);
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

    public Boolean updateClientData(String l[], String d[]) {
        String q = "update hotel.klienci set ";
        for (int i = 1; i < 10; i++) {
            if (i != 1) {
                q += ", ";
            }
            if (!d[i].isEmpty()) {
                q += l[i] + " = \"" + d[i] + "\"";
            } else {
                q += l[i] + " = :OLD." + l[i];
            }
        }
        q += " where " + l[0] + " = \"" + d[0] + "\"";
        log.info(q);
        return sing.update(q);
    }

    public void setSing(Singleton sing) {
        this.sing = sing;
    }
}
