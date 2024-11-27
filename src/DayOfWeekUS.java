import java.util.HashMap;
import java.util.Map;

public enum DayOfWeekUS {

    //This enum is used in favor of DayOfWeek when needing an integer value returned from the enum
    //because DayOfWeek uses Monday for the start of the week (Day 1) whereas the U.S. starts the
    //week on Sunday.
    SUNDAY(1, "Sunday"),
    MONDAY(2, "Monday"),
    TUESDAY(3, "Tuesday"),
    WEDNESDAY(4, "Wednesday"),
    THURSDAY(5, "Thursday"),
    FRIDAY(6, "Friday"),
    SATURDAY(7, "Saturday");

    private final int value;
    private final String description;



    DayOfWeekUS(int p_value, String p_description){
        value = p_value;
        description = p_description;
    }

    public static DayOfWeekUS getEnumByDescription(String p_description){
        for (DayOfWeekUS day : DayOfWeekUS.values()){
            if (day.description.equals(p_description)){
                return day;
            }
        }
        return null;
    }

    public int getValue(){
        return value;
    }

    public String getDescription(){
        return description;
    }
}
