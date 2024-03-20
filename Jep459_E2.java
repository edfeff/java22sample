import static java.lang.StringTemplate.STR;

public class Jep459_E2 {
    public static void main(String[] args) {
        var f = STR;
        var first = "hello";
        var second = "world";
        System.out.println(f."\{first} \{second.toUpperCase()}");
    }
}
