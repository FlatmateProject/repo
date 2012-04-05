package dao;

import java.util.List;

import model.dictionary.WordEntity;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import exception.DaoException;


public class ClientDaoImpl extends AbstractDao implements ClientDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<WordEntity> getRandomWords(int limit) throws DaoException {
		try {
			Query query = getSession().createQuery("SELECT id, wordName FROM WordEntity ORDER BY RAND()");
			query.setMaxResults(limit);
			return query.list();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}		
}
