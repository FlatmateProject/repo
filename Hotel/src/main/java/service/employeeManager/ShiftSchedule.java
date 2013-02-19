package service.employeeManager;

import dao.EmployeeManagerDao;
import dto.EmployeeData;
import exception.DAOException;
import exception.RoomsNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ShiftSchedule {

    private EmployeeManagerDao employeeManagerDao;

    private RoomRangeCalculator roomRangeCalculator;

    private EmployeesPool employeesPool;

    private MonthDaysCounter monthDaysCounter;

    private int shift;

    public ShiftSchedule(EmployeeManagerDao employeeManagerDao, RoomRangeCalculator roomRangeCalculator, EmployeesPool employeesPool) {
        this.employeeManagerDao = employeeManagerDao;
        this.roomRangeCalculator = roomRangeCalculator;
        this.employeesPool = employeesPool;

    }

    public List<WorkItem> create(MonthDaysCounter monthDaysCounter, int shift) throws DAOException, RoomsNotFoundException {
        this.monthDaysCounter = monthDaysCounter;
        this.shift = shift;
        List<WorkItem> workItems = new ArrayList<WorkItem>();
        RoomRange range = roomRangeCalculator.getFirstEmptyRange();
        while (roomRangeCalculator.isNextRoom()) {
            range = roomRangeCalculator.getNextRoomRange(range);
            WorkItem workItem = saveWork(range);
            workItems.add(workItem);
        }
        return workItems;
    }

    public void prepareToNextShift() {
        employeesPool.prepareToNextShift();
    }

    private WorkItem saveWork(RoomRange range) throws DAOException {
        if (employeesPool.hasNext()) {
            EmployeeData employee = employeesPool.nextEmployee();
            employeeManagerDao.saveStandardTime(employee.getPesel(), shift, monthDaysCounter.getCurrentDate(), range);
            return WorkItem.Standard;
        } else {
            employeeManagerDao.saveStandardOvertime(employeesPool.generateNextOvertimeId(), shift, monthDaysCounter.getCurrentDate(), range);
            return WorkItem.Overtime;
        }
    }
}