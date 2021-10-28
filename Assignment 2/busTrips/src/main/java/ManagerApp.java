import java.util.Calendar;
import java.util.Date;

public class ManagerApp {

    public static Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }
    public static Date getTimeStamp (int day, int month, int year, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    public static void main (String[] args) {
        
    }

}
