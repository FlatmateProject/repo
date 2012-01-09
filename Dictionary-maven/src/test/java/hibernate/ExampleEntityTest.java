package hibernate;

import static org.junit.Assert.*;
import org.junit.Test;

public class ExampleEntityTest extends EntityTest {

	@Override
	@Test
	public void testShouldBeEqual() {

		ExampleEntity ex1 = ExampleEntity.Factory.createExample("siema", null);
		ExampleEntity ex2 = ExampleEntity.Factory.createExample("siema", null);
		ExampleEntity ex3 = ex1;
		
		assertTrue(ex1.equals(ex2));
		assertTrue(ex1.equals(ex3));

	}

	@Override
	@Test
	public void testShouldNotBeEqual() {

		ExampleEntity ex1 = ExampleEntity.Factory.createExample("siema1", null);
		ExampleEntity ex2 = ExampleEntity.Factory.createExample("siema2", null);

		assertFalse(ex1.equals(ex2));

	}

}
