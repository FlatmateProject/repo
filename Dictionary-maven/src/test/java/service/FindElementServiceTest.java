package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hibernate.WordEntity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import exception.DatasourceException;
import exception.ServiceException;

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
				service.setWordName(word.getWordName());
			}

			@Override
			public void assertResult(WordEntity result) {
				assertNotNull(result);
				assertTrue(result.getWordName().equals(word.getWordName()));			
			}

			@Override
			public boolean assertException(Exception exception) {
				return false;
			}
		});
	}
	
	@Test
	public void test_element_not_exist() {
		patternTestMethod(new TestPattern<FindElementService, WordEntity>() {
			
			@Override
			public void initialize(FindElementService service) throws DatasourceException {
				service.setWordName("not exist element");
			}

			@Override
			public void assertResult(WordEntity result) {			
			}

			@Override
			public boolean assertException(Exception exception) {
				assertTrue(exception instanceof ServiceException);
				ServiceException serviceException = (ServiceException) exception;
				
				assertEquals(FindElementService.WORD_NOT_EXIST, serviceException.getErrorMessage());
				return true;
			}
		});
	}
}
