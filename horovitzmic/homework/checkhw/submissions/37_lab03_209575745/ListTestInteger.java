
public class ListTestInteger extends ListTest<Integer>{
	@Override
	public Integer getParameterInstance() {
		//TODO add your implementation
		Integer retint = new java.util.Random().nextInt();
		return retint;
	}

}
