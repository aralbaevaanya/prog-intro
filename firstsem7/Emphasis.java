package markup;

import java.util.List;

class Emphasis extends AllMoves {
    Emphasis(List<Expression> expressions) {
        super(expressions,"*", "\\emph{", "}");
    }

}

