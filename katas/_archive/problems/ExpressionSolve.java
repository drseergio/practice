import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;

public class ExpressionSolve {
  private Stack<Double> operands;
  private Stack<String> operators;
  private String[] input;

  public ExpressionSolve(String expression) {
    operands = new Stack<Double>();
    operators = new Stack<String>();
    input = expression.split(" ");
  }

  public double solve() {
    for (int i = 0; i < input.length; i++) {
      if (input[i].equals("") || input[i].equals("("));
      else if ((input[i].equals("+")) ||
               (input[i].equals("-")) ||
               (input[i].equals("*")) ||
               (input[i].equals("/")) ||
               (input[i].equals("sqrt")) ||
               (input[i].equals("in"))) {
        operators.push(input[i]);
      } else if (input[i].equals(")")) {
        String op = operators.pop();
        double val = operands.pop();
        if (op.equals("+")) val += operands.pop();
        else if (op.equals("-")) val -= operands.pop();
        else if (op.equals("*")) val *= operands.pop();
        else if (op.equals("/")) val = operands.pop() / val;
        else if (op.equals("sqrt")) val = Math.sqrt(val);
        else if (op.equals("in")) val = Math.pow(operands.pop(), val);
        operands.push(val);
      } else {
        operands.push(Double.parseDouble(input[i]));
      }
    }
    return operands.pop();
  }

  /**
   * input data looks like ( 1 + ( 3 * 5 ) )
   * supported operations: +, /, -, *, sqrt, in
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
      ExpressionSolve solver = new ExpressionSolve(scanner.nextLine());
      System.out.println(solver.solve());
    }
  }
}
