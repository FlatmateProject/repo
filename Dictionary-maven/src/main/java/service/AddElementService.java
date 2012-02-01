package service;


import hibernate.WordEntity;
import java.util.HashSet;
import java.util.Set;
import exception.DaoException;
import exception.ServiceException;

public class AddElementService extends AbstractService<WordEntity> {

	public static final String EMPTY_WORD = "Słow nie możę być puste";
	
	public static final String EMPTY_TRANSLATIONS = "Lista tłumaczeń nie może być pusta";
	
	public static final String EMPTY_EXAMPLES = "Lista przykładów nie może być pusta";
	
	private String wordName;

	private Set<String> translations = new HashSet<String>();

	private Set<String> examples = new HashSet<String>();
	
	private WordEntity word;

	@Override
	protected WordEntity runService(ServiceContext serviceContext) throws ServiceException, DaoException {
		
		word = getDictionaryDao().saveElement(wordName, translations, examples);
		
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
