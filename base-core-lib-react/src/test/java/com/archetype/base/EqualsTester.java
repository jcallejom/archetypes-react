/**
 * Ayesa
 * @author jcallejo
 */

package com.archetype.base;

import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class EqualsTester.
 *
 * @param <T> the generic type
 */
//@Configuration
public class EqualsTester<T> {

	/**
	 * New instance.
	 *
	 * @param <T> the generic type
	 * @param defaultObject the default object
	 * @return the equals tester
	 */
	public static <T> EqualsTester<T> newInstance(T defaultObject) {
		return new EqualsTester<T>(defaultObject);
	}

	/** The default object. */
	private final T defaultObject;
	
	/** The omit hash code tests for unequal pairs. */
	private boolean omitHashCodeTestsForUnequalPairs;

	/**
	 * Instantiates a new equals tester.
	 *
	 * @param defaultObject the default object
	 */
	private EqualsTester(T defaultObject) {
		checkNotNull(defaultObject, "defaultObject");
		this.defaultObject = defaultObject;
		assertGeneralConditions(defaultObject);
	}

	/**
	 * Prevents checking the {@link Object#hashCode() hash code} in
	 * {@link #assertNotEqual()}.
	 *
	 * <p>
	 * Once this method was called, <code>assertNotEqual()</code> does verify the
	 * hash code any more.
	 * </p>
	 */
	public void omitHashCodeTestForUnequalPairs() {
		omitHashCodeTestsForUnequalPairs = true;
	}

	/**
	 * Ensures that the <code>defaultObject</code> that was passed to
	 * {@link #newInstance(Object) newInstance()} implements both
	 * {@link Object#equals(Object) equals()} and {@link Object#hashCode()
	 * hashCode()}.
	 *
	 * <p>
	 * It is generally necessary to override the <code>hashCode()</code> method
	 * whenever <code>equals()</code> is overridden, to maintain the general
	 * contract that equal objects must have equal hash codes. This method helps in
	 * ensuring this contract.
	 * </p>
	 */
	public void assertImplementsEqualsAndHashCode() {
		new Implementation(defaultObject.getClass()).test();
	}

	/**
	 * Ensures that the given objects and their hash code are equal.
	 * 
	 * <p>
	 * The method tests if the given <code>object</code> is equal to
	 * <code>otherObject</code> and vice versa (is <em>symmetric</em>). Equality is
	 * determined by calling {@link Object#equals(Object) equals()} on the objects
	 * respectively. To also ensure <em>consistency</em>, the equals test is
	 * executed twice.
	 * </p>
	 * <p>
	 * If this holds true, the {@link Object#hashCode() hash code} of both objects
	 * is checked in that
	 * </p>
	 * <ul>
	 * <li>both objects must return the same hash code.</li>
	 * <li>it is <em>consistent</em>: return the same value when invoked more than
	 * once</li>
	 * </ul>
	 * <p>
	 * An {@link java.lang.AssertionError AssertionError} is thrown if any of the
	 * two mentioned conditions is not true.
	 * </p>
	 *
	 * @param object      an object that should be equal to
	 *                    <code>otherObject</code>. Must not be <code>null</code>
	 * @param otherObject the object with which <code>object</code> is compared
	 */
	public void assertEqual(T object, Object otherObject) {
		checkNotNull(object, "object");
		new EqualPair(object, otherObject).test();
	}

	/**
	 * Ensures that the <code>equals()</code> implementation of the given objects is
	 * <em>transitive</em>.
	 *
	 * <p>
	 * This method ensures that
	 * </p>
	 * <ul>
	 * <li><code>object1.equals( object2 )</code> and</li>
	 * <li><code>object2.equals( object3 )</code> and</li>
	 * <li><code>object1.equals( object3 )</code></li>
	 * </ul>
	 * <p>
	 * An {@link java.lang.AssertionError AssertionError} is thrown if any of the
	 * equals tests fail.
	 * </p>
	 *
	 * @param object1 an object that should be equal to <code>object2</code> and
	 *                <code>object3</code>. Must not be <code>null</code>.
	 * @param object2 an object that should be equal to <code>object1</code> and
	 *                <code>object3</code>. Must not be <code>null</code>.
	 * @param object3 an object that should be equal to <code>object1</code> and
	 *                <code>object2</code>. Must not be <code>null</code>.
	 */
	public void assertEqual(T object1, Object object2, Object object3) {
		checkNotNull(object1, "object1");
		checkNotNull(object2, "object2");
		checkNotNull(object3, "object3");
		new EqualPair(object1, object2).test();
		new EqualPair(object1, object3).test();
		new EqualPair(object2, object3).test();
	}

	/**
	 * Ensures that the given objects are not equal. If not suppressed it is also
	 * ensured that the hash code of the objects differs.
	 * 
	 * <p>
	 * The method tests if the given <code>object</code> is not equal to
	 * <code>otherObject</code> by using its {@link Object#equals(Object) equals()}
	 * method. Though not strictly required by the equals and
	 * {@link Object#hashCode() hash code} contract, it helps hash tables when the
	 * hash code of unequal objects also differs. Therefore it is also ensured that
	 * the hash code of both objects isn't equal. This test can be suppressed by
	 * calling {@link #omitHashCodeTestForUnequalPairs()} beforehand.
	 * </p>
	 * <p>
	 * An {@link java.lang.AssertionError AssertionError} is thrown if any of the
	 * above mentioned conditions is not true.
	 * </p>
	 *
	 * @param object      an object that should not be equal to
	 *                    <code>otherObject</code>. Must not be <code>null</code>
	 * @param otherObject the object with which <code>object</code> is compared
	 */
	public void assertNotEqual(T object, Object otherObject) {
		checkNotNull(object, "object");
		new UnequalPair(object, otherObject).test();
	}

	/**
	 * Assert general conditions.
	 *
	 * @param defaultObject the default object
	 */
	private void assertGeneralConditions(T defaultObject) {
		assertEqual(defaultObject, defaultObject);
		assertNotEqual(defaultObject, null);
		assertNotEqual(defaultObject, new Object());
	}

	/**
	 * Check not null.
	 *
	 * @param argument the argument
	 * @param argumentName the argument name
	 */
	private static void checkNotNull(Object argument, String argumentName) {
		if (argument == null) {
			throw new IllegalArgumentException("Argument must not be null: " + argumentName);
		}
	}

	/**
	 * Assert true.
	 *
	 * @param message the message
	 * @param condition the condition
	 */
	private static void assertTrue(String message, boolean condition) {
		if (!condition) {
			throw new AssertionError(message);
		}
	}

	/**
	 * Assert false.
	 *
	 * @param message the message
	 * @param condition the condition
	 */
	private static void assertFalse(String message, boolean condition) {
		if (condition) {
			throw new AssertionError(message);
		}
	}

	/**
	 * The Class Pair.
	 */
	static abstract class Pair {
		
		/** The object 1. */
		final Object object1;
		
		/** The object 2. */
		final Object object2;

		/**
		 * Instantiates a new pair.
		 *
		 * @param object1 the object 1
		 * @param object2 the object 2
		 */
		Pair(Object object1, Object object2) {
			this.object1 = object1;
			this.object2 = object2;
		}

		/**
		 * Test.
		 */
		void test() {
			testEquals();
			if (objectsNotNull()) {
				testHashCode();
			}
		}

		/**
		 * Test equals.
		 */
		abstract void testEquals();

		/**
		 * Test hash code.
		 */
		abstract void testHashCode();

		/**
		 * Message for failed equals test.
		 *
		 * @param messagePrefix the message prefix
		 * @return the string
		 */
		String messageForFailedEqualsTest(String messagePrefix) {
			String string1 = String.valueOf(object1);
			String string2 = String.valueOf(object2);
			return String.format("%s for: <%s> and: <%s>", messagePrefix, string1, string2);
		}

		/**
		 * Objects not null.
		 *
		 * @return true, if successful
		 */
		private boolean objectsNotNull() {
			return object1 != null && object2 != null;
		}
	}

	/**
	 * The Class EqualPair.
	 */
	static class EqualPair extends Pair {

		/**
		 * Instantiates a new equal pair.
		 *
		 * @param object1 the object 1
		 * @param object2 the object 2
		 */
		EqualPair(Object object1, Object object2) {
			super(object1, object2);
		}

		/**
		 * Test equals.
		 */
		@Override
		void testEquals() {
			String message = messageForFailedEqualsTest("Equals test failed");
			assertTrue(message, object1.equals(object2));
			assertTrue(message, object1.equals(object1)); // ensure consistency
			assertTrue(message, object2.equals(object1));
		}

		/**
		 * Test hash code.
		 */
		@Override
		void testHashCode() {
			boolean isEqual = object1.hashCode() == object2.hashCode();
			assertTrue(messageForUnequalHashCode(), isEqual);
			boolean isConsistent = object1.hashCode() == object1.hashCode();
			assertTrue(messageForInconsistentHashCode(), isConsistent);
		}

		/**
		 * Message for unequal hash code.
		 *
		 * @return the string
		 */
		private String messageForUnequalHashCode() {
			String message = "HashCode is unequal for equal objects, expected: %d for <%s>, was: %d for <%s>";
			String string1 = object1.toString();
			String string2 = object2.toString();
			Integer hashCode1 = Integer.valueOf(object1.hashCode());
			Integer hashCode2 = Integer.valueOf(object2.hashCode());
			Object[] args = { hashCode1, string1, hashCode2, string2 };
			return String.format(message, args);
		}

		/**
		 * Message for inconsistent hash code.
		 *
		 * @return the string
		 */
		private String messageForInconsistentHashCode() {
			return String.format("HashCode is inconsistent for object: <%s>", object1.toString());
		}
	}

	/**
	 * The Class UnequalPair.
	 */
	class UnequalPair extends Pair {

		/**
		 * Instantiates a new unequal pair.
		 *
		 * @param object1 the object 1
		 * @param object2 the object 2
		 */
		UnequalPair(Object object1, Object object2) {
			super(object1, object2);
		}

		/**
		 * Test equals.
		 */
		@Override
		void testEquals() {
			String message = messageForFailedEqualsTest("Unequals test failed");
			assertFalse(message, object1.equals(object2));
		}

		/**
		 * Test hash code.
		 */
		@Override
		void testHashCode() {
			if (!omitHashCodeTestsForUnequalPairs) {
				String msg = messageForFailedHashCodeTest();
				assertTrue(msg, object1.hashCode() != object2.hashCode());
			}
		}

		/**
		 * Message for failed hash code test.
		 *
		 * @return the string
		 */
		private String messageForFailedHashCodeTest() {
			String message = "HashCode test failed for unequal objects, was %d for: <%s> and for: <%s>";
			String string1 = object1.toString();
			String string2 = object2.toString();
			Integer hashCode = Integer.valueOf(object1.hashCode());
			Object[] args = { hashCode, string1, string2 };
			return String.format(message, args);
		}
	}

	/**
	 * The Class Implementation.
	 */
	static class Implementation {
		
		/** The type. */
		private final Class<? extends Object> type;

		/**
		 * Instantiates a new implementation.
		 *
		 * @param type the type
		 */
		Implementation(Class<? extends Object> type) {
			this.type = type;
		}

		/**
		 * Test.
		 */
		void test() {
			String message = messageForUnimplementedEqualsAndHashCode();
			assertTrue(message, declaresEquals() && declaresHashCode());
		}

		/**
		 * Declares equals.
		 *
		 * @return true, if successful
		 */
		private boolean declaresEquals() {
			return declaresMethod("equals", new Class[] { Object.class });
		}

		/**
		 * Declares hash code.
		 *
		 * @return true, if successful
		 */
		private boolean declaresHashCode() {
			return declaresMethod("hashCode", (Class<?>[]) null);
		}

		/**
		 * Declares method.
		 *
		 * @param methodName the method name
		 * @param parameters the parameters
		 * @return true, if successful
		 */
		private boolean declaresMethod(String methodName, Class<?>... parameters) {
			boolean result = false;
			try {
				type.getDeclaredMethod(methodName, parameters);
				result = true;
			} catch (SecurityException ignore) {
			} catch (NoSuchMethodException ignore) {
			}
			return result;
		}

		/**
		 * Message for unimplemented equals and hash code.
		 *
		 * @return the string
		 */
		private String messageForUnimplementedEqualsAndHashCode() {
			String className = type.getSimpleName();
			return String.format("%s does not implement both, equals() and hashCode()", className);
		}
	}
}