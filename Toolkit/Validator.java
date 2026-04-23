package Toolkit;

public class Validator {
    private static final String EMAIL_REGEX =
            "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";

    public static boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
