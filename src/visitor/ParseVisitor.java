package visitor;

import token.*;
import token.Number;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParseVisitor implements TokenVisitor {
    private final List<Token> result;
    private final Stack<Token> operations;

    public ParseVisitor() {
        result = new ArrayList<>();
        operations = new Stack<>();
    }

    @Override
    public void visit(Number token) {
        result.add(token);
    }

    @Override
    public void visit(Operation token) {
        while (!operations.isEmpty()
                && operations.peek() instanceof Operation
                && ((Operation) operations.peek()).getPriority() >= token.getPriority()) {
            result.add(operations.pop());
        }
        operations.push(token);
    }

    @Override
    public void visit(Brace token) {
        if (token instanceof OpenBrace) {
            operations.push(token);
        } else {
            while (!operations.isEmpty() && !(operations.peek() instanceof OpenBrace)) {
                result.add(operations.pop());
            }
            if (operations.isEmpty()) {
                throw new RuntimeException("Unexpected close brace");
            }
            operations.pop();
        }
    }

    public List<Token> getResult() {
        while (!operations.isEmpty()) {
            if (!(operations.peek() instanceof Operation)) {
                throw new RuntimeException("Expected a close brace");
            }
            result.add(operations.pop());
        }

        return result;
    }
}
