package service.cantor;

import dto.SimpleNameData;

import java.util.ArrayList;
import java.util.List;

public class TableBuilder {

    private List rowsData;

    private List<SimpleNameData> columnNames;

    private TableBuilder() {
        rowsData = new ArrayList<String>();
        columnNames = new ArrayList<SimpleNameData>();
    }

    public static TableBuilder table() {
        return new TableBuilder();
    }

    public TableBuilder columns(List<SimpleNameData> columnNames) {
        this.columnNames = columnNames;
        return this;
    }

    public <T extends GetArray> TableBuilder data(List<T> rowsData) {
        this.rowsData = rowsData;
        return this;
    }

    public CantorTableResult build() {
        return createTable(rowsData);
    }

    private <T extends GetArray> CantorTableResult createTable(List<T> rowsData) {
        String columns[] = createTableColumns(columnNames);
        Object[][] rows = createTableRows(rowsData);
        return CantorTableResult.store(rows, columns);
    }

    private String[] createTableColumns(List<SimpleNameData> currencyColumns) {
        int i = 0;
        int cols = currencyColumns.size();
        String columnNames[] = new String[cols];
        for (SimpleNameData currencyColumn : currencyColumns) {
            columnNames[i] = currencyColumn.getName();
            i++;
        }
        return columnNames;
    }

    private <T extends GetArray> Object[][] createTableRows(List<T> rows) {
        Object[][] rowsData = new Object[rows.size()][];
        int i = 0;
        for (T row : rows) {
            rowsData[i] = row.getArray();
            i++;
        }
        return rowsData;
    }
}
