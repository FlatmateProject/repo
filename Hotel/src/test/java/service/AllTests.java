package service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import service.statistic.StatisticMockitoTest;
import service.statistic.StatisticTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	StatisticTest.class,
	StatisticMockitoTest.class
})
public class AllTests {

}
