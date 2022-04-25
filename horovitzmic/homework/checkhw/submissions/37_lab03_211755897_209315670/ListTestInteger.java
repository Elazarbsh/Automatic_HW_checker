
public class ListTestInteger extends ListTest<Integer>{
	public static int in = 0;

	@Override
	public Integer getParameterInstance() 
	{
		//TODO add your implementation
		in++;
        return Integer.valueOf(in);

	}

}
