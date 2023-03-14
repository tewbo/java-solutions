package expression;

public class Add extends BinaryExpression {
    public final static int PRIORITY = 3;

    public Add(ExpressionElement operand1, ExpressionElement operand2) {
        super(operand1, operand2);
    }

    @Override
    public int evaluate(int x) {
        return operand1.evaluate(x) + operand2.evaluate(x);
    }

    @Override
    public double evaluate(double x) {
        return operand1.evaluate(x) + operand2.evaluate(x);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operand1.evaluate(x, y, z) + operand2.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return "(" + operand1.toString() + " + " + operand2.toString() + ")";
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        return toMiniStringForBinary(topPriority, Add.PRIORITY, Add.PRIORITY, Add.PRIORITY, "+");
    }
}
