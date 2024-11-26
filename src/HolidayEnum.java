public enum HolidayEnum {

    NEW_YEARS_DAY("New Year's Day", 1, 1),
    MLK_DAY("Martin Luther King Jr. Day", 1),
    LINCOLNS_BIRTHDAY("Lincoln's Birthday", 2, 12),
    PRESIDENTS_DAY("President's Day", 2),
    MEMORIAL_DAY("Memorial Day", 5),
    JUNETEENTH("Juneteenth", 6, 19),
    INDEPENDENCE_DAY("4th of July", 7, 4),
    LABOR_DAY("Labor Day", 9),
    COLUMBUS_DAY("Columbus Day", 10),
    ELECTION_DAY("Election Day", 11),
    VETERANS_DAY("Veteran's Day", 11, 11),
    THANKSGIVING("Thanksgiving", 11),
    CHRISTMAS("Christmas Day", 12, 25);

//    BLACK_FRIDAY("Black Friday", 11),
//    CHRISTMAS_EVE(),
//    NEW_YEARS_EVE();

    private final String description;
    private final int month;
    private int day;

    HolidayEnum(final String p_description, final int p_month, final int p_day){
        description = p_description;
        month = p_month;
        day = p_day;
    }

    HolidayEnum(final String p_description, final int p_month){
        description = p_description;
        month = p_month;
    }

    public String getDescription() {
        return description;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
