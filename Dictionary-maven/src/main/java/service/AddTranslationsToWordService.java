package service;

import java.util.Set;

import hibernate.WordEntity;

import org.jboss.system.ServiceContext;

import exception.DaoException;
import exception.ServiceException;
import service.AbstractService;

public class AddTranslationsToWordService extends AbstractService<WordEntity> {

	private String WORD_NOT_FOUND = "Podane słowo nie istnieje";
	
	public static final String EMPTY_TRANSLATIONS = "Lista tłumaczeń nie może być pusta";
	
	private String wordName;

	private WordEntity word;

	private Set<String> translations;

	
	@Override
	protected WordEntity runService(ServiceContext serviceContext) throws ServiceException, DaoException {
		
		word = getServiceManager().invoceElementWord(wordName);
		restrictionIsNotNull(word, WORD_NOT_FOUND);
		
		word.addTranslations(translations);
		
		return word;
	}

	@Override
	public void validation() throws ServiceException {
		restrictionIsNotNullAndEmpty(translations, EMPTY_TRANSLATIONS);
	}

}
