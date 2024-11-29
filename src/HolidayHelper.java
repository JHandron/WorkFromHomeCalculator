import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.next;

public class HolidayHelper {

    //New Year's Day occurs on January 1st.
    public static boolean isNewYears(final LocalDate p_workFromHomeDate){
        final LocalDate newYearsDay = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.NEW_YEARS_DAY.getMonth(), HolidayEnum.NEW_YEARS_DAY.getDay());
        return IObservableHoliday.getObservableHolidayDate(newYearsDay).equals(p_workFromHomeDate);
    }

    //Martin Luther King Jr. Day occurs on the third Monday in the month of January.
    public static boolean isMLKDay(final LocalDate p_workFromHomeDate){
        final LocalDate firstDayOfJanuary = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.MLK_DAY.getMonth(), 1);
        //If the first day of January is a Monday, then we can use that date to find the second and third Monday
        //Otherwise, we have to find the first Monday of the month, and then chain the second and third Monday using that
        if (DayOfWeek.MONDAY.equals(firstDayOfJanuary.getDayOfWeek())){
            final LocalDate secondMondayofJanuary = firstDayOfJanuary.with(next(DayOfWeek.MONDAY));
            final LocalDate MLKDay = secondMondayofJanuary.with(next(DayOfWeek.MONDAY));
            return MLKDay.equals(p_workFromHomeDate);
        } else {
            final LocalDate firstMondayOfJanuary =  firstDayOfJanuary.with(next(DayOfWeek.MONDAY));
            final LocalDate secondMondayOfJanuary = firstMondayOfJanuary.with(next(DayOfWeek.MONDAY));
            final LocalDate MLKDay = secondMondayOfJanuary.with(next(DayOfWeek.MONDAY));
            return MLKDay.equals(p_workFromHomeDate);
        }
    }

    //Lincoln's Birthday occurs on February 12th.
    public static boolean isLincolnsBirthday(final LocalDate p_workFromHomeDate){
        final LocalDate lincolnsBirthday = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.LINCOLNS_BIRTHDAY.getMonth(), HolidayEnum.LINCOLNS_BIRTHDAY.getDay());
        return IObservableHoliday.getObservableHolidayDate(lincolnsBirthday).equals(p_workFromHomeDate);
    }

    //President's Day occurs on the third Monday in the month of February.
    public static boolean isPresidentsDay(final LocalDate p_workFromHomeDate){
        final LocalDate firstDayOfFebruary = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.PRESIDENTS_DAY.getMonth(), 1);
        //If the first day of January is a Monday, then we can use that date to find the second and third Monday
        //Otherwise, we have to find the first Monday of the month, and then chain the second and third Monday using that
        if (DayOfWeek.MONDAY.equals(firstDayOfFebruary.getDayOfWeek())){
            final LocalDate secondMondayofFebruary = firstDayOfFebruary.with(next(DayOfWeek.MONDAY));
            final LocalDate presidentsDay = secondMondayofFebruary.with(next(DayOfWeek.MONDAY));
            return presidentsDay.equals(p_workFromHomeDate);
        } else {
            final LocalDate firstMondayOfFebruary =  firstDayOfFebruary.with(next(DayOfWeek.MONDAY));
            final LocalDate secondMondayOfFebruary = firstMondayOfFebruary.with(next(DayOfWeek.MONDAY));
            final LocalDate presidentsDay = secondMondayOfFebruary.with(next(DayOfWeek.MONDAY));
            return presidentsDay.equals(p_workFromHomeDate);
        }
    }

    //Memorial Day occurs on the last Monday in the month of May.
    public static boolean isMemorialDay(final LocalDate p_workFromHomeDate){
        final LocalDate firstDayofMay = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.MEMORIAL_DAY.getMonth(), 1);
        final LocalDate memorialDay = firstDayofMay.with((TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY)));
        return memorialDay.equals(p_workFromHomeDate);
    }

    //Juneteenth occurs on June 19th.
    public static boolean isJuneteenth(final LocalDate p_workFromHomeDate){
        final LocalDate juneteenth = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.JUNETEENTH.getMonth(), HolidayEnum.JUNETEENTH.getDay());
        return IObservableHoliday.getObservableHolidayDate(juneteenth).equals(p_workFromHomeDate);
    }

    //Independence Day occurs on July 4th.
    public static boolean isIndependenceDay(final LocalDate p_workFromHomeDate){
        final LocalDate independenceDay = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.INDEPENDENCE_DAY.getMonth(), HolidayEnum.INDEPENDENCE_DAY.getDay());
        return IObservableHoliday.getObservableHolidayDate(independenceDay).equals(p_workFromHomeDate);
    }

    //Labor Day occurs on the first Monday in the month of September.
    public static boolean isLaborDay(final LocalDate p_workFromHomeDate){
        final LocalDate firstDayOfSeptember = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.LABOR_DAY.getMonth(), 1);
        return DayOfWeek.MONDAY.equals(firstDayOfSeptember.getDayOfWeek()) ?
                    firstDayOfSeptember.equals(p_workFromHomeDate) : firstDayOfSeptember.with(next(DayOfWeek.MONDAY)).equals(p_workFromHomeDate);
    }

    //Columbus Day is observed on the second Monday in the month of October.
    public static boolean isColumbusDay(final LocalDate p_workFromHomeDate){
        final LocalDate firstDayOfOctober = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.COLUMBUS_DAY.getMonth(), 1);
        if (DayOfWeek.MONDAY.equals(firstDayOfOctober.getDayOfWeek())){
            final LocalDate columbusDay = firstDayOfOctober.with(next(DayOfWeek.MONDAY));
            return columbusDay.equals(p_workFromHomeDate);
        } else {
            final LocalDate firstMondayOfOctober =  firstDayOfOctober.with(next(DayOfWeek.MONDAY));
            final LocalDate columbusDay = firstMondayOfOctober.with(next(DayOfWeek.MONDAY));
            return columbusDay.equals(p_workFromHomeDate);
        }
    }

    //Election Day occurs on the Tuesday immediately after the first Monday in November.
    public static boolean isElectionDay(final LocalDate p_workFromHomeDate){
        final LocalDate firstDayOfNovember = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.ELECTION_DAY.getMonth(), 1);
        //If the first day of November is a Monday, just find the following Tuesday to compare
        //Otherwise, find the first Monday of the month and then find the following Tuesday
        if (DayOfWeek.MONDAY.equals(firstDayOfNovember.getDayOfWeek())){
            return firstDayOfNovember.with(next(DayOfWeek.TUESDAY)).equals(p_workFromHomeDate);
        } else {
            final LocalDate firstMonday = firstDayOfNovember.with(next(DayOfWeek.MONDAY));
            final LocalDate electionDay = firstMonday.with(next(DayOfWeek.TUESDAY));
            return electionDay.equals(p_workFromHomeDate);
        }
    }

    //Veteran's Day occurs on November 11th.
    public static boolean isVeteransDay(final LocalDate p_workFromHomeDate){
        final LocalDate veteransDay = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.VETERANS_DAY.getMonth(), HolidayEnum.VETERANS_DAY.getDay());
        return IObservableHoliday.getObservableHolidayDate(veteransDay).equals(p_workFromHomeDate);
    }

    //Thanksgiving occurs on the fourth Thursday in the month of November.
    public static boolean isThanksgiving(final LocalDate p_workFromHomeDate){
        final LocalDate firstDayOfNovember = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.THANKSGIVING.getMonth(), 1);
        if (DayOfWeek.THURSDAY.equals(firstDayOfNovember.getDayOfWeek())){
            final LocalDate secondThursdayOfNovember = firstDayOfNovember.with(next(DayOfWeek.THURSDAY));
            final LocalDate thirdThursdayOfNovember = secondThursdayOfNovember.with(next(DayOfWeek.THURSDAY));
            final LocalDate thanskgiving = thirdThursdayOfNovember.with(next(DayOfWeek.THURSDAY));
            return thanskgiving.equals(p_workFromHomeDate);
        } else {
            final LocalDate firstThursdayOfNovember = firstDayOfNovember.with(next(DayOfWeek.THURSDAY));
            final LocalDate secondThursdayOfNovember = firstThursdayOfNovember.with(next(DayOfWeek.THURSDAY));
            final LocalDate thirdThursdayOfNovember = secondThursdayOfNovember.with(next(DayOfWeek.THURSDAY));
            final LocalDate thanskgiving = thirdThursdayOfNovember.with(next(DayOfWeek.THURSDAY));
            return thanskgiving.equals(p_workFromHomeDate);
        }
    }

    //Christmas occurs on December 25th.
    public static boolean isChristmas(final LocalDate p_workFromHomeDate){
        final LocalDate christmasDay = LocalDate.of(p_workFromHomeDate.getYear(), HolidayEnum.CHRISTMAS.getMonth(), HolidayEnum.CHRISTMAS.getDay());
        return IObservableHoliday.getObservableHolidayDate(christmasDay).equals(p_workFromHomeDate);
    }
}
