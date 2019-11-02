import java.util.List;
import java.util.concurrent.Callable;

public class SumCallable implements Callable<Integer> {

    private List<Integer> numbers;
    private int sum;

    public SumCallable(List<Integer> numbers) {
        this.numbers = numbers;
        sum = 0;
    }

    @Override
    public Integer call() throws Exception {
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
