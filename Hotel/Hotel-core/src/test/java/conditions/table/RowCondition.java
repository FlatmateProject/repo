package conditions.table;

import common.ArrayObtained;
import common.TableContent;
import org.fest.assertions.Condition;

import java.util.List;

/**
 * User: piotro
 * Date: 11/11/12
 * Time: 12:08 PM
 */
public class RowCondition extends Condition<TableContent> {

    private final Object[] expectedCells;

    private RowCondition(Object[] expectedCells, String description) {
        this.expectedCells = expectedCells;
        as(description);
    }

    public static RowCondition containsRow(Object[] expectedColumnNames) {
        return new RowCondition(expectedColumnNames, "containsRow");
    }

    @Override
    public boolean matches(TableContent tableContent) {
        List<? extends ArrayObtained> data = tableContent.getRowsData();
        for (ArrayObtained aData : data) {
            Object[] actualCells = aData.getArray();
            if (isActualRowEqualToExpectedRow(actualCells)) {
                return true;
            }
        }
        return false;
    }

    private boolean isActualRowEqualToExpectedRow(Object[] actualCells) {
        for (int i = 0; i < actualCells.length; i++) {
            if (isNotEqual(actualCells[i], expectedCells[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotEqual(Object actualCell, Object expectedCell) {
        return !actualCell.equals(expectedCell);
    }
}
