package service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hibernate.WordEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import exception.DatasourceException;

import service.FindElementService;

public class FindElementServiceTest extends AbstractServiceTest {
	
	final Logger log = LogManager.getLogger(FindElementServiceTest.class);
	
	private void patternTestMethod(TestPattern<FindElementService, WordEntity> testPattern) {
		patternTestMethod(testPattern, FindElementService.class);
	}
	
	
	
	@Test
	public void test_find_element() {
		patternTestMethod(new TestPattern<FindElementService, WordEntity>() {

			WordEntity word;
			
			@Override
			public void initialize(FindElementService service) throws DatasourceException {
				word = dictionaryDatasource.createWord();	
				service.setWord(word.getWordName());
			}

			@Override
			public void assertResult(WordEntity result) {
				assertNotNull(result);
				assertTrue(result.getWordName().equals(word));			
			}

			@Override
			public boolean assertException(Exception exception) {
				
				return false;
			}
		});
	}
}
