public class CountVowels {

    static final String VOWELS = "aeiou";
    
    public static void main(String[] args) {
        String text = "Hello";
        // int vowelCount = 0;
        
        // for(char c : text.toLowerCase().toCharArray()) {
        //     if(VOWELS.indexOf(c) != -1) {
        //         vowelCount++;
        //     }
        // }

        System.out.println(
            "The number of vowels in the string is: " + vowelCount(text)
        );
        
        System.out.println(
            "The number of consonents in the string is: " +
            consonantCount(text)
        );
    }

    public static int vowelCount( String str ) {
        int count = 0;
        for ( char c : str.toLowerCase().toCharArray() )
            if ( VOWELS.indexOf( c ) != -1 ) count++;

        return count;
    }

    public static int consonantCount( String str ) {
        int letterCount = 0;
        for ( char c : str.toLowerCase().toCharArray() )
            if ( Character.isLetter(c) ) letterCount++;

        return letterCount - vowelCount(str); 
    }
}
