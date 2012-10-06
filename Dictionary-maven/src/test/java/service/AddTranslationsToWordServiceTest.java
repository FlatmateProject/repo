package service;

import java.util.HashSet;
import java.util.Set;

import model.dictionary.TranslationEntity;
import model.dictionary.WordEntity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import exception.MyException;
import exception.ServiceException;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class AddTranslationsToWordServiceTest extends AbstractServiceTest {
	
	final Logger log = LogManager.getLogger(AddTranslationsToWordServiceTest.class);
			
	public <T> void patternTestMethod(TestPattern<AddTranslationsToWordService, WordEntity> testPattern) {
		patternTestMethod(testPattern, AddTranslationsToWordService.class);
	}

	
	@Test
	public void test_add_translations(){
		patternTestMethod(new TestPattern<AddTranslationsToWordService, WordEntity>() {

			private WordEntity word;
			
			private Set<String> translationNames = new HashSet<String>();
			
			private TranslationEntity translation;
			
			@Override
			public void initialize(AddTranslationsToWordService service) throws ServiceException {
				
				word = dictionaryDatasource.createWord();
				translation = dictionaryDatasource.createTranslation(word);
				
				translationNames.add(translation.getTranslationName());
				service.setWordName(word.getWordName());
				service.setTranslations(translationNames);
			}

			@Override
			public void assertResult(WordEntity result) {
				assertNotNull(result);
				assertTrue(result.getWordName().equals(word.getWordName()));
				assertTrue(result.getTranslations().contains(translation));
			}

			@Override
			public boolean assertException(MyException exception) {
				return false;
			}
		});
	}
}
