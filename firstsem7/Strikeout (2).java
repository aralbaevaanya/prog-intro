import java.util.List;

class Strikeout extends AllMoves {
    Strikeout(List<Expression> expressions) {
        super(expressions,"~", "\\textst{", "}");
    }
}

