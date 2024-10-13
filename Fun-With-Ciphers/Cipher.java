/**
 * "Fun with Ciphers" Program
 * This program creates an instance of a Vigenere Cipher, which encodes and
 * decodes strings based on a given "key" value, which is used to find the
 * encoding value in a cipher table by iterating through the key and the string
 * to encode.
 *
 * Date Last Modified: 10/7/2024
 *
 * @author Josh Fernandez
 *
 * CS1131, Fall 2024
 * Lab Section 3
 */
public class Cipher {
    // INSTANCE VARIABLES

    // A string of letters to be split and reconstructed later
    // by a given offset
    private final String KEY_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String key;
    private int offset;
    
    // CONSTRUCTORS
    /**
    * Class constructor for the Cipher class. Constructs a new instance of the
    * Cipher class with a given cipher key and character code.
    *
    * @param code The character code to "offset" the given encryption of the
    *             letter rows.
    * @param key  The key to iterate through when encrypting and decrypting
    *             a given string for the cipher table.
    */
    public Cipher( char code, String key ) {
        // Initialize key and offset
        this.key = key;
        this.offset = KEY_LIST.indexOf( code );
    }

    /**
    * Returns a row of the cipher table from a given character, which is
    * derived from the initial alphabet string and the given offset of the
    * Cipher instance. The string returned with effectively be the alphabet
    * shifted by the offset, then appending the rest of the shifted string.
    *
    * @param  c The character to fetch the row of in the cipher table.
    * @return   A character array of the row from the cipher table.
    */
    private char [ ] getCipherRow( char c ) {
        int index = KEY_LIST.indexOf( String.valueOf( c ).toUpperCase( ) );
        
        index = ( index + offset ) -
            ( ( KEY_LIST.length( ) ) * ( int ) Math.floor( ( index + offset ) /
                (KEY_LIST.length( ) ) ) );
        return ( KEY_LIST.substring( index ) + KEY_LIST.substring( 0, index ) )
            .toCharArray( );
    }

    /**
    * Returns the specific character of the cipher table given two inputted
    * characters: the character from the key and the character of the message
    * being encrypted.
    *
    * @param  a The character input from the first dimension of the table.
    * @param  b The character input from the second dimension of the table.
    * @return   The character in the table where both the inputs meet.
    */
    private char getCharEncode( char a, char b ) {
        if( a == ' ' || b == ' ' ) return ' ';
        return getCipherRow( a )[
            KEY_LIST.indexOf( String.valueOf( b ).toUpperCase( ) )
        ];
    }

    /**
    * Returns the decoded value of the cipher table given the input from one
    * dimension of the table and the character within that input's row. This
    * method does the inverse of the 'getCharEncode' method, where instead of
    * getting the output from two inputs, the method returns an input from
    * one input and an output.
    *
    * @param  in  The input character of a dimension on the cipher table.
    * @param  out The output character found in the cipher row of the input
    *             character.
    * @return     The second input in the opposite dimension of the table as
    *             the first inputted character.
    */
    private char getCharDecode( char in, char out ) {
        if( in == ' ' || out == ' ' ) return ' ';

        return KEY_LIST.charAt(
            String.valueOf( getCipherRow( in ) ).indexOf( out )
        );
    }

    /**
    * DEAD CODE. Returns the cipher table based on the Cipher instance's
    * defined encoding code.
    *
    * @return The cipher table for the Cipher instance.
    */
    public char [ ] [ ] getCipherTable( ) {
        char [ ] [ ] out = new char [ 
                KEY_LIST.length( ) 
            ] [ 
                KEY_LIST.length( ) 
            ];
        
        for ( int i = 0; i < KEY_LIST.length( ); i++ )
            out [ i ] = getCipherRow( KEY_LIST.charAt( i ) );

        return out;
    }

    /**
    * DEAD CODE. Returns a char array of the cipher's key.
    *
    * @return The cipher key as a char array.
    */
    public char [ ] getKeyList( ) { return this.key.toCharArray( ); }

    // METHODS
    /**
    * Encrypts a given string based on the cipher table of the Cipher instance.
    *
    * @param  message The string to encode.
    * @return         The encoded string as iterated through the Cipher for
    *                 each of its characters.
    */
    public String encode( String message ) {
      String result = "";
      for ( int i = 0; i < message.length( ); i++ ) {
          int keyIndex = i - ( ( key.length( ) ) *
              ( int ) Math.floor( i / key.length( ) ) );
              
          result += getCharEncode(
              message.charAt( i ), key.charAt( keyIndex )
          );
      }
      return result;
    }

    /**
    * Decrypts a string based on the cipher table of the Cipher instance.
    *
    * @param  message The string to decode.
    * @return         The decoded string as iterated through each of its
    *                 characters to find the value against the Cipher
    *                 instance's key.
    */
    public String decode( String message ) {
      String result = "";
      for ( int i = 0; i < message.length(); i++ ) {
          int keyIndex = i - ( ( key.length( ) ) *
              ( int ) Math.floor( i / key.length( ) ) );

          result += getCharDecode(
              key.charAt( keyIndex ),
              message.charAt( i )
          );
      }
      return result;
    }

    // MAIN (TEST) Method
    /**
    * The main method for the program. This method serves as the entry-point
    * for the program's execution.
    *
    * @param args The array of arguments supplied to the java program.
    */
    public static void main( String[ ] args ) {      
        // Testing only works if using VM argument: java -ea Cipher
        Cipher self = new Cipher( 'H', "BABBAGE" );
        assert "PHXXF MQYBPKNJ".equals( self.encode( "HAPPY BIRTHDAY" ) );
        assert "HAPPY BIRTHDAY".equals( self.decode( "PHXXF MQYBPKNJ" ) );
    }
} // END OF CLASS --------------------------------------------------------
