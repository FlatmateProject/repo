package patterns;

import static org.junit.Assert.*;
import org.junit.Test;

public class SingletonTest {

	@Test
	public void shouldReturnSingleInstance(){
		//given
		
		//when
		Singleton instance = Singleton.getInstance();
		Singleton instance2 = Singleton.getInstance();
		
		//then
		assertNotNull(instance);
		assertNotNull(instance2);
		assertEquals(instance, instance2);
	}

	@Test
	public void shouldReturnNiceSingleInstance(){
		//given
		
		//when
		NiceSingleton instance = NiceSingleton.getInstance();
		NiceSingleton instance2 = NiceSingleton.getInstance();
		
		//then
		assertNotNull(instance);
		assertNotNull(instance2);
		assertEquals(instance, instance2);
	}
}
