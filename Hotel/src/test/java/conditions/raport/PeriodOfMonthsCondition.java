package conditions.raport;

import dictionary.MONTH;
import org.fest.assertions.Condition;

public class PeriodOfMonthsCondition extends Condition<String> {

    private final MONTH monthFrom;

    private final MONTH monthTo;

    private PeriodOfMonthsCondition(MONTH monthFrom, MONTH monthTo, String assertFailDescription) {
        this.monthFrom = monthFrom;
        this.monthTo = monthTo;
        as(assertFailDescription);
    }

    public static PeriodOfMonthsCondition headerContainPeriodOfMonths(MONTH monthFrom, MONTH monthTo) {
        return new PeriodOfMonthsCondition(monthFrom, monthTo, "headerContainPeriodOfMonths");
    }

    @Override
    public boolean matches(String textReport) {
        return textReport.contains(monthFrom.getDesc())
                && textReport.contains(monthTo.getDesc())
                && textReport.indexOf(monthFrom.getDesc()) < textReport.indexOf(monthTo.getDesc());
    }

}
