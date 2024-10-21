package vn.gs.core.constant;

/**
 * @author hungnm
 * @created 19/10/2024 - 19:50
 * @description ...
 */

public class GsConst {
    public static class Date {
        public static final String US_NORMAL_DATE = "yyyy-MM-dd";
        public static final String US_NORMAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";
    }

    public static class Regex {
        public static final String EMAIL = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W]).{1,150}$";
        public static final String PASSWORD = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,100})\\S$";
        public static final String USERNAME = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,12})\\S$";
    }
}
