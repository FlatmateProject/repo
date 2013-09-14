package service.cantor;

import common.tableBuilder.TableContent;
import dao.CantorDao;
import dto.ColumnData;
import dto.CompanyData;
import dto.CurrencyData;
import dto.CustomerData;
import exception.DAOException;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Date;

import static assertions.TableAssert.assertThat;
import static conditions.table.ColumnCondition.containColumns;
import static conditions.table.RowCondition.containsRow;
import static conditions.table.ZeroRowsCondition.zeroRows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CantorTableCreatorMockitoTest {

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
        String[] columnNames = new String[]{"Id", "Name", "Sale Price", "Buy Price", "Quantity"};

        ColumnData columnData = mock(ColumnData.class);
        when(columnData.getName()).thenReturn(columnNames[0], Arrays.copyOfRange(columnNames, 1, columnNames.length));

        when(cantorDao.showColumnsForCurrency()).thenReturn(Arrays.asList(columnData, columnData, columnData, columnData, columnData));

        //when
        TableContent result = creator.createCurrencyTable();

        //then
        assertThat(result)
                .isNotNull()
                .hasRowNumber(0)
                .hasColumnNumber(5)
                .is(containColumns(columnNames))
                .is(zeroRows());
    }

    @Test
    public void shouldCreateCurrencyTableWithOneRow() throws DAOException {
        // given
        Object[] row = new Object[]{1, "USD", 320, 300, 100};
        String[] columnNames = new String[]{"Id", "Name", "Sale Price", "Buy Price", "Quantity"};
        CurrencyData currencyData = mock(CurrencyData.class);
        when(currencyData.getArray()).thenReturn(row);

        ColumnData columnData = mock(ColumnData.class);
        when(columnData.getName()).thenReturn(columnNames[0], Arrays.copyOfRange(columnNames, 1, columnNames.length));

        when(cantorDao.showColumnsForCurrency()).thenReturn(Arrays.asList(columnData, columnData, columnData, columnData, columnData));
        when(cantorDao.findAllCurrency()).thenReturn(Arrays.asList(currencyData));

        // when
        TableContent result = creator.createCurrencyTable();

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
        String[] columnNames = new String[]{"Pesel", "Name", "Family", "County", "City", "Street", "Block", "Flat", "Status", "Notes", "Phone", "Nip"};

        ColumnData columnData = mock(ColumnData.class);
        when(columnData.getName()).thenReturn(columnNames[0], Arrays.copyOfRange(columnNames, 1, columnNames.length));


        when(cantorDao.showColumnsForCustomer()).thenReturn(Arrays.asList(columnData, columnData, columnData, columnData, columnData, columnData,
                columnData, columnData, columnData, columnData, columnData, columnData));

        //when
        TableContent result = creator.createCustomerTable(pesel);

        //then
        assertThat(result)
                .isNotNull()
                .hasRowNumber(0)
                .hasColumnNumber(12)
                .is(containColumns(columnNames))
                .is(zeroRows());
    }

    @Test
    public void shouldCreateCustomerTableWithOneRow() throws DAOException {
        // given
        long pesel = 87122206592L;
        String[] columnNames = new String[]{"Pesel", "Name", "Family", "County", "City", "Street", "Block", "Flat", "Status", "Notes", "Phone", "Nip"};
        Object[] row = new Object[]{pesel, "Piotr", "Piotrowski", "Małopolsie", "Kraków", "Zdunów", "22c", 30L, "NEW", "OK", 889225169L, 6582514L};

        ColumnData columnData = mock(ColumnData.class);
        when(columnData.getName()).thenReturn(columnNames[0], Arrays.copyOfRange(columnNames, 1, columnNames.length));

        CustomerData customerData = mock(CustomerData.class);
        when(customerData.getArray()).thenReturn(row);

        when(cantorDao.showColumnsForCustomer()).thenReturn(Arrays.asList(columnData, columnData, columnData, columnData, columnData, columnData,
                columnData, columnData, columnData, columnData, columnData, columnData));
        when(cantorDao.findAllCustomers(pesel)).thenReturn(Arrays.asList(customerData));

        // when
        TableContent result = creator.createCustomerTable(pesel);

        // then
        assertThat(result)
                .isNotNull()
                .hasColumnNumber(12)
                .hasRowNumber(1)
                .is(containColumns(columnNames))
                .is(containsRow(row));
    }

    @Test
    public void shouldCreateEmptyCompanyTable() throws Exception {
        //given
        long krs = 311911L;

        String[] columnNames = new String[]{"Krs", "Name", "County", "City", "Street", "Block", "Flat", "Status", "Notes", "Regon", "Nip", "Phone", "Data_zalozenia"};

        ColumnData columnData = mock(ColumnData.class);
        when(columnData.getName()).thenReturn(columnNames[0], Arrays.copyOfRange(columnNames, 1, columnNames.length));

        when(cantorDao.showColumnsForCompany()).thenReturn(Arrays.asList(columnData, columnData, columnData, columnData, columnData, columnData,
                columnData, columnData, columnData, columnData, columnData, columnData, columnData));

        //when
        TableContent result = creator.createCompanyTable(krs);

        //then
        assertThat(result)
                .isNotNull()
                .hasRowNumber(0)
                .hasColumnNumber(13)
                .is(containColumns(columnNames))
                .is(zeroRows());
    }

    @Test
    public void shouldCreateCompanyTableWithOneRow() throws DAOException {
        // given
        long krs = 311911L;
        String[] columnNames = new String[]{"Krs", "Name", "County", "City", "Street", "Block", "Flat", "Status", "Notes", "Regon", "Nip", "Phone", "Data_zalozenia"};
        Object[] row = new Object[]{krs, "Sabre", "Małopolsie", "Kraków", "Zdunów", "22c", 30L, "NEW", "OK", 260259015L, 8641909961L, 889225169L, new Date()};

        ColumnData columnData = mock(ColumnData.class);
        when(columnData.getName()).thenReturn(columnNames[0], Arrays.copyOfRange(columnNames, 1, columnNames.length));

        CompanyData companyData = mock(CompanyData.class);
        when(companyData.getArray()).thenReturn(row);

        when(cantorDao.showColumnsForCompany()).thenReturn(Arrays.asList(columnData, columnData, columnData, columnData, columnData, columnData,
                columnData, columnData, columnData, columnData, columnData, columnData, columnData));
        when(cantorDao.findAllCompanies(krs)).thenReturn(Arrays.asList(companyData));

        // when
        TableContent result = creator.createCompanyTable(krs);

        // then
        assertThat(result)
                .isNotNull()
                .hasColumnNumber(13)
                .hasRowNumber(1)
                .is(containColumns(columnNames))
                .is(containsRow(row));
    }
}
