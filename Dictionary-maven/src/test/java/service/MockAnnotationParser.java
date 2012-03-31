package service;

import java.lang.reflect.Field;

import org.easymock.EasyMock;

public class MockAnnotationParser {

	public void parse(Class<?> clazz, Object object) throws Exception {

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(Mock.class)) {
				

				field.setAccessible(true);
				field.set(object, EasyMock.createMock(field.getType()));
				System.out.println("mock: " + field.toString());
			}
		}
	}

}
