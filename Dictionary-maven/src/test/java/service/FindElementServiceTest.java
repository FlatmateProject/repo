package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import model.WordEntity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import exception.MyException;
import exception.ServiceException;


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
			public void initialize(FindElementService service) throws ServiceException {
				word = dictionaryDatasource.createWord();	
				service.setWordName(word.getWordName());
			}

			@Override
			public void assertResult(WordEntity result) {
				assertNotNull(result);
				assertTrue(result.getWordName().equals(word.getWordName()));			
			}

			@Override
			public boolean assertException(MyException exception) {
				return false;
			}
		});
	}
	
	@Test
	public void test_element_not_exist() {
		patternTestMethod(new TestPattern<FindElementService, WordEntity>() {
			
			@Override
			public void initialize(FindElementService service) throws ServiceException {
				service.setWordName("not exist element");
			}

			@Override
			public void assertResult(WordEntity result) {			
			}

			@Override
			public boolean assertException(MyException exception) {
				assertTrue(exception instanceof ServiceException);
				ServiceException serviceException = (ServiceException) exception;
				
				assertEquals(ERROR_MESSAGE.WORD_NOT_FOUND, serviceException.getErrorMessage());
				return true;
			}
		});
	}
}
