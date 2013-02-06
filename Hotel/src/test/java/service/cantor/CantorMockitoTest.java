package service.cantor;

import dao.CantorDao;
import dto.CurrencyData;
import exception.CantorTransactionCanceledException;
import exception.DAOException;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static assertions.ExchangeCalculationAssertion.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CantorMockitoTest {

    @Mock
    CantorDao cantorDao;

    private CantorMoneyExchanger cantor;

    @BeforeMethod
    public void beforeEachTest() {
        initMocks(this);
        cantor = new CantorMoneyExchanger(cantorDao);
    }

    @Test(dataProvider = "prepareCasesForCalculateMoneyExchange")
    public void shouldCalculateMoneyExchangeForGivenCurrency(CURRENCY saleCurrency, CURRENCY buyCurrency, float amount, float cost, float gain,
                                                             float buyPriceForSaleCurrency, float salePriceForSaleCurrency, float salePriceForBuyCurrency) throws DAOException {
        // given
        CurrencyData saleCurrencyData = mock(CurrencyData.class);
        when(saleCurrencyData.getBuyPrice()).thenReturn(buyPriceForSaleCurrency);
        when(saleCurrencyData.getSalePrice()).thenReturn(salePriceForSaleCurrency);
        when(saleCurrencyData.asEnum()).thenReturn(saleCurrency);

        CurrencyData buyCurrencyData = mock(CurrencyData.class);
        when(buyCurrencyData.getSalePrice()).thenReturn(salePriceForBuyCurrency);
        when(buyCurrencyData.asEnum()).thenReturn(buyCurrency);

        when(cantorDao.findCurrencyByName(buyCurrency)).thenReturn(buyCurrencyData);
        when(cantorDao.findCurrencyByName(saleCurrency)).thenReturn(saleCurrencyData);

        // when
        ExchangeCalculation exchangeCalculation = cantor.calculateExchange(saleCurrency, buyCurrency, amount);

        // then
        assertThat(exchangeCalculation)
                .isNotNull()
                .isSaleCurrency(saleCurrency)
                .isBuyingCurrency(buyCurrency)
                .isAmount(amount)
                .isCost(cost)
                .isGain(gain);
    }

    @DataProvider
    public static Object[][] prepareCasesForCalculateMoneyExchange() {
        return new Object[][]{
                {CURRENCY.PLN, CURRENCY.EUR, 100.0f, 25.0f, 0.0f, 1.0f, 1.0f, 4.0f},
                {CURRENCY.EUR, CURRENCY.PLN, 100.0f, 300.0f, 100.0f, 3.0f, 4.0f, 1.0f},
                {CURRENCY.EUR, CURRENCY.USD, 100.0f, 150.0f, 100.0f, 3.0f, 4.0f, 2.0f},
                {CURRENCY.EUR, CURRENCY.EUR, 100.0f, 75.0f, 100.0f, 3.0f, 4.0f, 4.0f}
        };
    }

    @Test
    public void shouldCommitMoneyExchangeTransactionForCustomer() throws CantorTransactionCanceledException, DAOException {
        // given

        long clientId = 87122206592L;
        float amount = 100;
        float cost = 300;
        float gain = 100;
        long buyingCurrencyId = 1;
        long sellingCurrencyId = 3;

        CurrencyData sellingCurrency = mock(CurrencyData.class);
        when(sellingCurrency.getCurrencyId()).thenReturn(sellingCurrencyId);

        CurrencyData buyingCurrency = mock(CurrencyData.class);
        when(buyingCurrency.asEnum()).thenReturn(CURRENCY.EUR);
        when(buyingCurrency.getCurrencyId()).thenReturn(buyingCurrencyId);

        ExchangeCalculation calculation = mock(ExchangeCalculation.class);
        when(calculation.getAmount()).thenReturn(amount);
        when(calculation.getCost()).thenReturn(cost);
        when(calculation.getGain()).thenReturn(gain);
        when(calculation.getSellingCurrency()).thenReturn(sellingCurrency);
        when(calculation.getBuyingCurrency()).thenReturn(buyingCurrency);
        when(calculation.getClientId()).thenReturn(clientId);
        when(calculation.isCustomer()).thenReturn(true);

        // when
        cantor.exchangeMoney(calculation);

        // then
        verify(cantorDao).insertTransactionForCustomer(calculation);
        verify(cantorDao).updateCurrency(sellingCurrency);
        verify(cantorDao).updateCurrency(buyingCurrency);
    }

    @Test
    public void shouldCommitMoneyExchangeTransactionForCompany() throws CantorTransactionCanceledException, DAOException {
        // given
        long clientId = 87122206592L;
        float amount = 100;
        float cost = 300;
        float gain = 100;
        long buyingCurrencyId = 1;
        long sellingCurrencyId = 3;

        CurrencyData sellingCurrency = mock(CurrencyData.class);
        when(sellingCurrency.getCurrencyId()).thenReturn(sellingCurrencyId);

        CurrencyData buyingCurrency = mock(CurrencyData.class);
        when(buyingCurrency.asEnum()).thenReturn(CURRENCY.EUR);
        when(buyingCurrency.getCurrencyId()).thenReturn(buyingCurrencyId);

        ExchangeCalculation calculation = mock(ExchangeCalculation.class);
        when(calculation.getAmount()).thenReturn(amount);
        when(calculation.getCost()).thenReturn(cost);
        when(calculation.getGain()).thenReturn(gain);
        when(calculation.getSellingCurrency()).thenReturn(sellingCurrency);
        when(calculation.getBuyingCurrency()).thenReturn(buyingCurrency);
        when(calculation.getClientId()).thenReturn(clientId);
        when(calculation.isCompany()).thenReturn(true);

        // when
        cantor.exchangeMoney(calculation);

        // then
        verify(cantorDao).insertTransactionForCompany(calculation);
        verify(cantorDao).updateCurrency(sellingCurrency);
        verify(cantorDao).updateCurrency(buyingCurrency);
    }

    @Test
    public void shouldNotCommitMoneyExchangeTransactionWithoutClient() throws CantorTransactionCanceledException {
        // given
        ExchangeCalculation calculation = mock(ExchangeCalculation.class);
        when(calculation.isCustomer()).thenReturn(false);
        when(calculation.isCompany()).thenReturn(false);

        // when
        cantor.exchangeMoney(calculation);

        // then
    }

    @Test(expectedExceptions = CantorTransactionCanceledException.class)
    public void shouldCancelMoneyExchangeTransactionWhenSomethingBadHappened() throws CantorTransactionCanceledException, DAOException {
        // given
        ExchangeCalculation calculation = mock(ExchangeCalculation.class);
        when(calculation.isCustomer()).thenReturn(true);

        doThrow(new DAOException()).when(cantorDao).insertTransactionForCustomer(calculation);

        // when
        cantor.exchangeMoney(calculation);

        // then
        verifyNoMoreInteractions(cantorDao);
    }
}
