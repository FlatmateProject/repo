package service;

import hibernate.ExampleEntityTest;
import hibernate.TranslationEntityTest;
import hibernate.WordEntityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dao.DictionaryDaoTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
	//service
	AddElementServiceTest.class,
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
