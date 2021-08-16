import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class WordAnalyzerTests {

    @Test
    public void testCalculateFrequency(){
        CountingWords countingWords = new CountingWords();
        String msg1 = "The sun shines over the lake";
        String msg2 = "The sun shines over the lake two times";
        Map<String, Integer> map = new HashMap<>();
        map.put("the", 2);
        map.put("over", 1);
        map.put("sun", 1);
        map.put("lake", 1);
        map.put("shines", 1);

        Assert.assertEquals(countingWords.calculateFrequency(msg1), map);
        Assert.assertNotEquals(countingWords.calculateFrequency(msg2), map);
    }

    @Test
    public void testCalculateFrequencyForWord(){
        CountingWords countingWords = new CountingWords();
        String msg1 = "The sun shines over the lake";
        String msg2 = "The sun shines over the lake two times";

        Assert.assertEquals(countingWords.calculateFrequencyForWord(msg1,"sun"), 1);
        Assert.assertEquals(countingWords.calculateFrequencyForWord(msg2,"the"), 2);
        Assert.assertNotEquals(countingWords.calculateFrequencyForWord(msg1,"the"), 4);
    }

    @Test
    public void testCalculateHighestFrequency(){
        CountingWords countingWords = new CountingWords();
        String msg1 = "The sun shines over the lake";
        String msg2 = "The sun shines over the lake two times";

        Assert.assertEquals(countingWords.calculateHighestFrequency(msg1), 2);
        Assert.assertEquals(countingWords.calculateHighestFrequency(msg2), 2);
        Assert.assertNotEquals(countingWords.calculateHighestFrequency(msg1), 4);
    }

    @Test
    public void testCalculateMostFrequentNWords(){

    }
}
