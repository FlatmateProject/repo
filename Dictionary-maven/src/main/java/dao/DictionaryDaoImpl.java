package dao;

import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import exception.DaoException;
import hibernate.*;

public class DictionaryDaoImpl extends AbstractDao implements DictionaryDao {

	public DictionaryDaoImpl(Session session) {
		super(session);
	}

	@Override
	public WordEntity saveElement(String wordName, Set<String> translations, Set<String> examples) throws DaoException {		
		try {
			WordEntity word = WordEntity.Factory.createWord(wordName, translations, examples);
			getSession().save(word);
			return word;
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}


	@Override
	public WordEntity findWord(String word) throws DaoException {
		try {
			Criteria crit = getSession().createCriteria(WordEntity.class);
			crit.add(Restrictions.eq("wordName", word));
			return (WordEntity) crit.uniqueResult();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public ExampleEntity addExampleToWord(String exampleName, String wordName) throws DaoException {
		
		return null;
	}

	@Override
	public Set<ExampleEntity> addExamplesToWord(Set<String> examples, String wordName) throws DaoException {

		return null;
	}

	@Override
	public TranslationEntity addTranslationToWord(String translationName, String wordName) throws DaoException {
	
		return null;
	}

	@Override
	public Set<TranslationEntity> addTranslationsToWord(Set<String> translationNames, String wordName)throws DaoException {
		
		return null;
	}

}
