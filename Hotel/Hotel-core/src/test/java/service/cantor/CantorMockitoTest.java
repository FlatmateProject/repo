package service.cantor;

import entity.CurrencyData;
import entity.CurrencyExchangeData;
import exception.CantorTransactionCanceledException;
import exception.DAOException;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import repository.CurrencyExchangeRepository;
import repository.CurrencyRepository;

import static assertions.CurrencyExchangeDataAssertion.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CantorMockitoTest {

    @Mock
    CurrencyRepository currencyRepositoryMock;

    @Mock
    CurrencyExchangeRepository currencyExchangeRepositoryMock;

    CantorMoneyExchanger exchanger;

    @BeforeMethod
    public void beforeEachTest() {
        initMocks(this);
        exchanger = new CantorMoneyExchanger();
        exchanger.setCurrencyExchangeRepository(currencyExchangeRepositoryMock);
        exchanger.setCurrencyRepository(currencyRepositoryMock);
    }

    @Test(dataProvider = "prepareCasesForCalculateMoneyExchange")
    public void shouldCalculateMoneyExchangeForGivenCurrency(CURRENCY saleCurrency, CURRENCY buyCurrency, float amount, float cost, float gain,
                                                             float buyPriceForSaleCurrency, float salePriceForSaleCurrency, float salePriceForBuyCurrency) throws DAOException {
        // given
        CurrencyData saleCurrencyData = mock(CurrencyData.class);
        when(saleCurrencyData.getName()).thenReturn(saleCurrency.name());
        when(saleCurrencyData.getBuyPrice()).thenReturn(buyPriceForSaleCurrency);
        when(saleCurrencyData.getSalePrice()).thenReturn(salePriceForSaleCurrency);

        CurrencyData buyCurrencyData = mock(CurrencyData.class);
        when(buyCurrencyData.getName()).thenReturn(buyCurrency.name());
        when(buyCurrencyData.getSalePrice()).thenReturn(salePriceForBuyCurrency);

        when(currencyRepositoryMock.findByName(buyCurrency.name())).thenReturn(buyCurrencyData);
        when(currencyRepositoryMock.findByName(saleCurrency.name())).thenReturn(saleCurrencyData);
        when(currencyRepositoryMock.save(buyCurrencyData)).thenReturn(buyCurrencyData);
        when(currencyRepositoryMock.save(saleCurrencyData)).thenReturn(saleCurrencyData);

        // when
        CurrencyExchangeData currencyExchangeData = exchanger.calculateExchange(saleCurrency, buyCurrency, amount);

        // then
        assertThat(currencyExchangeData)
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

        long clientId = 87122235514L;
        float amount = 100;
        float cost = 300;
        float gain = 100;
        long buyingCurrencyId = 1;
        long sellingCurrencyId = 3;

        CurrencyData sellingCurrency = mock(CurrencyData.class);
        when(sellingCurrency.getCurrencyId()).thenReturn(sellingCurrencyId);

        CurrencyData buyingCurrency = mock(CurrencyData.class);
        when(buyingCurrency.getCurrencyId()).thenReturn(buyingCurrencyId);

        CurrencyExchangeData calculation = mock(CurrencyExchangeData.class);
        when(calculation.getAmount()).thenReturn(amount);
        when(calculation.getCost()).thenReturn(cost);
        when(calculation.getGain()).thenReturn(gain);
        when(calculation.getSellingCurrency()).thenReturn(sellingCurrency);
        when(calculation.getBuyingCurrency()).thenReturn(buyingCurrency);
        when(calculation.getClientId()).thenReturn(clientId);
        when(calculation.isCustomer()).thenReturn(true);

        when(currencyExchangeRepositoryMock.save(calculation)).thenReturn(calculation);

        // when
        exchanger.exchangeMoney(calculation);

        // then
        verify(currencyExchangeRepositoryMock).save(calculation);
        verify(currencyRepositoryMock).save(sellingCurrency);
        verify(currencyRepositoryMock).save(buyingCurrency);
    }

    @Test
    public void shouldCommitMoneyExchangeTransactionForCompany() throws CantorTransactionCanceledException, DAOException {
        // given
        long clientId = 87122235514L;
        float amount = 100;
        float cost = 300;
        float gain = 100;
        long buyingCurrencyId = 1;
        long sellingCurrencyId = 3;

        CurrencyData sellingCurrency = mock(CurrencyData.class);
        when(sellingCurrency.getCurrencyId()).thenReturn(sellingCurrencyId);

        CurrencyData buyingCurrency = mock(CurrencyData.class);
        when(buyingCurrency.getCurrencyId()).thenReturn(buyingCurrencyId);

        CurrencyExchangeData calculation = mock(CurrencyExchangeData.class);
        when(calculation.getAmount()).thenReturn(amount);
        when(calculation.getCost()).thenReturn(cost);
        when(calculation.getGain()).thenReturn(gain);
        when(calculation.getSellingCurrency()).thenReturn(sellingCurrency);
        when(calculation.getBuyingCurrency()).thenReturn(buyingCurrency);
        when(calculation.getClientId()).thenReturn(clientId);
        when(calculation.isCompany()).thenReturn(true);

        when(currencyExchangeRepositoryMock.save(calculation)).thenReturn(calculation);

        // when
        exchanger.exchangeMoney(calculation);

        // then
        verify(currencyExchangeRepositoryMock).save(calculation);
        verify(currencyRepositoryMock).save(sellingCurrency);
        verify(currencyRepositoryMock).save(buyingCurrency);
    }

    @Test(expectedExceptions = CantorTransactionCanceledException.class)
    public void shouldNotCommitMoneyExchangeTransactionWithoutClient() throws CantorTransactionCanceledException {
        // given
        CurrencyExchangeData calculation = mock(CurrencyExchangeData.class);
        when(calculation.isCustomer()).thenReturn(false);
        when(calculation.isCompany()).thenReturn(false);

        // when
        exchanger.exchangeMoney(calculation);

        // then
        verifyZeroInteractions(currencyExchangeRepositoryMock);
    }

    @Test(expectedExceptions = CantorTransactionCanceledException.class)
    public void shouldCancelMoneyExchangeTransactionWhenSomethingBadHappened() throws CantorTransactionCanceledException, DAOException {
        // given
        CurrencyExchangeData calculation = mock(CurrencyExchangeData.class);
        when(calculation.isCustomer()).thenReturn(true);

        when(currencyExchangeRepositoryMock.save(calculation)).thenReturn(null);

        // when
        exchanger.exchangeMoney(calculation);

        // then
        verifyZeroInteractions(currencyExchangeRepositoryMock);
    }
}
