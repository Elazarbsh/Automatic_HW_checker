

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestHW2 {

	private Time time1_1; // time1_1 is initialized time 05:34:13 in class Time1
	private Time time1_2; // time1_2 is initialized time 15:03:49 in class Time1
	private Time time2_1; // time2_1 is initialized time 05:34:13 in class Time2
	private Time time2_2; // time2_2 is initialized time 15:03:49 in class Time2

	private short[] timeArr1;
	private short[] timeArr2;

	@Before
	public void before() {
		timeArr1 = new short[3];
		timeArr1[0] = 5;
		timeArr1[1] = 34;
		timeArr1[2] = 13;

		time1_1 = new Time1(timeArr1[0], timeArr1[1], timeArr1[2]);
		time2_1 = new Time1(timeArr1[0], timeArr1[1], timeArr1[2]);

		timeArr2 = new short[3];
		timeArr2[0] = 15;
		timeArr2[1] = 3;
		timeArr2[2] = 49;

		time1_2 = new Time1(timeArr2[0], timeArr2[1], timeArr2[2]);
		time2_2 = new Time1(timeArr2[0], timeArr2[1], timeArr2[2]);
	}

	@Test
	public void testConstractors() {
		Time t1 = new Time1();
		Time t2 = new Time1(0);
		Time t3 = new Time1((short) 0, (short) 0, (short) 0);
		Time t4 = new Time1(t1);
		short[] hms = { 0, 0, 0 };
		chackEqualsHMS(t1.getHMS(), hms);
		chackEqualsObject(t1, t2, t3, t4);

		t1 = new Time2();
		t2 = new Time2(0);
		t3 = new Time2((short) 0, (short) 0, (short) 0);
		t4 = new Time2(t1);
		chackEqualsHMS(t1.getHMS(), hms);
		chackEqualsObject(t1, t2, t3, t4);

		t1 = time1_1;
		t2 = t1;
		t3 = new Time1(timeArr1[0], timeArr1[1], timeArr1[2]);
		t4 = new Time1(t1);
		chackEqualsHMS(t1.getHMS(), timeArr1);
		chackEqualsObject(t1, t2, t3, t4);

		t1 = time1_2;
		t2 = t1;
		t3 = new Time2(timeArr2[0], timeArr2[1], timeArr2[2]);
		t4 = new Time2(t1);
		chackEqualsHMS(t1.getHMS(), timeArr2);
		chackEqualsObject(t1, t2, t3, t4);

	}

	private void chackEqualsHMS(short[] hms1, short[] hms2) {
		for (int i = 0; i < 3; i++) {
			assertEquals(hms1[i], hms2[i]);
		}
	}

	private void chackEqualsObject(Time t1, Time t2, Time t3, Time t4) {
		assertTrue(t1.equals(t2));
		assertTrue(t2.equals(t3));
		assertTrue(t3.equals(t4));
		assertTrue(t4.equals(t1));
	}

	@Test
	public void testGetters() {
		assertEquals(timeArr2[0], time1_2.getHour());
		assertEquals(timeArr2[1], time1_2.getMinute());
		assertEquals(timeArr2[2], time1_2.getSecond());
		assertEquals(time1_2.getSecondsFromMidnight(), secondsHMSfromMidnight(timeArr2));
		chackEqualsHMS(timeArr2, time1_2.getHMS());

		assertEquals(timeArr1[0], time2_1.getHour());
		assertEquals(timeArr1[1], time2_1.getMinute());
		assertEquals(timeArr1[2], time2_1.getSecond());
		assertEquals(time2_1.getSecondsFromMidnight(), secondsHMSfromMidnight(timeArr1));
		chackEqualsHMS(timeArr1, time2_1.getHMS());
	}

	@Test
	public void testSetters() {
		time1_1.setHMS(timeArr2);
		chackEqualsHMS(timeArr2, time1_1.getHMS());

		time2_2.setHMS(timeArr1);
		chackEqualsHMS(timeArr1, time2_2.getHMS());

		time1_1.setHour((short) 1);
		assertEquals(time1_1.getHour(), 1);

		time2_2.setHour((short) 1);
		assertEquals(time1_1.getHour(), 1);

		time1_1.setMinute((short) 1);
		assertEquals(time1_1.getHour(), 1);

		time2_2.setMinute((short) 1);
		assertEquals(time1_1.getHour(), 1);

		time1_1.setSecond((short) 1);
		assertEquals(time1_1.getHour(), 1);

		time2_2.setSecond((short) 1);
		assertEquals(time1_1.getHour(), 1);

		time1_1.setSecondsFromMidnight(240);
		assertEquals(time1_1.getSecondsFromMidnight(), 240);

		time2_2.setSecondsFromMidnight(240);
		assertEquals(time1_1.getSecondsFromMidnight(), 240);

	}

	@Test
	public void testDifference() {
		assertEquals(time1_1.difference(time2_1), 0);
		time1_1 = new Time1();

		System.out.println(time1_1.difference(time2_2));
		System.out.println(secondsHMSfromMidnight(timeArr2));
		assertEquals(time1_1.difference(time2_2), -secondsHMSfromMidnight(timeArr2));
		assertEquals(time2_2.difference(time1_1), secondsHMSfromMidnight(timeArr2));
	}

	@Test
	public void testEqualsBforeAfter() {
		assertFalse(time1_1.equals(time1_2));
		assertFalse(time1_1.equals(time2_2));
		assertTrue(time1_1.equals(time2_1));
		assertTrue(time1_2.equals(time2_2));

		assertFalse(time1_1.before(time2_1));
		assertFalse(time2_1.after(time1_2));

		assertTrue(time1_1.before(time2_2));
		assertFalse(time2_2.before(time2_1));
		assertFalse(time2_1.after(time1_2));
		assertTrue(time1_2.after(time2_1));
	}

	public static short fromSecondsMidnightToHour(long secsSinceMidnight) {
		long day = secsSinceMidnight % Time.SECS_PER_DAY;
		return (short) (day / Time.SECS_PER_HOUR);
	}

	public static short fromSecondsMidnightToMinute(long secsSinceMidnight) {
		long hour = secsSinceMidnight % Time.SECS_PER_HOUR;
		return (short) (hour / Time.SECS_PER_MIN);
	}

	public static short fromSecondsMidnightToSecond(long secsSinceMidnight) {
		return (short) (secsSinceMidnight % Time.SECS_PER_MIN);
	}

	public static long secondsHMSfromMidnight(short[] hms) {
		return hms[0] * Time.SECS_PER_HOUR + hms[1] * Time.SECS_PER_MIN + hms[2];
	}

}