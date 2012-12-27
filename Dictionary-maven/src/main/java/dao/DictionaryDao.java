package dao;


import exception.DaoException;
import model.AddSesion;
import model.dictionary.WordEntity;

import java.util.List;
import java.util.Set;

public interface DictionaryDao extends AddSesion {
	
	public WordEntity saveElement(String wordName, Set<String> translations, Set<String> examples) throws DaoException;
	
	public WordEntity findWordByName(String word) throws DaoException;
	
	public List<WordEntity> getRandomWords(int limit) throws DaoException;

	public void delete(WordEntity word) throws DaoException;

	public WordEntity findWordById(long wordId) throws DaoException;
}
