package regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * The Main method for this assignment.
     * You can optionally run this to interactively try the three methods.
     * @param args parameters are unused
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        final String userInput = scanner.nextLine();
        scanner.close();
        System.out.println("You entered \"" + userInput + "\"");
        System.out.println(checkForPassword(userInput, 6));
        System.out.println(extractEmails(userInput));
        System.out.println(checkForDoubles(userInput));
    }

    /**
     * Returns whether a given string is non-empty, contains at least one lower-case letter,
     * at least one upper-case letter, at least one digit, and meets the minimum length.
     * @param str the string to check
     * @param minLength the minimum required string length
     * @return whether the string satisfies all password requirements
     */
    public static boolean checkForPassword(String str, int minLength) {
        if (str == null) {
            return false;
        }
        // Regex ensures: at least 1 lowercase, 1 uppercase, 1 digit, and length >= minLength
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{" + minLength + ",}$";
        return Pattern.matches(regex, str);
    }

    /**
     * Returns a list of email addresses found in the given string.
     * Valid emails must have at least one character before '@'
     * and must end with either "@mail.utoronto.ca" or "@utoronto.ca".
     * @param str the string to search
     * @return list of email addresses in the order found
     */
    public static List<String> extractEmails(String str) {
        // If input is null, return an empty list (no emails to extract)
        List<String> result = new ArrayList<>();
        if (str == null) {
            return result;
        }

        // Regex for emails ending with specific domains
        Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@(mail\\.utoronto\\.ca|utoronto\\.ca)\\b");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    /**
     * Checks whether the string contains the same capital letter at least twice.
     * Example: "Amazing Apple" → 'A' appears twice → return true.
     * @param str the string to check
     * @return true if the same capital letter repeats, false otherwise
     */
    public static boolean checkForDoubles(String str) {
        // Capture a capital letter [A-Z], then if the same one appears later → match
        return str != null && str.matches(".*([A-Z]).*\\1.*");
    }
}
