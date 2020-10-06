import org.stringtemplate.v4.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.*;
import java.io.*;
import lib.*;

public class Compiler extends analiseDimensionalBaseVisitor<ST> {

   private SymbolTable symbolTable;

   public Compiler(SymbolTable symbolTable) {
      this.symbolTable = symbolTable;
   }

   private ST setDeclaration(VariableSymbol s, analiseDimensionalParser.DeclarationContext ctx) {

      ST st = templates.getInstanceOf("declarations");
      ST decl = templates.getInstanceOf("decl");

      decl.add("var", "var" + (++numVars));
      if (s != null) {
         decl.add("type", s.type().toString());
         s.setVarName("var" + numVars);

      } else {
         decl.add("type", ctx.type().res.name());
      }

      st.add("decl", decl.render());
      return st;
   }

   @Override
   public ST visitMain(analiseDimensionalParser.MainContext ctx) {
      Iterator<analiseDimensionalParser.ProgramContext> list = ctx.program().iterator();
      ST res = templates.getInstanceOf("module");
      while (list.hasNext()) {
         res.add("stat", visit(list.next()).render());
      }
      return res;
   }

   @Override

   public ST visitLoopFor(analiseDimensionalParser.LoopForContext ctx) {
      ST forLoop = templates.getInstanceOf("for");
      forLoop.add("var", ctx.var.getText());
      forLoop.add("value", ctx.n.getText());
      forLoop.add("e1", ctx.expr(0).getText());
      forLoop.add("e2", ctx.expr(1).getText());
      Iterator<analiseDimensionalParser.ProgramContext> list = ctx.program().iterator();
      while (list.hasNext()) {
         forLoop.add("program", visit(list.next()).render());
      }
      return forLoop;
   }

   @Override
   public ST visitLoopWhile(analiseDimensionalParser.LoopWhileContext ctx) {
      ST whileLoop = templates.getInstanceOf("while");
      whileLoop.add("e", ctx.expr().getText());
      Iterator<analiseDimensionalParser.ProgramContext> list = ctx.program().iterator();
      while (list.hasNext()) {
         whileLoop.add("program", visit(list.next()).render());
      }
      return whileLoop;
   }

   @Override
   public ST visitDoWhile(analiseDimensionalParser.DoWhileContext ctx) {
      ST doLoop = templates.getInstanceOf("do");
      Iterator<analiseDimensionalParser.ProgramContext> list = ctx.program().iterator();
      while (list.hasNext()) {
         doLoop.add("program", visit(list.next()).render());
      }
      doLoop.add("loopWhile", ctx.loopWhile().getText());

      return doLoop;
   }

   @Override
   public ST visitIfCond(analiseDimensionalParser.IfCondContext ctx) {
      ST ifCond = templates.getInstanceOf("if");
      if (ctx.expr().getText() != null) {
         ifCond.add("e", ctx.expr().getText());
      }
      if (ctx.not().getText() != null) {
         ifCond.add("e", ctx.not().getText());
      }

      ifCond.add("stat_true", visit(ctx.trueSL).render());
      if (ctx.falseSL != null) {
         ifCond.add("stat_false", visit(ctx.falseSL).render());
      }
      return ifCond;
   }

   @Override
   public ST visitAssignment(analiseDimensionalParser.AssignmentContext ctx) {
      ST assign = templates.getInstanceOf("assign");
      String id = ctx.declaration().ID().getText();

      assign.add("type", ctx.declaration().type().res);
      assign.add("var", ctx.declaration().ID().getText());
      assign.add("e", ctx.expr().getText());
      return assign;
   }

   @Override
   public ST visitDeclaration(analiseDimensionalParser.DeclarationContext ctx) {
      String id = ctx.ID().getText();
      VariableSymbol s = symbolTable.getVar(id);
      if (s == null) {
         return setDeclaration(null, ctx);
      }
      return setDeclaration(s, null);
   }

   @Override
   public ST visitPrint(analiseDimensionalParser.PrintContext ctx) {
      ST print = templates.getInstanceOf("print");
      print.add("value", ctx.ID().getText());
      return print;
   }

   @Override
   public ST visitImportFile(analiseDimensionalParser.ImportFileContext ctx) {
      ST imp = templates.getInstanceOf("importFile");
      imp.add("String", ctx.s.getText());
      return imp;
   }

   @Override
   public ST visitFunction(analiseDimensionalParser.FunctionContext ctx) {
      ST function = templates.getInstanceOf("function");
      function.add("type", ctx.type().getText());
      function.add("name", ctx.name.getText());
      Iterator<analiseDimensionalParser.ProgramContext> list = ctx.program().iterator();
      while (list.hasNext()) {
         function.add("e", visit(list.next()).render());
      }
      Iterator<analiseDimensionalParser.ProgramContext> list2 = ctx.program().iterator();
      while (list2.hasNext()) {
         function.add("program", visit(list2.next()).render());
      }
      return function;

   }

   @Override
   public ST visitType(analiseDimensionalParser.TypeContext ctx) {
      ST res = templates.getInstanceOf("Type");
      res.add("type", ctx.res.name());
      return res;
   }

   @Override
   public ST visitExprString(analiseDimensionalParser.ExprStringContext ctx) {
      return visit(ctx.String());
   }

   @Override
   public ST visitExprParent(analiseDimensionalParser.ExprParentContext ctx) {
      ST res = visit(ctx.expr());
      ctx.var = ctx.expr().var;
      return res;
   }

   @Override
   public ST visitExprNumberUnity(analiseDimensionalParser.ExprNumberUnityContext ctx) {
      ST number = templates.getInstanceOf("decl");
      ctx.var = newVar();
      number.add("type", ctx.ti.name());
      number.add("var", ctx.var);
      number.add("value", ctx.n.getText());
      return number;
   }

   @Override
   public ST visitNot(analiseDimensionalParser.NotContext ctx) {
      ST bl = templates.getInstanceOf("ExprNot");
      bl.add("e", ctx.expr().getText());
      return bl;
   }

   @Override
   public ST visitExprPow(analiseDimensionalParser.ExprPowContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());
      ST bop = templates.getInstanceOf("operations");
      bop.add("dimension", ctx.ti.name());
      bop.add("var", ctx.var);
      bop.add("e1", ctx.expr(0).var);
      bop.add("op", '^');
      bop.add("e2", ctx.expr(1).var);
      res.add("stat", bop.render());
      return res;
   }

   @Override
   public ST visitExprNewDimension(analiseDimensionalParser.ExprNewDimensionContext ctx) {
      ST res = templates.getInstanceOf("stats");
      ctx.var = newVar();
      res.add("stat", visit(ctx.expr(0)).render());
      res.add("stat", visit(ctx.expr(1)).render());
      ST bop = templates.getInstanceOf("operations");
      bop.add("dimension", ctx.ti.name());
      bop.add("var", ctx.var);
      bop.add("e1", ctx.expr(0).var);
      bop.add("op", ctx.op.getText());
      bop.add("e2", ctx.expr(1).var);
      res.add("stat", bop.render());
      return res;
   }

   @Override
   public ST visitExprAddSub(analiseDimensionalParser.ExprAddSubContext ctx) {
      ctx.var = newVar();
      ST bop = templates.getInstanceOf("operations");
      bop.add("dimension", ctx.ti.name());
      bop.add("var", ctx.var);
      bop.add("e1", ctx.expr(0).var);
      bop.add("op", ctx.op.getText());
      bop.add("e2", ctx.expr(1).var);
      return bop;
   }

   @Override
   public ST visitBoolLiteral(analiseDimensionalParser.BoolLiteralContext ctx) {
      ST bl = templates.getInstanceOf("BooleanLiteral");
      bl.add("value", ctx.Bool().getText());
      return bl;
   }

   @Override
   public ST visitBoolBinary(analiseDimensionalParser.BoolBinaryContext ctx) {
      ST bop = templates.getInstanceOf("BooleanComparator");
      ctx.var = newVar();
      bop.add("e1", ctx.expr(0).var);
      bop.add("op", ctx.op.getText());
      bop.add("e2", ctx.expr(1).var);
      return bop;
   }

   @Override
   public ST visitBoolComparator(analiseDimensionalParser.BoolComparatorContext ctx) {
      ST bop = templates.getInstanceOf("BooleanComparator");
      ctx.var = newVar();
      bop.add("e1", ctx.expr(0).var);
      bop.add("op", ctx.op.getText());
      bop.add("e2", ctx.expr(1).var);
      return bop;
   }

   @Override
   public ST visitExprReal(analiseDimensionalParser.ExprRealContext ctx) {
      return visit(ctx.Real());
   }

   @Override
   public ST visitExprInteger(analiseDimensionalParser.ExprIntegerContext ctx) {
      return visit(ctx.Integer());
   }

   @Override
   public ST visitExprID(analiseDimensionalParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();
      VariableSymbol s = symbolTable.getVar(id);

      ST res = templates.getInstanceOf("id");
      res.add("val", s.varName());
      symbolTable.insert(id, s);

      return res;
   }

   private STGroup templates = new STGroupFile("patterns.stg");
   private int numVars = 0;
   protected String target = "java";

   private String newVar() {
      numVars++;
      return "v" + numVars;
   }
}
