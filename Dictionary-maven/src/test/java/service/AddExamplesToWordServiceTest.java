package service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import hibernate.ExampleEntity;
import hibernate.WordEntity;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import exception.DatasourceException;

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
			public void initialize(AddExamplesToWordService service) throws DatasourceException {
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
			public boolean assertException(Exception exception) {
				return false;
			}
		});
	}
}
