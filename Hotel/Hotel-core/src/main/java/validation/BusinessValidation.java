package validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BusinessValidation {

    public static boolean isNotDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(date);
        } catch (ParseException e) {
            return true;
        } catch (IllegalArgumentException e) {
            return true;
        }
        return false;
    }

    private static String trimInput(String input) {
        return input.replaceAll("\\D*", "");
    }

    public static boolean isPesel(String pesel) {
        pesel = trimInput(pesel);
        int psize = pesel.length();
        if (psize != 11) {
            return false;
        }
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int j, sum = 0, control;
        int csum = Integer.parseInt(pesel.substring(psize - 1));
        for (int i = 0; i < psize - 1; i++) {
            char c = pesel.charAt(i);
            j = Integer.parseInt(String.valueOf(c));
            sum += j * weights[i];
        }
        control = 10 - (sum % 10);
        if (control == 10) {
            control = 0;
        }
        return (control == csum);
    }

    public static boolean isKRS(String krs) {
        if (krs.length() != 10)
            return false;
        for (int i = 0; i < krs.length(); ++i) {
            if (krs.charAt(i) < '0' || krs.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
}
