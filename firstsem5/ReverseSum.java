import sum.MyScanner;

import java.io.IOException;
import java.util.ArrayList;

public class ReverseSum {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
        MyScanner in = new MyScanner();
        Integer width = 0;
        ArrayList<Integer> intLine = new ArrayList<>();
        try {
            while (in.hasNext()) {
                if (in.nextString()) {
                    lines.add(intLine);
                    width = Math.max(width, intLine.size());
                    intLine.clear();
                }
                intLine.add(in.nextInt());

            }
            if (intLine.size() > 0) {
                lines.add(intLine);
                width = Math.max(width, intLine.size());
            }
        } catch (IOException e) {
            System.err.println("IOE");
        }

        Integer[] sum_i = new Integer[lines.size()];
        for (Integer i = 0; i < lines.size(); ++i) {
            sum_i[i] = 0;
        }
        Integer[] sum_j = new Integer[width];
        for (Integer j = 0; j < width; ++j) {
            sum_j[j] = 0;
        }
        int shift;
        for (int i = 0; i < lines.size(); ++i) {
            for (int j = 0; j < lines.get(i).size(); ++j) {
                shift = width - lines.get(i).size();
                sum_i[i] += lines.get(i).get(j);
                sum_j[j + shift] += lines.get(i).get(j);
            }
        }

        for (int i = lines.size() - 1; i >= 0; --i) {
            for (int j = lines.get(i).size() - 1; j >= 0; --j) {
                System.out.print(sum_i[i] + sum_j[j] - lines.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}