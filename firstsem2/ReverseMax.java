import java.util.Scanner;
import java.util.*;

import static java.lang.Integer.max;

public class ReverseMax {
    public static void main(String [] args){
        List<Integer> column = new ArrayList<>();
        List<Integer> string = new ArrayList<>();
        List<Integer> ex = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int numberstr = 0;
        while (in.hasNextLine()){
            String s = in.nextLine();
            string.add(Integer.MIN_VALUE);
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
                    k++;
                    if (a > string.get(numberstr)) {
                        string.set(numberstr, a);
                    }
                    if (k > column.size()-1){
                        column.add(a);
                    } else if (a > column.get(k)){
                        column.set(k, a);
                    }
                } else {
                    i++;
                }
            }
            ex.add(k);
            numberstr++;
        }
        in.close();
        for (int i = 0; i < numberstr; i++){
            for (int j = 0; j <= ex.get(i); j++){
                System.out.print(max(string.get(i), column.get(j)) + " ");
            }
            System.out.print("\n");
        }
    }
}
