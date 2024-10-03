import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PirateTranslator {

    final String END_OF_SENTENCE = ".!?";
    final String PUNCTUATION = ",;:" + END_OF_SENTENCE;
    
    final HashMap< List< String >, String > PIRATE_TRANSLATIONS = 
        new HashMap< >( ) {{
            put( List.of ( "hello" ), "ahoy" );
            put( List.of( "hi" ), "yo-ho-ho" );
            put( List.of( "my" ), "me" );
            put( List.of( "friend" ), "bucko" );
            put( List.of( "sir" ), "matey" );
            put( List.of( "miss" ), "proud beauty" );
            put( List.of( "stranger" ), "scurvy dog" );
            put( List.of( "officer" ), "foul blaggart" );
            put( List.of( "where" ), "whar" );
            put( List.of( "is", "are", "am" ), "be" );
        }};

    public String convertToPirateSpeak( String sentence ) {
        String[ ] loweredSentence = sentence.toLowerCase( ).split( " " );

        final String[ ] sentenceArray = sentence.split( " " );
        StringBuilder output = new StringBuilder( );
        Random random = new Random( );
        for ( int i = 0; i < loweredSentence.length; i++ ) {

            
            String lastChar = String.valueOf(
                sentenceArray[ i ].charAt( sentenceArray[ i ].length( ) - 1 )
            );

            String str = PUNCTUATION.contains( lastChar ) ?
                loweredSentence[ i ].substring(
                    0, loweredSentence[ i ].length( ) - 1
                ) : loweredSentence[ i ];

            if (!PIRATE_TRANSLATIONS
                .keySet( )
                .stream( )
                .anyMatch( n -> n.contains( str ) ||
                    n.contains( str.substring( 0, str.length( ) - 1 ) ) )
            ) {
                output.append( sentenceArray[ i ] + " " );
                continue;
            }

            String translation = PIRATE_TRANSLATIONS.get(
                PIRATE_TRANSLATIONS
                    .keySet( )
                    .stream( )
                    .filter( n -> n.contains( str ) ||
                        n.contains( str.substring( 0, str.length( ) - 1 ) ) )
                    .toArray( )[ 0 ]
            );
            if ( Character.isUpperCase( sentenceArray[ i ].charAt( 0 ) ) )
                translation = Character.toUpperCase( translation.charAt( 0 ) )
                    + translation.substring( 1 );

            output.append( translation );


            // Checks if we're at the end of a sentence
            if ( PUNCTUATION.contains( lastChar ) ) {
                if ( END_OF_SENTENCE.contains( lastChar ) &&
                    random.nextInt( 2 ) == 1 ) output.append( " ARRR" );
                output.append( lastChar );
            }
            if(i != loweredSentence.length - 1) output.append( " " );
        }

        return output.toString( );
    }
    
    public static void main( String[ ] args ) {
        PirateTranslator pirateTranslator = new PirateTranslator( );
        
        String[ ] sentences = {
            "Hi, my friend, hello. How are you?",
            "I asked the officer: \"Do you know, sir, " +
                "where my miss has gone?\"",
            "He said, \"Yes. I am sorry, but she is over there with" +
                " strangers, and she is sick with the dreaded flu.\"",
            // "stranger",
            // "stranger"
        };
        for ( int i = 0; i < sentences.length; i++ ) {
            String sentence = sentences[ i ];
            System.out.printf(
                "Original Sentence %d: %s\nPirate Sentence %d: %s\n",
                i + 1,
                sentence,
                i + 1,
                pirateTranslator.convertToPirateSpeak( sentence )
            );
        }

        // System.out.println(pirateTranslator.convertToPirateSpeak("stranger"));

        // System.out.println(pirateTranslator.convertToPirateSpeak("stranger").equals("scurvy dog"));
    }
}
