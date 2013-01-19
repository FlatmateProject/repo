package dao;

import dto.SimpleNameData;
import exception.DAOException;

import java.util.List;

public interface GuestBookDao {

    List<SimpleNameData> showColumnsForTable(String table) throws DAOException;

    <T> List<T> getDataWithTable(String table, String conditions, Class<T> customerDataClass) throws DAOException;
}
