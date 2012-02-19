package service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hibernate.TranslationEntity;
import hibernate.WordEntity;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import exception.DatasourceException;

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
			public void initialize(AddTranslationsToWordService service) throws DatasourceException {
				
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
			public boolean assertException(Exception exception) {
				return false;
			}
		});
	}
}
