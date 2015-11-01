package edu.poc.pattern.utility.unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sun.misc.Unsafe;
import sun.reflect.ReflectionFactory;

public class UnsafeDemoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

		// Tutorial-1

		/*
		 * Exception in thread "main" java.lang.SecurityException: Unsafe at
		 * sun.misc.Unsafe.getUnsafe(Unsafe.java:90) at
		 * poc.java.feature.unsafw.UnsafeDemo.main(UnsafeDemo.java:8)
		 */
		// Unsafe unsafe = Unsafe.getUnsafe();

		Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
		theUnsafe.setAccessible(true);
		Unsafe unsafe = (Unsafe) theUnsafe.get(null);

		// Tutorial-2
		ClassWithExpensiveConstructor instance = (ClassWithExpensiveConstructor) unsafe
				.allocateInstance(ClassWithExpensiveConstructor.class);
		// output 1

		// Tutorial-3
		@SuppressWarnings("unchecked")
		Constructor<ClassWithExpensiveConstructor> silentConstructor = (Constructor<ClassWithExpensiveConstructor>) ReflectionFactory
				.getReflectionFactory().newConstructorForSerialization(ClassWithExpensiveConstructor.class,
						Object.class.getConstructor());
		silentConstructor.setAccessible(true);

		// Tutorial-4
		@SuppressWarnings("unchecked")
		Constructor<ClassWithExpensiveConstructor> silentConstructor1 = (Constructor<ClassWithExpensiveConstructor>) ReflectionFactory
				.getReflectionFactory().newConstructorForSerialization(ClassWithExpensiveConstructor.class,
						OtherClass.class.getDeclaredConstructor());
		silentConstructor1.setAccessible(true);
		ClassWithExpensiveConstructor instance1 = silentConstructor1.newInstance();
		System.out.println(instance1.getValue());
		System.out.println(instance1.getClass());
	}

}
