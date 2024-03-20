import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.TimeoutException;

public class Jep462_E4 {
    public static void main(String[] args) {
        mainTask();
    }

    public static void mainTask() {
        var scope = new StructuredTaskScope.ShutdownOnFailure();
        var subTask1 = scope.fork(() -> {
            System.out.println("subTask1 start");
            Thread.sleep(1000);
            System.out.println("subTask1 finish");
            return null;
        });
        var subTask2 = scope.fork(() -> {
            System.out.println("subTask2 start");
            Thread.sleep(3000);
            System.out.println("subTask2 finish");
            return null;
        });

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        scope.shutdown();
        System.out.println("finish");
    }
}
