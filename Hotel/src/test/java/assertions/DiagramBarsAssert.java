package assertions;

import org.fest.assertions.Assertions;
import org.fest.assertions.DoubleArrayAssert;

public class DiagramBarsAssert extends DoubleArrayAssert {

	private DiagramBarsAssert(double[] actual) {
		super(actual);
	}
	
	public static DiagramBarsAssert assertThat(double[] actual) {
		return new DiagramBarsAssert(actual);
	}
	
	public DiagramBarsAssert isSummaryGainEqualTo(double summaryGain) {
		return assertArrayElementOfIndex(0, summaryGain);
	}

	
	public DiagramBarsAssert isUnitGainEqualTo(double unitGain) {
		return assertArrayElementOfIndex(1, unitGain);
	}

	public DiagramBarsAssert isReservationSummaryGainEqualTo(double reservationSummaryGain) {
		return assertArrayElementOfIndex(0, reservationSummaryGain);
	}
	
	public DiagramBarsAssert isServiceSummaryGainEqualTo(double serviceSummaryGain) {
		return assertArrayElementOfIndex(1, serviceSummaryGain);
	}
	
	public DiagramBarsAssert isCantorSummaryGainEqualTo(double cantorSummaryGain) {
		return assertArrayElementOfIndex(2, cantorSummaryGain);
	}
	
	public DiagramBarsAssert isHotelSummaryGainEqualTo(double summaryGain) {
		return assertArrayElementOfIndex(3, summaryGain);
	}

	private DiagramBarsAssert assertArrayElementOfIndex(int i, double expectedGain) {
		Assertions.assertThat(actual[i]).isEqualTo(expectedGain);
		return this;
	}
}
