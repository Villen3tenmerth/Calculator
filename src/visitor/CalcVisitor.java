package visitor;

import token.Brace;
import token.Number;
import token.Operation;

import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private final Stack<Integer> args;

    public CalcVisitor() {
        args = new Stack<>();
    }

    @Override
    public void visit(Number token) {
        args.push(token.getVal());
    }

    @Override
    public void visit(Operation token) {
        if (args.size() < 2) {
            throw new RuntimeException("Not enough arguments for operation : " + token.toString());
        }
        int arg2 = args.pop();
        int arg1 = args.pop();
        args.push(token.eval(arg1, arg2));
    }

    @Override
    public void visit(Brace token) {
        throw new RuntimeException("Unexpected token : " + token.toString());
    }

    public int getResult() {
        if (args.isEmpty()) {
            throw new RuntimeException("Empty expression");
        } else if (args.size() > 1) {
            throw new RuntimeException("Missing operation token");
        }

        return args.peek();
    }
}
