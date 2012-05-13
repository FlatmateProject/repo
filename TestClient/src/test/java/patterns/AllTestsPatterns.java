package patterns;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import patterns.builder.WelcomeUserTest;
import patterns.globalFactoryMethod.GlobalFactoryMethodTest;
import patterns.localFactoryMethod.LocalFactoryMethodTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	WelcomeUserTest.class,
	GlobalFactoryMethodTest.class,
	LocalFactoryMethodTest.class,
	SingletonTest.class
})
public class AllTestsPatterns {

}
