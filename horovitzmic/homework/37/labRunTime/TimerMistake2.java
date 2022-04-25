public class TimerMistake2 extends Timer{
	private boolean active = false;
	private long beginningTime=-1;
	private long endTime;

	
	// Precondition: The beginning of a time interval has been marked (-the timer is active).
	// Postcondition: Marks the end of a time interval (stops the timer).
	public void stop() {
//		if (!active) throw new IllegalStateException("Stop should be done only after start");
		active = false;
		endTime = System.currentTimeMillis();
	}

}