package service;



import model.ExampleEntityTest;
import model.TranslationEntityTest;
import model.WordEntityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dao.DictionaryDaoTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	//service
	AddWordServiceTest.class,
	AddExamplesToWordServiceTest.class,
	AddTranslationsToWordServiceTest.class,
	FindElementServiceTest.class,
	
	//dao
	DictionaryDaoTest.class,
	
	//entity
	ExampleEntityTest.class,
	TranslationEntityTest.class,
	WordEntityTest.class
	
})
public class AllTests {
}
