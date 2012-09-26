package conditions.raport.contain;

import org.fest.assertions.Condition;

public class UseNumberCondition extends Condition<String> {

	private int useNumber;
	
	public UseNumberCondition(int useNumber) {
		this.useNumber = useNumber;
	}
	
	public static UseNumberCondition bodyContainUseNumber(int useNumber) {
		return new UseNumberCondition(useNumber);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Integer.toString(useNumber));
	}

}
