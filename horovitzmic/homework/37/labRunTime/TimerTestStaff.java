import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

public class TimerTestStaff {

	private Timer t;

	@Before
	public void setUp() {
		t = new Timer();
	}

	@Test
	public void testStopBeforeStart() {
		try {
			t.stop();
			fail("Should not work");
		} catch (Exception e) {
		}
		try {
			t.start();
			t.stop();
			t.stop();
			fail("Should not work");
		} catch (Exception e) {
		}
	}

	@Test
	public void testStartAfterStart() {
		t.start();
		try {
			t.start();
			fail("Should not work");
		} catch (Exception e) {
		}
	}

	@Test
	public void testElapsedTimeBeforeStart() {
		try {
			t.elapsedTime();
			fail("Should not work");
		} catch (Exception e) {
		}
	}

	@Test
	public void testRegularUsing() {
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		t.stop();
		long time = t.elapsedTime();
		assertTrue((time < 1100) && (time > 900));

		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		t.stop();
		time = t.elapsedTime();
		assertTrue((time < 1100) && (time > 900));

	}

	@Test
	public void testElapseTimeBeforeStop() {
		t.start();
		try {
			t.elapsedTime();
			fail("Should not work");
		} catch (Exception e) {
		}
	}

}