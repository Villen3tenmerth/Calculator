package visitor;

import token.Brace;
import token.Operation;
import token.Number;

public interface TokenVisitor {
    void visit(Number token);

    void visit(Operation token);

    void visit(Brace token);
}
