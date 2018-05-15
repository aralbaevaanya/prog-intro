import java.util.List;

public class AllMoves implements Expression {
    private List<Expression> expressions;
    private String markDownFirst;
    private String markDownSecond;
    private String HTML;


    AllMoves(List<Expression> expressions, String markDownFirst,String markDownSecond, String HTML){
        this.expressions = expressions;
        this.HTML = HTML;
        this.markDownSecond = markDownSecond;
        this.markDownFirst = markDownFirst;
    }


    public void toMarkdown(StringBuilder sb) {
        sb.append(markDownFirst);
        for (Expression expression: expressions){
            expression.toMarkdown(sb);
        }
        sb.append(markDownSecond);
    }

    public void toHTML (StringBuilder sb){
        sb.append('<').append(HTML).append('>');
        for (Expression expression:expressions){
            expression.toHTML(sb);
        }
        sb.append("</").append(HTML).append('>');
    }


}
