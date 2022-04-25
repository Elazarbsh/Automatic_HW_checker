public class TimerMistake1 extends Timer{
	
	private boolean active = false;
	private long beginningTime=-1;
	private long endTime;

	// Precondition: There timer is not active.
	// Postcondition: Marks the beginning of a time interval (starts the timer).
	public void start() {
//		if (active) throw new IllegalStateException();
		active = true;
		beginningTime = System.currentTimeMillis();
	}
	

}