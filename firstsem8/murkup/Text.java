package murkup;

public class Text implements Expression {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }

    public void toHTML(StringBuilder sb) {
        sb.append(text);
    }

}
