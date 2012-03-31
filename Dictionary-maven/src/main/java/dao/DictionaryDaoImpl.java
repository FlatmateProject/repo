package dao;




import java.util.List;
import java.util.Set;


import model.dictionary.ExampleEntity;
import model.dictionary.TranslationEntity;
import model.dictionary.WordEntity;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import exception.DaoException;

public class DictionaryDaoImpl extends AbstractDao implements DictionaryDao {

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

	@SuppressWarnings("unchecked")
	@Override
	public List<WordEntity> getRandomWords(int limit) throws DaoException {
		try {
			Query query = getSession().createQuery("SELECT * FROM 'Word' ORDER BY RAND() LIMIT " + limit);
			return query.list();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

}
