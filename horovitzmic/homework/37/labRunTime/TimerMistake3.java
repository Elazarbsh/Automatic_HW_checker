public class TimerMistake3 extends Timer{
	private boolean active = false;
	private long beginningTime=-1;
	private long endTime;

	
	// Precondition: The beginning and end of a time interval have been marked.
	// Postcondition: Returns the length of the time interval in milliseconds.
	public long elapsedTime() {
//		if (active || beginningTime == -1)
//			throw new IllegalStateException("ElapsedTime should be done only after start and stop");
		return (endTime - beginningTime);
	}

}