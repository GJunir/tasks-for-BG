package tasks.first;

class ParseException extends Exception {

    //Input should be as I want :p
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
