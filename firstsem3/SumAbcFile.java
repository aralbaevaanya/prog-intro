import javax.print.DocFlavor;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;


public class SumAbcFile {
    private static boolean fileend = false;

    public static void main(String args[]) {

        if (args.length < 1) {
            System.err.format("NotFoundPathOfInputFile");
            return;
        }

        String par = args[0];
        Path f = Paths.get(par);
        if (!Files.isReadable(f)) {
            System.err.format("SecurityExceptionInput");
            return;
        }
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(par)));
            int ans = 0;

            int cur = in.read();
            //System.out.println(cur);
            while (cur != -1) {
                try {
                    if (!Character.isWhitespace(cur)) {
                        int temp = 0;
                        boolean isNegate = false;
                        cur = Character.toLowerCase((char) cur);
                        if (cur == '-') {
                            isNegate = true;
                            cur = Character.toLowerCase((char) in.read());
                        }
                        while (cur >= 'a' && cur <= 'j' || Character.isDigit(cur)) {
                            temp = 10 * temp + cur - (Character.isDigit(cur) ? '0' : 'a');
                            cur = Character.toLowerCase((char) in.read());
                            //     System.out.println(cur);
                        }
                        ans += isNegate ? -temp : temp;
                    }
                    cur = in.read();
                    //System.out.println(cur);
                } catch (InputMismatchException e) {
                    System.out.println("File contains non-integers");
                }
            }

            in.close();
            Path f1;
            try {
                f1 = Paths.get(args[1]);
                par = args[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                f1 = Paths.get("output");
                par = "output";
            }
            if (!Files.isWritable(f1)) {
                new File(par);
            }
            FileWriter out = new FileWriter(par);
            out.write(Integer.toString(ans));
            out.close();
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}