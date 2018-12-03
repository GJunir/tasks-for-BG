package tasks.first;

public abstract class Converter {

    public static Integer parse(String s) throws ParseException {
        final String[] depthTokens = {"ty","teen", "hundred", "thousand"};
        Integer result = 0;
        String[] tokens =  s.split(" ");
        if (tokens.length > 6 || tokens.length < 1) throw new ParseException("Cannot parse, wrong input: " + s);
        if (tokens.length == 1) return getDigit(tokens[0]);
        for (int i = 0; i < tokens.length - 1; i++) {
            for (String level : depthTokens) {
                //in this level we parse thousands and hundreds
                if (tokens[i + 1].equals(level)) {
                    result += getDigit(tokens[i]) * getDepth(level);
                }
                else
                    //in this level we parse tens and teens
                    if (tokens[i].contains(level)) {
                        result += getDigit(tokens[i]);
                    }
            }
        }
        //don't forget about digits
        result += getDigit(tokens[tokens.length - 1]);
        return result;
    }

    private static int getDepth(String s) {
        switch (s) {
            case "ty" :
            case "teen" :
                return 10;
            case "hundred" :
                return 100;
            case "thousand" :
                return 1000;
        }
        return 1;
    }

    //OMG looks like horrible. But really eazy to use this
    private static int getDigit(String s) {
        switch (s) {
            case "one":
                return 1;
            case "two" :
                return 2;
            case "three" :
                return 3;
            case "four" :
                return 4;
            case "five" :
                return 5;
            case "six" :
                return 6;
            case "seven" :
                return 7;
            case "eight" :
                return 8;
            case "nine" :
                return 9;
            case "ten" :
                return 10;
            case "eleven" :
                return 11;
            case "twelve" :
                return 12;
            case "thirteen" :
                return 13;
            case "fourteen" :
                return 14;
            case "fifteen" :
                return 15;
            case "sixteen" :
                return 16;
            case "seventeen" :
                return 17;
            case "eighteen" :
                return 18;
            case "nineteen" :
                return 19;
            case "twenty" :
                return 20;
            case "thirty" :
                return 30;
            case "fourty" :
                return 40;
            case "fifty":
                return 50;
            case "sixty" :
                return 60;
            case "seventy" :
                return 70;
            case "eighty" :
                return 80;
            case "ninety" :
                return 90;
        }
        return 0;
    }
}