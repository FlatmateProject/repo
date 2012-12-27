package service;

import exception.DaoException;
import exception.ServiceException;
import model.dictionary.WordEntity;
import org.testng.annotations.Test;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.testng.Assert.assertNotNull;

public class FindWordServiceTestMock extends AbstractServiceTestMock{

	@Mock
	private WordEntity word;
	
	private String wordName = "carry out";
	
	private long wordId = 1;
	
	
	@Test
	public void testShouldFindWordByName() throws DaoException, ServiceException {
		//given
		FindWordService service = getService(FindWordService.class);
		
		expect(dictionaryDao.findWordByName(wordName)).andReturn(word);
		replay(dictionaryDao);
		
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
		
		expect(dictionaryDao.findWordById(wordId)).andReturn(word);
		replay(dictionaryDao);
		
		//when
		service.setWordId(wordId);
		WordEntity result = executeService(service);
		
		//then
		assertNotNull(result);
	}
	
}
