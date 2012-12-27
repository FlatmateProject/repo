package service;

import exception.MyException;
import exception.ServiceException;
import model.dictionary.ExampleEntity;
import model.dictionary.WordEntity;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class AddExamplesToWordServiceTest extends AbstractServiceTest {
	
	public void patternTestMethod(TestPattern<AddExamplesToWordService, WordEntity> testPattern) {
		super.patternTestMethod(testPattern, AddExamplesToWordService.class);
	}

	@Test
	public void test_add_example() {
		patternTestMethod(new TestPattern<AddExamplesToWordService, WordEntity>() {

			private WordEntity word;

			private Set<String> exampleNames = new HashSet<String>();

			private ExampleEntity example;
			
			@Override
			public void initialize(AddExamplesToWordService service) throws ServiceException {
				word = dictionaryDatasource.createWord();
				example = dictionaryDatasource.createExample(word);
				
				exampleNames.add(example.getExampleName());
				service.setWordName(word.getWordName());
				service.setExamples(exampleNames);
			}

			@Override
			public void assertResult(WordEntity result) {
				assertNotNull(result);
				assertTrue(result.getWordName().equals(word.getWordName()));
				assertTrue(result.getExamples().contains(example));
			}

			@Override
			public boolean assertException(MyException exception) {
				return false;
			}
		});
	}
}
