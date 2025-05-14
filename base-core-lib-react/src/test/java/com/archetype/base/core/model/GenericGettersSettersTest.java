/*
 * @author jcallejo
 */
package com.archetype.base.core.model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.archetype.base.EqualsTester;
import com.archetype.base.PropertyAsserter;


// TODO: Auto-generated Javadoc
/**
 * The Class GenericGettersSettersTest.
 *
 * @param <T> the generic type
 */
public class GenericGettersSettersTest<T> {

	/**
	 * Gets the clases.
	 *
	 * @return the clases
	 */
	@SuppressWarnings("unchecked")
	public List<T> getClases() {

		List<T> listadoClases = new ArrayList<>();
		
//		listadoClases.add((T) VersionEntity.class);
	
		return listadoClases;
	}
	
	/**
	 * Test getter and setter.
	 */
	@Test
	public void testGetterAndSetter() {
		try {

			for (T clase : getClases()) {
				System.out.println(clase);
				final Class<?> clazz = (Class<?>) clase;
				final Constructor<?> ctor = clazz.getConstructor();
				final Object object = ctor.newInstance();
				PropertyAsserter.assertBasicGetterSetterBehavior(object);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test hash code and equals.
	 */
	@Test
	public void testHashCodeAndEquals() {

		try {
			for (T clase : getClases()) {

				final Class<?> clazz = (Class<?>) clase;
				final Constructor<?> ctor = clazz.getConstructor();
				final Object object = ctor.newInstance();

				try {
					EqualsTester<Object> tester = EqualsTester.newInstance(object);
					tester.assertImplementsEqualsAndHashCode();
					tester.assertEqual(object, object, object);
					tester.assertNotEqual(object, new ArrayList<>());

				} catch (AssertionError e) {

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
