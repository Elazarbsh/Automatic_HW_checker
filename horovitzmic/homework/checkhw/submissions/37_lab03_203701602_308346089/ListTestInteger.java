public class ListTestInteger extends ListTest<Integer>{
	static int counter=0;
	@Override
	public Integer getParameterInstance() {



		return counter++;
		//TODO add your implementation
	}

}
