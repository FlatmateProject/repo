package service;

import dto.ColumnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import spring.ApplicationConfiguration;

import java.util.List;

import static assertions.ColumnListAssert.assertThat;

@ContextConfiguration(classes = ApplicationConfiguration.class)
public class GuestBookTest extends AbstractTestNGSpringContextTests {

    @Autowired
    GuestBook guestBook;

    @Test
    public void shouldGetLabels() {
        // given
        String tableName = "klienci";

        // when
        List<ColumnData> labels = guestBook.getLabels(tableName);

        // then
        assertThat(labels)
                .isNotEmptyList()
                .containColumn("IDK_PESEL")
                .containColumn("IMIE")
                .containColumn("NAZWISKO")
                .containColumn("WOJEWODZTWO")
                .containColumn("MIASTO")
                .containColumn("ULICA")
                .containColumn("BLOK")
                .containColumn("NR_LOKALU")
                .containColumn("STATUS")
                .containColumn("UWAGI")
                .containColumn("TELEFON")
                .containColumn("NIP")
                .exactly();
    }
}
