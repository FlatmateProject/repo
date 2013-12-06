package service.guestBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import service.guessBook.GuestBook;
import spring.ApplicationConfiguration;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class GuestBookTest extends AbstractTestNGSpringContextTests {

    @Autowired
    GuestBook guestBook;

    @Test
    public void testName() throws Exception {
        //given

        //when

        //then
    }
}
