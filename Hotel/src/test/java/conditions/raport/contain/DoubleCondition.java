package conditions.raport.contain;

import org.fest.assertions.Condition;

public class DoubleCondition extends Condition<String> {

	private double value;

	public DoubleCondition(double value) {
		this.value = value;
	}

	public static DoubleCondition bodyContainSummaryGain(double summaryGain) {
		return new DoubleCondition(summaryGain);
	}

	public static DoubleCondition bodyContainReservationSummaryGain(double reservationSummaryGain) {
		return new DoubleCondition(reservationSummaryGain);
	}
	
	public static DoubleCondition bodyContainServiceSummaryGain(double serviceSummaryGain) {
		return new DoubleCondition(serviceSummaryGain);
	}
	
	public static DoubleCondition bodyContainCantorSummaryGain(double cantorSummaryGain) {
		return new DoubleCondition(cantorSummaryGain);
	}
	public static DoubleCondition bodyContainHotelSummaryGain(double sumaryGain) {
		return new DoubleCondition(sumaryGain);
	}
	
	public static DoubleCondition bodyContainUnitGain(double unitGain) {
		return new DoubleCondition(unitGain);
	}
	
	@Override
	public boolean matches(String textReport) {
		return textReport.contains(Double.toString(value));
	}
}
