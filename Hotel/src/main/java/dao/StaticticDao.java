package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.RoomTypesData;
import dto.RoomData;
import dto.ServeData;
import dto.ServeTypeData;

public interface StaticticDao {
	
	Singleton getSession();
	
	void setSession(Singleton session);
	
	List<RoomTypesData> findRoomTypes(int month, int year);

	List<RoomData> createRoomRaport(int month, int year, String classRoom);

	List<ServeTypeData> createServesRaport(int month, int year);

	List<ServeData> createServeRaport(int month, int year, String serve);

	ResultSet createMonthRaport(int monthFrom, int monthTo, int year);

	ResultSet createYearRaport(int yearFrom, int yearTo);

	int countUseNumberForServeType(String serveType) throws SQLException;

	int countUseNumberForServeName(String serveName) throws SQLException;;
}
