package expression.parser;

public class BaseParser {
    private static final char END = '\0';
    private final CharSource source;
    private char ch = 0xffff;

    public BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    public char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    public boolean test(final char expected) {
        return ch == expected;
    }

    public boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    public void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    public void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    public boolean eof() {
        return take(END);
    }

    public IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    public boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    public boolean isWhiteSpace() {
        return Character.isWhitespace(ch);
    }

    public boolean isLetter() {
        return Character.isLetter(ch);
    }
}
