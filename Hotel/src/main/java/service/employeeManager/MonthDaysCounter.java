package service.employeeManager;

import dictionary.MONTH;

public class MonthDaysCounter {

    private int year;

    private int month;

    private int day;

    private int numberOfDaysInMonth;

    public MonthDaysCounter(int year, MONTH month) {
        this.year = year;
        this.month = month.numberCountedFromOne();
        this.day = 1;
        this.numberOfDaysInMonth = calculateNumberOfDaysInMonth(month);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void nextDay() {
        day++;
    }

    public int getNumberOfDaysInMonth() {
        return numberOfDaysInMonth;
    }

    private int calculateNumberOfDaysInMonth(MONTH month) {
        int days = month.getNumberOfDayInMonth();
        if (year % 4 == 0 && month.equals(MONTH.February)) {
            days++;
        }
        return days;
    }

    public String getCurrentDate() {
        return String.format("%d/%d/%d", year, month, day);
    }

    @Override
    public String toString() {
        return getCurrentDate();
    }
}
