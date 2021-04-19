package finalProject.common;

import java.time.LocalDate;

public class Utils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static LocalDate parseDate(String date) {
        String[] parseDate = date.split(" ");
        int year = Integer.parseInt(parseDate[parseDate.length - 1]);
        int month = Utils.parseMonth(parseDate[parseDate.length - 2]);
        int day = Integer.parseInt(parseDate[parseDate.length - 3]);
        return LocalDate.of(year, month, day);
    }

    public static int parseMonth(String mon) {
        switch (mon.toLowerCase()) {
            case "jan":
                return 1;
            case "feb":
                return 2;
            case "mar":
                return 3;
            case "apr":
                return 4;
            case "may":
                return 5;
            case "jun":
                return 6;
            case "jul":
                return 7;
            case "aug":
                return 8;
            case "sep":
                return 9;
            case "oct":
                return 10;
            case "nov":
                return 11;
            case "dec":
                return 12;
        }
        return 0;
    }
}
