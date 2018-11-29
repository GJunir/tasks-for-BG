package tasks.first;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements Runnable {

    private final static Main instance = new Main();
    private Thread readerThread = new Thread() {
        @Override
        public void run() {
            try {
                reader();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public void run() {
        try {
            reader();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private Thread writerThread = new Thread();
    private static List<String> input = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in, "UTF-8");

    private int reader() throws ParseException {
            String s;
            int min = 10_000;
            while (!(s = scanner.nextLine()).equals("")) {
                input.add(s);
                int number = Converter.parse(s);
                if  (number < min) min = number;
            }
            return min;
    }

    public static void main(String[] args) throws ParseException {
        int min = instance.reader();
        System.out.println(min);
    }
}
