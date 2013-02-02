package dao;

import exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import spring.ApplicationConfiguration;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class GuessBookDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    GuestBookDao guestBookDao;

    //update hotel.klienci set , WOJEWODZTWO = "Małopolsie" where IDK_PESEL = "87122206592"
    @Test
    public void shouldUpdateCustomerData() throws DAOException {
        // given
        String[] labels = new String[]{"IDK_PESEL", "MIASTO", "WOJEWODZTWO"};
        String[] data = new String[]{"87122206592", "", "Małopolsie"};

        // when
        guestBookDao.updateClientData(labels, data);

        // then
    }
}
