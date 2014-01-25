package ch.santo.howoldis.business;

import org.joda.time.*;



/**
 * Created by Oliver Santschi on 25.01.14.
 */
public class DateCalculator {

    public static String getAge() {

        LocalDate birthdate = new LocalDate (2006, 7, 11);

        LocalDate now = new LocalDate();
        Years yearsBetween = Years.yearsBetween(birthdate, now);
        Months monthsBetween = Months.monthsBetween(birthdate, now);
        Days daysBetween = Days.daysBetween(birthdate, now);

        int yearsinMonths = yearsBetween.getYears()*12;
        int months = monthsBetween.getMonths() - yearsinMonths;

        boolean test = isLeapYear(2000);
        boolean test2 = isLeapYear(2001);
        return String.valueOf(yearsBetween.getYears())+" Jahre "+String.valueOf(months)+" Monate";

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
