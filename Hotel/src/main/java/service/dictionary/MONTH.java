package service.dictionary;

public enum MONTH {

	January("Styczeń", 31), //
	February("Luty", 28), //
	March("Marzec", 31), //
	April("Kwiecień", 30), //
	May("Maj", 31), //
	June("Czerwiec", 30), //
	July("Lipiec", 31), //
	August("Sierpień", 31), //
	September("Wrzesien", 30), //
	October("Październik", 31), //
	November("Listopad", 30), //
	December("Grudzień", 31)//
	;
	
	private final static MONTH[] months = values();

	private String desc;
	
	private int dayOfMonth;
	
	private MONTH(String desc, int dayOfMonth) {
		this.desc = desc;
		this.dayOfMonth = dayOfMonth;
	}

	public String getDesc() {
		return desc;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}
	
	public static MONTH getMonth(int i) {
		return months[i];
	}

	public static String getMonthName(int i) {
		return getMonth(i).desc;
	}

	public static int getDayOfMonth(int i) {
		return getMonth(i).dayOfMonth;
	}
	
	@Override
	public String toString() {
		return desc;
	}
}
