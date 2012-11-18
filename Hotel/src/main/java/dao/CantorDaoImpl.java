package dao;

import dto.SimpleNameData;
import dto.cantor.CurrencyData;
import exception.DAOException;

import java.util.List;

public class CantorDaoImpl extends AbstractDao implements CantorDao{

    public List<SimpleNameData> showColumnsForCurrency() throws DAOException {
        String query = "show columns from hotel.waluty;";
        return executeQuery(query, SimpleNameData.class);
    }

    @Override
    public List<CurrencyData> findAllCurrency() throws DAOException {
        String query = "select * from waluty;";
        return executeQuery(query, CurrencyData.class);

    }
}
