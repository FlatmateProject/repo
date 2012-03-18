package service;


import static service.ERROR_MESSAGE.EMPTY_EXAMPLES;
import static service.ERROR_MESSAGE.EMPTY_TRANSLATIONS;
import static service.ERROR_MESSAGE.EMPTY_WORD;
import static service.ERROR_MESSAGE.WORD_IS_NULL;
import hibernate.WordEntity;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import exception.DaoException;
import exception.ServiceException;

public class AddWordService extends AbstractService<WordEntity> {
	
	private String wordName;

	private Set<String> translations = new HashSet<String>();

	private Set<String> examples = new HashSet<String>();
	
	private WordEntity word;

	@Override
	protected WordEntity runService(ApplicationContext serviceContext) throws ServiceException, DaoException {
		
		word = getDictionaryDao().saveElement(wordName, translations, examples);
		restrictionIsNotNull(word, WORD_IS_NULL);
		
		return word;
	}

	public void validation() throws ServiceException {
		
		restrictionIsNotNullAndEmpty(wordName, EMPTY_WORD);
		
		restrictionIsNotNullAndEmpty(translations, EMPTY_TRANSLATIONS);
		
		restrictionIsNotNullAndEmpty(examples, EMPTY_EXAMPLES);
	}

	public String getWordName() {
		return wordName;
	}

	public void setWordName(String word) {
		this.wordName = reduceWhitespace(word);
	}

	public Set<String> getTranslations() {
		return translations;
	}

	public void setTranslations(Set<String> translations) {
		this.translations = reduceWhitespace(translations);
	}

	public Set<String> getExamples() {
		return examples;
	}

	public void setExamples(Set<String> examples) {
		this.examples = reduceWhitespace(examples);
	}
}
