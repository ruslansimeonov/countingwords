public class main {
    public static void main(String[] args) {
        CountingWords countingWords = new CountingWords();

        countingWords.calculateHighestFrequency("One word is oNE written");
        countingWords.calculateFrequencyForWord("One word is oNE written one is", "is");
        countingWords.calculateMostFrequentNWords("The sun shines over the lake", 2);

    }
}
