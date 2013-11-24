package common.tableBuilder;

import dictionary.TABLE;
import dto.ColumnData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TableContent {

    public static final String EMPTY_LABEL = "Brak danych";

    public static final Object[] EMPTY_ROW = new Object[]{EMPTY_LABEL};

    public static final TableContent EMPTY = new TableContent(Arrays.asList(new ColumnData(EMPTY_LABEL)), Arrays.asList(new ArrayObtained() {
        @Override
        public Object[] getArray() {
            return EMPTY_ROW;
        }
    }));

    final private List<ColumnData> columnNames;

    final private List<? extends ArrayObtained> rowsData;

    public static TableContent emptyContent(List<ColumnData> columnNames) {
        return new TableContent(columnNames, Collections.<ArrayObtained>emptyList());
    }

    public static <T extends ArrayObtained> TableContent store(List<ColumnData> columnNames, List<T> rowsData) {
        return new TableContent(columnNames, rowsData);
    }

    public static <T extends ArrayObtained> TableContent store(List<ColumnData> columnNames, Iterable<T> rowsData) {
        List<T> list = convertToList(rowsData);
        return new TableContent(columnNames, list);
    }

    private static <T extends ArrayObtained> List<T> convertToList(Iterable<T> rowsData) {
        List<T> list = new ArrayList<T>();
        for (T row : rowsData) {
            list.add(row);
        }
        return list;
    }

    public List<ColumnData> getColumnNames() {
        return columnNames;
    }

    public List<? extends ArrayObtained> getRowsData() {
        return rowsData;
    }

    public TableContent(List<ColumnData> columnNames, List<? extends ArrayObtained> rowsData) {
        this.columnNames = columnNames;
        this.rowsData = rowsData;
    }

    public static List<ColumnData> asList(TABLE table) {
        List<ColumnData> columns = new ArrayList<ColumnData>();
        for (String columnName : table.getColumns()) {
            columns.add(new ColumnData(columnName));
        }
        return columns;
    }
}
