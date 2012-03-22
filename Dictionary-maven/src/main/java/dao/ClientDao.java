package dao;

import hibernate.WordEntity;

import java.util.List;

import exception.DaoException;

public interface ClientDao {
	
	public List<WordEntity> getRandomWords(int limit) throws DaoException;

}
