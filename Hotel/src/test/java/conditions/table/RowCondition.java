package conditions.table;

import org.fest.assertions.Condition;

/**
 * Created with IntelliJ IDEA.
 * User: piotro
 * Date: 11/11/12
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class RowCondition extends Condition<Object[]> {

    private Object object;

    public RowCondition(Object object) {
        this.object = object;
    }

    @Override
    public boolean matches(Object[] objects) {
        return false;
    }

    public static RowCondition containsRow(Object object){
        return new RowCondition(object);
    }
}
