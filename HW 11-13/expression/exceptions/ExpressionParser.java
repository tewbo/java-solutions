package expression.exceptions;

import expression.*;
import expression.parser.BaseParser;
import expression.parser.BinaryOperation;
import expression.parser.StringSource;

import java.util.Map;

public class ExpressionParser implements TripleParser {
    private static final String TOKEN_END = "\0";
    private String token;

    private boolean isConstant;

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
        isConstant = false;
        takeToken();
        ExpressionElement expr = parseExpression(0);
        if (token.equals(TOKEN_END)) {
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
            if (token.charAt(0) == '-' && token.length() > 1) {
                token = token.substring(1);
            } else {
                takeToken();
            }
            expr = switch (operation) {
                case ADD -> new CheckedAdd(expr, parseExpression(binaryOperationPriority));
                case SUBTRACT -> new CheckedSubtract(expr, parseExpression(binaryOperationPriority));
                case MULTIPLY -> new CheckedMultiply(expr, parseExpression(binaryOperationPriority));
                case DIVIDE -> new CheckedDivide(expr, parseExpression(binaryOperationPriority));
                case GCD -> new CheckedGcd(expr, parseExpression(binaryOperationPriority));
                case LCM -> new CheckedLcm(expr, parseExpression(binaryOperationPriority));
                default -> throw new AssertionError("The case of the operation is not processed.");
            };
        }
    }

    BinaryOperation parseBinaryOperation() {
        if (token.charAt(0) == '-')
            return BinaryOperation.SUBTRACT;
        return switch (token) {
            case ("+") -> BinaryOperation.ADD;
            case ("-") -> BinaryOperation.SUBTRACT;
            case ("/") -> BinaryOperation.DIVIDE;
            case ("*") -> BinaryOperation.MULTIPLY;
            case ("gcd") -> BinaryOperation.GCD;
            case ("lcm") -> BinaryOperation.LCM;
            default -> BinaryOperation.NOTHING;
        };
    }

    private ExpressionElement parseElement() {
        final ExpressionElement expr;
        if (take("-")) {
            expr = new CheckedNegate(parseElement());
        } else if (isConstant) {
            expr = parseConstant();
            takeToken();
            isConstant = false;
        } else if (test("x") || test("y") || test("z")) {
            expr = new Variable(token);
            takeToken();
        } else if (take("(")) {
            expr = parseExpression(0);
            expect(")");
        } else {
            expr = parseWordUnaryOperation();
        }
        return expr;
    }

    private ExpressionElement parseWordUnaryOperation() {
        if (take("reverse")) {
            return new CheckedReverse(parseElement());
        } else if (take("log10")) {
            return new Log10(parseElement());
        } else if (take("pow10")) {
            return new Pow10(parseElement());
        } else {
            throw parser.error("Invalid unary operation.");
        }
    }

    private void takeDigits(final StringBuilder sb) {
        while (parser.between('0', '9')) {
            sb.append(parser.take());
        }
    }

    private boolean takeConstantToken(final StringBuilder sb) {
        if (parser.between('0', '9')) {
            takeDigits(sb);
            isConstant = true;
            return true;
        }
        return false;
    }

    private ExpressionElement parseConstant() {
        try {
            return new Const(Integer.parseInt(token));
        } catch (IllegalArgumentException e) {
            throw parser.error("Invalid number. " + e.getMessage());
        }
    }

    private void takeToken() {
        skipWhitespaces();
        StringBuilder sb = new StringBuilder();
        if (parser.take('-')) {
            sb.append('-');
            takeConstantToken(sb);
        } else if (parser.test('+') || parser.test('*') || parser.test('/') ||
                    parser.test('(') || parser.test(')')) {
                sb.append(parser.take());
        } else if (parser.isLetter()) {
            sb.append(parser.take());
            while (parser.between('0', '9') || parser.isLetter()) {
                sb.append(parser.take());
            }
        } else if (parser.eof()) {
            token = TOKEN_END;
            return;
        } else if (!takeConstantToken(sb)) {
            throw parser.error("Invalid token");
        }
        token = sb.toString();
        skipWhitespaces();
    }

    private boolean test(final String expected) {
        return token.equals(expected);
    }

    private boolean take(final String expectedToken) {
        if (test(expectedToken)) {
            takeToken();
            return true;
        }
        return false;
    }

    private void expect(final String expectedToken) {
        if (!take(expectedToken)) {
            throw parser.error("Expected '" + expectedToken + "', found '" + token + "'");
        }
    }

    private void skipWhitespaces() {
        while (parser.isWhiteSpace()) {
            parser.take();
        }
    }
}
