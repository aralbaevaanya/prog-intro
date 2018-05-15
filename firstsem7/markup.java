package markup;

import java.util.Arrays;
import java.util.Collections;

public class markup {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        new Emphasis(
                Arrays.asList(
                        new Emphasis(
                                Arrays.asList(
                                        new Text("Bel"),
                                        new Text("Bec"))),
                        new Text("!")))
                .toMarkdown(sb);
        System.out.println(sb.toString());

        sb = new StringBuilder();
        new Emphasis(Collections.singletonList(
                new Emphasis(Arrays.asList(
                        new Text("Less"),
                        new Text(" "),
                        new Text("than"),
                        new Text(" "),
                        new Text("three")
                ))))
                .toMarkdown(sb);

        System.out.println(sb.toString());

        sb = new StringBuilder();

        Paragraph paragraph = new Paragraph(Collections.singletonList(
                new Strong(Arrays.asList(
                        new Text("1"),
                        new Strikeout(Arrays.asList(
                                new Text("2"),
                                new Emphasis(Arrays.asList(
                                        new Text("3"),
                                        new Text("4")
                                )),
                                new Text("5")
                        )),
                        new Text("6")
                ))
        ));

        paragraph.toMarkdown(sb);
        System.out.println(sb.toString());
    }


}

