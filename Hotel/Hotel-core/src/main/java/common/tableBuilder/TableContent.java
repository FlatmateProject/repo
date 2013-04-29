package common.tableBuilder;

public class TableContent {

    public static final String EMPTY_LABEL = "Brak danych";

    public static final Object[] EMPTY_ROW = new Object[]{EMPTY_LABEL};

    public static final String[] EMPTY_COLUMN = new String[]{EMPTY_LABEL};

    public static final Object[][] EMPTY_DATA = new Object[][]{EMPTY_ROW};

    public static final TableContent EMPTY = new TableContent(EMPTY_DATA, EMPTY_COLUMN);

    final private Object rowsData[][];

    final private String columnNames[];

    public static TableContent store(Object[][] rowsData, String[] columnNames) {
        return new TableContent(rowsData, columnNames);
    }

    public Object[][] getRowsData() {
        return rowsData;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    private TableContent(Object[][] rowsData, String[] columnNames) {
        this.columnNames = columnNames;
        this.rowsData = rowsData;
    }
}
