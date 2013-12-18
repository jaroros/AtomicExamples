import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * User: Alexander Nazarenko
 */
public class Main {
    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final AtomicFibonacci atomicFibonacci = new AtomicFibonacci();
        final List<Callable<BigInteger>> callableList = new ArrayList<Callable<BigInteger>>();
        for(int count = 10; count > 0; count--){
            callableList.add(new Callable<BigInteger>() {
                @Override
                public BigInteger call() throws Exception {
                    return atomicFibonacci.next();
                }
            });
        }
        final List<Future<BigInteger>> futureList = executorService.invokeAll(callableList);
        for(final Future<BigInteger> future :futureList) {
            System.out.println(future.get());
        }

    }
}
