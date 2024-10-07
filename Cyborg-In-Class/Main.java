/**
 * Cyborg Inheritance Program
 * This program creates a unique sub-class of the 'Cyborg' class, and displays
 * the overridden function calls.
 *
 * Date Last Modified: 10/7/2024
 *
 * @author Josh Fernandez
 *
 * CS1131, Fall 2024
 * Lab Section 3
 */
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main( String[ ] args ) {
        Cyborg basicCyborg = new Cyborg( "Basic-01", "CyborgCorp" );
        Cyborg visionCyborg = new EnhancedVisionCyborg(
            "VisionX",
            "OptiTech",
            "Night Vision"
        );
        Cyborg fastCyborg = new SpeedCyborg(
            "Model X",
            "twitter.com",
            "Nike Air Forces"
        );

        List< Cyborg > cyborgs = new ArrayList< >( ) {{
            add( basicCyborg );
            add( visionCyborg );
            add( fastCyborg );
        }};

        for( Cyborg cyborg : cyborgs ) {
            cyborg.displayInfo( );
            cyborg.performTask( );
            System.out.println( );
        }
    }
}
