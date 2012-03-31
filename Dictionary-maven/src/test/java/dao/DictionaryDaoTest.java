package dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import model.dictionary.WordEntity;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

import exception.DaoException;
import exception.MyException;

public class DictionaryDaoTest extends AbstractDaoTest {
	
	final Logger log = LogManager.getLogger(DictionaryDaoTest.class);
	
	private void patternTestMethod(TestDaoPattern<DictionaryDao, ?> testDaoPattern) {
		patternTestMethod(testDaoPattern, DictionaryDao.class);
	}

	@Test
	public void test_saveWord() {

		patternTestMethod(new TestDaoPattern<DictionaryDao, WordEntity>() {

			private String wordName;

			private Set<String> translations = new HashSet<String>();

			private Set<String> examples = new HashSet<String>();

			@Override
			public WordEntity initialize(DictionaryDao dao) throws DaoException {

				wordName = "xxx";

				translations.add("aaa");
				translations.add("bbb");

				examples.add("This is example 1.");
				examples.add("This is example 2.");

				return dao.saveElement(wordName, translations, examples);
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
	public void test_findWord() {
		patternTestMethod(new TestDaoPattern<DictionaryDao, WordEntity>() {

			private String wordName;
			
			@Override
			public WordEntity initialize(DictionaryDao dao) throws DaoException{
				
				wordName = "xxx";
				
				return dao.findWord(wordName);
			}

			@Override
			public void assertResult(WordEntity result) {
				assertNull(result);
			}

			@Override
			public boolean assertException(MyException exception) {
				return false;
			}
		});
	}
	
}
