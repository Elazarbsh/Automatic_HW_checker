import java.util.Random;

public class ListTestInteger extends ListTest<Integer> {
    @Override
    public Integer getParameterInstance() {
        return new Random().nextInt(1000);

    }

}
