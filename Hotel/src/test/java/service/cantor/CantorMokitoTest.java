package service.cantor;

import dao.CantorDao;
import dto.SimpleNameData;
import dto.cantor.CurrencyData;
import exception.DAOException;
import org.testng.annotations.Test;

import java.util.Arrays;

import static assertions.TableAssert.assertThat;
import static conditions.table.ColumnCondition.containColumns;
import static conditions.table.RowCondition.containsRow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CantorMokitoTest {

    @Test
    public void shouldCreateEmptyCurrencyTable() throws Exception {
        //given
        CantorDao cantorDao = mock(CantorDao.class);

        //when
        Cantor cantor = new Cantor(cantorDao);
        CantorTableResult result = cantor.createCurrencyTable();

        //then
        assertThat(result)
                .isNotNull()
                .hasRowNumber(1)
                .hasColumnNumber(1)
                .is(containColumns(CantorTableResult.EMPTY_COLUMN))
                .is(containsRow(CantorTableResult.EMPTY_ROW));
    }

    @Test
    public void shouldCreateCurrencyTableWithOneRow() throws DAOException {
        // given
        Object[] row = new Object[]{1, "USD", 3.2, 3.0, 100};
        String[] columnNames = new String[]{"Id", "Name", "Sale Price", "Buy Price", "Quantity"};

        CurrencyData currencyData = mock(CurrencyData.class);
        when(currencyData.getArray()).thenReturn(row);

        SimpleNameData simpleNameData = mock(SimpleNameData.class);
        when(simpleNameData.getName()).thenReturn(columnNames[0], columnNames[1], columnNames[2], columnNames[3], columnNames[4]);

        CantorDao cantorDao = mock(CantorDao.class);
        when(cantorDao.showColumnsForCurrency()).thenReturn(Arrays.asList(simpleNameData, simpleNameData, simpleNameData, simpleNameData, simpleNameData));
        when(cantorDao.findAllCurrency()).thenReturn(Arrays.asList(currencyData));

        // when
        Cantor cantor = new Cantor(cantorDao);
        CantorTableResult result = cantor.createCurrencyTable();

        // then
        assertThat(result)
                .isNotNull()
                .hasColumnNumber(5)
                .hasRowNumber(1)
                .is(containColumns(columnNames))
                .is(containsRow(row));
    }

    @Test
    public void shouldCreateEmptyCustomerTable() throws Exception {
        //given
        CantorDao cantorDao = mock(CantorDao.class);

        //when
        Cantor cantor = new Cantor(cantorDao);
        CantorTableResult result = cantor.createClientTable("");

        //then
        assertThat(result)
                .isNotNull()
                .hasRowNumber(1)
                .hasColumnNumber(1)
                .is(containColumns(CantorTableResult.EMPTY_COLUMN))
                .is(containsRow(CantorTableResult.EMPTY_ROW));
    }
}
