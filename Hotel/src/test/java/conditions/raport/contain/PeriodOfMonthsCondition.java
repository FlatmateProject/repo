package conditions.raport.contain;

import org.fest.assertions.Condition;
import service.dictionary.MONTH;

public class PeriodOfMonthsCondition extends Condition<String> {

	private MONTH monthFrom;
	
	private MONTH monthTo;
	
	public PeriodOfMonthsCondition(MONTH monthFrom, MONTH monthTo) {
		this.monthFrom = monthFrom;
		this.monthTo = monthTo;
	}
	
	public static PeriodOfMonthsCondition headerContainPeriodOfMonths(MONTH monthFrom, MONTH monthTo) {
		return new PeriodOfMonthsCondition(monthFrom, monthTo);
	}

	@Override
	public boolean matches(String textReport) {
		return textReport.contains(monthFrom.getDesc())
				&& textReport.contains(monthTo.getDesc())
				&& textReport.indexOf(monthFrom.getDesc()) < textReport.indexOf(monthTo.getDesc());
	}

}
