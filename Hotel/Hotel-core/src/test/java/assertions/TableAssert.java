package assertions;

import common.tableBuilder.TableResult;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class TableAssert extends GenericAssert<TableAssert, TableResult> {

    private TableAssert(TableResult actual) {
        super(TableAssert.class, actual);
    }

    public static TableAssert assertThat(TableResult actual) {
        return new TableAssert(actual);
    }

    public TableAssert hasRowNumber(int rowNumber) {
        Assertions.assertThat(actual.getRowsData().length).isEqualTo(rowNumber);
        return this;
    }

    public TableAssert hasColumnNumber(int columnNumber) {
        Assertions.assertThat(actual.getColumnNames().length).isEqualTo(columnNumber);
        return this;
    }


}
