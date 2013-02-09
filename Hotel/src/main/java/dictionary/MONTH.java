package dictionary;

public enum MONTH {

    January("Styczeń", 31),
    February("Luty", 28),
    March("Marzec", 31),
    April("Kwiecień", 30),
    May("Maj", 31),
    June("Czerwiec", 30),
    July("Lipiec", 31),
    August("Sierpień", 31),
    September("Wrzesień", 30),
    October("Październik", 31),
    November("Listopad", 30),
    December("Grudzień", 31);

    private final String desc;

    private final int numberOfDayInMonth;

    private MONTH(String desc, int numberOfDayInMonth) {
        this.desc = desc;
        this.numberOfDayInMonth = numberOfDayInMonth;
    }

    public String getDesc() {
        return desc;
    }

    public int getNumberOfDayInMonth() {
        return numberOfDayInMonth;
    }

    public int numberCountedFormOne() {
        return ordinal() + 1;
    }

    public boolean after(MONTH month) {
        return numberCountedFormOne() > month.numberCountedFormOne();
    }

    public static MONTH getMonth(int indexCountedFromZero) {
        MONTH[] months = values();
        return months[indexCountedFromZero];
    }

    public static String getMonthName(int i) {
        return getMonth(i).desc;
    }

    public static int getDayOfMonth(int i) {
        return getMonth(i).numberOfDayInMonth;
    }


    @Override
    public String toString() {
        return desc;
    }

}
