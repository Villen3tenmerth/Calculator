import token.Token;
import tokenizer.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParseVisitor;
import visitor.PrintVisitor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Tokenizer tokenizer = new Tokenizer(System.in);
            List<Token> tokenizeResult = tokenizer.tokenize();

            ParseVisitor parseVisitor = new ParseVisitor();
            for (Token t : tokenizeResult) {
                t.accept(parseVisitor);
            }
            List<Token> parseResult = parseVisitor.getResult();

            PrintVisitor printVisitor = new PrintVisitor(System.out);
            System.out.println("Expression in reverse polish notation :");
            for (Token t : parseResult) {
                t.accept(printVisitor);
            }
            System.out.println();

            CalcVisitor calcVisitor = new CalcVisitor();
            for (Token t : parseResult) {
                t.accept(calcVisitor);
            }
            System.out.println("Evaluation result: " + calcVisitor.getResult());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
