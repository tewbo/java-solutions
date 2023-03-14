package expression.parser;

import java.util.Map;

import expression.*;

public class ExpressionParser implements TripleParser {
    private static final Map<BinaryOperation, Integer> PRIORITIES = Map.of(
        BinaryOperation.ADD, 2,
        BinaryOperation.SUBTRACT, 2,
        BinaryOperation.MULTIPLY, 3,
        BinaryOperation.DIVIDE, 3,
        BinaryOperation.GCD, 1,
        BinaryOperation.LCM, 1
    );
    private BaseParser parser;

    @Override
    public TripleExpression parse(String expression) {
        parser = new BaseParser(new StringSource(expression));
        ExpressionElement expr = parseExpression(0);
        if (parser.eof()) {
            return expr;
        }
        throw parser.error("End of expression expected");
    }

    private ExpressionElement parseExpression(int priorityFromAbove) {
        ExpressionElement expr = parseElement();
        while (true) {
            final BinaryOperation operation = parseBinaryOperation();
            if (operation == BinaryOperation.NOTHING) {
                return expr;
            }
            final int binaryOperationPriority = PRIORITIES.get(operation);
            if (binaryOperationPriority <= priorityFromAbove) {
                return expr;
            }
            if (parser.take('+')) {
                expr = new Add(expr, parseExpression(PRIORITIES.get(BinaryOperation.ADD)));
            } else if (parser.take('-')) {
                expr = new Subtract(expr, parseExpression(PRIORITIES.get(BinaryOperation.SUBTRACT)));
            } else if (parser.take('*')) {
                expr = new Multiply(expr, parseExpression(PRIORITIES.get(BinaryOperation.MULTIPLY)));
            } else if (parser.take('/')) {
                expr = new Divide(expr, parseExpression(PRIORITIES.get(BinaryOperation.DIVIDE)));
            } else if (parser.take('g')) {
                // :NOTE: не расширяемо
                parser.expect("cd");
                expr = new Gcd(expr, parseExpression(PRIORITIES.get(BinaryOperation.GCD)));
            } else if (parser.take('l')) {
                parser.expect("cm");
                expr = new Lcm(expr, parseExpression(PRIORITIES.get(BinaryOperation.LCM)));
            } else {
                throw parser.error("\"parseBinaryOperation\" doesn't match with \"parseExpression\"");
            }
        }
    }

    BinaryOperation parseBinaryOperation() {
        if (parser.test('+')) {
            return BinaryOperation.ADD;
        } else if (parser.test('-')) {
            return BinaryOperation.SUBTRACT;
        } else if (parser.test('/')) {
            return BinaryOperation.DIVIDE;
        } else if (parser.test('*')) {
            return BinaryOperation.MULTIPLY;
        } else if (parser.test('g')) {
            return BinaryOperation.GCD;
        } else if (parser.test('l')) {
            return BinaryOperation.LCM;
        }
        return BinaryOperation.NOTHING;
    }

    private ExpressionElement parseElement() {
        final ExpressionElement expr;
        skipWhitespaces();
        if (parser.take('-')) {
            if (!parser.between('0', '9')) {
                expr = new Negate(parseElement());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append('-');
                expr = parseConstant(sb);
            }
        } else if (parser.isLetter()) {
            expr = parseWord(nextWord());
        } else if (parser.take('(')) {
            expr = parseExpression(0);
            parser.expect(')');
        } else {
            expr = parseConstant(new StringBuilder());
        }
        skipWhitespaces();
        return expr;
    }

    private ExpressionElement parseWord(String word) {
        if (word.equals("reverse")) {
            return new Reverse(parseElement());
        } else if (word.equals("x") || word.equals("y") || word.equals("z")) {
            return new Variable(word);
        } else {
            throw new IllegalArgumentException("There are invalid token.");
        }
    }

    private ExpressionElement parseConstant(StringBuilder sb) {
        takeInteger(sb);
        return new Const(Integer.parseInt(sb.toString()));
    }

    private void takeInteger(final StringBuilder sb) {
        if (parser.take('-')) {
            sb.append('-');
        }
        if (parser.between('0', '9')) {
            takeDigits(sb);
        } else {
            throw parser.error("Invalid number");
        }
    }

    private void takeDigits(final StringBuilder sb) {
        while (parser.between('0', '9')) {
            sb.append(parser.take());
        }
    }

    private void skipWhitespaces() {
        while (parser.isWhiteSpace()) {
            parser.take();
        }
    }

    private String nextWord() {
        StringBuilder sb = new StringBuilder();
        while (!parser.eof() && parser.isLetter()) {
            sb.append(parser.take());
        }
        skipWhitespaces();
        return sb.toString();
    }
}
