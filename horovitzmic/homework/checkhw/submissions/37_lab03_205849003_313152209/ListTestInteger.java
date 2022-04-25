import java.util.Random;

public class ListTestInteger extends ListTest<Integer>{
	@Override
	public Integer getParameterInstance() {
		return new Integer(new Random().nextInt());
	}

}
