package service.statistic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.fest.assertions.Condition;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import service.dictionary.MONTH;
import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;
import dao.StaticticDao;
import dao.StaticticDaoImpl;
import dto.MonthSumaryGainData;
import dto.RoomData;
import dto.RoomTypesData;
import dto.ServiceData;
import dto.ServiceTypeData;
import static assertions.DiagramBarsAssert.assertThat;
import static conditions.raport.contain.DoubleCondition.bodyContainCantorSumaryGain;
import static conditions.raport.contain.DoubleCondition.bodyContainHotelSumaryGain;
import static conditions.raport.contain.DoubleCondition.bodyContainReservationSumaryGain;
import static conditions.raport.contain.DoubleCondition.bodyContainSeviceSumaryGain;
import static conditions.raport.contain.DoubleCondition.bodyContainSumaryGain;
import static conditions.raport.contain.DoubleCondition.bodyContainUnitGain;
import static conditions.raport.contain.IntegerCondition.bodyContainNumberOccupiedRooms;
import static conditions.raport.contain.IntegerCondition.bodyContainOccupationNumber;
import static conditions.raport.contain.IntegerCondition.bodyContainUseNumber;
import static conditions.raport.contain.IntegerCondition.headerContainYear;
import static conditions.raport.contain.LongCondition.bodyContainSumaryTime;
import static conditions.raport.contain.MonthCondition.headerContainMonth;
import static conditions.raport.contain.PeriodOfMonthsCondition.headerContainPeriodOfMonths;
import static conditions.raport.contain.StringCondition.footerContainLegend;
import static conditions.raport.contain.StringCondition.headerContainRoomType;
import static conditions.raport.contain.StringCondition.headerContainServiceType;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatisticMockitoTest {
	
	static final Logger log = Logger.getLogger(StatisticMockitoTest.class);
	
	int year = 2012;
	
	MONTH month = MONTH.September;
	
	String roomType = "jednoosobowy";

	String serviceTypeName = "wynajem";

	int yearTo = 2012;
	
	@Test
	public void shouldCreateEmptyRoomTypesRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOM_TYPES;
		List<RoomTypesData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomTypes(month.id(), year)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(new StaticticDaoImpl());
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);

		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainMonth(month))//
				.is(headerContainYear(year))//
				.isNot(footerContainLegend());
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNull();
	}
	
	@Test
	public void shouldCreateRoomTypesRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOM_TYPES;
		float sumaryGain = 100.0f;
		int numberOccupiedRooms = 2;
		float unitGain = sumaryGain / numberOccupiedRooms;
		int expectedNumberOfBars = 2;
		
		RoomTypesData row = mock(RoomTypesData.class);
		when(row.getRoomTypeName()).thenReturn(roomType);
		when(row.getNuberOccupiedRooms()).thenReturn(numberOccupiedRooms);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		
		List<RoomTypesData> inputData = Arrays.asList(row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomTypes(month.id(), year)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);
		
		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainMonth(month))//
				.is(headerContainYear(year))//
				.is(footerContainLegend())//
				.is(bodyContainSumaryGain(sumaryGain))//
				.is(bodyContainNumberOccupiedRooms(numberOccupiedRooms))//
				.is(bodyContainUnitGain(unitGain));
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNotNull().hasSize(1);
		assertThat(array[0]).hasSize(expectedNumberOfBars);
		assertThat(array[0]).isSumaryGainEqualTo(sumaryGain).isUnitGainEqualTo(unitGain);
	}

	@Test
	public void shouldCreateEmptyRoomsInTypeRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOMS;
		String roomType = "jednoosobowy";
		List<RoomData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomsByType(month.id(), year, roomType)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(new StaticticDaoImpl());
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);

		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainMonth(month))//
				.is(headerContainYear(year))//
				.is(headerContainRoomType(roomType))//
				.isNot(footerContainLegend());
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNull();
	}
	
	@Test
	public void shouldCreateRoomsInTypeRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOMS;
		String roomType = "jednoosobowy";
		float sumaryGain = 100.0f;
		int occupationNumber = 2;
		float unitGain = sumaryGain / occupationNumber;
		int expectedNumberOfBars = 2;
		long roomId = 1;

		RoomData row = mock(RoomData.class);
		when(row.getRoomId()).thenReturn(roomId);
		when(row.getOccupationNumber()).thenReturn(occupationNumber);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		
		List<RoomData> inputData = Arrays.asList(row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomsByType(month.id(), year, roomType)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);
		
		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainMonth(month))//
				.is(headerContainYear(year))//
				.is(footerContainLegend())//
				.is(bodyContainSumaryGain(sumaryGain))//
				.is(bodyContainOccupationNumber(occupationNumber))//
				.is(bodyContainUnitGain(unitGain));
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNotNull().hasSize(1);
		assertThat(array[0]).hasSize(expectedNumberOfBars);
		assertThat(array[0]).isSumaryGainEqualTo(sumaryGain).isUnitGainEqualTo(unitGain);
	}
	
	@Test
	public void shouldCreateEmptyServiceTypesRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE_TYPES;
		List<ServiceTypeData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findServiceTypes(month.id(), year)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(new StaticticDaoImpl());
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);

		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainMonth(month))//
				.is(headerContainYear(year))//
				.isNot(footerContainLegend());
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNull();
	}
	
	@Test
	public void shouldCreateServiceTypesRaport() throws Exception {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE_TYPES;
		float sumaryGain = 100.0f;
		int useNumber = 2;
		float unitGain = sumaryGain / useNumber;
		int expectedNumberOfBars = 2;
		long sumaryTime = 10;

		ServiceTypeData row = mock(ServiceTypeData.class);
		when(row.getSummaryTime()).thenReturn(sumaryTime);
		when(row.getTypeName()).thenReturn(serviceTypeName);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		
		List<ServiceTypeData> inputData = Arrays.asList(row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findServiceTypes(month.id(), year)).thenReturn(inputData);
		when(staticticDao.countUseNumberForServiceType(serviceTypeName)).thenReturn(useNumber);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);
		
		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainMonth(month))//
				.is(headerContainYear(year))//
				.is(footerContainLegend())//
				.is(bodyContainSumaryTime(sumaryTime))//
				.is(bodyContainSumaryGain(sumaryGain))//
				.is(bodyContainUseNumber(useNumber))//
				.is(bodyContainUnitGain(unitGain));
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNotNull().hasSize(1);
		assertThat(array[0]).hasSize(expectedNumberOfBars);
		assertThat(array[0]).isSumaryGainEqualTo(sumaryGain).isUnitGainEqualTo(unitGain);
	}
	
	@Test
	public void shouldCreateEmptyServiceRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE;
		String serviceTypeName = "wynajem";
		List<ServiceData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findServiceByType(month.id(), year, serviceTypeName)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(new StaticticDaoImpl());
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);

		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainMonth(month))//
				.is(headerContainYear(year))//
				.is(headerContainServiceType(serviceTypeName))//
				.isNot(footerContainLegend());
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNull();
	}
	
	@Test
	public void shouldCreateServiceRaport() throws Exception {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE;
		String serviceTypeName = "wynajem";
		float sumaryGain = 100.0f;
		int useNumber = 2;
		float unitGain = sumaryGain / useNumber;
		String serviceName = "jednosobosy";
		long sumaryTime = 10;
		int expectedNumberOfBars = 2;
		
		ServiceData row = mock(ServiceData.class);
		when(row.getSummaryTime()).thenReturn(sumaryTime);
		when(row.getServiceName()).thenReturn(serviceName);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		
		List<ServiceData> inputData = Arrays.asList(row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findServiceByType(month.id(), year, serviceTypeName)).thenReturn(inputData);
		when(staticticDao.countUseNumberForServiceName(serviceName)).thenReturn(useNumber);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);
		
		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(headerContainMonth(month))//
				.is(headerContainYear(year))//
				.is(footerContainLegend())//
				.is(bodyContainSumaryTime(sumaryTime))//
				.is(bodyContainSumaryGain(sumaryGain))//
				.is(bodyContainUseNumber(useNumber))//
				.is(bodyContainUnitGain(unitGain));
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNotNull().hasSize(1);
		assertThat(array[0]).isNotNull().hasSize(expectedNumberOfBars);
		assertThat(array[0]).isSumaryGainEqualTo(sumaryGain).isUnitGainEqualTo(unitGain);
	}
	
	@Test(dataProvider = "prepareCasesForFinanceMonthRaport")
	public void shouldCreateEmptyFinanceMonthRaportWithCorrectTitle(MONTH monthFrom, MONTH monthTo, Condition<String> monthCodnition) {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.FINANCE_MONTH;
		
		List<MonthSumaryGainData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findMonthSumaryGains(monthFrom.id(), monthTo.id(), year)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.finance(raportKind, monthFrom, monthTo, year, yearTo);
		
		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport)//
				.isNotNull()//
				.is(monthCodnition)//
				.is(headerContainYear(year))//
				.isNot(footerContainLegend());
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNull();
	}
	
	@DataProvider
	public static Object[][] prepareCasesForFinanceMonthRaport() {
		Object[][] datas = new Object[][] {//
				{ MONTH.September, MONTH.December,  headerContainPeriodOfMonths(MONTH.September, MONTH.December) },//
				{ MONTH.September, MONTH.September, headerContainMonth(MONTH.September) },//
				{ MONTH.September, MONTH.May,       headerContainPeriodOfMonths(MONTH.May, MONTH.September) },//
		};
		return datas;
	}
	
	@Test
	public void shouldCreateFinanceMonthRaportWithCorrectTitle() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.FINANCE_MONTH;
		MONTH monthFrom = MONTH.September;
		MONTH monthTo = MONTH.May;
		int expectedNumberOfBars = 4;
		double reservationSumaryGain = 100.0;
		double serviceSumaryGain = 200.0;
		double cantorSumaryGain = 300.0;
		double sumaryGain = reservationSumaryGain + serviceSumaryGain + cantorSumaryGain;
		
		MonthSumaryGainData row = mock(MonthSumaryGainData.class);
		when(row.getMonth()).thenReturn(monthTo.id());
		when(row.getReservationSumaryGain()).thenReturn(reservationSumaryGain);
		when(row.getServiceSumaryGain()).thenReturn(serviceSumaryGain);
		when(row.getCantorSumaryGain()).thenReturn(cantorSumaryGain);
		
		List<MonthSumaryGainData> inputData = Arrays.asList(row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findMonthSumaryGains(monthTo.id(), monthFrom.id(), year)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.finance(raportKind, monthFrom, monthTo, year, yearTo);
		
		// then
		assertThat(raport).isNotNull();
		assertThat(raport.getRaportKind()).isEqualTo(raportKind);
		String textRaport = raport.getTextResult();
		assertThat(textRaport).isNotNull()//
				.is(headerContainPeriodOfMonths(monthTo, monthFrom))//
				.is(headerContainYear(year))//
				.is(footerContainLegend())//
				.is(bodyContainReservationSumaryGain(reservationSumaryGain))//
				.is(bodyContainSeviceSumaryGain(serviceSumaryGain))//
				.is(bodyContainCantorSumaryGain(cantorSumaryGain))//
				.is(bodyContainHotelSumaryGain(sumaryGain));
		log.info(textRaport);
		
		double[][] array = raport.getArrayResult();
		assertThat(array).isNotNull().hasSize(1);
		assertThat(array[0]).isNotNull().hasSize(expectedNumberOfBars);
		assertThat(array[0]).isReservationSumaryGainEqualTo(reservationSumaryGain)
				.isServiceSumaryGainEqualTo(serviceSumaryGain)//
				.isCantorSumaryGainEqualTo(cantorSumaryGain)//
				.isHotelSumaryGainEqualTo(sumaryGain);
	}
	
}
