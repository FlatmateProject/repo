package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import model.WordEntity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import exception.MyException;
import exception.ServiceException;

public class AddWordServiceTest extends AbstractServiceTest {
	
	final Logger log = LogManager.getLogger(AddWordServiceTest.class);
	
	private void patternTestMethod(TestPattern<AddWordService, WordEntity> testPattern) {
		patternTestMethod(testPattern, AddWordService.class);
	}

	@Test
	public void testSuccess_add_element() {
		patternTestMethod(new TestPattern<AddWordService, WordEntity>() {

			private String wordName;

			private Set<String> translations = new HashSet<String>();

			private Set<String> examples = new HashSet<String>();

			@Override
			public void initialize(AddWordService service) throws ServiceException {
				 
				wordName = "answer back";
				translations.add("oopowiadać niegrzecznie");
				translations.add("pyskować");
				examples.add("How dare you answer back like that?!");

				service.setWordName(wordName);
				service.setTranslations(translations);
				service.setExamples(examples);
			}

			@Override
			public void assertResult(WordEntity result) {

				assertNotNull(result);
				assertTrue(result.getWordName().equals(wordName));
				assertEquals(result.getTranslations().size(), translations.size());
				assertEquals(result.getExamples().size(), examples.size());
			}

			@Override
			public boolean assertException(MyException exception) {
				return false;
			}

		});

	}
	
	@Test
	public void testError_add_element_EMPTY_WORD() {
		patternTestMethod(new TestPattern<AddWordService, WordEntity>() {
			
			private String wordName;

			@Override
			public void initialize(AddWordService service) throws ServiceException {

				wordName = "";
				
				service.setWordName(wordName);
				
			}

			@Override
			public void assertResult(WordEntity result) {
			
				
			}

			@Override
			public boolean assertException(MyException exception) {
				assertTrue(exception instanceof ServiceException);
				ServiceException serviceException = (ServiceException) exception;
				
				assertEquals(ERROR_MESSAGE.EMPTY_WORD, serviceException.getErrorMessage());
				return true;
			}
		});
	}
}
