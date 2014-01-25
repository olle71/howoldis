package ch.santo.howoldis.business;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.joda.time.LocalDate;

/**
 * Created by Oliver Santschi on 25.01.14.
 */
public class DateCalculatorTest extends TestCase {
    public void testGetAge() throws Exception {

    }

    public void testIsLeapYear() throws Exception {
        Assert.assertFalse(DateCalculator.isLeapYear(1999));
        Assert.assertTrue(DateCalculator.isLeapYear(2000));
        Assert.assertTrue(DateCalculator.isLeapYear(2004));
        Assert.assertFalse(DateCalculator.isLeapYear(2001));
    }

    public void testisBirthyearCountsasLeapYear() throws Exception{
        Assert.assertTrue(DateCalculator.isBirthyearCountsasLeapYear(new LocalDate(2000,1,1)));
        Assert.assertTrue(DateCalculator.isBirthyearCountsasLeapYear(new LocalDate(2000,2,29)));
        Assert.assertFalse(DateCalculator.isBirthyearCountsasLeapYear(new LocalDate(2000,3,1)));
        Assert.assertFalse(DateCalculator.isBirthyearCountsasLeapYear(new LocalDate(2001,1,31)));
    }
}
