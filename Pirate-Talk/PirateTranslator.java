import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PirateTranslator {

    static final String END_OF_SENTENCE = ".!?";
    static final String PUNCTUATION = ",;:" + END_OF_SENTENCE;
    
    static final HashMap<List<String>, String> PIRATE_TRANSLATIONS = 
        new HashMap<>() {{
            put(List.of("hello"), "ahoy");
            put(List.of("hi"), "yo-ho-ho");
            put(List.of("my"), "me");
            put(List.of("friend"), "bucko");
            put(List.of("sir"), "matey");
            put(List.of("miss"), "proud beauty");
            put(List.of("stranger"), "scurvy dog");
            put(List.of("officer"), "foul blaggart");
            put(List.of("where"), "whar");
            put(List.of("is", "are", "am"), "be");
        }};

    public static String convertToPirateSpeak( String sentence ) {
        String[] loweredSentence = sentence.toLowerCase().split(" ");

        final String[] sentenceArray = sentence.split(" ");
        StringBuilder output = new StringBuilder();
        Random random = new Random();
        for ( int i = 0; i < loweredSentence.length; i++ ) {

            
            String lastChar = String.valueOf(
                sentenceArray[i].charAt(sentenceArray[i].length() - 1)
            );

            String str = PUNCTUATION.contains(lastChar) ?
                loweredSentence[i].substring(
                    0, loweredSentence[i].length() - 1
                ) : loweredSentence[i];

            if(!PIRATE_TRANSLATIONS
                .keySet()
                .stream()
                .anyMatch(n -> n.contains(str))
            ) {
                output.append(sentenceArray[i] + " ");
                continue;
            }

            String translation = PIRATE_TRANSLATIONS.get(
                PIRATE_TRANSLATIONS
                    .keySet()
                    .stream()
                    .filter(n -> n.contains(str))
                    .toArray()[0]
            );
            if(Character.isUpperCase(sentenceArray[i].charAt(0)))
                translation = Character.toUpperCase(translation.charAt(0))
                    + translation.substring(1);

            output.append(translation);


            // Checks if we're at the end of a sentence
            if(PUNCTUATION.contains(lastChar)) {
                if(END_OF_SENTENCE.contains(lastChar) && random.nextInt(2) == 1) output.append(" ARRR");
                output.append(lastChar); 
            }
            output.append(" ");
        }

        return output.toString();
    }
    
    public static void main( String[ ] args ) {
        String sentence = "Will you be my Friend, sir?";
        System.out.printf(
            "Original Sentence: %s\nPirate Sentence: %s\n",
            sentence,
            convertToPirateSpeak(sentence)
        );
    }
}
