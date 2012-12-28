package service;

import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;

public class MockAnnotationParser {

	public void parse(Class<?> clazz, Object object) throws Exception {
		parseMethods(clazz, object);
		parseFields(clazz, object);
	}

	private void parseMethods(Class<?> clazz, Object object) throws Exception {
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(Mock.class)) {
				field.setAccessible(true);
				field.set(object, mock(field.getType()));
				System.out.println("mock: " + field.toString());
			}
		}

	}

	private void parseFields(Class<?> clazz, Object object) throws Exception {
		for (Method method : clazz.getDeclaredMethods()) {
			if (method.isAnnotationPresent(Test.class)) {
				System.out.println("test method: " + method.toString());
			}
		}
	}

}
