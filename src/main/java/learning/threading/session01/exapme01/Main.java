package learning.threading.session01.exapme01;

public class Main {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> System.out.println("Thread - " + Thread.currentThread().getName() + " is running"));
        Thread thread2 = new Thread(() -> System.out.println("Thread - " + Thread.currentThread().getName() + " is running"));
        thread1.setName("Thread 1 ");
        thread2.setName("Thread 2 ");
        thread1.start();
        thread2.start();
        System.out.println("here from "+Thread.currentThread().getName());
    }
}
