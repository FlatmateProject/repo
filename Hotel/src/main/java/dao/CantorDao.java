package dao;

import dto.statictic.cantor.CurrencyColumnData;
import exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public interface CantorDao {

    List<CurrencyColumnData> showColumnsForCurrency() throws DAOException;

    ResultSet findAllCurrency();
}
