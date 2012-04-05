package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import model.dictionary.WordEntity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import exception.DaoException;
import exception.MyException;

public class ClientDaoTest extends AbstractDaoTest {
	
final Logger log = LogManager.getLogger(ClientDaoTest.class);
	
	private void patternTestMethod(TestDaoPattern<ClientDao, ?> testDaoPattern) {
		patternTestMethod(testDaoPattern, ClientDao.class);
	}

	@Test
	public void test_getRantomWord() {

		patternTestMethod(new TestDaoPattern<ClientDao,List<WordEntity>>() {

			private int limit;
			
			@Override
			public List<WordEntity> initialize(ClientDao dao) throws DaoException {	
				limit = 1;
				return dao.getRandomWords(limit);
			}

			@Override
			public void assertResult(List<WordEntity> result) {
				assertNotNull(result);	
				assertEquals(0,result.size());
			}

			@Override
			public boolean assertException(MyException exception) {
				// TODO Auto-generated method stub
				assertTrue(false);
				return false;
			}});
	}
}
