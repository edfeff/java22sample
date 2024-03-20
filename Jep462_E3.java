import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class Jep462_E3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        mainTask();
    }

    public static void mainTask() throws InterruptedException, ExecutionException {
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

        scope.join().throwIfFailed();
        scope.close();
        System.out.println("finish");
    }
}
