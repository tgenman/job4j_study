package ru.job4j.comparator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Created by tgenman on 4/4/18.
 */
public class StringCompareTest {
	/**
	 * Test 1.
	 */
	@Test
	public void whenStringsAreEqualThenZero() {
		StringCompare compare = new StringCompare();
		int rst = compare.compare(
				"Ivanov",
				"Ivanov"
		);
		assertThat(rst, is(0));
	}

	/**
	 * Test 2.
	 */
	@Test
	public void whenLeftLessThanRightResultShouldBeNegative() {
		StringCompare compare = new StringCompare();
		int rst = compare.compare(
				"Ivanov",
				"Ivanova"
		);
		assertThat(rst, lessThan(0));
	}

	/**
	 * Test 3.
	 */
	@Test
	public void whenLeftGreaterThanRightResultShouldBePositive() {
		StringCompare compare = new StringCompare();
		int rst = compare.compare(
				"Petrov",
				"Ivanova"
		);
		assertThat(rst, greaterThan(0));
	}

	/**
	 * Test 4.
	 */
	@Test
	public void secondCharOfLeftGreaterThanRightShouldBePositive() {
		StringCompare compare = new StringCompare();
		int rst = compare.compare(
				"Petrov",
				"Patrov"
		);
		assertThat(rst, greaterThan(0));
	}

	/**
	 * Test 5.
	 */
	@Test
	public void secondCharOfLeftLessThanRightShouldBeNegative() {
		StringCompare compare = new StringCompare();
		int rst = compare.compare(
				"Patrova",
				"Petrov"
		);
		assertThat(rst, lessThan(0));
	}
}
