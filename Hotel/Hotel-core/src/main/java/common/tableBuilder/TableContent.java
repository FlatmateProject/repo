package common.tableBuilder;

import dto.ColumnData;

import java.util.Arrays;
import java.util.List;

public class TableContent {

    public static final String EMPTY_LABEL = "Brak danych";

    public static final Object[] EMPTY_ROW = new Object[]{EMPTY_LABEL};

    public static final String[] EMPTY_COLUMN = new String[]{EMPTY_LABEL};

    public static final TableContent EMPTY = new TableContent(Arrays.asList(new ColumnData(EMPTY_LABEL)), Arrays.asList(new ArrayObtained() {
        @Override
        public Object[] getArray() {
            return EMPTY_ROW;
        }
    }));

    final private List<ColumnData> columnNames;

    final private List<? extends ArrayObtained> rowsData;


    public static <T extends ArrayObtained> TableContent store(List<ColumnData> columnNames, List<T> rowsData) {
        return new TableContent(columnNames, rowsData);
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
}
