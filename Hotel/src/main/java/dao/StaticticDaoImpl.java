package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import dto.ClassRoomData;
import dto.RoomData;

public class StaticticDaoImpl extends AbstractDao implements StaticticDao {
	
	@Override
	public List<ClassRoomData> findClassRooms(int month, int year) {
		String query = "SELECT k.opis opis, count(r.id_rez) meldunki, sum((r.data_w-r.data_z)*k.cena) zysk ";
		query += "FROM rezerwacje r ";
		query += "JOIN pokoje p ON r.id_pokoju=p.id_pokoju  JOIN klasy k ON p.id_klasy=k.id_klasy ";
		query += "WHERE MONTH(r.data_w)=" + month + " and YEAR(r.data_w)="
				+ year + " GROUP BY k.id_klasy ORDER BY k.id_klasy";
		return exacuteQuery(query, ClassRoomData.class);
	}

	@Override
	public List<RoomData> createRoomRaport(int month, int year, String classRoom) {
		String query = "SELECT p.id_pokoju pokuj, count(r.id_rez) meldunki, sum((r.data_w-r.data_z)*k.cena) zysk ";
		query += "FROM rezerwacje r ";
		query += "JOIN pokoje p ON r.id_pokoju=p.id_pokoju  JOIN klasy k ON p.id_klasy=k.id_klasy ";
		query += "WHERE MONTH(r.data_w)=" + month + " and YEAR(r.data_w)="
				+ year + " and k.opis='" + classRoom + "' ";
		query += " GROUP BY p.id_pokoju ORDER BY p.id_pokoju";
 		return exacuteQuery(query, RoomData.class);
	}

	@Override
	public ResultSet createServesRaport(int month, int year) {
		String query = "SELECT u.typ, sum(rk.czas) czas, sum(rk.czas*u.cena) zysk ";
		query += "FROM rekreacja rk JOIN uslugi u ON rk.id_uslugi=u.id_uslugi ";
		query += "JOIN rezerwacje rz ON rk.id_rez=rz.id_rez ";
		query += "WHERE MONTH(rz.data_w)=" + month + " and YEAR(rz.data_w)="
				+ year + " GROUP BY u.typ";
		return getSession().query(query);
	}

	@Override
	public ResultSet createServeRaport(int month, int year, String serve) {
		String query = "SELECT u.nazwa, sum(rk.czas) czas, sum(rk.czas*u.cena) zysk ";
		query += "FROM rekreacja rk JOIN uslugi u ON rk.id_uslugi=u.id_uslugi ";
		query += "JOIN rezerwacje rz ON rk.id_rez=rz.id_rez ";
		query += "WHERE MONTH(rz.data_w)=" + month
				+ " and YEAR(rz.data_w)=" + year + " AND u.typ='" + serve
				+ "' ";
		query += "GROUP BY u.nazwa";
		return getSession().query(query);
	}

	@Override
	public ResultSet createMonthRaport(int monthFrom, int monthTo, int year) {
		String query = "SELECT MONTH(r.data_w) miesiac, sum((r.data_w-r.data_z)*k.cena) zysk_r, ";
		query += " ( SELECT sum(rk.czas*u.cena) FROM rekreacja rk JOIN rezerwacje rz ON rk.id_rez=rz.id_rez ";
		query += "JOIN uslugi u ON rk.id_uslugi=u.id_uslugi WHERE MONTH(rz.data_w)=MONTH(r.data_w) ";
		query += ") zysk_u, ( SELECT sum(ka.wartosc) FROM kantor ka ";
		query += "WHERE MONTH(ka.data)=MONTH(r.data_w) ) zysk_k FROM rezerwacje r ";
		query += "JOIN pokoje p ON r.id_pokoju=p.id_pokoju JOIN klasy  k ON p.id_klasy=k.id_klasy ";
		query += "WHERE MONTH(r.data_w)>=" + monthFrom
				+ " AND MONTH(r.data_w)<=" + monthTo + " ";
		query += " AND YEAR(r.data_w)=" + year
				+ " GROUP BY MONTH(r.data_w)";
		return getSession().query(query);
	}

	@Override
	public ResultSet createYearRaport(int yearFrom, int yearTo) {
		String query = "SELECT YEAR(r.data_w) miesiac, sum((r.data_w-r.data_z)*k.cena) zysk_r, ";
		query += " ( SELECT sum(rk.czas*u.cena) FROM rekreacja rk JOIN rezerwacje rz ON rk.id_rez=rz.id_rez ";
		query += "JOIN uslugi u ON rk.id_uslugi=u.id_uslugi WHERE YEAR(rz.data_w)=YEAR(r.data_w) ";
		query += ") zysk_u, ( SELECT sum(ka.wartosc) FROM kantor ka ";
		query += "WHERE YEAR(ka.data)=YEAR(r.data_w) ) zysk_k FROM rezerwacje r ";
		query += "JOIN pokoje p ON r.id_pokoju=p.id_pokoju JOIN klasy  k ON p.id_klasy=k.id_klasy ";
		query += "WHERE YEAR(r.data_w)>=" + yearFrom
				+ " AND YEAR(r.data_w)<=" + yearTo + " ";
		query += "GROUP BY YEAR(r.data_w)";
		return getSession().query(query);
	}

	@Override
	public int countUseNumberForServe(String serve) throws SQLException {
		String query = "SELECT count(*) ilosc_zameldowan FROM (SELECT count(rrk.id_rez) ilosc_rez ";
		query += "FROM rekreacja rrk JOIN uslugi uu ON rrk.id_uslugi=uu.id_uslugi JOIN rezerwacje ";
		query += "rrz ON rrk.id_rez=rrz.id_rez WHERE uu.typ='"
				+ serve + "' ";
		query += "  GROUP BY rrk.id_rez ) tab";
		return (Integer) uniqueResult(query);
	}
}
