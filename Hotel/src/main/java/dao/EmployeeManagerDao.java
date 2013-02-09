package dao;

import dto.EmployeeData;
import dto.employeeManager.CleanTimeData;
import exception.DAOException;

import java.util.List;

public interface EmployeeManagerDao {

    List<CleanTimeData> findCleanTimeForRooms() throws DAOException;

    List<EmployeeData> findEmployeesByOccupationId(int occupationId) throws DAOException;
}
