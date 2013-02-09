package conditions.raport;

import dictionary.MONTH;
import org.fest.assertions.Condition;

public class MonthCondition extends Condition<String> {

    private final MONTH month;

    private MonthCondition(MONTH month, String assertFailDescription) {
        this.month = month;
        as(assertFailDescription);
    }

    public static MonthCondition headerContainMonth(MONTH month) {
        return new MonthCondition(month, "headerContainMonth");
    }

    @Override
    public boolean matches(String textReport) {
        return textReport.contains(month.getDesc());
    }

}
