package tasks.first;

import java.util.*;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Main {

    private static final Main instance = new Main();

    private static Scanner scanner = new Scanner(System.in, "UTF-8");
    private volatile List<Integer> input = new ArrayList<>();
    private Semaphore semaphore = new Semaphore(0);
    private final Object sync = new Object();

    public static void main(String[] args) {
        try {
            instance.starter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void starter() throws InterruptedException { //aaawwww so hard not to write fireStarter
        Thread reader = new Thread(new Reader());
        Thread writer = new Thread(new Writer());
        reader.start();
        writer.start();
        reader.join();
        writer.join();
    }

    class Reader implements Runnable {

        private void reader() throws ParseException {
            String s;
            System.out.println("Write numbers");
            while (!(s = scanner.nextLine()).equals("")) {
                Integer number = Converter.parse(s);
                synchronized (sync) {
                    input.add(number);
                    semaphore.release();
                }
            }
        }

        @Override
        synchronized public void run() {
            try {
                reader();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    class Writer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    if (input.isEmpty()) return;
                    System.out.println("get in there");
                    semaphore.acquire();
                    sleep(5000);
                    writer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void writer() {
                synchronized (sync) {
                    input.sort(Integer::compareTo);
                    System.out.println(input.remove(0));
                }
        }
    }
}
