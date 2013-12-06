package dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import spring.ApplicationConfiguration;

@ContextConfiguration(classes = ApplicationConfiguration.class)
@TestExecutionListeners({DbUnitTestExecutionListener.class})
public class StatisticDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    StatisticDao statisticDao;
}
