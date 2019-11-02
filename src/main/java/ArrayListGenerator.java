import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayListGenerator {

    public static Random random = new Random();

    public static List<Integer> generate(int size, int min, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt((max - min) + 1) + min);
        }
        return list;
    }
}
