
//package lfa1920-g12;
import lib.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.*;

public class SemanticAnalysis extends analiseDimensionalBaseVisitor<Boolean> {

   private final RealType realType = new RealType();
   private final IntegerType intType = new IntegerType();
   private final StringType stringType = new StringType();
   private final BooleanType boolType = new BooleanType();
   private SymbolTable symbolTable;

   public SemanticAnalysis(SymbolTable symbolTable) {
      this.symbolTable = symbolTable;
   }

   @Override
   public Boolean visitType(analiseDimensionalParser.TypeContext ctx) {
      if (fetchType(ctx.res, intType) != null) {
         ctx.res = intType;
      }
      if (fetchType(ctx.res, realType) != null) {
         ctx.res = realType;
      }
      if (fetchType(ctx.res, boolType) != null) {
         ctx.res = boolType;
      }
      if (fetchType(ctx.res, stringType) != null) {
         ctx.res = stringType;
      }
      return true;
   }

   @Override
   public Boolean visitLoopFor(analiseDimensionalParser.LoopForContext ctx) {
      Boolean b = null;
      String id = ctx.ID().getText();
      if (id != null) {
         b = false;
      } else {
         b = true;
      }
      Boolean n = null;

      if (ctx.Integer().getText() != null) {
         try {
            Integer i = Integer.parseInt(ctx.Integer().getText());
            n = true;
         } catch (Exception e) {
            n = false;
         }
      }
      if (ctx.Real().getText() != null) {
         try {
            Double d = Double.parseDouble(ctx.Real().getText());
            n = true;
         } catch (Exception e) {
            n = false;
         }
      }

      Boolean res = b && n && visit(ctx.e1) && visit(ctx.e2);

      if (res) {
         if (fetchType(ctx.e1.ti, ctx.e2.ti) == null) {
            ErrorHandling.printError(ctx, "OOPS: Comparison operator applied to invalid operands!");
            res = false;
         } else {
            ctx.e1.ti = boolType;
            if (fetchType(ctx.e2.ti, intType) != null) {
               ctx.e2.ti = intType;
            }
            if (fetchType(ctx.e2.ti, realType) != null) {
               ctx.e2.ti = realType;
            }
         }
      }

      Iterator<analiseDimensionalParser.ProgramContext> list = ctx.program().iterator();
      int i = 0;
      while (list.hasNext()) {
         if (ctx.program(i).getText().contains("import")) {
            ErrorHandling.printError(ctx, "OOPS: Don't use import like this!");
            res = false;
         }
         visit(ctx.program(i));
         i++;
      }
      return res;
   }

   @Override
   public Boolean visitLoopWhile(analiseDimensionalParser.LoopWhileContext ctx) {
      Boolean res = visit(ctx.e);
      Iterator<analiseDimensionalParser.ProgramContext> list = ctx.program().iterator();
      int i = 0;
      while (list.hasNext()) {
         if (ctx.program(i).getText().contains("import")) {
            ErrorHandling.printError(ctx, "OOPS: Don't use import like this!");
            res = false;
         }
         visit(ctx.program(i));
         i++;
      }

      if (res) {
         if (!"boolean".equals(ctx.e.ti.name())) {
            ErrorHandling.printError(ctx, "OOPS: Boolean expression required in conditional instruction!");
            res = false;
         }
      }
      return res;
   }

   @Override
   public Boolean visitDoWhile(analiseDimensionalParser.DoWhileContext ctx) {
      Boolean res = null;
      Iterator<analiseDimensionalParser.ProgramContext> list = ctx.program().iterator();
      int i = 0;
      while (list.hasNext()) {
         if (ctx.program(i).getText().contains("import")) {
            ErrorHandling.printError(ctx, "OOPS: Don't use import like this!");
            res = false;
         }
         visit(ctx.program(i));
         res = true;
         i++;
      }

      res = res && visit(ctx.loopWhile());

      return res;
   }

   @Override
   public Boolean visitIfCond(analiseDimensionalParser.IfCondContext ctx) {
      Boolean res = visit(ctx.program(0));
      if (ctx.expr().getText() != null) {
         res = res && visit(ctx.expr());
      }
      if (ctx.not().getText() != null) {
         res = res && visit(ctx.not());
      }
      /*if (res) {
         if (ctx.expr().getText() != null) {
            if (!"boolean".equals(ctx.ti.name())) {
               ErrorHandling.printError(ctx, "OOPS: Boolean expression required in conditional instruction!");
               res = false;
            }
            if (ctx.program(1) != null) {
               visit(ctx.program(1));
            }
         }
      }*/
      return res;
   }

   @Override
   public Boolean visitAssignment(analiseDimensionalParser.AssignmentContext ctx) {
      Boolean res = visit(ctx.e);
      String id = ctx.declaration().ID().getText();

      if (res) {
         if (!symbolTable.containsVar(id)) {
            ErrorHandling.printError(ctx, "OOPS: Variable \"" + id + "\" does not exist!");
            res = false;
         } else {
            Symbol vs = symbolTable.getVar(id);
            if (!ctx.expr().ti.conformsTo(vs.type())) {
               ErrorHandling.printError(ctx, "OOPS: Expression type does not conform to variable \"" + id + "\" type!");
               res = false;
            } else {
               vs.setValueDefined();
            }
         }
      }

      return res;
   }

   @Override
   public Boolean visitDeclaration(analiseDimensionalParser.DeclarationContext ctx) {

      Boolean res = visit(ctx.type());
      // for (TerminalNode t : ctx.idList().ID()) {
      String id = ctx.ID().getText();
      if (symbolTable.containsVar(id)) {
         ErrorHandling.printError(ctx, "OOPS: Variable \"" + id + "\" already declared!");
         res = false;
      } else {
         // symbolTable.insert(id,new VariableSymbol(id, ctx.type().res));
         symbolTable.insert(id, new VariableSymbol(id, ctx.type().res));
      }
      // }
      return res;

   }

   @Override
   public Boolean visitPrint(analiseDimensionalParser.PrintContext ctx) {
      Boolean res = null;
      if (ctx.ID().getText().isEmpty()) {
         res = false;
      } else {
         res = true;
      }
      if (res) {
         if (!symbolTable.containsVar(ctx.var.getText())) {
            ErrorHandling.printError(ctx, "OOPS: Variable not defined!");
            res = false;
         }
      }
      return res;
   }

   @Override
   public Boolean visitImportFile(analiseDimensionalParser.ImportFileContext ctx) {
      Boolean res = null;
      if (ctx.String().getText() != null) {
         res = false;
      } else {
         res = true;
      }
      return res;
   }

   @Override
   public Boolean visitFunction(analiseDimensionalParser.FunctionContext ctx) {
      Boolean res = visit(ctx.type());
      Boolean b = null;
      if (ctx.ID().isEmpty()) {
         b = false;
      } else {
         b = true;
      }

      res = res && b;

      Iterator<analiseDimensionalParser.ExprContext> listEx = ctx.expr().iterator();
      int j = 0;
      if (ctx.expr() != null) {
         while (listEx.hasNext()) {
            visit(ctx.expr(j));
            j++;
         }
      }
      Iterator<analiseDimensionalParser.ProgramContext> list = ctx.program().iterator();
      int i = 0;
      while (list.hasNext()) {
         if (ctx.program(i).getText().contains("import")) {
            ErrorHandling.printError(ctx, "OOPS: The import can not be used!");
            res = false;
         }
         visit(ctx.program(i));
         res = true;
         i++;
      }

      return res;
   }

   @Override
   public Boolean visitExprString(analiseDimensionalParser.ExprStringContext ctx) {
      ctx.ti = stringType;
      return true;
   }

   @Override
   public Boolean visitExprParent(analiseDimensionalParser.ExprParentContext ctx) {
      Boolean res = visit(ctx.expr());
      if (res) {
         ctx.ti = ctx.expr().ti;
      }
      return res;
   }

   @Override
   public Boolean visitExprNumberUnity(analiseDimensionalParser.ExprNumberUnityContext ctx) {
      Boolean n = null;
      if (ctx.Integer().getText() != null) {
         try {
            Integer i = Integer.parseInt(ctx.Integer().getText());
            n = true;
         } catch (Exception e) {
            n = false;
         }
      }
      if (ctx.Real().getText() != null) {
         try {
            Double d = Double.parseDouble(ctx.Real().getText());
            n = true;
         } catch (Exception e) {
            n = false;
         }
      }
      Boolean res = n;
      if (ctx.ID().getText() != null) {
         res = res && true;
      }
      if (res) {
         if (ctx.n == intType || ctx.n == realType) {
            res = res && true;
         } else {
            ErrorHandling.printError(ctx, "OOPS: Value isn't a valid type!");
            res = res && false;
         }
      }
      return res;
   }

   @Override
   public Boolean visitNot(analiseDimensionalParser.NotContext ctx) {
      Boolean res = visit(ctx.expr());
      /*
       * if (res) {
       * 
       * if (fetchType(ctx.expr().ti, boolType) == null) {
       * ErrorHandling.printError(ctx, "OOPS: Value isn't of the type Boolean!");
       * 
       * res = false;
       * 
       * } else {
       * 
       * ctx.ti = boolType;
       * 
       * }
       * 
       * }
       */
      return res;

   }

   @Override

   public Boolean visitExprNewDimension(analiseDimensionalParser.ExprNewDimensionContext ctx) {

      Boolean res = visit(ctx.e1) && checkNumericType(ctx, ctx.e1.ti) && visit(ctx.e2)

            && checkNumericType(ctx, ctx.e2.ti);

      if (res) {

         if (fetchType(ctx.e1.ti, ctx.e2.ti) == null) {

            ErrorHandling.printError(ctx, "OOPS: Value isn't of the type Boolean!");

            res = false;

         } else {

            ctx.ti = fetchType(ctx.e1.ti, ctx.e2.ti);

         }

      }

      return res;

   }

   @Override

   public Boolean visitExprAddSub(analiseDimensionalParser.ExprAddSubContext ctx) {

      Boolean res = visit(ctx.e1) && checkNumericType(ctx, ctx.e1.ti) && visit(ctx.e2)

            && checkNumericType(ctx, ctx.e2.ti);

      if (res) {

         if (fetchType(ctx.e1.ti, ctx.e2.ti) == null) {
            ErrorHandling.printError(ctx, "OOPS: Invalid operands!");

            res = false;

         } else {

            ctx.ti = fetchType(ctx.e1.ti, ctx.e2.ti);

         }

      }

      return res;

   }

   @Override

   public Boolean visitExprPow(analiseDimensionalParser.ExprPowContext ctx) {

      Boolean res = visit(ctx.e1) && checkNumericType(ctx, ctx.e1.ti) && visit(ctx.e2)

            && checkNumericType(ctx, ctx.e2.ti);

      if (res)

         ctx.ti = fetchType(ctx.e1.ti, ctx.e2.ti);

      return res;

   }

   @Override

   public Boolean visitBoolBinary(analiseDimensionalParser.BoolBinaryContext ctx) {

      Boolean res = visit(ctx.e1) && visit(ctx.e2);

      if (res) {

         if (fetchType(ctx.e1.ti, ctx.e2.ti) == null) {

            ErrorHandling.printError(ctx, "OOPS: Comparison operator applied to invalid operands!");

            res = false;

         } else {

            ctx.ti = boolType;

         }

      }

      return res;

   }

   @Override

   public Boolean visitBoolLiteral(analiseDimensionalParser.BoolLiteralContext ctx) {

      /*
       * Boolean res = null; if (ctx.Bool().getText().equals("true")) { res = true; }
       * else if (ctx.Bool().getText().equals("false")) { res = false; } if (res) { if
       * (fetchType(ctx.ti, boolType) == null) { ErrorHandling.printError(ctx,
       * "OOPS: Invalid Type!"); res = res && false; } else { ctx.ti = boolType; } }
       */
      ctx.ti = boolType;
      return true;
   }

   @Override
   public Boolean visitBoolComparator(analiseDimensionalParser.BoolComparatorContext ctx) {
      Boolean res = visit(ctx.e1) && visit(ctx.e2);
      if (res) {
         if (fetchType(ctx.e1.ti, ctx.e2.ti) == null) {
            ErrorHandling.printError(ctx, "OOPS: Comparison operator applied to invalid operands!");
            res = false;
         } else {
            ctx.ti = boolType;
         }
      }
      return res;
   }

   @Override
   public Boolean visitExprReal(analiseDimensionalParser.ExprRealContext ctx) {

      /*
       * Boolean res = null; VariableSymbol vs = new VariableSymbol(ctx.var, ctx.ti);
       * 
       * if (fetchType(ctx.ti, realType) == null) { ErrorHandling.printError(ctx,
       * "OOPS: Invalid Type!"); res = false; } else { ctx.ti = realType; res = true;
       * }
       */
      ctx.ti = realType;
      return true;
   }

   @Override
   public Boolean visitExprInteger(analiseDimensionalParser.ExprIntegerContext ctx) {

      /*
       * Boolean res = null;
       * 
       * if (fetchType(ctx.ti, intType) == null) { ErrorHandling.printError(ctx,
       * "OOPS: Invalid Type!"); res = false; } else { ctx.ti = intType; res = true; }
       */
      ctx.ti = intType;
      return true;
   }

   @Override
   public Boolean visitExprID(analiseDimensionalParser.ExprIDContext ctx) {
      Boolean res = true;
      String id = ctx.ID().getText();
      if (!symbolTable.containsVar(id)) {
         ErrorHandling.printError(ctx, "OOPS: Variable \"" + id + "\" does not exist!");
         res = false;
      } else {
         Symbol sym = symbolTable.getVar(id);
         if (!sym.valueDefined()) {
            ErrorHandling.printError(ctx, "OOPS: Variable \"" + id + "\" not defined!");
            res = false;
         } else {
            ctx.ti = sym.type();
         }
      }
      return res;
   }

   private Boolean checkNumericType(ParserRuleContext ctx, Type t) {
      Boolean res = true;
      if (!t.isNumeric()) {
         ErrorHandling.printError(ctx, "OOPS: Numeric operator applied to a non-numeric operand!");
         res = false;
      }
      return res;
   }

   private Type fetchType(Type t1, Type t2) {
      Type res = null;
      if (t1.isNumeric() && t2.isNumeric()) {
         if ("real".equals(t1.name()))
            res = t1;
         else if ("real".equals(t2.name()))
            res = t2;
         else
            res = t1;
      } else if ("boolean".equals(t1.name()) && "boolean".equals(t2.name())) {
         res = t1;
      } else if ("string".equals(t1.name()) && "string".equals(t2.name())) {
         res = t1;
      }
      return res;
   }

}