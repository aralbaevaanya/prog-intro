package murkup;

import java.util.List;

class AllMoves implements Expression{
    private List<Expression> expressions;
    private String HTML;
    private String markupFirst;
    private String markupSecond;

    AllMoves(List<Expression> expressions, String markupFirst, String markupSecond, String HTML){
        this.expressions = expressions;
        this.HTML = HTML;
        this.markupFirst = markupFirst;
        this.markupSecond = markupSecond;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(markupFirst);
        for (Expression expression : expressions) {
            expression.toMarkdown(sb);
        }
        sb.append(markupSecond);
    }

    @Override
    public void toHTML(StringBuilder sb) {
        sb.append('<').append(HTML).append('>');
        for (Expression expression : expressions) {
            expression.toHTML(sb);
        }
        sb.append("</").append(HTML).append('>');
    }
}
