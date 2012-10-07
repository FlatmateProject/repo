package conditions.raport.contain;

import org.fest.assertions.Condition;

public class PeriodOfYearsCondition extends Condition<String> {

    private int yearFrom;

    private int yearTo;

    public PeriodOfYearsCondition(int yearFrom, int yearTo) {
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
    }

    public static PeriodOfYearsCondition headerContainPeriodOfYears(int yearFrom, int yearTo) {
        return new PeriodOfYearsCondition(yearFrom, yearTo);
    }

    @Override
    public boolean matches(String textReport) {
        return textReport.contains(Integer.toString(yearFrom))
                && textReport.contains(Integer.toString(yearTo))
                && textReport.indexOf(Integer.toString(yearFrom)) < textReport.indexOf(Integer.toString(yearTo));
    }
}
