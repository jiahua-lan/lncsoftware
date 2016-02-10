package cn.lncsoftware.data.common;

/**
 * Created by catten on 16/2/6.
 */
public class RegexTools {
    public static boolean legalUsername(String s) {
        return s != null && s.matches("[\\w\\d_-]{6,32}");
    }

    public static boolean legalPassword(String s) {
        return s != null && s.matches("[\\w\\d=<>,_@&%#\"!¥\\-\\*\\$\\?\\.]{6,32}");
    }

    public static boolean legalContactInfo(String s){
        return s != null && (isContactEmail(s) || isContactNumber(s));
    }

    public static boolean isContactNumber(String s){
        return s != null && s.matches("\\d{6,15}");
    }

    public static boolean isContactEmail(String s){
        return s != null && s.matches("[\\w\\d\\.-_]{1,64}@[\\w\\d\\.-_]{1,32}\\.[\\w\\d\\.-_]{1,10}");
    }

    public static boolean isCellPhoneNumber(String s){
        return  s != null && s.matches("1\\d{10}");
    }

    public static boolean isURL(String s){
        return s != null && s.matches(".+://(.+/?)+");
    }
}
