package view.common;

import com.vaadin.ui.Table;
import common.tableBuilder.TableContent;

public class TableUIBuilder {

    private String title = "";

    boolean selectable = false;

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
        Object[][] rowsData = content.getRowsData();
        Object[] firstRow = rowsData[0];
        int i = 0;
        for (String column : content.getColumnNames()) {
            int propertyId = i + 1;
            table.addContainerProperty(propertyId, firstRow[i].getClass(), null, column, null, Table.Align.CENTER);
            i++;
        }
        i = 1;
        for (Object[] row : rowsData) {
            table.addItem(row, i);
            i++;
        }
        return table;
    }
}
