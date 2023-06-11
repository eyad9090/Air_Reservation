package Controller;
import java.util.concurrent.*;

public class ServiceExecutor {
    //int cntCore=Runtime.getRuntime().availableProcessors();
    public static ExecutorService service = Executors.newFixedThreadPool(4);
}
