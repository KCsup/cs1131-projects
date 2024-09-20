import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class IceCream {

    final HashMap< String, Double > FLAVORS = new HashMap< >( ) {{
        put( "", 0d );
        put( "Chocolate ", 1.75 );
        put( "Vanilla ", 1.15 );
        put( "Strawberry ", 1.15 );
    }};

    final HashMap< String, Double > TOPPINGS = new HashMap< >( ) {{
        put( "Sprinkles ", 0.15 );
        put( "Whipped-Cream ", 0.35 );
        put( "Chocolate-Chips ", 0.40 );
    }};
    
    // returns the number of combinations
    public long printMenu( ) {
        long count = 0;

        for ( String flavor : FLAVORS.keySet( ) ) {
            for (
                String toppingsComb : getBinaryCombinations( TOPPINGS.size( ) )
            ) {
                String menuItem = flavor;
                double cost = FLAVORS.get( flavor );
                for ( int c = 0; c < toppingsComb.length( ); c++ ) {
                    if ( toppingsComb.charAt( c ) == '1' ) {
                        String topping = ( String ) TOPPINGS
                            .keySet( ).toArray( )[ c ];
                        menuItem += topping;
                        cost += TOPPINGS.get( topping );
                    }
                }
                
                System.out.printf(
                    "%02d %-60s$%.2f\n",
                    count,
                    menuItem,
                    BigDecimal.valueOf( cost )
                        .setScale( 2, RoundingMode.HALF_UP )
                        .doubleValue( )
                );
                count += 1;
            }
        }

        return count;
    }


    private String getBinaryCombination(
        int index,
        int length
    ) {
        return String.format(
            "%" + length + "s",
            Integer.toBinaryString( index )
        ).replace( ' ', '0' );
    }

    private String[ ] getBinaryCombinations( int length ) {
        int maxNum = ( int ) Math.pow( 2, length );
        
        String[ ] combs = new String[ maxNum ];
        for ( int i = 0; i < maxNum; i++ ) {
            combs[ i ] = getBinaryCombination( i, length );
        }

        return combs;
    }
    
    public static void main( String [ ] args ) {
        IceCream obj = new IceCream( );
        long count = obj.printMenu( );
        // System.out.println(count);
        assert count == 32;
    }
}
