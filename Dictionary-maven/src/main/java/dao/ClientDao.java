package dao;

import exception.DaoException;
import model.AddSesion;
import model.dictionary.WordEntity;

import java.util.List;

public interface ClientDao extends AddSesion {
	
	public List<WordEntity> getRandomWords(int limit) throws DaoException;

}
