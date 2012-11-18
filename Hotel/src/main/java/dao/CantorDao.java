package dao;

import dto.SimpleNameData;
import dto.cantor.CurrencyData;
import exception.DAOException;

import java.util.List;

public interface CantorDao {

    List<SimpleNameData> showColumnsForCurrency() throws DAOException;

    List<CurrencyData> findAllCurrency() throws DAOException;
}
