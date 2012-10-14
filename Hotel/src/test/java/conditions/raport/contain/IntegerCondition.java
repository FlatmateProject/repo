package conditions.raport.contain;

import org.fest.assertions.Condition;

public class IntegerCondition extends Condition<String> {

	private int value;
	
	public IntegerCondition(int value) {
		this.value = value;
	}
	
	public static IntegerCondition bodyContainUseNumber(int useNumber) {
		return new IntegerCondition(useNumber);
	}

	public static IntegerCondition headerContainYear(int year) {
		return new IntegerCondition(year);
	}
	
	public static IntegerCondition bodyContainNumberOccupiedRooms(int numberOccupiedRooms) {
		return new IntegerCondition(numberOccupiedRooms);
	}
	
	public static IntegerCondition bodyContainOccupationNumber(int occupationNumber) {
		return new IntegerCondition(occupationNumber);
	}
	
	@Override
	public boolean matches(String textReport) {
		return textReport.contains(Integer.toString(value));
	}

}
