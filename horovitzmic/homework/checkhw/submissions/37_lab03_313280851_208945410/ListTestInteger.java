import java.util.Random;

public class ListTestInteger extends ListTest<Integer>{
	@Override
	public Integer getParameterInstance() {
		Random rand = new Random();
		return rand.nextInt();
	}

}
