package service.employeeManager;

import dto.EmployeeData;

import java.util.List;

public class EmployeesPool {

    private final int numberOfEmployees;

    private List<EmployeeData> employees;

    private int[] workHours;

    private int currentEmployeeIndex;

    private int overtimeId;

    public EmployeesPool(List<EmployeeData> employees) {
        this.employees = employees;
        this.numberOfEmployees = employees.size();
        this.workHours = createEmptyArray(numberOfEmployees);
    }

    public boolean hasNext() {
        return currentEmployeeIndex < numberOfEmployees;
    }

    public EmployeeData nextEmployee() {
        EmployeeData employee = employees.get(currentEmployeeIndex);
        workHours[currentEmployeeIndex] += 8;
        currentEmployeeIndex++;
        return employee;
    }

    public long generateNextOvertimeId() {
        long currentOvertimeId = overtimeId;
        overtimeId++;
        return currentOvertimeId;
    }


    public void prepareToNextShift() {
        if (!hasNext()) {
            overtimeId = 0;
            currentEmployeeIndex = 0;
        }
    }

    private int[] createEmptyArray(int size) {
        int[] workHours = new int[size];
        for (int j = 0; j < workHours.length; j++) {
            workHours[j] = 0;
        }
        return workHours;
    }
}
