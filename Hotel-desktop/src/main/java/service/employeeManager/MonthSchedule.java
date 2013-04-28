package service.employeeManager;

import dao.EmployeeManagerDao;
import dictionary.MONTH;
import dto.EmployeeData;
import dto.employeeManager.CleanTimeData;
import exception.DAOException;
import exception.RoomsNotFoundException;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.List;

public class MonthSchedule {

    private static final Logger log = Logger.getLogger(MonthSchedule.class);

    private Calendar calendar;

    private EmployeeManagerDao employeeManagerDao;

    private List<CleanTimeData> cleanTimes;

    private List<EmployeeData> employees;

    public MonthSchedule(Calendar calendar, EmployeeManagerDao employeeManagerDao) {
        this.calendar = calendar;
        this.employeeManagerDao = employeeManagerDao;
    }

    public String calculateForMonth(MONTH month) throws DAOException, RoomsNotFoundException {
        StringBuffer resultText = new StringBuffer();
        cleanTimes = employeeManagerDao.findCleanTimeForRooms();
        employees = employeeManagerDao.findEmployeesByOccupationId(1);
        int year = calendar.get(Calendar.YEAR);
        MonthDaysCounter monthDaysCounter = new MonthDaysCounter(year, month);
        int numberOfDaysInMonth = monthDaysCounter.getNumberOfDaysInMonth();
        log.info("numberOfDaysInMonth: " + numberOfDaysInMonth);
        for (int i = 0; i < numberOfDaysInMonth; i++) {
            createDaySchedule(monthDaysCounter);
            monthDaysCounter.nextDay();
        }
        return resultText.toString();
    }

    private void createDaySchedule(MonthDaysCounter monthDaysCounter) throws DAOException, RoomsNotFoundException {
        int shiftNumber = 3;
        for (int shift = 1; shift <= shiftNumber; shift++) {
            RoomRangeCalculator roomRangeCalculator = new RoomRangeCalculator(cleanTimes);
            EmployeesPool employeesPool = new EmployeesPool(employees);
            ShiftSchedule shiftSchedule = new ShiftSchedule(employeeManagerDao, roomRangeCalculator, employeesPool);
            shiftSchedule.create(monthDaysCounter, shift);
            shiftSchedule.prepareToNextShift();
        }
    }
}