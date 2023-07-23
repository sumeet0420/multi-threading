package learning.threading.session01.exapme02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HackerProblemMain {

    public static int MAX_PASSWORD = 90;

    public static void main(String[] args) {
        Random random = new Random();
        int password = random.nextInt(MAX_PASSWORD);
        PasswordVault passwordVault = new PasswordVault(password);
        List<Thread> threads = new ArrayList<>();
        threads.add(new PoliceThread());
        threads.add(new AscendingHackerThread(passwordVault));
        threads.add(new DescendingHackerThread(passwordVault));
        threads.forEach(Thread::start);
    }

    private static class PasswordVault {
        private int password;

        PasswordVault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int passwordGuess) {
            if (passwordGuess == password) {
                return true;
            }
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return false;
        }
    }
    private static abstract class HackerThread extends Thread {
        protected PasswordVault passwordVault;

        HackerThread(PasswordVault passwordVault) {
            this.passwordVault = passwordVault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }
    }

    private static class AscendingHackerThread extends HackerThread {

        AscendingHackerThread(PasswordVault passwordVault) {
            super(passwordVault);
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_PASSWORD; i++) {
                if (passwordVault.isCorrectPassword(i)) {
                    System.out.println("Password found "+i+" by "+Thread.currentThread().getName());
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {

        DescendingHackerThread(PasswordVault passwordVault) {
            super(passwordVault);
        }

        @Override
        public void run() {
            for (int i = MAX_PASSWORD; i >=0; i--) {
                if (passwordVault.isCorrectPassword(i)) {
                    System.out.println("Password found "+i+" by "+Thread.currentThread().getName());
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        PoliceThread() {
            this.setName("Police Thread");
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("Police tyring at "+i);
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("System is intact...no password breach");
            System.exit(0);
        }
    }

}
