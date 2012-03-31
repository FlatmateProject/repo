package service;

import java.util.Set;

import model.dictionary.WordEntity;

import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

import static service.ERROR_MESSAGE.EMPTY_PARAMETERS_LIST;
import static service.ERROR_MESSAGE.EMPTY_TRANSLATIONS;
import static service.ERROR_MESSAGE.WORD_NOT_FOUND;

public class AddTranslationsToWordService extends AbstractService<WordEntity> {
	
	private String wordName;

	private Set<String> translations;
	
	private WordEntity word;

	private long wordId;
	
	@Override
	protected WordEntity runService(ApplicationContext serviceContext) throws ServiceException, DaoException {
		
		word = getServiceManager().invokeFindWord(wordName, wordId);
		restrictionIsNotNull(word, WORD_NOT_FOUND);
		
		word.addTranslations(translations);
		
		return word;
	}

	@Override
	public void validation() throws ServiceException {
		if(wordName == null && wordId <= 0){
			throw new ServiceException(EMPTY_PARAMETERS_LIST);
		}
		restrictionIsNotNullAndEmpty(translations, EMPTY_TRANSLATIONS);
	}


	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public void setTranslations(Set<String> translations) {
		this.translations = translations;
	}

	public void setWordId(long wordId) {
		this.wordId = wordId;
	}

}
