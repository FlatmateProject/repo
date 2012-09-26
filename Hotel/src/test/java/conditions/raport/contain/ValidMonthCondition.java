package conditions.raport.contain;

import org.fest.assertions.Condition;

import service.dictionary.MONTH;

public class ValidMonthCondition extends Condition<String> {

	private MONTH month;
	
	public ValidMonthCondition(MONTH month) {
		this.month = month;
	}
	
	public static ValidMonthCondition headerContainValidMonth(MONTH month) {
		return new ValidMonthCondition(month);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(this.month.getDesc());
	}

}
