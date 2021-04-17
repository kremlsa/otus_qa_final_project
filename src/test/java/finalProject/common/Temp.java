package finalProject.common;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class CheckTheEssay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        // write your code here
        Map<String, String> replaces = Stream.of(new String[][] {
                { "Franse", "France" },
                { "Eifel tower", "Eiffel Tower" },
                { "19th", "XIXth" },
                { "20th", "XXth" },
                { "21st", "XXIst" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

        for (Map.Entry<String, String> entry : replaces.entrySet()) {
            Pattern pattern = Pattern.compile(entry.getKey());
            Matcher matcher = pattern.matcher(text);
            text = matcher.replaceAll(entry.getValue());
        }
        System.out.println(text);
    }
}