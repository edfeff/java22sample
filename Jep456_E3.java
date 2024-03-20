import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 未命名变量示例3
 */

public class Jep456_E3 {
    public static void main(String[] args) {
        oldLambda();
        newLambda();
    }

    private static void oldLambda() {
        List<String> list = Arrays.asList("a", "b", "c");
        var upperCharters = list.stream().collect(Collectors.toMap(String::toUpperCase, v -> true));
        System.out.println(upperCharters);//{A=true, B=true, C=true}
    }

    private static void newLambda() {
        List<String> list = Arrays.asList("a", "b", "c");
        var upperCharters = list.stream().collect(Collectors.toMap(String::toUpperCase, _ -> true));
        System.out.println(upperCharters);//{A=true, B=true, C=true}
    }
}