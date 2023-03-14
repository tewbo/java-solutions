package expression;

import expression.exceptions.ExpressionParser;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        ExpressionParser expressionParser = new ExpressionParser();
        ExpressionElement expr = (ExpressionElement) expressionParser.parse(data);
        System.out.println(expr.toMiniString());
        System.out.println(expr.evaluate(1, -1357061, 3));
    }
}
