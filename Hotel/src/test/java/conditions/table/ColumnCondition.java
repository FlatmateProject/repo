package conditions.table;

import org.fest.assertions.Condition;

public class ColumnCondition extends Condition<String> {

    private String object;

    public ColumnCondition(String object) {
        this.object = object;
    }

    @Override
    public boolean matches(String objects) {
        return false;
    }

    public static ColumnCondition containColumn(String object){
      return new ColumnCondition(object);
    }
}
