package conditions.table;

import common.tableBuilder.TableContent;
import org.fest.assertions.Condition;

public class ColumnCondition extends Condition<TableContent> {

    private final String[] expectedColumnNames;

    private ColumnCondition(String[] object, String description) {
        this.expectedColumnNames = object;
        as(description);
    }

    public static ColumnCondition containColumns(String[] expectedColumnNames) {
        return new ColumnCondition(expectedColumnNames, "containColumns");
    }

    @Override
    public boolean matches(TableContent tableContent) {
        String[] actualColumnNames = tableContent.getColumnNames();
        for (int i = 0; i < actualColumnNames.length; i++) {
            if (isNotEqual(actualColumnNames[i], expectedColumnNames[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotEqual(String actualColumnName, String expectedColumnName) {
        return !actualColumnName.equals(expectedColumnName);
    }
}
