import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MyScanner {
    private BufferedReader input;
    private int ch;
    private boolean EOF = false;

    boolean isEOF() {
        return EOF;
    }

    MyScanner() throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in));
        ch = input.read();
    }

    boolean goNextLine() throws IOException {
        while (ch != -1 && ch != '-' && !Character.isDigit(ch) && ch != '\n') {
            ch = input.read();
        }

        if (ch == -1) {
            EOF = true;
            return false;
        }

        if (ch == '\n') {
            ch = input.read();

            return !EOF;
        }
        return false;
    }

    int nextInt() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(ch) || ch == '-') {
            sb.append((char) ch);
            ch = input.read();
        }
        return Integer.parseInt(sb.toString());
    }

}