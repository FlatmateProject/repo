import hibernate.ExampleEntityTest;
import hibernate.TranslationEntityTest;
import hibernate.WordEntityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dao.DictionaryDaoTest;

import service.AddElementServiceTest;
import service.AddExamplesToWordServiceTest;
import service.AddTranslationsToWordServiceTest;
import service.FindElementServiceTest;

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
