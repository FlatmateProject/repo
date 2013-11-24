package view.common;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import common.ArrayObtained;
import common.TableContent;
import dictionary.TABLE;
import dto.ColumnData;

import java.util.List;

public class TableUIBuilder {

    private String title = "";

    boolean selectable = false;

    private ItemClickEvent.ItemClickListener tableMouseListener;

    private TableContent content = TableContent.EMPTY;

    public static TableUIBuilder table() {
        return new TableUIBuilder();
    }

    public TableUIBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TableUIBuilder withContent(TableContent content) {
        this.content = content;
        return this;
    }

    public TableUIBuilder withSelection() {
        this.selectable = true;
        return this;
    }

    public Table build() {
        Table table = new Table(title);
        table.setSelectable(selectable);
        List<? extends ArrayObtained> rowsData = content.getRowsData();
        if (rowsData == null || rowsData.isEmpty()) {
            createColumnsForTableWithoutData(table);
        } else {
            createColumns(table, rowsData.get(0).getArray());
            addRows(table, rowsData);
        }
        if (tableMouseListener != null) {
            table.addItemClickListener(tableMouseListener);
        }
        return table;
    }

    private void createColumnsForTableWithoutData(Table table) {
        int i = 0;
        for (ColumnData column : content.getColumnNames()) {
            int propertyId = i + 1;
            table.addContainerProperty(propertyId, Object.class, null, column.getName(), null, Table.Align.CENTER);
            i++;
        }
    }

    private void createColumns(Table table, Object[] firstRow) {
        int i = 0;
        for (ColumnData column : content.getColumnNames()) {
            int propertyId = i + 1;
            table.addContainerProperty(propertyId, firstRow[i].getClass(), null, column.getName(), null, Table.Align.CENTER);
            i++;
        }
    }

    private void addRows(Table table, List<? extends ArrayObtained> rowsData) {
        int i = 1;
        for (ArrayObtained row : rowsData) {
            table.addItem(row.getArray(), i);
            i++;
        }
    }

    public TableUIBuilder withTitle(TABLE table) {
        this.title = table.getTableName();
        return this;
    }

    public TableUIBuilder withClickListener(ItemClickEvent.ItemClickListener tableMouseListener) {
        this.tableMouseListener = tableMouseListener;
        return this;
    }
}
