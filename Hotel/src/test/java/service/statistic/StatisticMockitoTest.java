package service.statistic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import service.dictionary.MONTH;
import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;
import dao.StaticticDao;
import dto.RoomData;
import dto.RoomTypesData;
import dto.ServiceData;
import dto.ServiceTypeData;

public class StatisticMockitoTest {
	
	private static final Logger log = Logger.getLogger(StatisticMockitoTest.class);
	
	
	@Test
	public void shouldReturnEmptyRoomTypesRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOM_TYPES;
		int year = 2012;
		MONTH month = MONTH.September;
		String roomType = "jednoosobowy";
		String serviceTypeName = null;
		List<RoomTypesData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomTypes(month.id(), year)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic();
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);

		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNull(arrayResult);
	}
	
	@Test
	public void shouldReturnRoomTypesRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOM_TYPES;
		int year = 2012;
		MONTH month = MONTH.September;
		String roomType = "jednoosobowy";
		String serviceTypeName = null;
		float sumaryGain = 100.0f;
		int numberOccupatedRooms = 2;
		float unitGain = 50.0f;
		int expectedNumberOfElements = 2;
		

		RoomTypesData row = mock(RoomTypesData.class);
		when(row.getRoomTypeName()).thenReturn(roomType);
		when(row.getNuberOccupiedRooms()).thenReturn(numberOccupatedRooms);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		
		List<RoomTypesData> inputData = Arrays.asList(row, row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomTypes(month.id(), year)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);
		
		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNotNull(arrayResult);
		assertEquals(2, arrayResult.length);
		assertEquals(expectedNumberOfElements, arrayResult[0].length);
		assertEquals(sumaryGain, arrayResult[0][0], 0);
		assertEquals(expectedNumberOfElements, arrayResult[1].length);
		assertEquals(unitGain, arrayResult[0][1], 0);
	}

	@Test
	public void shouldReturnEmptyRoomsRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOM_TYPES;
		int year = 2012;
		MONTH month = MONTH.September;
		String roomType = "jednoosobowy";
		String serviceTypeName = null;
		List<RoomData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomsByType(month.id(), year, roomType)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic();
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);

		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNull(arrayResult);
	}
	
	@Test
	public void shouldReturnRoomsRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_ROOMS;
		int year = 2012;
		MONTH month = MONTH.September;
		String roomType = "jednoosobowy";
		String serviceTypeName = null;
		float sumaryGain = 100.0f;
		int numberOccupatedRooms = 2;
		float unitGain = 50.0f;
		int expectedNumberOfElements = 2;
		

		RoomData row = mock(RoomData.class);
		when(row.getRoomId()).thenReturn(generateRandomId());
		when(row.getNuberOccupiedRooms()).thenReturn(numberOccupatedRooms);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		
		List<RoomData> inputData = Arrays.asList(row, row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findRoomsByType(month.id(), year, roomType)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);
		
		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNotNull(arrayResult);
		assertEquals(2, arrayResult.length);
		assertEquals(expectedNumberOfElements, arrayResult[0].length);
		assertEquals(sumaryGain, arrayResult[0][0], 0);
		assertEquals(expectedNumberOfElements, arrayResult[1].length);
		assertEquals(unitGain, arrayResult[0][1], 0);
	}
	
	@Test
	public void shouldReturnEmptyServiceTypesRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE_TYPES;
		int year = 2012;
		MONTH month = MONTH.September;
		String roomType = null;
		String serviceTypeName = null;
		List<ServiceTypeData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findServiceTypes(month.id(), year)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic();
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);

		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNull(arrayResult);
	}
	
	@Test
	public void shouldReturnServiceTypesRaport() throws Exception {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE_TYPES;
		int year = 2012;
		MONTH month = MONTH.September;
		String roomType = null;
		String serviceTypeName = "wynajem";
		float sumaryGain = 100.0f;
		float unitGain = 50.0f;
		int expectedNumberOfElements = 2;
		int useNumber = 2;
		

		ServiceTypeData row = mock(ServiceTypeData.class);
		when(row.getTime()).thenReturn(generateRandomId());
		when(row.getTypeName()).thenReturn(serviceTypeName);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		
		List<ServiceTypeData> inputData = Arrays.asList(row, row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findServiceTypes(month.id(), year)).thenReturn(inputData);
		when(staticticDao.countUseNumberForServiceType(serviceTypeName)).thenReturn(useNumber);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);
		
		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNotNull(arrayResult);
		assertEquals(2, arrayResult.length);
		assertEquals(expectedNumberOfElements, arrayResult[0].length);
		assertEquals(sumaryGain, arrayResult[0][0], 0);
		assertEquals(expectedNumberOfElements, arrayResult[1].length);
		assertEquals(unitGain, arrayResult[0][1], 0);
	}
	
	@Test
	public void shouldReturnEmptyServiceRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE;
		int year = 2012;
		MONTH month = MONTH.September;
		String roomType = null;
		String serviceTypeName = "wynajem";
		List<ServiceData> inputData = Collections.emptyList();
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findServiceByType(month.id(), year, serviceTypeName)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic();
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);

		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNull(arrayResult);
	}
	
	@Test
	public void shouldReturnServiceRaport() throws Exception {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_SERVICE;
		int year = 2012;
		MONTH month = MONTH.September;
		String roomType = null;
		String serviceTypeName = "wynajem";
		float sumaryGain = 100.0f;
		float unitGain = 50.0f;
		int expectedNumberOfElements = 2;
		String serviceName = "jednosobosy";
		int useNumber = 2;
		
		ServiceData row = mock(ServiceData.class);
		when(row.getTime()).thenReturn(generateRandomId());
		when(row.getServiceName()).thenReturn(serviceName);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		
		List<ServiceData> inputData = Arrays.asList(row, row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findServiceByType(month.id(), year, serviceTypeName)).thenReturn(inputData);
		when(staticticDao.countUseNumberForServiceName(serviceName)).thenReturn(useNumber);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, roomType, serviceTypeName);
		
		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNotNull(arrayResult);
		assertEquals(2, arrayResult.length);
		assertEquals(expectedNumberOfElements, arrayResult[0].length);
		assertEquals(sumaryGain, arrayResult[0][0], 0);
		assertEquals(expectedNumberOfElements, arrayResult[1].length);
		assertEquals(unitGain, arrayResult[0][1], 0);
	}
	
	private long generateRandomId() {
		return Math.round((Math.random() * 100));
	}
}
