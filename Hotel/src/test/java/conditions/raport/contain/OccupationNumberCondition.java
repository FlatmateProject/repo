package conditions.raport.contain;

import org.fest.assertions.Condition;

public class OccupationNumberCondition extends Condition<String> {

	private int occupationNumber;

	public OccupationNumberCondition(int occupationNumber) {
		this.occupationNumber = occupationNumber;
	}

	public static OccupationNumberCondition bodyContainOccupationNumber(int occupationNumber) {
		return new OccupationNumberCondition(occupationNumber);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Integer.toString(occupationNumber));
	}
}
