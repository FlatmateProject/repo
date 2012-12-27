package dao;

import exception.DaoException;
import model.dictionary.ExampleEntity;
import model.dictionary.TranslationEntity;
import model.dictionary.WordEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Set;

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
	public WordEntity findWordByName(String wordName) throws DaoException {
		try {
			Criteria crit = getSession().createCriteria(WordEntity.class);
			crit.add(Restrictions.eq("wordName", wordName));
			return (WordEntity) crit.uniqueResult();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<WordEntity> getRandomWords(int limit) throws DaoException {
		try {
			Query query = getSession().createQuery("SELECT * FROM 'Word' ORDER BY RAND() LIMIT " + limit);
			return (List<WordEntity>)query.list();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}


	@Override
	public void delete(WordEntity word) throws DaoException {
		try {
			for(TranslationEntity translation : word.getTranslations()) {
				getSession().delete(translation);
			}
			for(ExampleEntity example : word.getExamples()) {
				getSession().delete(example);
			}
			getSession().delete(word);
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}


	@Override
	public WordEntity findWordById(long wordId) throws DaoException {
		try {
			Criteria crit = getSession().createCriteria(WordEntity.class);
			crit.add(Restrictions.eq("id", wordId));
			return (WordEntity) crit.uniqueResult();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

}
