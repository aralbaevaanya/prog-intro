import java.util.Collections;

public class Test {
    public static void main(String[] args){
        StringBuilder stringBuilder = new StringBuilder();
        new Paragraph(Collections.singletonList(new Emphasis(Collections.singletonList(new Text("bchs"))))).toTex(stringBuilder);
        System.out.println(stringBuilder.toString());
    }
}
