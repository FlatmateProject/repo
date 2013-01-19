package dao;

import dto.SimpleNameData;
import exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import spring.ApplicationConfiguration;

import java.util.List;

import static assertions.SimpleNameListAssert.assertThat;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class StatisticDaoTest extends AbstractTestNGSpringContextTests   {

    @Autowired
    StatisticDao statisticDao;

    @Test
    public void shouldFindAllRoomTypes() throws DAOException {
        // given

        // when
        List<SimpleNameData> allRoomTypes = statisticDao.findAllRoomTypes();

        // then
        assertThat(allRoomTypes)
                .isNotEmptyList()
                .containRoomType("jednoosobowy")
                .containRoomType("dwuosobowy")
                .containRoomType("rodzinny")
                .containRoomType("apartament")
                .containRoomType("prezydencki")
                .exactly();
    }

    @Test
    public void shouldFindAllServiceTypes() throws DAOException {
        // given

        // when
        List<SimpleNameData> allServiceTypes = statisticDao.findAllServiceTypes();

        // then
        assertThat(allServiceTypes)
                .isNotEmptyList()
                .containServiceType("kantor")
                .containServiceType("rekreacja")
                .containServiceType("wynajem")
                .exactly();
    }
}
