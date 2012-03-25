package model;

import static org.junit.Assert.*;

import java.util.HashSet;


import model.WordEntity;

import org.junit.Test;

public class WordEntityTest extends EntityTest {

	@Override
	@Test
	public void testShouldBeEqual() {
		
		WordEntity word1 = WordEntity.Factory.createWord("hello", new HashSet<String>(), new HashSet<String>());
		WordEntity word2 = WordEntity.Factory.createWord("hello", new HashSet<String>(), new HashSet<String>());
		WordEntity word3 = word2;
		
		assertTrue(word1.equals(word2));
		assertTrue(word2.equals(word3));
	}

	@Override
	@Test
	public void testShouldNotBeEqual() {
		
		WordEntity word1 = WordEntity.Factory.createWord("hello1", new HashSet<String>(), new HashSet<String>());
		WordEntity word2 = WordEntity.Factory.createWord("hello2", new HashSet<String>(), new HashSet<String>());
		
		assertFalse(word1.equals(word2));

		
	}

}
