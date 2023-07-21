package hashtable;

public class Exercises {
    public static String mostFrequent(String sentence) {
        var map = new MyHashtable<String, Integer>(10);

        String mostFrequentWord = null;
        int largestFrequency = 0;
        StringBuilder lastWord = new StringBuilder();
        for (int i = 0; i <= sentence.length(); i++) {
            if (i == sentence.length() || Character.isWhitespace(sentence.charAt(i))) {
                if (!lastWord.isEmpty()) {
                    String word = lastWord.toString();

                    Integer value = map.get(word);
                    map.put(word, value = (value == null ? 1 : value + 1));
                    if (value > largestFrequency) {
                        largestFrequency = value;
                        mostFrequentWord = word;
                    }

                    lastWord.delete(0, lastWord.length());
                }
            } else {
                lastWord.append(sentence.charAt(i));
            }
        }

        return mostFrequentWord;
    }

    public static int countPairsWithDiff(int[] elements, int k) {
        var numbers = new MyHashSet<Integer>();
        for (int element : elements)
            numbers.add(element);

        int count = 0;
        for (int element : elements) {
            if (!numbers.contains(element))
                continue;

            if (numbers.contains(element + k))
                count++;
            if (numbers.contains(element - k))
                count++;

            numbers.remove(element);
        }

        return count;
    }

    public static int[] twoSum(int[] input, int target) {
        var map = new MyHashtable<Integer, Integer>(input.length);
        for (int i = 0; i < input.length; i++) {
            Integer pairIndex = map.get(input[i]);
            if (pairIndex != null)
                return new int[] { pairIndex, i };
            map.put(target - input[i], i);
        }
        return null;
    }
}
