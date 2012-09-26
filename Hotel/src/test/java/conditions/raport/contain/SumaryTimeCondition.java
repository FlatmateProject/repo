package conditions.raport.contain;

import org.fest.assertions.Condition;

public class SumaryTimeCondition extends Condition<String> {

	private long sumaryTime;
	
	public SumaryTimeCondition(long sumaryTime) {
		this.sumaryTime = sumaryTime;
	}
	
	public static SumaryTimeCondition bodyContainValidSumaryTime(long sumaryTime) {
		return new SumaryTimeCondition(sumaryTime);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Long.toString(sumaryTime));
	}

}
