package service;

import model.dictionary.WordEntity;

import org.junit.Test;

import exception.MyException;
import exception.ServiceException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DeleteWordServiceTest extends AbstractServiceTest {
	
	public void patternTestMethod(TestPattern<DeleteWordService, Boolean> testPattern) {
		super.patternTestMethod(testPattern, DeleteWordService.class);
	}

	@Test
	public void test_delete() {
		patternTestMethod(new TestPattern<DeleteWordService, Boolean>() {

			private WordEntity word;

			@Override
			public void initialize(DeleteWordService service) throws ServiceException {
				word = dictionaryDatasource.createWord();
				
				service.setWordName(word.getWordName());
			}

			@Override
			public void assertResult(Boolean result) {
				assertNotNull(result);
				assertTrue(result);
			}

			@Override
			public boolean assertException(MyException exception) {
				return false;
			}
		});
	}
}
