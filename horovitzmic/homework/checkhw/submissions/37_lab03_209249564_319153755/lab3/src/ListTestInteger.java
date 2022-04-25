import java.util.Random;

public class ListTestInteger extends ListTest<Integer>{
    @Override
    public Integer getParameterInstance() {
        return Integer.valueOf(new Random().nextInt());
    }

}
