package expression;

public class Variable extends ExpressionElement {
    final private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public double evaluate(double x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case ("x") -> {
                return x;
            }
            case ("y") -> {
                return y;
            }
            case ("z") -> {
                return z;
            }
        }
        throw new AssertionError("Wrong name of variable");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String toMiniString() {
        return name;
    }

    @Override
    public String toMiniStringWithPriority(int topPriority) {
        if (topPriority == UNARY_PRIORITY) {
            return " " + toMiniString();
        }
        return toMiniString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Variable) {
            return name.equals(((Variable) object).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + 1337;
    }
}
