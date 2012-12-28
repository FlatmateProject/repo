package service;

import exception.DaoException;
import exception.ServiceException;
import model.dictionary.WordEntity;
import org.mockito.Mock;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertNotNull;

public class FindWordServiceTestMock extends AbstractServiceTestMock{

    @Mock
    WordEntity word;
	
	private final String wordName = "carry out";
	
	private long wordId = 1;
	
	
	@Test
	public void testShouldFindWordByName() throws DaoException, ServiceException {
		//given
		FindWordService service = getService(FindWordService.class);
		
		when(dictionaryDao.findWordByName(wordName)).thenReturn(word);
		
		//when
		service.setWordName(wordName);
		WordEntity result = executeService(service);
		
		//then
		assertNotNull(result);
	}

	@Test
	public void testShouldFindWordById() throws DaoException, ServiceException {

		//given
		FindWordService service = getService(FindWordService.class);
		
		when(dictionaryDao.findWordById(wordId)).thenReturn(word);

		//when
		service.setWordId(wordId);
		WordEntity result = executeService(service);
		
		//then
		assertNotNull(result);
	}
	
}
