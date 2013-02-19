package service.employeeManager;

import dto.EmployeeData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static service.employeeManager.EmployeesPoolAssertions.assertThat;

public class EmployeesPoolTest {

    @Test
    public void shouldDoesNotHaveNextForEmptyEmployeesList() {
        // given
        List<EmployeeData> employees = Collections.emptyList();

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool).hasNotNext();
    }

    @Test
    public void shouldHasNextForOneElementEmployeesList() {
        // given
        EmployeeData employee = mock(EmployeeData.class);

        List<EmployeeData> employees = Arrays.asList(employee);

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool).hasNext();
    }

    @Test
    public void shouldDoesHaveNextForOneElementEmployeesListAfterMovedOne() {
        // given
        EmployeeData employee = mock(EmployeeData.class);

        List<EmployeeData> employees = Arrays.asList(employee);

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool)
                .move(1)
                .currentEmployee(employee)
                .hasNotNext();
    }

    @Test
    public void shouldGetThirdElementAfterMovedThree() {
        // given
        EmployeeData thirdElement = mock(EmployeeData.class);
        List<EmployeeData> employees = Arrays.asList(mock(EmployeeData.class), mock(EmployeeData.class), thirdElement);

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool)
                .move(3)
                .currentEmployee(thirdElement)
                .hasNotNext();
    }

    @Test
    public void shouldThrowExceptionForOneElementEmployeesListAfterMovedTwo() {
        // given
        EmployeeData employee = mock(EmployeeData.class);

        List<EmployeeData> employees = Arrays.asList(employee);

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool)
                .move(2)
                .isThrownArrayIndexOutOfBoundsException();
    }

    @Test
    public void shouldGenerateFirstOvertimeId() {
        // given
        long firstOvertimeId = 0;

        List<EmployeeData> employees = Collections.emptyList();

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool).isOverTimeId(firstOvertimeId);
    }

    @Test
    public void shouldGenerateSecondOvertimeId() {
        // given
        long secondOvertimeId = 1;

        List<EmployeeData> employees = Collections.emptyList();

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool)
                .overtime()
                .isOverTimeId(secondOvertimeId);
    }

    @Test
    public void shouldGenerateNextOvertimeId() {
        // given
        long nextOvertimeId = 2;

        List<EmployeeData> employees = Collections.emptyList();

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool)
                .overtime()
                .overtime()
                .isOverTimeId(nextOvertimeId);
    }


    @Test
    public void shouldPrepareNextShiftDoNothingWhenListHasNextElement() {
        // given
        EmployeeData thirdElement = mock(EmployeeData.class);
        List<EmployeeData> employees = Arrays.asList(mock(EmployeeData.class), mock(EmployeeData.class), thirdElement);

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool)
                .move(2)
                .prepareNextShift()
                .move(1)
                .currentEmployee(thirdElement)
                .hasNotNext();
    }

    @Test
    public void shouldPrepareNextShiftMoveListToBeginningWhenListHasNotNextElement() {
        // given
        EmployeeData firstElement = mock(EmployeeData.class);
        List<EmployeeData> employees = Arrays.asList(firstElement, mock(EmployeeData.class), mock(EmployeeData.class));

        // when
        EmployeesPool employeesPool = new EmployeesPool(employees);

        // then
        assertThat(employeesPool)
                .move(3)
                .prepareNextShift()
                .move(1)
                .currentEmployee(firstElement)
                .hasNext();
    }
}
