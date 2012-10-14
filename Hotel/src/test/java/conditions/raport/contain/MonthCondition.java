package conditions.raport.contain;

import org.fest.assertions.Condition;
import service.dictionary.MONTH;

public class MonthCondition extends Condition<String> {

	private MONTH month;
	
	public MonthCondition(MONTH month) {
		this.month = month;
	}
	
	public static MonthCondition headerContainMonth(MONTH month) {
		return new MonthCondition(month);
	}

	@Override
	public boolean matches(String textReport) {
		return textReport.contains(month.getDesc());
	}

}
