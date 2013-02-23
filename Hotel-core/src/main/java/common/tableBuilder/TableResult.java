package common.tableBuilder;

public class TableResult {

    public static final String EMPTY_LABEL = "Brak danych";

    public static final Object[] EMPTY_ROW = new Object[]{EMPTY_LABEL};

    public static final String[] EMPTY_COLUMN = new String[]{EMPTY_LABEL};

    public static final Object[][] EMPTY_DATA = new Object[][]{EMPTY_ROW};

    public static final TableResult EMPTY = new TableResult(EMPTY_DATA, EMPTY_COLUMN);

    final private Object rowsData[][];

    final private String columnNames[];

    public static TableResult store(Object[][] rowsData, String[] columnNames) {
        return new TableResult(rowsData, columnNames);
    }

    public Object[][] getRowsData() {
        return rowsData;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    private TableResult(Object[][] rowsData, String[] columnNames) {
        this.columnNames = columnNames;
        this.rowsData = rowsData;
    }
}
