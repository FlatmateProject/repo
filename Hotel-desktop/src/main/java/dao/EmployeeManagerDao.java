package dao;

import dto.EmployeeData;
import dto.employeeManager.CleanTimeData;
import exception.DAOException;
import service.employeeManager.RoomRange;

import java.util.List;

public interface EmployeeManagerDao {

    List<CleanTimeData> findCleanTimeForRooms() throws DAOException;

    List<EmployeeData> findEmployeesByOccupationId(int occupationId) throws DAOException;

    void saveStandardTime(long pesel, int shift, String date, RoomRange range) throws DAOException;

    void saveStandardOvertime(long extra, int shift, String date, RoomRange range) throws DAOException;
}
