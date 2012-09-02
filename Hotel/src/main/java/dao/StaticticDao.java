package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.ClassRoomData;
import dto.RoomData;

public interface StaticticDao {
	
	Singleton getSession();
	
	void setSession(Singleton session);
	
	List<ClassRoomData> findClassRooms(int month, int year);

	List<RoomData> createRoomRaport(int month, int year, String classRoom);

	ResultSet createServesRaport(int month, int year);

	ResultSet createServeRaport(int month, int year, String serve);

	ResultSet createMonthRaport(int monthFrom, int monthTo, int year);

	ResultSet createYearRaport(int yearFrom, int yearTo);

	int countUseNumberForServe(String query) throws SQLException;
}
