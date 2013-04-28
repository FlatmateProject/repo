package service.statistic;

import assertions.DiagramBarsAssert;
import dao.StatisticDao;
import dictionary.MONTH;
import dto.statictic.*;
import exception.DAOException;
import org.apache.log4j.Logger;
import org.fest.assertions.Condition;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.statictic.REPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticReport;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static conditions.raport.DoubleCondition.*;
import static conditions.raport.IntegerCondition.*;
import static conditions.raport.LongCondition.bodyContainSummaryTime;
import static conditions.raport.MonthCondition.headerContainMonth;
import static conditions.raport.PeriodOfMonthsCondition.headerContainPeriodOfMonths;
import static conditions.raport.PeriodOfYearsCondition.headerContainPeriodOfYears;
import static conditions.raport.StringCondition.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class StatisticMockitoTest {

    @Mock
    StatisticDao statisticDao;

    private static final Logger log = Logger.getLogger(StatisticMockitoTest.class);

    private final int year = 2012;

    private final MONTH month = MONTH.September;

    private final String roomType = "jednoosobowy";

    private final String serviceTypeName = "wynajem";

    private final int yearTo = 2012;

    private Statistic statistic;

    @BeforeMethod
    public void beforeEachTest() {
        initMocks(this);
        statistic = new Statistic();
        statistic.setStatisticDao(statisticDao);
    }

    @Test
    public void shouldCreateEmptyRoomTypesReport() throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.HOTEL_ROOM_TYPES;
        List<RoomTypeStatisticData> inputData = Collections.emptyList();

        when(statisticDao.findRoomTypesStatistics(month.numberCountedFromOne(), year)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.hotel(reportKind, month, year, roomType, serviceTypeName);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(headerContainMonth(month))//
                .is(headerContainYear(year))//
                .isNot(footerContainLegend());
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array).isNull();
    }

    @Test
    public void shouldCreateRoomTypesReport() throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.HOTEL_ROOM_TYPES;
        float summaryGain = 100.0f;
        int numberOccupiedRooms = 2;
        float unitGain = summaryGain / numberOccupiedRooms;
        int expectedNumberOfBars = 2;

        RoomTypeStatisticData row = mock(RoomTypeStatisticData.class);
        when(row.getRoomTypeName()).thenReturn(roomType);
        when(row.getNumberOccupiedRooms()).thenReturn(numberOccupiedRooms);
        when(row.getSummaryGain()).thenReturn(summaryGain);

        List<RoomTypeStatisticData> inputData = Arrays.asList(row);

        when(statisticDao.findRoomTypesStatistics(month.numberCountedFromOne(), year)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.hotel(reportKind, month, year, roomType, serviceTypeName);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(headerContainMonth(month))//
                .is(headerContainYear(year))//
                .is(footerContainLegend())//
                .is(bodyContainSummaryGain(summaryGain))//
                .is(bodyContainNumberOccupiedRooms(numberOccupiedRooms))//
                .is(bodyContainUnitGain(unitGain));
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array)
                .isNotNull()
                .hasSize(1);
        DiagramBarsAssert.assertThat(array[0])
                .isNotNull()
                .hasSize(expectedNumberOfBars)
                .isSummaryGainEqualTo(summaryGain)
                .isUnitGainEqualTo(unitGain);
    }

    @Test
    public void shouldCreateEmptyRoomsInTypeReport() throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.HOTEL_ROOMS;
        String roomType = "jednoosobowy";
        List<RoomStatisticData> inputData = Collections.emptyList();

        when(statisticDao.findRoomsStatisticsByType(month.numberCountedFromOne(), year, roomType)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.hotel(reportKind, month, year, roomType, serviceTypeName);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(headerContainMonth(month))//
                .is(headerContainYear(year))//
                .is(headerContainRoomType(roomType))//
                .isNot(footerContainLegend());
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array).isNull();
    }

    @Test
    public void shouldCreateRoomsInTypeReport() throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.HOTEL_ROOMS;
        String roomType = "jednoosobowy";
        float summaryGain = 100.0f;
        int occupationNumber = 2;
        float unitGain = summaryGain / occupationNumber;
        int expectedNumberOfBars = 2;
        long roomId = 1;

        RoomStatisticData row = mock(RoomStatisticData.class);
        when(row.getRoomId()).thenReturn(roomId);
        when(row.getOccupationNumber()).thenReturn(occupationNumber);
        when(row.getSummaryGain()).thenReturn(summaryGain);

        List<RoomStatisticData> inputData = Arrays.asList(row);

        when(statisticDao.findRoomsStatisticsByType(month.numberCountedFromOne(), year, roomType)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.hotel(reportKind, month, year, roomType, serviceTypeName);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(headerContainMonth(month))//
                .is(headerContainYear(year))//
                .is(footerContainLegend())//
                .is(bodyContainSummaryGain(summaryGain))//
                .is(bodyContainOccupationNumber(occupationNumber))//
                .is(bodyContainUnitGain(unitGain));
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array)
                .isNotNull()
                .hasSize(1);
        DiagramBarsAssert.assertThat(array[0])
                .isNotNull()
                .hasSize(expectedNumberOfBars)
                .isSummaryGainEqualTo(summaryGain)
                .isUnitGainEqualTo(unitGain);
    }

    @Test
    public void shouldCreateEmptyServiceTypesReport() throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.HOTEL_SERVICE_TYPES;
        List<ServiceTypeData> inputData = Collections.emptyList();

        when(statisticDao.findServiceTypesStatistics(month.numberCountedFromOne(), year)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.hotel(reportKind, month, year, roomType, serviceTypeName);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(headerContainMonth(month))//
                .is(headerContainYear(year))//
                .isNot(footerContainLegend());
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array).isNull();
    }

    @Test
    public void shouldCreateServiceTypesReport() throws Exception {
        // given
        REPORT_KIND reportKind = REPORT_KIND.HOTEL_SERVICE_TYPES;
        float summaryGain = 100.0f;
        int useNumber = 2;
        float unitGain = summaryGain / useNumber;
        int expectedNumberOfBars = 2;
        long summaryTime = 10;

        ServiceTypeData row = mock(ServiceTypeData.class);
        when(row.getSummaryTime()).thenReturn(summaryTime);
        when(row.getTypeName()).thenReturn(serviceTypeName);
        when(row.getSummaryGain()).thenReturn(summaryGain);

        List<ServiceTypeData> inputData = Arrays.asList(row);

        when(statisticDao.findServiceTypesStatistics(month.numberCountedFromOne(), year)).thenReturn(inputData);
        when(statisticDao.countUseNumberForServiceType(serviceTypeName)).thenReturn(useNumber);

        // when
        StatisticReport report = statistic.hotel(reportKind, month, year, roomType, serviceTypeName);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(headerContainMonth(month))//
                .is(headerContainYear(year))//
                .is(footerContainLegend())//
                .is(bodyContainSummaryTime(summaryTime))//
                .is(bodyContainSummaryGain(summaryGain))//
                .is(bodyContainUseNumber(useNumber))//
                .is(bodyContainUnitGain(unitGain));
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array)
                .isNotNull()
                .hasSize(1);
        DiagramBarsAssert.assertThat(array[0])
                .isNotNull()
                .hasSize(expectedNumberOfBars)
                .isSummaryGainEqualTo(summaryGain)
                .isUnitGainEqualTo(unitGain);
    }

    @Test
    public void shouldCreateEmptyServiceReport() throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.HOTEL_SERVICE;
        String serviceTypeName = "wynajem";
        List<ServiceStatisticData> inputData = Collections.emptyList();

        when(statisticDao.findServiceStatisticsByType(month.numberCountedFromOne(), year, serviceTypeName)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.hotel(reportKind, month, year, roomType, serviceTypeName);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(headerContainMonth(month))//
                .is(headerContainYear(year))//
                .is(headerContainServiceType(serviceTypeName))//
                .isNot(footerContainLegend());
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array).isNull();
    }

    @Test
    public void shouldCreateServiceReport() throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.HOTEL_SERVICE;
        String serviceTypeName = "wynajem";
        float summaryGain = 100.0f;
        int useNumber = 2;
        float unitGain = summaryGain / useNumber;
        String serviceName = "jednosobosy";
        long summaryTime = 10;
        int expectedNumberOfBars = 2;

        ServiceStatisticData row = mock(ServiceStatisticData.class);
        when(row.getSummaryTime()).thenReturn(summaryTime);
        when(row.getServiceName()).thenReturn(serviceName);
        when(row.getSummaryGain()).thenReturn(summaryGain);

        List<ServiceStatisticData> inputData = Arrays.asList(row);

        when(statisticDao.findServiceStatisticsByType(month.numberCountedFromOne(), year, serviceTypeName)).thenReturn(inputData);
        when(statisticDao.countUseNumberForServiceName(serviceName)).thenReturn(useNumber);

        // when
        StatisticReport report = statistic.hotel(reportKind, month, year, roomType, serviceTypeName);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(headerContainMonth(month))//
                .is(headerContainYear(year))//
                .is(footerContainLegend())//
                .is(bodyContainSummaryTime(summaryTime))//
                .is(bodyContainSummaryGain(summaryGain))//
                .is(bodyContainUseNumber(useNumber))//
                .is(bodyContainUnitGain(unitGain));
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array).isNotNull().hasSize(1);
        assertThat(array[0])
                .isNotNull()
                .hasSize(expectedNumberOfBars);
        DiagramBarsAssert.assertThat(array[0])
                .isNotNull()
                .hasSize(expectedNumberOfBars)
                .isSummaryGainEqualTo(summaryGain)
                .isUnitGainEqualTo(unitGain);
    }

    @Test(dataProvider = "prepareCasesForFinanceMonthReport")
    public void shouldCreateEmptyFinanceMonthReportWithCorrectTitle(MONTH monthFrom, MONTH monthTo, Condition<String> monthCondition) throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.FINANCE_MONTH;

        List<MonthSummaryGainData> inputData = Collections.emptyList();

        when(statisticDao.findMonthSummaryGains(monthFrom.numberCountedFromOne(), monthTo.numberCountedFromOne(), year)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.finance(reportKind, monthFrom, monthTo, year, yearTo);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(monthCondition)//
                .is(headerContainYear(year))//
                .isNot(footerContainLegend());
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array).isNull();
    }

    @DataProvider
    public static Object[][] prepareCasesForFinanceMonthReport() {
        return new Object[][]{//
                {MONTH.September, MONTH.December, headerContainPeriodOfMonths(MONTH.September, MONTH.December)},//
                {MONTH.September, MONTH.September, headerContainMonth(MONTH.September)},//
                {MONTH.September, MONTH.May, headerContainPeriodOfMonths(MONTH.May, MONTH.September)},//
        };
    }

    @Test
    public void shouldCreateFinanceMonthReportWithCorrectTitle() throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.FINANCE_MONTH;
        MONTH monthFrom = MONTH.September;
        MONTH monthTo = MONTH.May;
        int expectedNumberOfBars = 4;
        double reservationSummaryGain = 100.0;
        double serviceSummaryGain = 200.0;
        double cantorSummaryGain = 300.0;
        double summaryGain = reservationSummaryGain + serviceSummaryGain + cantorSummaryGain;

        MonthSummaryGainData row = mock(MonthSummaryGainData.class);
        when(row.getMonth()).thenReturn(monthTo.numberCountedFromOne());
        when(row.getReservationSummaryGain()).thenReturn(reservationSummaryGain);
        when(row.getServiceSummaryGain()).thenReturn(serviceSummaryGain);
        when(row.getCantorSummaryGain()).thenReturn(cantorSummaryGain);

        List<MonthSummaryGainData> inputData = Arrays.asList(row);

        when(statisticDao.findMonthSummaryGains(monthTo.numberCountedFromOne(), monthFrom.numberCountedFromOne(), year)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.finance(reportKind, monthFrom, monthTo, year, yearTo);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport).isNotNull()//
                .is(headerContainPeriodOfMonths(monthTo, monthFrom))//
                .is(headerContainYear(year))//
                .is(footerContainLegend())//
                .is(bodyContainReservationSummaryGain(reservationSummaryGain))//
                .is(bodyContainServiceSummaryGain(serviceSummaryGain))//
                .is(bodyContainCantorSummaryGain(cantorSummaryGain))//
                .is(bodyContainHotelSummaryGain(summaryGain));
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array)
                .isNotNull()
                .hasSize(1);
        DiagramBarsAssert.assertThat(array[0])
                .isNotNull()
                .hasSize(expectedNumberOfBars)
                .isReservationSummaryGainEqualTo(reservationSummaryGain)
                .isServiceSummaryGainEqualTo(serviceSummaryGain)//
                .isCantorSummaryGainEqualTo(cantorSummaryGain)//
                .isHotelSummaryGainEqualTo(summaryGain);
    }

    @Test(dataProvider = "prepareCasesForFinanceYearReport")
    public void shouldCreateEmptyFinanceYearReportWithCorrectTitle(int yearFrom, int yearTo, Condition<String> yearCondition) throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.FINANCE_YEAR;

        List<YearSummaryGainData> inputData = Collections.emptyList();

        when(statisticDao.findYearSummaryGains(yearFrom, yearTo)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.finance(reportKind, null, null, yearFrom, yearTo);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport)//
                .isNotNull()//
                .is(yearCondition)//
                .isNot(footerContainLegend());
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array).isNull();
    }

    @DataProvider
    public static Object[][] prepareCasesForFinanceYearReport() {
        return new Object[][]{//
                {2012, 2013, headerContainPeriodOfYears(2012, 2013)},//
                {2012, 2012, headerContainYear(2012)},//
                {2012, 2011, headerContainPeriodOfYears(2011, 2012)},//
        };
    }

    @Test
    public void shouldCreateFinanceYearReportWithCorrectTitle() throws DAOException {
        // given
        REPORT_KIND reportKind = REPORT_KIND.FINANCE_YEAR;
        int yearFrom = 2013;
        int yearTo = 2012;
        int expectedNumberOfBars = 4;
        double reservationSummaryGain = 100.0;
        double serviceSummaryGain = 200.0;
        double cantorSummaryGain = 300.0;
        double summaryGain = reservationSummaryGain + serviceSummaryGain + cantorSummaryGain;

        YearSummaryGainData row = mock(YearSummaryGainData.class);
        when(row.getYear()).thenReturn(yearTo);
        when(row.getReservationSummaryGain()).thenReturn(reservationSummaryGain);
        when(row.getServiceSummaryGain()).thenReturn(serviceSummaryGain);
        when(row.getCantorSummaryGain()).thenReturn(cantorSummaryGain);

        List<YearSummaryGainData> inputData = Arrays.asList(row);

        when(statisticDao.findYearSummaryGains(yearTo, yearFrom)).thenReturn(inputData);

        // when
        StatisticReport report = statistic.finance(reportKind, null, null, yearFrom, yearTo);

        // then
        assertThat(report).isNotNull();
        assertThat(report.getReportKind()).isEqualTo(reportKind);
        String textReport = report.getTextResult();
        assertThat(textReport).isNotNull()//
                .is(headerContainPeriodOfYears(yearTo, yearFrom))//
                .is(footerContainLegend())//
                .is(bodyContainReservationSummaryGain(reservationSummaryGain))//
                .is(bodyContainServiceSummaryGain(serviceSummaryGain))//
                .is(bodyContainCantorSummaryGain(cantorSummaryGain))//
                .is(bodyContainHotelSummaryGain(summaryGain));
        log.info(textReport);

        double[][] array = report.getArrayResult();
        assertThat(array)
                .isNotNull()
                .hasSize(1);
        DiagramBarsAssert.assertThat(array[0])
                .isNotNull().
                hasSize(expectedNumberOfBars)
                .isReservationSummaryGainEqualTo(reservationSummaryGain)
                .isServiceSummaryGainEqualTo(serviceSummaryGain)//
                .isCantorSummaryGainEqualTo(cantorSummaryGain)//
                .isHotelSummaryGainEqualTo(summaryGain);
    }
}
