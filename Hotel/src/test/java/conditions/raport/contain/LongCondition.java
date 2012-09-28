package conditions.raport.contain;

import org.fest.assertions.Condition;

public class LongCondition extends Condition<String> {

	private long value;
	
	public LongCondition(long value) {
		this.value = value;
	}
	
	public static LongCondition bodyContainSumaryTime(long sumaryTime) {
		return new LongCondition(sumaryTime);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Long.toString(value));
	}

}
