import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * User: Alexander Nazarenko
 */
public class AtomicFibonacci {
    private final AtomicReference<BigIntegerHolder> integerHolder
            = new AtomicReference<BigIntegerHolder>();

    public AtomicFibonacci() {
        integerHolder.set(new BigIntegerHolder(BigInteger.ZERO, BigInteger.ONE));
    }

    public BigInteger next() {
        for (;;) {
            final BigIntegerHolder current = integerHolder.get();
            final BigInteger big1New = current.big1.add(current.big2);
            final BigInteger big2New = big1New.subtract(current.big2);
            final BigIntegerHolder next = new BigIntegerHolder(big1New, big2New);
            if (integerHolder.compareAndSet(current, next)) {
                return next.big1;
            }
        }
    }

    class BigIntegerHolder {
        public final BigInteger big1;
        public final BigInteger big2;

        public BigIntegerHolder(final BigInteger big1, final BigInteger big2) {
            this.big1 = big1;
            this.big2 = big2;
        }
    }
}