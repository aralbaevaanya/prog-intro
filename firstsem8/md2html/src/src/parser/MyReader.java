package parser;

import java.io.BufferedReader;
import java.io.IOException;

public class MyReader {
    private BufferedReader in;
    private boolean isEOF;
    private int ch;

    MyReader(BufferedReader in) throws IOException {
        this.in = in;
        isEOF = false;
        ch = in.read();
    }

    public boolean hasNextLine() {
        return !isEOF;
    }

    public String getNextLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (ch != -1 && ch != '\n') {
            sb.append((char) ch);
            ch = in.read();
        }
        if (ch == -1){
            isEOF = true;
        } else {
            ch = in.read();
        }
        return sb.toString();
    }
}
