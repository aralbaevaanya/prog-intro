package murkup;

import java.util.List;

public class Header extends AllMoves {
    public Header(List<Expression> expressions, int num) {
        super(expressions, getMarkup(num), "\n\n",  getHTML(num));
    }

    private static String getMarkup(int num){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append('#');
        }
        return sb.toString();
    }

    private static String getHTML(int num){
        return "h" + Integer.toString(num);
    }
}
