package dao;


import java.util.List;
import java.util.Set;

import model.dictionary.WordEntity;
import exception.DaoException;

public interface DictionaryDao {

	public WordEntity saveElement(String wordName, Set<String> translations, Set<String> examples) throws DaoException;
	
	public WordEntity findWordByName(String word) throws DaoException;
	
	public List<WordEntity> getRandomWords(int limit) throws DaoException;

	public void delete(WordEntity word) throws DaoException;

	public WordEntity findWordById(long wordId) throws DaoException;

}
