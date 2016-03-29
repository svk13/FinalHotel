package Tests;

import static org.junit.Assert.*;

import org.junit.Test;
/*
 * Prufu klasi fyrir JUnit test sem fail-ar alltaf
 */
public class TestFailure {

	@Test
	public void test() {
		fail();
	}

}
