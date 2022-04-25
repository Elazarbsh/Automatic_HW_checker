import java.util.Random;



public class ListTestInteger extends ListTest<Integer>{
	@Override
	public Integer getParameterInstance() {
		//TODO add your implementation
		Random rn=new Random();
		Integer x=new Integer(rn.nextInt());
		return x;
	}
}