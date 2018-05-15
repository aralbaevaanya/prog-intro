import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.max;

public class ReverseSum {
    public static void main(String [] args){
        List<Integer> columnSum = new ArrayList<>();
        List<Integer> stringSum = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ex = new ArrayList<>();
        MyScanner in = new MyScanner();
        int numberstr = 0;
        int maxcolumn = -1;
        String s = in.readLine();
        while (s != null){
            stringSum.add(0);
            ArrayList<Integer> inex = new ArrayList<>();
            int k = -1; //кол-во чисел в строке
            int i = 0;
            while(i < s.length()){
                if (Character.isDigit(s.charAt(i)) || s.charAt(i) == '-' ) {
                    int l = i;
                    i++;
                    while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '-')) {
                        i++;
                    }
                    int a = Integer.parseInt(s.substring(l, i));
                    inex.add(a);
                    k++;
                    stringSum.set(numberstr,stringSum.get(numberstr)+a);
                    maxcolumn = max(maxcolumn,k);
                } else
                    i++;
            }
            ex.add(inex);
            numberstr++;
            s = in.readLine();
            }
        for (int i=0; i<= maxcolumn; i++){
            columnSum.add(0);
        }
        for (ArrayList<Integer> anEx : ex) {
            int p = maxcolumn - anEx.size(); //сдвиг
            for (int j = 0; j < anEx.size(); j++) {
                columnSum.set(j + p, columnSum.get(j + p) + anEx.get(j));
            }
        }

        for (int i = 0 ; i < ex.size(); i++){
            for (int j=0; j< ex.get(i).size() -1; j++){
                System.out.print(stringSum.get(i) + columnSum.get(j) - ex.get(i).get(j) + " ");
            }
            System.out.print("\n");
        }
    }
    static class MyScanner {
        BufferedReader into = new BufferedReader(new InputStreamReader(System.in));

        String readLine() {
            try {
                return into.readLine();
            } catch (IOException e) {
                return null;
            }
        }
    }
}
