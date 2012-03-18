package hibernate;

import hibernate.TranslationEntity;

import org.junit.Test;
import static org.junit.Assert.*;

public class TranslationEntityTest extends EntityTest {

	@Override
	@Test
	public void testShouldBeEqual() {
		
		TranslationEntity tr1 = TranslationEntity.Factory.createTranslation("hello", null);
		TranslationEntity tr2 = TranslationEntity.Factory.createTranslation("hello", null);
		TranslationEntity tr3 = tr1;
		
		assertTrue(tr1.equals(tr2));
		assertTrue(tr1.equals(tr3));
		
	}

	@Override
	@Test
	public void testShouldNotBeEqual() {

		TranslationEntity tr1 = TranslationEntity.Factory.createTranslation("hello1", null);
		TranslationEntity tr2 = TranslationEntity.Factory.createTranslation("hello2", null);
		
		assertFalse(tr1.equals(tr2));
		
	}

}
