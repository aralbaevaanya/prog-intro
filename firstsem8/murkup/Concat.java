package murkup;

import java.util.List;

public class Concat implements Expression{
    List<Expression> expressions;
    private String splitter;
    public Concat(List<Expression> expressions, String spliter){
        this.expressions = expressions;
        this.splitter = spliter;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (int i = 0; i < expressions.size(); i++){
            expressions.get(i).toMarkdown(sb);
            if (i < expressions.size() - 1){
                sb.append(splitter);
            }
        }
    }

    @Override
    public void toHTML(StringBuilder sb) {
        for (int i = 0; i < expressions.size(); i++){
            expressions.get(i).toHTML(sb);
            if (i < expressions.size() - 1){
                sb.append(splitter);
            }
        }
    }
}
