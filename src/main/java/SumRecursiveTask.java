import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SumRecursiveTask extends RecursiveTask<Integer> {

    private List<Integer> numbers;
    private int sum;
    private static final int THREADS_NUMBER = 10;

    public SumRecursiveTask(List<Integer> numbers) {
        this.numbers = numbers;
        sum = 0;
    }

    @Override
    protected Integer compute() {
        for (List<Integer> subList : split()) {
            computeDirectly(subList);
        }
        return sum;
    }

    private List<List<Integer>> split() {
        List<List<Integer>> list = new ArrayList<>();
        final int range = numbers.size() / THREADS_NUMBER;
        int index = range;
        for (int i = 0; i < THREADS_NUMBER; i++) {
            list.add(numbers.subList(index - range, index));
            index += range;
        }
        return list;
    }

    private void computeDirectly(List<Integer> subList) {
        for (int number : subList) {
            sum += number;
        }
    }
}
