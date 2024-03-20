
/** 未命名变量示例1 */
import java.util.*;

public class Jep456_E1 {
    public static void main(String[] args) {
        old_code();
        new_code();
    }

    private static void old_code() {
        Iterable<Integer> list = Arrays.asList(1, 2, 3);
        var total = 0;
        for (var unused : list) {
            total++;
        }
        System.out.printf("old total=%d\n", total);
    }

    private static void new_code() {
        Iterable<Integer> list = Arrays.asList(1, 2, 3);
        var total = 0;
        for (var _ : list) {
            total++;
        }
        System.out.printf("new total=%d\n", total);
    }
}