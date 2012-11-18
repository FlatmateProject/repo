package service.cantor;

import dao.CantorDao;
import org.testng.annotations.Test;

import static assertions.TableAssert.assertThat;
import static org.mockito.Mockito.mock;


public class CantorMokitoTest {

    @Test
    public void shouldCreateEmptyCurrencyTable() throws Exception {
        //given
        CantorDao cantorDao = mock(CantorDao.class);

        //when
        Cantor cantor = new Cantor(cantorDao);
        CantorTableResult result = cantor.createCurrencyTable();

        //then
        assertThat(result).isNotNull();
        assertThat(result).hasRowNumber(1)
                .hasColumnNumber(1);
//                .is(containColumn(CantorTableResult.EMPTY_COLUMN[0]));
//                .is(containsRow(CantorTableResult.EMPTY_ROW[0]));
    }
}
