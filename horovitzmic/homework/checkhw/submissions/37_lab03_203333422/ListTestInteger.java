
public class ListTestInteger extends ListTest<Integer>{
	int i=0;
	@Override
	public Integer getParameterInstance() {
		i++;
		return i;
	}
}

