package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

public class EntityManagerFactoryTest {

	@Test
	public void test_created() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");

		EntityManager em = emf.createEntityManager();

		em.close();

		emf.close();
	}
}
