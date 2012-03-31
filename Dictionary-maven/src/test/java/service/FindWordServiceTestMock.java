package service;

import model.dictionary.WordEntity;

import org.easymock.EasyMock;
import org.junit.Test;

import exception.DaoException;
import exception.ServiceException;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import static org.junit.Assert.assertNotNull;

public class FindWordServiceTestMock extends AbstractServiceTestMock{

	private WordEntity word;
	
	private String wordName = "carry out";
	
	private long wordId = 1;
	
	
	@Test
	public void testShouldFindWordByName() throws DaoException, ServiceException {
		//given
		FindWordService service = getService(FindWordService.class);
		
		word = EasyMock.createMock(WordEntity.class);
		replay(word);
		
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
		
		word = EasyMock.createMock(WordEntity.class);
		replay(word);
		
		expect(dictionaryDao.findWordById(wordId)).andReturn(word);
		replay(dictionaryDao);
		
		//when
		service.setWordId(wordId);
		WordEntity result = executeService(service);
		
		//then
		assertNotNull(result);
	}
	
}
