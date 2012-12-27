package service.cantor;

import dao.CantorDao;
import dto.SimpleNameData;
import dto.cantor.CurrencyData;
import dto.cantor.CustomerData;
import exception.DAOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static assertions.TableAssert.assertThat;
import static conditions.table.ColumnCondition.containColumns;
import static conditions.table.RowCondition.containsRow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.Mock;
import static org.mockito.MockitoAnnotations.initMocks;

public class CantorTableCreatorTest {

    @Mock
    CantorDao cantorDao;

    private CantorTableCreator creator;

    @BeforeMethod
    public void beforeEachTest() {
        initMocks(this);
        creator = new CantorTableCreator(cantorDao);
    }

    @Test
    public void shouldCreateEmptyCurrencyTable() throws Exception {
        //given

        //when
        CantorTableResult result = creator.createCurrencyTable();

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
        Object[] row = new Object[]{1, "USD", 320, 300, 100};
        String[] columnNames = new String[]{"Id", "Name", "Sale Price", "Buy Price", "Quantity"};

        CurrencyData currencyData = mock(CurrencyData.class);
        when(currencyData.getArray()).thenReturn(row);

        SimpleNameData simpleNameData = mock(SimpleNameData.class);
        when(simpleNameData.getName()).thenReturn(columnNames[0], columnNames[1], columnNames[2], columnNames[3], columnNames[4]);

        when(cantorDao.showColumnsForCurrency()).thenReturn(Arrays.asList(simpleNameData, simpleNameData, simpleNameData, simpleNameData, simpleNameData));
        when(cantorDao.findAllCurrency()).thenReturn(Arrays.asList(currencyData));

        // when
        CantorTableResult result = creator.createCurrencyTable();

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
        long pesel = 87122206592L;

        //when
        CantorTableResult result = creator.createCustomerTable(pesel);

        //then
        assertThat(result)
                .isNotNull()
                .hasRowNumber(1)
                .hasColumnNumber(1)
                .is(containColumns(CantorTableResult.EMPTY_COLUMN))
                .is(containsRow(CantorTableResult.EMPTY_ROW));
    }

    @Test
    public void shouldCreateCustomerTableWithOneRow() throws DAOException {
        // given
        long pesel = 87122206592L;
        String[] columnNames = new String[]{"Pesel", "Name", "Family", "County", "City", "Street", "Block", "Flat", "Status", "Notes", "Phone", "Nip"};
        Object[] row = new Object[]{87122206592L, "Piotr", "Piotrowski", "Małopolsie", "Kraków", "Zdunów", "22c", 30L, "NEW", "OK", 889225169L, 6582514L};

        SimpleNameData simpleNameData = mock(SimpleNameData.class);
        when(simpleNameData.getName()).thenReturn(columnNames[0], columnNames[1], columnNames[2], columnNames[3], columnNames[4],
                columnNames[5], columnNames[6], columnNames[7], columnNames[8], columnNames[9], columnNames[10], columnNames[11]);

        CustomerData customerData = mock(CustomerData.class);
        when(customerData.getArray()).thenReturn(row);

        when(cantorDao.showColumnsForCustomer()).thenReturn(Arrays.asList(simpleNameData, simpleNameData, simpleNameData, simpleNameData, simpleNameData, simpleNameData,
                simpleNameData, simpleNameData, simpleNameData, simpleNameData, simpleNameData, simpleNameData));
        when(cantorDao.findAllCustomers(pesel)).thenReturn(Arrays.asList(customerData));

        // when
        CantorTableResult result = creator.createCustomerTable(pesel);

        // then
        assertThat(result)
                .isNotNull()
                .hasColumnNumber(12)
                .hasRowNumber(1)
                .is(containColumns(columnNames))
                .is(containsRow(row));
    }
}
