package org.reflections.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by jpmorin on 2014-04-30.
 */
public class NumericUtilsTest
{
	@Test
	public void NumericUtils_isAssignableFrom_success()
	{
		assertTrue(NumericUtils.isAssignableFrom(Byte.class, int.class));
		assertTrue(NumericUtils.isAssignableFrom(Short.class, int.class));
		assertTrue(NumericUtils.isAssignableFrom(Integer.class, int.class));
		assertFalse(NumericUtils.isAssignableFrom(Long.class, int.class));
		assertFalse(NumericUtils.isAssignableFrom(Float.class, int.class));
		assertFalse(NumericUtils.isAssignableFrom(Double.class, int.class));

		assertTrue(NumericUtils.isAssignableFrom(byte.class, Integer.class));
		assertTrue(NumericUtils.isAssignableFrom(short.class, Integer.class));
		assertTrue(NumericUtils.isAssignableFrom(int.class, Integer.class));
		assertFalse(NumericUtils.isAssignableFrom(long.class, Integer.class));
		assertFalse(NumericUtils.isAssignableFrom(float.class, Integer.class));
		assertFalse(NumericUtils.isAssignableFrom(double.class, Integer.class));
	}

	@Test(expected=RuntimeException.class)
	public void NumericUtils_isAssignableFrom_CustomNumberSubclass()
	{
		NumericUtils.isAssignableFrom(int.class, NumberSubclassStub.class);
	}

	@Test
	public void NumericUtils_isNumericType()
	{
		assertTrue(NumericUtils.isNumericType(byte.class));
		assertTrue(NumericUtils.isNumericType(short.class));
		assertTrue(NumericUtils.isNumericType(int.class));
		assertTrue(NumericUtils.isNumericType(long.class));
		assertTrue(NumericUtils.isNumericType(float.class));
		assertTrue(NumericUtils.isNumericType(double.class));
		assertFalse(NumericUtils.isNumericType(char.class));
		assertFalse(NumericUtils.isNumericType(boolean.class));

		assertTrue(NumericUtils.isNumericType(Byte.class));
		assertTrue(NumericUtils.isNumericType(Short.class));
		assertTrue(NumericUtils.isNumericType(Integer.class));
		assertTrue(NumericUtils.isNumericType(Long.class));
		assertTrue(NumericUtils.isNumericType(Float.class));
		assertTrue(NumericUtils.isNumericType(Double.class));
		assertFalse(NumericUtils.isNumericType(Character.class));
		assertFalse(NumericUtils.isNumericType(Boolean.class));
		assertFalse(NumericUtils.isNumericType(String.class));
		assertFalse(NumericUtils.isNumericType(Object.class));
	}

	private class NumberSubclassStub extends Number
	{

		@Override
		public int intValue()
		{
			return 0;
		}

		@Override
		public long longValue()
		{
			return 0;
		}

		@Override
		public float floatValue()
		{
			return 0;
		}

		@Override
		public double doubleValue()
		{
			return 0;
		}
	}
}
