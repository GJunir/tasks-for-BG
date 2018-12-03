package tasks.first;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class Main {

    private static final Main instance = new Main();

    private static Scanner scanner = new Scanner(System.in, "UTF-8");
    private volatile List<Integer> input = new ArrayList<>();
    private Semaphore semaphore = new Semaphore(0);
    private final Object sync = new Object();
    private AtomicBoolean ab = new AtomicBoolean(false);

    public static void main(String[] args) {
        try {
            instance.starter();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void starter() throws InterruptedException {
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
            ab.set(false);
        }

        @Override
        synchronized public void run() {
            try {
                reader();
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    class Writer implements Runnable {

        @Override
        public void run() {
            label:
            while (true) {
                try {
                    semaphore.acquire();
                    sleep(5000);
                    writer();
                    if(ab.get()) break label;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void writer() {
                synchronized (sync) {
                    input.sort(Integer::compareTo);
                    if (!input.get(0).equals(0)) {
                        Integer min = input.get(0);
                        System.out.println(min);
                        input.remove(min);
                    }
                }
        }
    }
}
//one hundred forty
//two
//forty
//sixty two
