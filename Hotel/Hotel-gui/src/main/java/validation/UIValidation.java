package validation;

public class UIValidation {

    public static boolean isNotNumber(String num) {
        try {
            Integer.valueOf(num);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
