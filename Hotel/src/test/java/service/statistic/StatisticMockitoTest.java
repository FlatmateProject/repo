package service.statistic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import service.dictionary.MONTH;
import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;
import dao.StaticticDao;
import dao.StaticticDaoImpl;
import dto.RoomData;
import dto.RoomTypesData;
import dto.ServiceData;
import dto.ServiceTypeData;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static assertions.DiagramBarsAssert.assertThat;
import static conditions.raport.contain.ValidMonthCondition.headerContainValidMonth;
import static conditions.raport.contain.ValidYearCondition.headerContainValidYear;
import static conditions.raport.contain.ShowLegendCondition.shownLegend;


public class StatisticMockitoTest {
	
	static final Logger log = Logger.getLogger(StatisticMockitoTest.class);
	
	int year = 2012;
	
	MONTH month = MONTH.September;
	
	String roomType = "jednoosobowy";

	String serviceTypeName = "wynajem";
	
	@Test
	public void shouldReturnEmptyRoomTypesRaport() {
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
		String textRaport = raport.getTextResult();
		assertThat(textRaport).isNotNull().is(headerContainValidMonth(month)).is(headerContainValidYear(year)).isNot(shownLegend());
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNull();
	}
	
	@Test
	public void shouldReturnRoomTypesRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOM_TYPES;
		float sumaryGain = 100.0f;
		int numberOccupiedRooms = 2;
		float unitGain = sumaryGain / numberOccupiedRooms;
		int expectedNumberOfElements = 2;
		
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
		String textRaport = raport.getTextResult();
		assertThat(textRaport).isNotNull().is(headerContainValidMonth(month)).is(headerContainValidYear(year)).is(shownLegend());
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNotNull().hasSize(1);
		assertThat(arrayResult[0]).hasSize(expectedNumberOfElements);
		assertThat(arrayResult[0]).isSumaryGainEqualTo(sumaryGain).isUnitGainEqualTo(unitGain);
	}

	@Test
	public void shouldReturnEmptyRoomsForTypeRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOM_TYPES;
		String roomType = "jednoosobowy";
		List<RoomData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomsByType(month.id(), year, roomType)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(new StaticticDaoImpl());
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);

		// then
		assertThat(raport).isNotNull();
		String textRaport = raport.getTextResult();
		assertThat(textRaport).isNotNull().is(headerContainValidMonth(month)).is(headerContainValidYear(year)).isNot(shownLegend());
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNull();
	}
	
	@Test
	public void shouldReturnRoomsForTypeRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOMS;
		String roomType = "jednoosobowy";
		float sumaryGain = 100.0f;
		int occupationNumber = 2;
		float unitGain = sumaryGain / occupationNumber;
		int expectedNumberOfElements = 2;
		long roomId = 1;

		RoomData row = mock(RoomData.class);
		when(row.getRoomId()).thenReturn(roomId);
		when(row.getNuberOccupiedRooms()).thenReturn(occupationNumber);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		
		List<RoomData> inputData = Arrays.asList(row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomsByType(month.id(), year, roomType)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);
		
		// then
		assertThat(raport).isNotNull();
		String textRaport = raport.getTextResult();
		assertThat(textRaport).isNotNull().is(headerContainValidMonth(month)).is(headerContainValidYear(year)).is(shownLegend());
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNotNull().hasSize(1);
		assertThat(arrayResult[0]).hasSize(expectedNumberOfElements);
		assertThat(arrayResult[0]).isSumaryGainEqualTo(sumaryGain).isUnitGainEqualTo(unitGain);
	}
	
	@Test
	public void shouldReturnEmptyServiceTypesRaport() {
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
		String textRaport = raport.getTextResult();
		assertThat(textRaport).isNotNull().is(headerContainValidMonth(month)).is(headerContainValidYear(year)).isNot(shownLegend());
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNull();
	}
	
	@Test
	public void shouldReturnServiceTypesRaport() throws Exception {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE_TYPES;
		float sumaryGain = 100.0f;
		int useNumber = 2;
		float unitGain = sumaryGain / useNumber;
		int expectedNumberOfElements = 2;
		long time = 10;

		ServiceTypeData row = mock(ServiceTypeData.class);
		when(row.getTime()).thenReturn(time);
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
		String textRaport = raport.getTextResult();
		assertThat(textRaport).isNotNull().is(headerContainValidMonth(month)).is(headerContainValidYear(year)).is(shownLegend());
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNotNull().hasSize(1);
		assertThat(arrayResult[0]).hasSize(expectedNumberOfElements);
		assertThat(arrayResult[0]).isSumaryGainEqualTo(sumaryGain).isUnitGainEqualTo(unitGain);
	}
	
	@Test
	public void shouldReturnEmptyServiceRaport() {
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
		String textRaport = raport.getTextResult();
		assertThat(textRaport).isNotNull().is(headerContainValidMonth(month)).is(headerContainValidYear(year)).isNot(shownLegend());
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNull();
	}
	
	@Test
	public void shouldReturnServiceRaport() throws Exception {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE;
		String serviceTypeName = "wynajem";
		float sumaryGain = 100.0f;
		int useNumber = 2;
		int expectedNumberOfElements = 2;
		float unitGain = sumaryGain / useNumber;
		String serviceName = "jednosobosy";
		long time = 10;
		
		ServiceData row = mock(ServiceData.class);
		when(row.getTime()).thenReturn(time);
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
		String textRaport = raport.getTextResult();
		assertThat(textRaport).isNotNull().is(headerContainValidMonth(month)).is(headerContainValidYear(year)).is(shownLegend());
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertThat(arrayResult).isNotNull().hasSize(1);
		assertThat(arrayResult[0]).hasSize(expectedNumberOfElements);
		assertThat(arrayResult[0]).isSumaryGainEqualTo(sumaryGain).isUnitGainEqualTo(unitGain);
	}
}
