package dao;

import dto.SimpleNameData;
import dto.statictic.*;
import exception.DAOException;

import java.util.List;

public interface StatisticDao {

    List<RoomTypeStatisticData> findRoomTypesStatistics(int month, int year) throws DAOException;

    List<RoomStatisticData> findRoomsStatisticsByType(int month, int year, String classRoom) throws DAOException;

    List<ServiceTypeData> findServiceTypesStatistics(int month, int year) throws DAOException;

    List<ServiceStatisticData> findServiceStatisticsByType(int month, int year, String serviceName) throws DAOException;

    List<MonthSummaryGainData> findMonthSummaryGains(int monthFrom, int monthTo, int year) throws DAOException;

    List<YearSummaryGainData> findYearSummaryGains(int yearFrom, int yearTo) throws DAOException;

    int countUseNumberForServiceType(String serviceTypeName) throws DAOException;

    int countUseNumberForServiceName(String serviceName) throws DAOException;

    List<SimpleNameData> findAllRoomTypes() throws DAOException;

    List<SimpleNameData> findAllServiceTypes() throws DAOException;
}