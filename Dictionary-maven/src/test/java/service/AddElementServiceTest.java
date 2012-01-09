package service;

import hibernate.WordEntity;
import java.util.HashSet;
import java.util.Set;
import org.junit.*;

import exception.DatasourceException;
import exception.ServiceException;
import static org.junit.Assert.*;
import service.AddElementService;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class AddElementServiceTest extends AbstractServiceTest {
	
	final Logger log = LogManager.getLogger(AddElementServiceTest.class);
	
	private void patternTestMethod(TestPattern<AddElementService, WordEntity> testPattern) {
		patternTestMethod(testPattern, AddElementService.class);
	}

	@Test
	public void test_add_element() {
		patternTestMethod(new TestPattern<AddElementService, WordEntity>() {

			private String wordName;

			private Set<String> translations = new HashSet<String>();

			private Set<String> examples = new HashSet<String>();

			@Override
			public void initialize(AddElementService service) throws DatasourceException {
				 
				wordName = "answer back";
				translations.add("oopowiadać niegrzecznie");
				translations.add("pyskować");
				examples.add("How dare you answer back like that?!");

				service.setWord(wordName);
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
			public boolean assertException(Exception exception) {
				
				return false;
			}

		});

	}
	
	@Test
	public void testError_add_element_EMPTY_WORD() {
		patternTestMethod(new TestPattern<AddElementService, WordEntity>() {
			
			private String wordName;

			@Override
			public void initialize(AddElementService service) throws DatasourceException {

				wordName = "";
				
				service.setWord(wordName);
				
			}

			@Override
			public void assertResult(WordEntity result) {
			
				
			}

			@Override
			public boolean assertException(Exception exception) {
				assertTrue(exception instanceof ServiceException);
				assertEquals(AddElementService.EMPTY_WORD, ((ServiceException)exception).getMessage());
				return true;
			}
		});
	}
}
