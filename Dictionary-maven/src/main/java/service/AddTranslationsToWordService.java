package service;

import java.util.Set;

import org.springframework.context.ApplicationContext;

import hibernate.WordEntity;

import exception.DaoException;
import exception.ServiceException;
import service.AbstractService;

public class AddTranslationsToWordService extends AbstractService<WordEntity> {

	private String WORD_NOT_FOUND = "Podane słowo nie istnieje";
	
	private String EMPTY_WORD = "Słowo nie może być puste";
	
	private static final String EMPTY_TRANSLATIONS = "Lista tłumaczeń nie może być pusta";
	
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
