package parser;

import base.Pair;
import murkup.*;

import java.io.IOException;
import java.util.*;

public class Parser {
    private int index;
    private String Text;
    private String text;
    private int headerSize;
    private HashSet<State> wasOpen;

    public Parser(String Text) {
        wasOpen = new HashSet<>();
        this.Text = Text;
        index = 0;
        current = State.endParagraph;
    }

    public Parser(Scanner in) throws IOException {
        wasOpen = new HashSet<>();
        current = State.endParagraph;
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine()) {
            sb.append(in.nextLine()).append(in.hasNextLine() ? "\n" : "");
        }
        Text = sb.toString();
    }


    private enum State {strikeOut, underLine, strongStar, strongDash, superStrongStar, superStrongDash,mark, header, code, text, end, endParagraph, paragraph}

    private State current;

    private int getNextChar() {
        index++;
        return index - 1 < Text.length() ? Text.charAt(index - 1) : -1;
    }

    private boolean addSpecial(char ch, StringBuilder sb) {
        switch (ch) {
            case '<':
                sb.append("&lt;");
                return true;
            case '>':
                sb.append("&gt;");
                return true;
            case '&':
                sb.append("&amp;");
                return true;
            default:
                return false;
        }
    }

    private void skipNext(){
        int ch = getNextChar();
        while (ch == '\n') {
            ch = getNextChar();
        }

        index--;
    }

    private void getText() {
        StringBuilder sb = new StringBuilder();
        char last = ' ';
        while (true) {
            int ch = getNextChar();
            if ((ch == '*' || ch == '_' || ch == '`' || ch =='~') && last != '\\')
                break;
            if (ch == '-' || ch == '+' || ch == '\n') {
                if (ch == getNextChar()) {
                    index--;
                    break;
                }
                index--;
            }
            if (ch == -1) {
                break;
            }
            last = (char) ch;
            if (last != '\\') {
                if (!addSpecial(last, sb)) {
                    sb.append(last);
                }
            }

        }
        index--;
        text = sb.toString();
    }

    private void getNext() {
        int ch = getNextChar();

        if (current == State.endParagraph) {
            if (ch == '#') {
                headerSize = 0;
                while (ch == '#') {
                    headerSize++;
                    ch = getNextChar();
                }
                if (ch == ' ') {
                    current = State.header;
                    index++;
                } else {
                    current = State.paragraph;
                    index -= headerSize;
                }
            } else {
                current = State.paragraph;
            }
            index--;
        } else {

            switch (ch) {
                case -1:
                    current = State.end;
                    break;
                case '*':
                    if (getNextChar() == '*') {
                        current = State.superStrongStar;
                    } else {
                        index--;
                        current = State.strongStar;
                    }
                    break;
                case '_':
                    if (getNextChar() == '_') {
                        current = State.superStrongDash;
                    } else {
                        index--;
                        current = State.strongDash;
                    }
                    break;
                case '`':
                    current = State.code;
                    break;
                case '~':
                    current = State.mark;
                    break;
                case '\n':
                    ch = getNextChar();
                    if (ch == '\n') {
                        current = State.endParagraph;
                        skipNext();
                    } else {
                        index -= 2;
                        current = State.text;
                        getText();
                    }
                    break;
                case '-':
                    if (getNextChar() == '-') {
                        current = State.strikeOut;
                    } else {
                        index -= 2;
                        current = State.text;
                        getText();
                    }
                    break;
                case '+':
                    if (getNextChar() == '+') {
                        current = State.underLine;
                    } else {
                        index -= 2;
                        current = State.text;
                        getText();
                    }
                    break;
                default:
                    current = State.text;
                    index--;
                    getText();
            }
        }
    }


    private Pair<List<Expression>, Boolean> getList(State what) {
        wasOpen.add(what);
        List<Expression> ans = new ArrayList<>();
        ans.add(next());
        while (current != what && current != State.endParagraph && current != State.end) {
            ans.add(next());
        }
        wasOpen.remove(what);
        return new Pair<>(ans, current == what);
    }

    private Expression getExpr(String first, List<Expression> second) {
        return new Concat(Arrays.asList(new Text(first), new Concat(second, "")), "");
    }

    private Expression next() {
        getNext();
        Expression ans = new Text("");
        Pair<List<Expression>, Boolean> temp;

        if (!wasOpen.contains(current)) {
            switch (current) {
                case text:
                    ans = new Text(text);
                    break;
                case code:
                    temp = getList(current);
                    ans = temp.second ? new Code(temp.first) : getExpr("`", temp.first);
                    break;
                case mark:
                    temp = getList(current);
                    ans = temp.second ? new Mark(temp.first) : getExpr("~", temp.first);
                    break;
                case strikeOut:
                    temp = getList(current);
                    ans = temp.second ? new Strikeout(temp.first) : getExpr("--", temp.first);
                    break;
                case underLine:
                    temp = getList(current);
                    ans = temp.second ? new Underline(temp.first) : getExpr("++", temp.first);
                    break;
                case strongDash:
                    temp = getList(current);
                    ans = temp.second ? new Strong(temp.first) : getExpr("_", temp.first);
                    break;
                case strongStar:
                    temp = getList(current);
                    ans = temp.second ? new Strong(temp.first) : getExpr("*", temp.first);
                    break;
                case superStrongDash:
                    temp = getList(current);
                    ans = temp.second ? new SuperStrong(temp.first) : getExpr("__", temp.first);
                    break;
                case superStrongStar:
                    temp = getList(current);
                    ans = temp.second ? new SuperStrong(temp.first) : getExpr("**", temp.first);
                    break;
            }
        }
        return ans;
    }

    private List<Expression> getAllToHeader() {
        List<Expression> expr = new ArrayList<>();
        while (current != State.endParagraph && current != State.end) {
            expr.add(next());
        }
        return expr;
    }


    private Expression getHeader() {
        getNext();
        Expression ans = new Text("");
        switch (current) {
            case header:
                ans = new Header(getAllToHeader(), headerSize);
                break;
            case paragraph:
                ans = new Paragraph(getAllToHeader());
                break;
        }
        return ans;
    }

    private Expression getAll() {
        List<Expression> expr = new ArrayList<>();
        while (current != State.end) {
            expr.add(getHeader());
        }
        return new Concat(expr, "\n");
    }

    public Expression parse() {
        skipNext();
        return getAll();
    }
}
