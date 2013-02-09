package dao.impl;

import dao.EmployeeManagerDao;
import dto.EmployeeData;
import dto.employeeManager.CleanTimeData;
import exception.DAOException;
import session.SimpleSession;

import java.util.List;

public class EmployeeManagerDaoImpl implements EmployeeManagerDao {

    private final SimpleSession session;

    public EmployeeManagerDaoImpl(SimpleSession session) {
        this.session = session;
    }

    @Override
    public List<CleanTimeData> findCleanTimeForRooms() throws DAOException {
        String query = "SELECT p.id_pokoju, k.czassp FROM pokoje p JOIN klasy k ON p.id_klasy=k.id_klasy ORDER BY id_pokoju";
        return session.executeQuery(query, CleanTimeData.class);
    }

    @Override
    public List<EmployeeData> findEmployeesByOccupationId(int occupationId) throws DAOException {
        String query = "SELECT imie, nazwisko, idp_pesel FROM pracownicy WHERE id_stanowiska=" + occupationId;
        return session.executeQuery(query, EmployeeData.class);
    }
}
