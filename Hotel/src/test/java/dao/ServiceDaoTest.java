package dao;

import exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import service.dictionary.TABLE;
import spring.ApplicationConfiguration;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ServiceDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    ServiceDao serviceDao;

    @Test
    public void shouldUpdateCustomerData() throws DAOException {
        // given
        TABLE table = TABLE.Customer;
        String[] labels = new String[]{"IDK_PESEL", "MIASTO", "WOJEWODZTWO"};
        String[] data = new String[]{"87122206592", "", "Małopolsie"};

        // when
        serviceDao.updateData(table, labels, data);

        // then
    }
}
