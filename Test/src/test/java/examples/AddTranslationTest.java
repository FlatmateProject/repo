package examples;

import static org.junit.Assert.assertNotNull;
import hibernate.WordEntity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import dao.DictionaryDao;
import dao.DictionaryDaoImpl;
import dao.HibernateUtil;
import exception.DaoException;


public class AddTranslationTest {
	
	@Test
	public void test_add() throws DaoException {
		Session session = HibernateUtil.getSession();
		DictionaryDao dao = new DictionaryDaoImpl(session);
		
		WordEntity word = createWord(dao);
		assertNotNull(word);
		session.flush();
		WordEntity word2 = dao.findWord(word.getWordName());
		assertNotNull(word2);
	}

	private WordEntity createWord(DictionaryDao dao) throws DaoException {
		String wordName;

		Set<String> translations = new HashSet<String>();

		Set<String> examples = new HashSet<String>();
			 
		wordName = "answer back";
		translations.add("oopowiadać niegrzecznie");
		translations.add("pyskować");
		examples.add("How dare you answer back like that?!");
		
		return dao.saveElement(wordName, translations, examples);
	}

}
