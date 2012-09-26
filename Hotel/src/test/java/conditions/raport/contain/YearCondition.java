package conditions.raport.contain;

import org.fest.assertions.Condition;

public class YearCondition extends Condition<String> {

	private int year;
	
	public YearCondition(int year) {
		this.year = year;
	}
	
	public static YearCondition headerContainYear(int year) {
		return new YearCondition(year);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Integer.toString(year));
	}

}
