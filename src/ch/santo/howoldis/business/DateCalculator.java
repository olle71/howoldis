package ch.santo.howoldis.business;

import org.joda.time.*;



/**
 * Created by Oliver Santschi on 25.01.14.
 */
public class DateCalculator {

    public static String getAge(LocalDate birthdate) {


        LocalDate now = new LocalDate();
        Years yearsBetween = Years.yearsBetween(birthdate, now);

        LocalDate birthdaythisyear = new LocalDate(now.getYear(),birthdate.getMonthOfYear(),birthdate.getDayOfMonth());
        LocalDate birthdaylastyear = new LocalDate(now.getYear()-1,birthdate.getMonthOfYear(),birthdate.getDayOfMonth());

        Months monthsBetween;
        if (birthdaythisyear.isBefore(now)) {
            monthsBetween = Months.monthsBetween(birthdaythisyear, now);
        }
        else if (birthdaythisyear.isAfter(now)) {
            monthsBetween = Months.monthsBetween(birthdaylastyear, now);
        } else  {
            monthsBetween = Months.months(0);
        }

        LocalDate birthdaythismonth = new LocalDate(now.getYear(), now.getMonthOfYear(), birthdate.getDayOfMonth());
        Days daysBetween = Days.daysBetween(birthdaythismonth, now);

        //if the actual day is befor the birthdaythismonth, the value is below 0. Switch it to positive
        int days = daysBetween.getDays();
        if (days<0) days /= (-1);

        return String.valueOf(yearsBetween.getYears())+" Jahre "+
               String.valueOf(monthsBetween.getMonths())+" Monate "+
                String.valueOf(days)+" Tage";

    }

    /**
     * Checks if the given year is a leap year
     * @param year as int
     * @return true: is a leap year (366 days)
     * @return false: is a normal year (365 days)
     */
    protected static boolean isLeapYear(int year) {
        LocalDate startYear = new LocalDate(year,1,1);
        LocalDate endYear = new LocalDate(year+1,1,1); //need the next day of the new year, otherwise a normal year has only 364 days (between)
        Days daysBetween = Days.daysBetween(startYear,endYear);
        return daysBetween.isGreaterThan(Days.days(365));
    }

    /**
     * Checks if in the year of birth there was the 29th of february
     * In this case the year has to count as leap year
     * @param birthdate as LocalDate
     * @return true if its a leap year and the birth was before or on the 29th of february
     * @return false if its not a leap year or the birth was after the 29th of february
     */
    protected static boolean isBirthyearCountsasLeapYear(LocalDate birthdate) {
        if (!isLeapYear(birthdate.getYear())) return false; //if not leap year go out with false

        //Its a leap year, so check is the birth is after or before
        return !birthdate.isAfter(new LocalDate(birthdate.getYear(), 2, 29));
    }
}
