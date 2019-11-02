import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {

    private static final int SIZE = 1_000_000;
    private static final int MIN = 10;
    private static final int MAX = 100;
    private static final int THREADS = 8;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> integers = ArrayListGenerator.generate(SIZE, MIN, MAX);

        ExecutorService service = Executors.newFixedThreadPool(THREADS);
        SumCallable sumCallable = new SumCallable(integers);
        Future<Integer> submit = service.submit(sumCallable);
        System.out.println(submit.get());
        service.shutdown();

        ForkJoinPool pool = new ForkJoinPool();
        SumRecursiveTask task = new SumRecursiveTask(integers);
        Integer sum = pool.invoke(task);
        System.out.println(sum);
    }
}
