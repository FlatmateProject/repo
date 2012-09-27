package conditions.raport.contain;

import org.fest.assertions.Condition;

import service.dictionary.MONTH;

public class MonthsPeriodCondition extends Condition<String> {

	private MONTH monthFrom;
	
	private MONTH monthTo;
	
	public MonthsPeriodCondition(MONTH monthFrom, MONTH monthTo) {
		this.monthFrom = monthFrom;
		this.monthTo = monthTo;
	}
	
	public static MonthsPeriodCondition headerContainPeriodMonths(MONTH monthFrom, MONTH monthTo) {
		return new MonthsPeriodCondition(monthFrom, monthTo);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(monthFrom.getDesc())
				&& textRaport.contains(monthTo.getDesc())
				&& textRaport.indexOf(monthFrom.getDesc()) < textRaport.indexOf(monthTo.getDesc());
	}

}
