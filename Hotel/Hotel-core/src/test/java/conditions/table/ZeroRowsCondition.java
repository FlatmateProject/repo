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
public class ZeroRowsCondition extends Condition<TableContent> {

    private ZeroRowsCondition(String description) {
        as(description);
    }

    public static ZeroRowsCondition zeroRows() {
        return new ZeroRowsCondition("zeroRows");
    }

    @Override
    public boolean matches(TableContent tableContent) {
        List<? extends ArrayObtained> data = tableContent.getRowsData();
        return data == null || data.isEmpty();
    }
}
