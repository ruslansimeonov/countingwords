import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CountingWords implements WordFrequencyAnalyzer{

    static Logger logger = Logger.getLogger(CountingWords.class.getName());

    // CalculateHighestFrequency should return the highest frequency in the text (several
    // words might have this frequency)
    @Override
    public int calculateHighestFrequency(String text) {
        Map<String, Integer> wordFrequency = calculateFrequency(text);
        Map.Entry<String, Integer> maxFrequency = null;

        try{
            for(Map.Entry<String, Integer> frequency : wordFrequency.entrySet()){
                if(maxFrequency == null || frequency.getValue().compareTo(maxFrequency.getValue()) > 0){
                    maxFrequency = frequency;
                }
            }
            System.out.println("Highest Frequency = " + maxFrequency.getValue());
        } catch (IllegalArgumentException illegalArgumentException){
            logger.log(Level.SEVERE, "Illegal Argument Exception Thrown @ calculateHighestFrequency()");
        } catch(Exception e){
            logger.log(Level.SEVERE, "Exception thrown @ calculateHighestFrequency()");
        }

        assert maxFrequency != null;
        return maxFrequency.getValue();
    }

    // CalculateFrequencyForWord should return the frequency of the specified word
    @Override
    public int calculateFrequencyForWord(String text, String word) {
        Map<String, Integer> wordFrequency = calculateFrequency(text);
        System.out.println("Highest frequency for word: " + wordFrequency.getOrDefault(word, 0));

        return wordFrequency.getOrDefault(word, 0);

    }

    //CalculateMostFrequentNWords should return a list of the most frequent „n‟ words in
    //the input text, all the words returned in lower case. If several words have the same
    //frequency, this method should return them in ascendant alphabetical order (for input
    //text “The sun shines over the lake” and n = 3, it should return the list {(“the”, 2),
    //(“lake”, 1), (“over”, 1) }

    // Sort Map - DONE
    // Filter by number of n words - DONE
    // Sort by ascendant alphabetical order - DONE
    // Add to List - DONE

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        Map<String, Integer> unsortedWordFrequency = calculateFrequency(text);
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
        List<WordFrequency> sortedWordFrequency = new ArrayList<>();

        try{
            // Give highest number and change data type to LinkedHashMap
            // Why Linked hash map - maintaining an order of elements inserted into it.
            unsortedWordFrequency.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

            // Reverse
            Map<String, Integer> resultMap = reverseSortedMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue()
                            .reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .limit(n)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));

            System.out.println("Most Frequent N Words: " + resultMap);

            for(Map.Entry<String, Integer> entry : resultMap.entrySet()){
                WordFrequency word = new WordFrequency() {
                    @Override
                    public String getWord() {
                        return entry.getKey();
                    }

                    @Override
                    public int getFrequency() {
                        return entry.getValue();
                    }
                };
                sortedWordFrequency.add(word);
            }
        } catch (IllegalArgumentException illegalArgumentException){
            logger.log(Level.SEVERE, "Illegal Argument Exception Thrown @ calculateMostFrequentNWords()");
        } catch (Exception e){
            logger.log(Level.SEVERE, "Exception Thrown @ calculateMostFrequentNWords()");
        }


        return sortedWordFrequency;
    }

    protected Map<String, Integer> calculateFrequency(String text){
        // Different character separators - https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/regex/Pattern.html#:~:text=lq-z%5D(subtraction)-,Predefined%20character%20classes,-.
        Map<String, Integer> words = new HashMap<>();
        try{
            String[] splitText = text.toLowerCase(Locale.ROOT).split("\\W");
            for (String word : splitText){
                if(words.containsKey(word)){
                    words.put(word, 1 + words.get(word));
                }
                else{
                    words.put(word, 1);
                }
            }
        } catch(Exception e){
            logger.log(Level.SEVERE, "Exception thrown @ calculateFrequency");
        }

        return words;
    }

}
