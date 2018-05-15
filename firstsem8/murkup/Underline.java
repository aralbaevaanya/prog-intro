package murkup;

import java.util.List;

public class Underline extends AllMoves {
    public Underline(List<Expression> expressions) {
        super(expressions, "++", "++", "u");
    }
}
