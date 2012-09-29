package assertions;

import org.fest.assertions.Assertions;
import org.fest.assertions.DoubleArrayAssert;

public class DiagramBarsAssert extends DoubleArrayAssert {

	protected DiagramBarsAssert(double[] actual) {
		super(actual);
	}
	
	public static DiagramBarsAssert assertThat(double[] actual) {
		return new DiagramBarsAssert(actual);
	}
	
	public DiagramBarsAssert isSumaryGainEqualTo(double sumaryGain) {
		return assertArrayElementOfIndex(0, sumaryGain);
	}

	
	public DiagramBarsAssert isUnitGainEqualTo(double unitGain) {
		return assertArrayElementOfIndex(1, unitGain);
	}

	public DiagramBarsAssert isReservationSumaryGainEqualTo(double reservationSumaryGain) {
		return assertArrayElementOfIndex(0, reservationSumaryGain);
	}
	
	public DiagramBarsAssert isServiceSumaryGainEqualTo(double serviceSumaryGain) {
		return assertArrayElementOfIndex(1, serviceSumaryGain);
	}
	
	public DiagramBarsAssert isCantorSumaryGainEqualTo(double cantorSumaryGain) {
		return assertArrayElementOfIndex(2, cantorSumaryGain);
	}
	
	public DiagramBarsAssert isHotelSumaryGainEqualTo(double sumaryGain) {
		return assertArrayElementOfIndex(3, sumaryGain);
	}

	private DiagramBarsAssert assertArrayElementOfIndex(int i, double expectedGain) {
		Assertions.assertThat(actual[i]).isEqualTo(expectedGain);
		return this;
	}
}
