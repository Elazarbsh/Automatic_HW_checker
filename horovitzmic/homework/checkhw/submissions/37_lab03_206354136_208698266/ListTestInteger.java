import java.util.Random;

public class ListTestInteger extends ListTest<Integer>{
    @Override
    public Integer getParameterInstance()
    {
        Random r = new Random();
        return r.nextInt();

    }

}
