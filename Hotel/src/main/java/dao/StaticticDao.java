package dao;

import java.sql.ResultSet;
import java.util.List;

import dto.ClassRoomData;

public interface StaticticDao {
	
	Singleton getSession();
	
	void setSession(Singleton session);
	
	List<ClassRoomData> findClassRooms(int month, int year);

	ResultSet createRoomRaport(int month, int year, String classRoom);

	ResultSet createServesRaport(int month, int year);

	ResultSet createServeRaport(int month, int year, String serve);

	ResultSet createMonthRaport(int monthFrom, int monthTo, int year);

	ResultSet createYearRaport(int yearFrom, int yearTo);
}
