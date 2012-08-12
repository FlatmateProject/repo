package patterns;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import patterns.abstractFactory.PaymentsFactoryWithAnnotationTest;
import patterns.abstractFactory.PaymentsFactoryWithMethodTest;
import patterns.builder.WelcomeUserConstructorTest;
import patterns.decorator.DataProcessingUnitTest;
import patterns.globalFactoryMethod.GlobalFactoryMethodTest;
import patterns.localFactoryMethod.LocalFactoryMethodTest;
import patterns.prototype.UserFeaturesHistoryTest;
import patterns.proxy.ClientResourceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	WelcomeUserConstructorTest.class,
	GlobalFactoryMethodTest.class,
	LocalFactoryMethodTest.class,
	PaymentsFactoryWithAnnotationTest.class,
	PaymentsFactoryWithMethodTest.class,
	UserFeaturesHistoryTest.class,
	DataProcessingUnitTest.class,
	ClientResourceTest.class,
	SingletonTest.class
})
public class AllTestsPatterns {

}
