package learning.threading.session02.example01;

import java.math.BigInteger;

public class LongPowerOfComputation implements Runnable {
    public LongPowerOfComputation(BigInteger base, BigInteger power) {
        this.power = power;
        this.base = base;
    }

    private BigInteger power;
    private BigInteger base;

    @Override
    public void run() {
        computePower(base, power);
    }

    public BigInteger computePower(BigInteger base, BigInteger power) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
            if ((Thread.currentThread().isInterrupted())) {
                System.out.println("Program interrupted --- result is zero");
                return BigInteger.ZERO;
            } else {
                result = result.multiply(base);
            }
        }
        System.out.println("result ---- " + result);
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LongPowerOfComputation(new BigInteger("12345"), new BigInteger("200000")));
        thread.start();
        thread.join(2_000);
        thread.interrupt();
    }
}
