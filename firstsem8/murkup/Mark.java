package murkup;

import java.util.List;

public class Mark extends AllMoves {
    public Mark(List<Expression> expressions){
        super(expressions, "~","~", "mark");
    }
}
