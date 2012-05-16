package patterns;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import patterns.builder.WelcomeUserConstructorTest;
import patterns.globalFactoryMethod.GlobalFactoryMethodTest;
import patterns.localFactoryMethod.LocalFactoryMethodTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	WelcomeUserConstructorTest.class,
	GlobalFactoryMethodTest.class,
	LocalFactoryMethodTest.class,
	SingletonTest.class
})
public class AllTestsPatterns {

}
