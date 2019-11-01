import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> integers = ArrayListGenerator.generate(1000000, 10, 100);

        ExecutorService service = Executors.newFixedThreadPool(8);
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
