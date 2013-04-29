package assertions;

import common.tableBuilder.TableContent;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class TableAssert extends GenericAssert<TableAssert, TableContent> {

    private TableAssert(TableContent actual) {
        super(TableAssert.class, actual);
    }

    public static TableAssert assertThat(TableContent actual) {
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
