package md2html;

import parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Md2Html {
    public static void main(String[] args) throws Exception{
        String path1 = args[0];
        String path2 = args[1];
        StringBuilder sb = new StringBuilder();
        Scanner in = new Scanner(new File(path1));
        PrintWriter out = new PrintWriter(new FileWriter(path2));
        new Parser(in).parse().toHTML(sb);
        out.print(sb.toString());
        in.close();
        out.close();
    }
}
