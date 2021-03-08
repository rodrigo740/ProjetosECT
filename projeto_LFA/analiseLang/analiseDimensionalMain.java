import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.stringtemplate.v4.*;
import java.util.*;
import lib.*;

public class analiseDimensionalMain {
   public static void main(String[] args) throws Exception {
      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(System.in);
      // create a lexer that feeds off of input CharStream:
      analiseDimensionalLexer lexer = new analiseDimensionalLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      analiseDimensionalParser parser = new analiseDimensionalParser(tokens);
      // replace error listener:
      // parser.removeErrorListeners(); // remove ConsoleErrorListener
      // parser.addErrorListener(new ErrorHandlingListener());
      // begin parsing at main rule:
      ParseTree tree = parser.main();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         SymbolTable sy = new SymbolTable();
         // SemanticAnalysis sa = new SemanticAnalysis(sy);
         Compiler c = new Compiler(sy);
         ErrorHandling.reset();
         // sa.visit(tree);
         if (ErrorHandling.error())
            System.exit(0);

         ST result = c.visit(tree);
         result.add("name", "Output");
         System.out.println(result.render());

      }
   }
}
