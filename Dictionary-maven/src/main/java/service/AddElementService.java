package service;


import hibernate.WordEntity;
import java.util.HashSet;
import java.util.Set;
import org.jboss.system.ServiceContext;
import exception.DaoException;
import exception.ServiceException;

public class AddElementService extends AbstractService<WordEntity> {

	public static final String EMPTY_WORD = "Słow nie możę być puste";
	
	public static final String EMPTY_TRANSLATIONS = "Lista tłumaczeń nie może być pusta";
	
	public static final String EMPTY_EXAMPLES = "Lista przykładów nie może być pusta";
	
	private String word;

	private Set<String> translations = new HashSet<String>();

	private Set<String> examples = new HashSet<String>();

	@Override
	protected WordEntity runService(ServiceContext serviceContext) throws ServiceException, DaoException {
		
		result = getDictionaryDao().saveElement(word, translations, examples);
		
		return result;
	}

	public void validation() throws ServiceException {
		
		restrictionIsNotNullAndEmpty(word, EMPTY_WORD);
		
		restrictionIsNotNullAndEmpty(translations, EMPTY_TRANSLATIONS);
		
		restrictionIsNotNullAndEmpty(examples, EMPTY_EXAMPLES);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Set<String> getTranslations() {
		return translations;
	}

	public void setTranslations(Set<String> translations) {
		this.translations = translations;
	}

	public Set<String> getExamples() {
		return examples;
	}

	public void setExamples(Set<String> examples) {
		this.examples = examples;
	}

}
