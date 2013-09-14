package conditions.table;

import common.tableBuilder.TableContent;
import dto.ColumnData;
import org.fest.assertions.Condition;

import java.util.List;

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
        List<ColumnData> actualColumnNames = tableContent.getColumnNames();
        for (int i = 0; i < actualColumnNames.size(); i++) {
            if (isNotEqual(actualColumnNames.get(i).getName(), expectedColumnNames[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotEqual(String actualColumnName, String expectedColumnName) {
        return !actualColumnName.equals(expectedColumnName);
    }
}
