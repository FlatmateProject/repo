package service.cantor;

import dto.SimpleNameData;

import java.util.ArrayList;
import java.util.List;

public class TableBuilder {

    private List rowsData;

    private List<SimpleNameData> columnNames;

    private TableBuilder() {
        final String EMPTY_LABEL = CantorTableResult.EMPTY_LABEL;
        rowsData = new ArrayList<ArrayObtained>();
        rowsData.add(new ArrayObtained() {
            @Override
            public Object[] getArray() {
                return new Object[]{EMPTY_LABEL};
            }
        });
        columnNames = new ArrayList<SimpleNameData>();
        columnNames.add(new SimpleNameData(EMPTY_LABEL));
    }

    public static TableBuilder table() {
        return new TableBuilder();
    }

    public TableBuilder columns(List<SimpleNameData> columnNames) {
        if (isNotEmptyList(columnNames)) {
            this.columnNames = columnNames;
        }
        return this;
    }


    public <T extends ArrayObtained> TableBuilder data(List<T> rowsData) {
        if (isNotEmptyList(rowsData)) {
            this.rowsData = rowsData;
        }
        return this;
    }

    private <T> boolean isNotEmptyList(List<T> columnNames) {
        return columnNames != null && columnNames.size() > 0;
    }

    public CantorTableResult build() {
        return createTable(rowsData);
    }

    private <T extends ArrayObtained> CantorTableResult createTable(List<T> rowsData) {
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
