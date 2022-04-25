public class ListTestInteger extends ListTest<Integer>{
	public static int NUM = 0;

	@Override
	public Integer getParameterInstance() 
	{
		//TODO add your implementation
		NUM++;
        return Integer.valueOf(NUM);

	}

}
