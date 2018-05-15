import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SumAbcFile {
    private static final boolean ofCourse = false;//if true resultOf("EZEZ Real Talk") would be IsNotSuitableNumberException
    // otherwise 48 (E equals 4, ea equals 40, so 4+4+40 = 48

    private static boolean isGood(int ch, final boolean isofCourse) {
        ch = Character.toLowerCase(ch);
        return !isofCourse ? (ch >= 'a' && ch <= 'j' || Character.isDigit(ch) || ch == '-') : (!Character.isWhitespace(ch) && ch != -1);
    }

    public static void main(String[] args) {
        String s = null;
        try {
            s = args[0];
        } catch (IndexOutOfBoundsException e) {
            System.err.format("Path of input File not found");
            return;
        }
        Path p = Paths.get(s);
        if (!Files.isReadable(p)) {
            System.err.format("File isn't readable");
            return;
        }
        int ans = 0;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(s)));
            int cur = in.read();
            while (cur != -1) {
                if (isGood(cur, ofCourse)) {
                    cur = Character.toLowerCase((char) cur);
                    StringBuilder sb = new StringBuilder();
                    while (isGood(cur, ofCourse)) {
                        cur = Character.toLowerCase(cur);
                        if (Character.isLetter(cur)) {
                            sb.append(cur - 'a');
                        } else {
                            if (Character.isDigit(cur)) {
                                sb.append(cur - '0');
                            } else {
                                sb.append((char) cur);
                            }
                        }
                        cur = in.read();
                    }
                    try {
                        System.out.println(sb.toString());
                        ans += Integer.parseInt(sb.toString());
                    } catch (Exception e) {
                        System.err.format(sb.toString() + " isn't a suitable number");
                    }
                }
                cur = in.read();
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.err.format("File " + s + " not found");
            return;
        } catch (IOException e) {
            System.err.format("IOException");
            return;
        }


        s = args.length >= 2 ? args[1] : "output";

        p = Paths.get(s);

        if (!Files.isWritable(p)) {
            new File(s);
        }
        try {
            FileWriter out = new FileWriter(s);
            out.write(Integer.toString(ans));
            out.close();
        } catch (IOException e) {
            System.err.format("IOException");
        }
    }
}

