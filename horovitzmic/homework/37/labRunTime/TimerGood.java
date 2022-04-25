public class TimerGood extends Timer{
	private boolean active = false;
	private long beginningTime = -1;
	private long endTime;

	// Precondition: There timer is not active.
	// Postcondition: Marks the beginning of a time interval (starts the timer).
	public void start() {
		if (active)
			throw new IllegalStateException();
		active = true;
		beginningTime = System.currentTimeMillis();
	}

	// Precondition: The beginning of a time interval has been marked (-the timer is
	// active).
	// Postcondition: Marks the end of a time interval (stops the timer).
	public void stop() {
		if (!active)
			throw new IllegalStateException("Stop should be done only after start");
		active = false;
		endTime = System.currentTimeMillis();
	}

	// Precondition: The beginning and end of a time interval have been marked.
	// Postcondition: Returns the length of the time interval in milliseconds.
	public long elapsedTime() {
		if (active || beginningTime == -1)
			throw new IllegalStateException("ElapsedTime should be done only after start and stop");
		return (endTime - beginningTime);
	}

}