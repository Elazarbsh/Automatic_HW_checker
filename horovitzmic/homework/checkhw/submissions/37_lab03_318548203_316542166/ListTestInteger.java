import java.util.Random;

public class ListTestInteger extends ListTest<Integer>{
	@Override
	public Integer getParameterInstance(){
		Integer integer = Integer.valueOf(new Random().nextInt(99999999));
		return integer;
	}

}
