package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.MonthSumaryGainData;
import dto.RoomTypesData;
import dto.RoomData;
import dto.ServiceData;
import dto.ServiceTypeData;

public interface StaticticDao {
	
	Singleton getSession();
	
	void setSession(Singleton session);
	
	List<RoomTypesData> findRoomTypes(int month, int year);

	List<RoomData> findRoomsByType(int month, int year, String classRoom);

	List<ServiceTypeData> findServiceTypes(int month, int year);

	List<ServiceData> findServiceByType(int month, int year, String serviceName);

	List<MonthSumaryGainData> findMonthSumaryGains(int monthFrom, int monthTo, int year);

	ResultSet createYearRaport(int yearFrom, int yearTo);

	int countUseNumberForServiceType(String serviceTypeName) throws SQLException;

	int countUseNumberForServiceName(String serviceName) throws SQLException;;
}
