package dto;

public class MonthSumaryGain {

	int month;

	double reservationSumaryGain;

	double serviceSumaryGain;

	double cantorSumaryGain;

	public MonthSumaryGain() {
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getReservationSumaryGain() {
		return reservationSumaryGain;
	}

	public void setReservationSumaryGain(double reservationSumaryGain) {
		this.reservationSumaryGain = reservationSumaryGain;
	}

	public double getServiceSumaryGain() {
		return serviceSumaryGain;
	}

	public void setServiceSumaryGain(double serviceSumaryGain) {
		this.serviceSumaryGain = serviceSumaryGain;
	}

	public double getCantorSumaryGain() {
		return cantorSumaryGain;
	}

	public void setCantorSumaryGain(double cantorSumaryGain) {
		this.cantorSumaryGain = cantorSumaryGain;
	}
}