package markup;

import java.util.List;

public class AllMoves implements Expression {
    private List<Expression> expressions;
    private String markDown;
    private String firstTeh;
    private String secondTeh;

    AllMoves(List<Expression> expressions, String markDown, String firstTeh, String secondTeh){
        this.expressions = expressions;
        this.firstTeh = firstTeh;
        this.markDown = markDown;
        this.secondTeh = secondTeh;
    }

    private void deep(StringBuilder sb, String first, String second){

    }
    public void toMarkdown(StringBuilder sb) {
        sb.append(markDown);
        for (Expression expression: expressions){
            expression.toMarkdown(sb);
        }
        sb.append(markDown);
    }
    public void toTex(StringBuilder sb){
        sb.append(firstTeh);
        for (Expression expression: expressions){
            expression.toTex(sb);
        }
        sb.append(secondTeh);
    }


}
