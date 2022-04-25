import java.lang.Thread.State;

public class Main {
	public static void main(String[] args) throws Exception {
		testGame(10, 10, 5, 0, 1, 0, 10 * 1000 * 2);
		testGame(0, 10, 5, 0, 1, 0, 1000 );
		testGame(10, 10, 1, 0, 5, 0, 10 * 1000 * 2);
		testGame(10, 10, 2, 1, 2, 1, 10 * 1000 * 2);
	}

	private static void testGame(long timeForGameM, int numPlayers, double playerActiveMean, double playerActiveVar,
			double playerArrivalMean, double playerArrivalVar, long sleepMainTime)
			throws Exception, InterruptedException {
		Game game = new Game(timeForGameM, numPlayers, playerActiveMean, playerArrivalMean, playerArrivalMean,playerArrivalVar);
		game.start();

//		Thread.sleep(sleepMainTime);
//		if (!game.getState().equals(State.TERMINATED)) {
//			System.err.println("Game state="+game.getState());
//			System.err.println("Game does not stop. The parameters are: " + "timeForGameM=" + timeForGameM
//					+ ", numPlayers=" + numPlayers + ", playerActiveMean=" + playerActiveMean + ", playerActiveVar="
//					+ playerActiveVar + ", playerArrivalMean=" + playerArrivalMean + ", playerArrivalVar="
//					+ playerArrivalVar + ",sleepMainTime=" + sleepMainTime);
//		}
	}
}