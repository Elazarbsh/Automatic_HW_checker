import java.util.Random;

public class ListTestInteger extends ListTest<Integer>{
	@Override
	public Integer getParameterInstance()
	{
		Random x = new Random();
		return x.nextInt();
	}

}
