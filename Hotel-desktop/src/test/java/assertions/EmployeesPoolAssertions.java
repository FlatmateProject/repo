package assertions;

import dto.EmployeeData;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;
import service.employeeManager.EmployeesPool;

public class EmployeesPoolAssertions extends GenericAssert<EmployeesPoolAssertions, EmployeesPool> {

    private EmployeeData currentEmployee;

    private Exception currentException;

    private EmployeesPoolAssertions(Class<EmployeesPoolAssertions> selfType, EmployeesPool actual) {
        super(selfType, actual);
    }

    public static EmployeesPoolAssertions assertThat(EmployeesPool actual) {
        return new EmployeesPoolAssertions(EmployeesPoolAssertions.class, actual);
    }


    public EmployeesPoolAssertions hasNotNext() {
        Assertions.assertThat(actual.hasNext()).isEqualTo(false);
        return this;
    }

    public EmployeesPoolAssertions hasNext() {
        Assertions.assertThat(actual.hasNext()).isEqualTo(true);
        return this;
    }

    public EmployeesPoolAssertions move(int numberOfMoves) {
        try {
            for (int i = 0; i < numberOfMoves; i++) {
                currentEmployee = actual.nextEmployee();
            }
        } catch (Exception e) {
            currentEmployee = null;
            currentException = e;
        }
        return this;
    }

    public EmployeesPoolAssertions isThrownArrayIndexOutOfBoundsException() {
        Assertions.assertThat(currentException).isNotNull();
        Assertions.assertThat(currentException.getClass()).isEqualTo(ArrayIndexOutOfBoundsException.class);
        currentException = null;
        return this;
    }

    public EmployeesPoolAssertions currentEmployee(EmployeeData currentEmployee) {
        Assertions.assertThat(this.currentEmployee).isEqualTo(currentEmployee);
        return this;
    }

    public EmployeesPoolAssertions isOverTimeId(long firstOvertimeId) {
        Assertions.assertThat(actual.generateNextOvertimeId()).isEqualTo(firstOvertimeId);
        return this;
    }

    public EmployeesPoolAssertions overtime() {
        actual.generateNextOvertimeId();
        return this;
    }

    public EmployeesPoolAssertions prepareNextShift() {
        actual.prepareToNextShift();
        return this;
    }
}
