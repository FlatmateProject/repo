package service;

import static service.ERROR_MESSAGE.EMPTY_WORD;
import static service.ERROR_MESSAGE.EMPTY_TRANSLATIONS;
import static service.ERROR_MESSAGE.WORD_NOT_FOUND;

import hibernate.WordEntity;

import java.util.Set;


import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

public class AddTranslationsToWordService extends AbstractService<WordEntity> {
	
	private String wordName;

	private Set<String> translations;
	
	private WordEntity word;
	
	@Override
	protected WordEntity runService(ApplicationContext serviceContext) throws ServiceException, DaoException {
		
		word = getServiceManager().invokeFindElementWord(wordName);
		restrictionIsNotNull(word, WORD_NOT_FOUND);
		
		word.addTranslations(translations);
		
		return word;
	}

	@Override
	public void validation() throws ServiceException {
		restrictionIsNotNullAndEmpty(wordName, EMPTY_WORD);
		restrictionIsNotNullAndEmpty(translations, EMPTY_TRANSLATIONS);
	}


	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public void setTranslations(Set<String> translations) {
		this.translations = translations;
	}

}
