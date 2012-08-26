package service.statictic;

public enum RAPORT_KIND {
	FINANCE_MONTH("Bilansu z miesięcy"), //
	FINANCE_YEAR("Bilansu z lat"), //
	HOTEL_CLASS("Raportu z wykorzystania klas pokoi"), //
	HOTEL_ROOM("Raportu z wykorzystania pokoi w klasie"), //
	HOTEL_SERVICES("Raportu z wykorzystania typów usług"), //
	HOTEL_SERVICE("Raportu z wybranej uslugi")//
	;

	private String desc;

	private RAPORT_KIND(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
};