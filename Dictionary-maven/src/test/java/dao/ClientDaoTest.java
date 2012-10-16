package dao;

import exception.DaoException;
import exception.MyException;
import model.dictionary.WordEntity;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ClientDaoTest extends AbstractDaoTest {
	
	private void patternTestMethod(TestDaoPattern<ClientDao, ?> testDaoPattern) {
		patternTestMethod(testDaoPattern, ClientDao.class);
	}

	@Test
	public void test_getRandomWord() {

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
				assertEquals(0, result.size());
			}

			@Override
			public boolean assertException(MyException exception) {
				assertTrue(false);
				return false;
			}});
	}
}
