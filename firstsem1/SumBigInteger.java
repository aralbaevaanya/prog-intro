import java.math.BigInteger;

public class SumBigInteger {
    public static void main(String [] args){
        BigInteger sum = BigInteger.ZERO;
        String s=null;
        int i=0; BigInteger a;
        for (String namber : args){
            while (i<namber.length()){
                a= BigInteger.ZERO;
                if (namber.charAt(i)=='-') {
                    while (Cheracter.namber.charAt(i).isDigit()) {
                        a.multiply(a, 10);
                        a.add(a, Integer.parseInt(namber[i]));
                        i++;
                    }
                    a.multiply(a,(-1));
                }
                else {
                    while (Cheracter.namber.charAt(i).isDigit()) {
                        a.multiply(a, 10);
                        a.add(a, Integer.parseInt(namber[i]));
                        i++;
                    }
                }
                sum.add(a);
                i++;

            }

        }
        System.out.println(sum.toString());
    }
}