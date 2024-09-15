import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

/**
 * Week3Program: Solves problems in RadioCarbon Dating
 *
 * @author Josh Fernandez
 *         CS1131, R01
 */
public class Week3Program {

    final double C_14_HALF_LIFE = 5730d;
    
    /**
     * Given the age of an artifact in years, return the percent of C14 left in it. Here a percentage is a
     * double in the range [0.0, 1.0]. Remember that N / N0 is the percent of C14 remaining in the object.
     * @param age - the age of an artifact in years
     * @return the percent of C14 remaining in the artifact
     */
    double radiocarbonPercent ( int age ) {
        // Rounding the output to 2 decimal places for proper percentage parsing
        // See method definition below.
        return roundToN( Math.pow( 0.5, age / C_14_HALF_LIFE ), 2 );
    }

    /**
     * Given the percentage of C14 remaining in an artifact, return the age of the artifact in years.
     * Here a percentage is a double in the range [0.0, 1.0].
     * Your result should be truncated, not rounded.
     * @param p - the percentage of C14 remaining in an artifact
     * @return the age of the artifact in years
     */
    int radiocarbonAge ( double p ) {
        return (int) ( ( Math.log( p ) / Math.log( 0.5 ) ) * C_14_HALF_LIFE );
    }

    /**
     * Given the percentage of C14 remaining in an artifact, return the year of the artifact was created or the
     * organism died. The date is the current year minus the age of the artifact.
     * Your result should be truncated, not rounded.
     * @param p the percentage of C14
     * @return The date is the current year minus the age of the artifact.
     */
    int radiocarbonDate ( double p ) {
        return Calendar
            .getInstance().get( Calendar.YEAR ) - radiocarbonAge( p );
    }

    /**
    * Convenience function for rounding a double to N decimal places
    * @param input The double to be rounded.
    * @param decimalPlaces The number of decimal places to round to. Ex. 2 will round the hundreths place.
    * @return The input double rounded to N decimal places.
    */
    double roundToN ( double input, int decimalPlaces ) {
        return BigDecimal
            .valueOf( input ).setScale( 2, RoundingMode.HALF_UP ).doubleValue();
    }

    // Test code
    public static void main ( String [ ] args ) {
        Week3Program self = new Week3Program( );

        System.out.println( "Test radiocarbonPercent( age )." );
        int age = 1845;
        double percentage = self.radiocarbonPercent( age );
        System.out.printf( "radiocarbonPercent( %d ) = %f%%. ", age, percentage );
        if ( (int) (percentage * 100) == 80 ) {
            System.out.println( "Success!\n" );
        } else {
            System.out.println( "FAILED!\n" );
        }

        System.out.println( "Test radiocarbonAge( percentage )." );
        percentage = 0.80;
        age = self.radiocarbonAge( percentage );
        System.out.printf( "radiocarbonAge( %f ) = %d years old. ", percentage, age );
        if ( Math.abs(age - 1845) <= 1.0 ) {
            System.out.println( "Success!\n" );
        } else {
            System.out.println( "FAILED!\n" );
        }

        System.out.println( "Test radiocarbonDate( percentage )." );
        percentage = 0.80;
        int year = self.radiocarbonDate( percentage );
        age = 1845;
        System.out.printf( "radiocarbonDate( %f ) = the year %d. ", percentage, year );
        if ( Math.abs( year - (Calendar.getInstance().get(Calendar.YEAR) - age ) ) <= 5.0) {
            System.out.println( "Success!\n" );
        } else {
            System.out.println( "FAILED!\n" );
        }
    }

}
