package conditions.raport.contain;

import org.fest.assertions.Condition;

public class ValidYearCondition extends Condition<String> {

	private int year;
	
	public ValidYearCondition(int year) {
		this.year = year;
	}
	
	public static ValidYearCondition headerContainValidYear(int year) {
		return new ValidYearCondition(year);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Integer.toString(this.year));
	}

}
