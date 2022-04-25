
public class ListTestInteger extends ListTest<Integer>{
	public static int m = 0;

	@Override
	public Integer getParameterInstance() 
	{
		//TODO add your implementation
		m++;
        return Integer.valueOf(m);

	}

}
