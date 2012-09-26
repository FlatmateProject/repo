package conditions.raport.contain;

import org.fest.assertions.Condition;

public class SumaryGainCondition extends Condition<String> {

	private double sumaryGain;

	public SumaryGainCondition(double sumaryGain) {
		this.sumaryGain = sumaryGain;
	}

	public static SumaryGainCondition bodyContainSumaryGain(
			double sumaryGain) {
		return new SumaryGainCondition(sumaryGain);
	}

	@Override
	public boolean matches(String textRaport) {
		return textRaport.contains(Double.toString(sumaryGain));
	}
}
