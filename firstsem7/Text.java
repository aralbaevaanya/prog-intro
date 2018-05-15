package markup;

import java.util.List;

public class Text implements Expression {
    private String text;

    Text(String text){
        this.text = text;
    }

    public void toMarkdown(StringBuilder sb){
        sb.append(text);
    }
    public void toTex(StringBuilder sb){
        sb.append(text);
    }
}
