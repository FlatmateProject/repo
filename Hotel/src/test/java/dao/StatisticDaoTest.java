package dao;

import dao.impl.Singleton;
import dao.impl.StatisticDaoImpl;
import dto.SimpleNameData;
import exception.DAOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static assertions.SimpleNameListAssert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class StatisticDaoTest {

    private StatisticDao statisticDao;

    @BeforeMethod
    public void beforeEachTest() {
        initMocks(this);
        statisticDao = new StatisticDaoImpl();
        statisticDao.setSession(Singleton.getInstance());
    }

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
