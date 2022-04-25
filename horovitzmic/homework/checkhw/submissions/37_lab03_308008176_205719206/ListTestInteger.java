public class ListTestInteger extends ListTest<Integer>{
	@Override
	public Integer getParameterInstance() {
		
		return new java.util.Random().nextInt();
	}

}
