package service.cantor;

public class CantorResult {

    public static final Object[][] EMPTY_DATA = new Object[][]{{"Brak danych"}};
    public static final String[] EMPTY_COLUMNS = new String[]{"Brak danych"};
    public static final CantorResult EMPTY = new CantorResult(EMPTY_DATA, EMPTY_COLUMNS);

    final private Object rowData[][];

    final private String columnNames[];

    public CantorResult(Object[][] rowData, String[] columnNames) {
        this.rowData = rowData;
        this.columnNames = columnNames;
    }

    public static CantorResult store(Object[][] rowData, String[] columnNames){
        return new CantorResult(rowData, columnNames);
    }

    public Object[][] getRowData() {
        return rowData;
    }

    public String[] getColumnNames() {
        return columnNames;
    }
}
