package dao;

import java.util.Set;
import exception.DaoException;
import hibernate.*;

public interface DictionaryDao {

	public WordEntity saveElement(String wordName, Set<String> translations, Set<String> examples) throws DaoException;

	public TranslationEntity addTranslationToWord(String translationName, String wordName) throws DaoException;

	public Set<TranslationEntity> addTranslationsToWord(Set<String> translationNames, String wordName) throws DaoException;;
	
	public ExampleEntity addExampleToWord(String exampleName, String wordName) throws DaoException;

	public Set<ExampleEntity> addExamplesToWord(Set<String> exampleNames, String wordName) throws DaoException;
	
	public WordEntity findWord(String word) throws DaoException;
	
	

}
