import java.io.*;
import java.util.*;

public class WordStatIndex {
    public static void main(String[] args) {
        try {
            LinkedHashMap<String, ArrayList<Integer>> ans = new LinkedHashMap<>();
            Integer wordCount = 0;

            Scanner reader = new Scanner(new File(args[0]), "UTF-8");
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] words = line.split("[^\\p{L}\\p{Pd}']+");

                for (String word : words) {
                    word = word.toLowerCase();
                    if (word.isEmpty()) {
                        continue;
                    }
                    if (ans.get(word) == null) {
                        ArrayList<Integer> toPut = new ArrayList<>();
                        toPut.add(++wordCount);
                        ans.put(word, toPut);
                    }
                    else {
                        ans.get(word).add(++wordCount);
                    }
                }
            }
            reader.close();

            PrintWriter writer = new PrintWriter(new File (args[1]), "UTF-8");
            for (Map.Entry<String, ArrayList<Integer>> word : ans.entrySet()) {
                writer.print(word.getKey() + " " + word.getValue().size());
                for (Integer i : word.getValue()) {
                    writer.print(" " + i);
                }
                writer.println();
            }
            writer.close();
        }
        catch (IOException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
