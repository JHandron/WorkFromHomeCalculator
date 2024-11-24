import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.*;

public class WorkFromHomeCalculator {

    private LocalDate workFromHomeStartDate;
    private LocalDate workFromHomeEndDate;
    private DayOfWeekUS weekOneDay;
    private DayOfWeekUS weekTwoDay;

//    private static final DayOfWeekUS WEEK_1_DAY = DayOfWeekUS.TUESDAY;
//    private static final DayOfWeekUS WEEK_2_DAY = DayOfWeekUS.WEDNESDAY;
    //TODO: Make this the actual work from home start date (9/12/24) and calculate weeks at the start of Sunday
//    private static final LocalDate WORK_FROM_HOME_START_DATE = LocalDate.of(2024, 9, 8);
//    private static final LocalDate WORK_FROM_HOME_END_DATE = LocalDate.of(2025, 3, 19);

    public WorkFromHomeCalculator(){ }

    public List<LocalDate> getWorkFromHomeDates(){
        final List<LocalDate> listWorkFromHomeDates = new ArrayList<>();
        final WeekFields weekFields = WeekFields.of(Locale.US);
        final long weeksBetweenStartDateAndEndDate = (ChronoUnit.WEEKS.between(workFromHomeStartDate, workFromHomeEndDate) + 1);
        final int startDateWeekNumber = workFromHomeStartDate.get(weekFields.weekOfWeekBasedYear());

        for (int i = 0; i < weeksBetweenStartDateAndEndDate; i++){
            DayOfWeekUS currentWorkFromHomeDayForWeekNumber = i % 2 == 0 ? weekOneDay : weekTwoDay;
            listWorkFromHomeDates.add(getOccurrenceOfWeekDayFromWeekNumber(startDateWeekNumber + i, currentWorkFromHomeDayForWeekNumber));
        }
        return listWorkFromHomeDates;
    }

    public LocalDate getOccurrenceOfWeekDayFromWeekNumber(final int p_weekNumber, final DayOfWeekUS p_weekDay){
        final int year = getYearFromWeekNumber(p_weekNumber);
        final int adjustedWeekNumber = getAdjustedWeekNumber(p_weekNumber);
        final WeekFields weekFields = WeekFields.of(Locale.US);
        final LocalDate firstDayOfWeek = LocalDate.now().withYear(year)
                                                        .with(weekFields.weekOfYear(), adjustedWeekNumber)
                                                        .with(weekFields.dayOfWeek(), 1);

        return firstDayOfWeek.with(weekFields.dayOfWeek(), p_weekDay.getValue());
    }

    public int getAdjustedWeekNumber(final int p_weekNumber){
        if (p_weekNumber <= 0){
            throw new IllegalArgumentException("Week number cannot be equal to or less than 0!");
        }
        return p_weekNumber > 52 ? p_weekNumber % 52 : p_weekNumber;
    }

    public int getYearFromWeekNumber(final int p_weekNumber){
        if (p_weekNumber <= 0){
            throw new IllegalArgumentException("Week number cannot be equal to or less than 0!");
        }
        return p_weekNumber > 52 ? (workFromHomeStartDate.getYear() + p_weekNumber / 52) : workFromHomeStartDate.getYear();
    }

    public LocalDate getWorkFromHomeStartDate() {
        return workFromHomeStartDate;
    }

    public void setWorkFromHomeStartDate(LocalDate p_workFromHomeStartDate) {
        workFromHomeStartDate = p_workFromHomeStartDate;
    }

    public LocalDate getWorkFromHomeEndDate() {
        return workFromHomeEndDate;
    }

    public void setWorkFromHomeEndDate(LocalDate p_workFromHomeEndDate) {
        workFromHomeEndDate = p_workFromHomeEndDate;
    }

    public DayOfWeekUS getWeekOneDay() {
        return weekOneDay;
    }

    public void setWeekOneDay(DayOfWeekUS weekOneDay) {
        this.weekOneDay = weekOneDay;
    }

    public DayOfWeekUS getWeekTwoDay() {
        return weekTwoDay;
    }

    public void setWeekTwoDay(DayOfWeekUS weekTwoDate) {
        this.weekTwoDay = weekTwoDate;
    }

}
