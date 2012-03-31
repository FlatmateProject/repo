package service;



import model.client.ClientEntityTest;
import model.dictionary.ExampleEntityTest;
import model.dictionary.TranslationEntityTest;
import model.dictionary.WordEntityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dao.DictionaryDaoTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	//service
	AddWordServiceTest.class,
	AddExamplesToWordServiceTest.class,
	AddTranslationsToWordServiceTest.class,
	FindWordServiceTest.class,
	DeleteWordServiceTest.class,
	
	//dao
	DictionaryDaoTest.class,
	
	//entity
	ExampleEntityTest.class,
	TranslationEntityTest.class,
	WordEntityTest.class,
	ClientEntityTest.class,
	
	//mock service
	FindWordServiceTestMock.class
	
})
public class AllTests {
}
