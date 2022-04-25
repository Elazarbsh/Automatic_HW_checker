import java.util.Random;

public class ListTestInteger extends ListTest<Integer>{
	@Override
	public Integer getParameterInstance() {
		return Math.abs(new Random().nextInt());
	}

}
