import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.previous;

interface IObservableHoliday {
     static LocalDate getObservableHolidayDate(LocalDate p_holidayDate) {
          if (DayOfWeek.SATURDAY.equals(p_holidayDate.getDayOfWeek())){
               return p_holidayDate.with(previous(DayOfWeek.FRIDAY));
          }
          else if (DayOfWeek.SUNDAY.equals(p_holidayDate.getDayOfWeek())){
               return p_holidayDate.with(next(DayOfWeek.MONDAY));
          }
          else {
               return p_holidayDate;
          }
     }
}
