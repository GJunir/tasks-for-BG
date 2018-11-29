package tasks.third;

public class YTranslator {

    private static final String KEY = "trnsl.1.1.20181129T101813Z.9c51a94b3c072c82.e1063156a83af57bcb353c2d4480f4b0cfb0420d";

    public static void main(String[] args) {
        YTranslateApiImpl api = new YTranslateApiImpl(KEY);
    }
}
