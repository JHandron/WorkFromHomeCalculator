import java.time.LocalDate;
import java.util.Collection;

public class WorkFromHomeCalculatorController {

    private final WorkFromHomeCalculator workFromHomeCalculator = new WorkFromHomeCalculator();

    public void doCalculation(LocalDate p_startDate, LocalDate p_endDate, DayOfWeekUS p_weekOneDay, DayOfWeekUS p_weekTwoDay){
        setValues(p_startDate, p_endDate, p_weekOneDay, p_weekTwoDay);
        findConflicts(workFromHomeCalculator.getWorkFromHomeDates());
    }

    public void setValues(LocalDate p_startDate, LocalDate p_endDate, DayOfWeekUS p_weekOneDay, DayOfWeekUS p_weekTwoDay){
        workFromHomeCalculator.setWorkFromHomeStartDate(p_startDate);
        workFromHomeCalculator.setWorkFromHomeEndDate(p_endDate);
        workFromHomeCalculator.setWeekOneDay(p_weekOneDay);
        workFromHomeCalculator.setWeekTwoDay(p_weekTwoDay);
    }

    public void findConflicts(final Collection<LocalDate> p_listWorkFromHomeDates) {
        for(LocalDate date : p_listWorkFromHomeDates){
            validate(date);
        }
    }

    public void validate(final LocalDate p_date){
        if (HolidayHelper.isNewYears(p_date) ||
            HolidayHelper.isMLKDay(p_date) ||
            HolidayHelper.isLincolnsBirthday(p_date) ||
            HolidayHelper.isPresidentsDay(p_date) ||
            HolidayHelper.isMemorialDay(p_date) ||
            HolidayHelper.isJuneteenth(p_date) ||
            HolidayHelper.isIndependenceDay(p_date) ||
            HolidayHelper.isLaborDay(p_date) ||
            HolidayHelper.isColumbusDay(p_date) ||
            HolidayHelper.isElectionDay(p_date) ||
            HolidayHelper.isVeteransDay(p_date) ||
            HolidayHelper.isThanksgiving(p_date) ||
            HolidayHelper.isChristmas(p_date))
        {
            System.out.println(p_date.getDayOfWeek() + " " + p_date);
            System.out.println("Conflict found with this date: " + p_date.getDayOfWeek() + ", " + p_date);
        } else {
            System.out.println(p_date.getDayOfWeek() + " " + p_date);
        }
        
    }
}
