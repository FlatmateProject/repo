package dao;

import java.util.List;

import model.AddSesion;
import model.dictionary.WordEntity;
import exception.DaoException;

public interface ClientDao extends AddSesion {
	
	public List<WordEntity> getRandomWords(int limit) throws DaoException;

}
