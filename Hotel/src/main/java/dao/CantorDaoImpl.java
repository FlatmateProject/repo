package dao;

import dto.cantor.CurrencyColumnData;
import exception.DAOException;

import java.sql.ResultSet;
import java.util.List;

public class CantorDaoImpl extends AbstractDao implements CantorDao{

    public List<CurrencyColumnData> showColumnsForCurrency() throws DAOException {
        String query = "show columns from hotel.waluty;";
        return executeQuery(query, CurrencyColumnData.class);
    }

    @Override
    public ResultSet findAllCurrency() {
        return session.query("select * from waluty;");
    }
}
