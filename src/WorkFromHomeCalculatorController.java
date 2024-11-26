import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WorkFromHomeCalculatorController {

    private final WorkFromHomeCalculator workFromHomeCalculator = new WorkFromHomeCalculator();

    public Map<LocalDate, HolidayEnum> doCalculation(LocalDate p_startDate, LocalDate p_endDate, DayOfWeekUS p_weekOneDay, DayOfWeekUS p_weekTwoDay) {
        setValues(p_startDate, p_endDate, p_weekOneDay, p_weekTwoDay);
        return findConflicts(workFromHomeCalculator.getWorkFromHomeDates());
    }

    public void setValues(LocalDate p_startDate, LocalDate p_endDate, DayOfWeekUS p_weekOneDay, DayOfWeekUS p_weekTwoDay) {
        workFromHomeCalculator.setWorkFromHomeStartDate(p_startDate);
        workFromHomeCalculator.setWorkFromHomeEndDate(p_endDate);
        workFromHomeCalculator.setWeekOneDay(p_weekOneDay);
        workFromHomeCalculator.setWeekTwoDay(p_weekTwoDay);
    }

    public Map<LocalDate, HolidayEnum> findConflicts(final Collection<LocalDate> p_listWorkFromHomeDates) {
        final Map<LocalDate, HolidayEnum> dateConflictMap = new HashMap<>();
        for (LocalDate date : p_listWorkFromHomeDates) {
            dateConflictMap.put(date, null);
            if (HolidayHelper.isNewYears(date)) {
                dateConflictMap.put(date, HolidayEnum.NEW_YEARS_DAY);
            }
            if (HolidayHelper.isMLKDay(date)) {
                dateConflictMap.put(date, HolidayEnum.MLK_DAY);
            }
            if (HolidayHelper.isLincolnsBirthday(date)) {
                dateConflictMap.put(date, HolidayEnum.LINCOLNS_BIRTHDAY);
            }
            if (HolidayHelper.isPresidentsDay(date)) {
                dateConflictMap.put(date, HolidayEnum.PRESIDENTS_DAY);
            }
            if (HolidayHelper.isMemorialDay(date)) {
                dateConflictMap.put(date, HolidayEnum.MEMORIAL_DAY);
            }
            if (HolidayHelper.isJuneteenth(date)) {
                dateConflictMap.put(date, HolidayEnum.JUNETEENTH);
            }
            if (HolidayHelper.isIndependenceDay(date)) {
                dateConflictMap.put(date, HolidayEnum.INDEPENDENCE_DAY);
            }
            if (HolidayHelper.isLaborDay(date)) {
                dateConflictMap.put(date, HolidayEnum.LABOR_DAY);
            }
            if (HolidayHelper.isColumbusDay(date)) {
                dateConflictMap.put(date, HolidayEnum.COLUMBUS_DAY);
            }
            if (HolidayHelper.isElectionDay(date)) {
                dateConflictMap.put(date, HolidayEnum.ELECTION_DAY);
            }
            if (HolidayHelper.isVeteransDay(date)) {
                dateConflictMap.put(date, HolidayEnum.VETERANS_DAY);
            }
            if (HolidayHelper.isThanksgiving(date)) {
                dateConflictMap.put(date, HolidayEnum.THANKSGIVING);
            }
            if (HolidayHelper.isChristmas(date)) {
                dateConflictMap.put(date, HolidayEnum.CHRISTMAS);
            }
        }
//        if (!dateConflictMap.isEmpty()) {
//            System.out.println(dateConflictMap.size() + " conflicts found.");
//            for (LocalDate date : p_listWorkFromHomeDates) {
//                if (dateConflictMap.containsKey(date)) {
//                    System.out.println("Conflict found with this date: " + dateConflictMap.get(date).getDescription() + " - " +
//                            date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US) + ", " + date);
//                }
//            }
//        }
        return dateConflictMap;
    }
}
