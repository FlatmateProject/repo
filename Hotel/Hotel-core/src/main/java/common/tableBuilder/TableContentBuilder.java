package common.tableBuilder;

import dto.ColumnData;

import java.util.List;

public class TableContentBuilder {

    private List rowsData;

    private List<ColumnData> columnNames;

    private TableContentBuilder() {

    }

    public static TableContentBuilder table() {
        return new TableContentBuilder();
    }

    public TableContentBuilder columns(List<ColumnData> columnNames) {
        this.columnNames = columnNames;
        return this;
    }


    public <T extends ArrayObtained> TableContentBuilder data(List<T> rowsData) {
        this.rowsData = rowsData;
        return this;
    }

    public TableContentBuilder appendColumn(String columnName) {
        columnNames.add(new ColumnData(columnName));
        return this;
    }

    public TableContent build() {
        if (isNotEmptyList(columnNames) && isNotEmptyList(rowsData)) {
            return createTable(rowsData);
        } else {
            return TableContent.EMPTY;
        }
    }

    private <T> boolean isNotEmptyList(List<T> columnNames) {
        return columnNames != null && columnNames.size() > 0;
    }

    private <T extends ArrayObtained> TableContent createTable(List<T> rowsData) {
        String columns[] = createTableColumns(columnNames);
        Object[][] rows = createTableRows(rowsData);
        return TableContent.store(rows, columns);
    }

    private String[] createTableColumns(List<ColumnData> currencyColumns) {
        int i = 0;
        int cols = currencyColumns.size();
        String columnNames[] = new String[cols];
        for (ColumnData currencyColumn : currencyColumns) {
            columnNames[i] = currencyColumn.getName();
            i++;
        }
        return columnNames;
    }

    private <T extends ArrayObtained> Object[][] createTableRows(List<T> rows) {
        Object[][] rowsData = new Object[rows.size()][];
        int i = 0;
        for (T row : rows) {
            rowsData[i] = row.getArray();
            i++;
        }
        return rowsData;
    }
}
