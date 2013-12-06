package service.cantor;

import common.TableContent;
import entity.CompanyData;
import entity.CurrencyData;
import entity.CustomerData;
import exception.DAOException;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import repository.CompanyRepository;
import repository.CurrencyRepository;
import repository.CustomerRepository;

import java.util.Arrays;
import java.util.Collections;
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
    CustomerRepository customerRepositoryMock;

    @Mock
    CurrencyRepository currencyRepositoryMock;

    @Mock
    CompanyRepository companyRepositoryMock;

    CantorTableCreator creator;

    @BeforeMethod
    public void beforeEachTest() {
        initMocks(this);
        creator = new CantorTableCreator();
        creator.setCustomerRepository(customerRepositoryMock);
        creator.setCurrencyRepository(currencyRepositoryMock);
        creator.setCompanyRepository(companyRepositoryMock);
    }

    @Test
    public void shouldCreateEmptyCurrencyTable() throws Exception {
        //given
        String[] columnNames = new String[]{"ID_WALUTY", "NAZWA", "CENA_SP", "CENA_KU", "ILOSC"};

        when(currencyRepositoryMock.findAll()).thenReturn(Collections.<CurrencyData>emptyList());

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
        String[] columnNames = new String[]{"ID_WALUTY", "NAZWA", "CENA_SP", "CENA_KU", "ILOSC"};
        CurrencyData currencyData = mock(CurrencyData.class);
        when(currencyData.getArray()).thenReturn(row);

        when(currencyRepositoryMock.findAll()).thenReturn(Arrays.asList(currencyData));

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
        long pesel = 87122235514L;
        String[] columnNames = new String[]{"IDK_PESEL", "IMIE", "NAZWISKO", "WOJEWODZTWO", "MIASTO", "ULICA", "BLOK", "NR_LOKALU", "STATUS", "UWAGI", "TELEFON", "NIP"};

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
        long pesel = 87122235514L;
        String[] columnNames = new String[]{"IDK_PESEL", "IMIE", "NAZWISKO", "WOJEWODZTWO", "MIASTO", "ULICA", "BLOK", "NR_LOKALU", "STATUS", "UWAGI", "TELEFON", "NIP"};
        Object[] row = new Object[]{pesel, "Piotr", "Piotrowski", "Małopolsie", "Kraków", "Zdunów", "22c", 30L, "NEW", "OK", 889225169L, 6582514L};

        CustomerData customerData = mock(CustomerData.class);
        when(customerData.getArray()).thenReturn(row);

        when(customerRepositoryMock.findOne(pesel)).thenReturn(customerData);

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

        String[] columnNames = new String[]{"IDF_KRS", "NAZWA", "WOJEWODZTWO", "MIASTO", "ULICA", "BLOK", "NR_LOKALU", "STATUS", "UWAGI", "REGON", "NIP", "TELEFON", "DATA_ZALOZENIA"};

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
        String[] columnNames = new String[]{"IDF_KRS", "NAZWA", "WOJEWODZTWO", "MIASTO", "ULICA", "BLOK", "NR_LOKALU", "STATUS", "UWAGI", "REGON", "NIP", "TELEFON", "DATA_ZALOZENIA"};
        Object[] row = new Object[]{krs, "Sabre", "Małopolsie", "Kraków", "Zdunów", "22c", 30L, "NEW", "OK", 260259015L, 8641909961L, 889225169L, new Date()};

        CompanyData companyData = mock(CompanyData.class);
        when(companyData.getArray()).thenReturn(row);

        when(companyRepositoryMock.findOne(krs)).thenReturn(companyData);

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
