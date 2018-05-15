import java.io.*;
import java.util.*;

public class WordStatCount {
    public static void main(String[] args) {
        try {
            LinkedHashMap<String, Integer> wordStat = new LinkedHashMap<>();

            Scanner input = new Scanner(new File(args[0]), "UTF-8");
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] words = line.split("[^\\p{L}\\p{Pd}']+");

                for (String word : words) {
                    if (!word.isEmpty()) {
                        word = word.toLowerCase();
                        wordStat.put(word, wordStat.getOrDefault(word, 0) + 1);
                    }
                }
            }
            input.close();

            ArrayList<Pair> sortStat = new ArrayList<>();
            for (Map.Entry<String, Integer> word : wordStat.entrySet()) {
                sortStat.add(new Pair(word.getKey(), word.getValue()));
            }

            for (int i = 0; i < sortStat.size(); ++i) {
                for (int j = sortStat.size() - 1; j > 0; --j) {
                    if (sortStat.get(j - 1).val > sortStat.get(j).val) {
                        Pair tmp = sortStat.get(j);
                        sortStat.set(j, new Pair(sortStat.get(j - 1).key, sortStat.get(j - 1).val));
                        sortStat.set(j - 1, new Pair(tmp.key, tmp.val));
                    }
                }
            }

            PrintWriter output = new PrintWriter(new File (args[1]), "UTF-8");
            for (Pair word : sortStat) {
                output.println(word.key + " " + word.val);
            }
            output.close();
        }
        catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static class Pair {
        String key;
        Integer val;
        public Pair() {
            key = "";
            val = 0;
        }
        public Pair(String key, Integer val) {
            this.key = key;
            this.val = val;
        }
    }
}