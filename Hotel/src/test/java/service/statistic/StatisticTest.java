package service.statistic;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import service.statictic.RAPORT_KIND;
import service.statictic.Statistic;
import service.statictic.StatisticRaport;

public class StatisticTest {

	
	private static final Logger log = Logger.getLogger(StatisticTest.class);
	@Test
	public void shouldReturnEmptyTextRaport() {
		// given
		RAPORT_KIND raportKind = RAPORT_KIND.HOTEL_CLASS_ROOM;
		int year = 2012;
		int month = 9;
		String serve = null;
		String classRoom = null;

		Statistic statistic = new Statistic();
		
		// when
		StatisticRaport raport = statistic.hotel(raportKind, month, year, classRoom, serve);

		// then
		String textRaport = raport.getTextResult();
		assertNotNull(textRaport);
		log.info(textRaport);
		
		double[][] arrayResult = raport.getArrayResult();
		assertNotNull(arrayResult);
		assertEquals(0, arrayResult.length);
	}
}
