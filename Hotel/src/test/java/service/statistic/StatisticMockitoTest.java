package service.statistic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import service.dictionary.MONTH;
import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;
import dao.StaticticDao;
import dto.ClassRoomData;

public class StatisticMockitoTest {
	
	private static final Logger log = Logger.getLogger(StatisticMockitoTest.class);
	
	@Test
	public void shouldReturnClassRoomRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_CLASS_ROOM;
		int year = 2012;
		MONTH month = MONTH.September;
		String classRoom = null;
		String serveTypeName = null;
		float sumaryGain = 100.0f;
		float unitGain = 100.0f;
		

		ClassRoomData row = mock(ClassRoomData.class);
		when(row.getDescription()).thenReturn("classRoomName");
		when(row.getNuberOccupiedRooms()).thenReturn(1);
		when(row.getSummaryGain()).thenReturn(sumaryGain);
		List<ClassRoomData> inputData = Arrays.asList(row);
		
		StaticticDao staticticDao = mock(StaticticDao.class);
		when(staticticDao.findClassRooms(month.id(), year)).thenReturn(inputData);
		
		// when
		Statistic statistic = new Statistic(staticticDao);
		StatisticRaport raport = statistic.hotel(raportKind, month, year, classRoom, serveTypeName);
		
		// then
		assertNotNull(raport);
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNotNull(arrayResult);
		assertEquals(1, arrayResult.length);
		assertEquals(sumaryGain, arrayResult[0][0], 0);
		assertEquals(unitGain, arrayResult[0][1], 0);
	}
}
