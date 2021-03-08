import java.util.*;
import static java.lang.System.*;

public class Interpreter extends configDimUniBaseVisitor<Object> {

   HashMap<Unity, String> relations = new HashMap<>();
   ArrayList<Dimension> dimensions = new ArrayList<>();
   ArrayList<Object> nums = new ArrayList<>();

   @Override
   public Object visitMain(configDimUniParser.MainContext ctx) {
      return null;
   }

   @Override
   public Object visitStatDimension(configDimUniParser.StatDimensionContext ctx) {
      return visit(ctx.defineDim());
   }

   @Override
   public Object visitStatUni(configDimUniParser.StatUniContext ctx) {
      return visit(ctx.defineUni());
   }

   @Override
   public Object visitExprAddSub(configDimUniParser.ExprAddSubContext ctx) {

      ArrayList<Double> array = new ArrayList<>();

      String expr = ctx.getText();
      String a = expr;
      String variable = "";
      if (expr.contains("-")) {
         expr = expr.replaceAll("-", "+-");
      }

      String splited[] = expr.split("+");

      for (Dimension dim : dimensions) {
         ArrayList<Unity> unis = dim.getUnities();
         for (Unity u : unis) {
            if (a.contains(u.getUnity())) {
               variable = u.getUnity();
               break;
            }
         }
      }

      for (int i = splited.length - 1; i >= 0; i--) {
         String aux = splited[i];
         if (aux.contains(variable)) {
            String temp[] = aux.split(variable);
            if (temp[0] == null) {
               array.add(1.0);
            } else {
               array.add(Double.parseDouble(temp[0]));
            }
         } else {
            array.add(Double.parseDouble(aux));
         }

      }

      return array;
   }

   @Override
   public Object visitExprPow(configDimUniParser.ExprPowContext ctx) {

      return Integer.parseInt(ctx.rhs.getText());
   }

   @Override
   public Object visitExprParent(configDimUniParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public Object visitExprUnityID(configDimUniParser.ExprUnityIDContext ctx) {
      String Unity = ctx.ID().getText();
      if (Unity == null) {
         System.err.println("Variavel nula!");
         System.exit(1);
      }
      return Unity;
   }

   @Override
   public Object visitExprNumberUnity(configDimUniParser.ExprNumberUnityContext ctx) {
      nums = new ArrayList<>();

      if (ctx.Number() != null) {
         nums.add(Integer.parseInt(ctx.Number().getText()));
      }
      if (ctx.ID() != null) {
         nums.add(ctx.ID().getText());
      }
      return nums;
   }

   @Override
   public Object visitExprMultDiv(configDimUniParser.ExprMultDivContext ctx) {
      boolean f1 = false, f2 = false, f3 = false, f4 = false;
      String op1 = ctx.expr(0).getText();
      String op2 = ctx.expr(1).getText();
      String uniDefault = "", p = "";

      for (Dimension dimension : dimensions) {
         if (dimension.checkUnityByName(op1)) {
            f1 = true;
            uniDefault = dimension.getUnity().getUnity();
            break;
         } else if (dimension.checkUnityByName(op2)) {
            f2 = true;
            uniDefault = dimension.getUnity().getUnity();
            break;
         } else if (dimension.getDimension().equals(op1)) {
            f3 = true;
            break;
         } else if (dimension.getDimension().equals(op2)) {
            f4 = true;
            break;
         }
      }

      p = "relation(<u>,<u2>,<op>) ::= <<u>> = <<u2>> <<op>> num";
      // ^^^^ depois da substituiçao vai ficar String p = "relation(u,u2,valor,op) ::=
      // <u> = <u2> <op> num";

      if (op1 != null && op2 != null) {
         // separar unidades e numeros dos operandos
         String digit1 = "";
         String digit2 = "";
         String uni1 = "";
         String uni2 = "";
         char[] o1 = op1.toCharArray();
         for (char c : o1) {
            if (Character.isDigit(c))
               digit1 += c;
            else
               uni1 += c;
         }
         char[] o2 = op2.toCharArray();
         for (char c : o2) {
            if (Character.isDigit(c))
               digit2 += c;
            else
               uni2 += c;
         }
         Double n1 = Double.parseDouble(digit1);
         Double n2 = Double.parseDouble(digit2);
         Unity u1 = null;
         Unity u2 = null;
         if (uni1 != null)
            u1 = new Unity(uni1, 1);
         if (uni2 != null)
            u2 = new Unity(uni2, 1);

         switch (ctx.op.getText()) {
            case " ":
         }
      }
      return p;
   }

   @Override
   public Object visitDefineUni(configDimUniParser.DefineUniContext ctx) {
      String u = ctx.ID(0).getText();

      Unity uni = new Unity(u, 1, ctx.expr().getText());

      for (Dimension dimension : dimensions) {
         if (dimension.getDimension().equals(ctx.ID(1).getText())) {
            dimension.addElem(uni);
            break;
         }
      }
      return uni;
   }

   // funcoes auxiliares

   protected Value powValues(Value v1, Value v2) {
      assert v1 != null;
      assert v2 != null;

      Value res = null;

      if ("real".equals("" + v1.type()) && "real".equals("" + v2.type()))
         res = new RealValue(Math.pow(v1.realValue(), v2.realValue()));
      else if ("real".equals("" + v1.type()) && "integer".equals("" + v2.type()))
         res = new RealValue(Math.pow(v1.realValue(), v2.intValue()));
      else if ("integer".equals("" + v1.type()) && "real".equals("" + v2.type()))
         res = new RealValue(Math.pow(v1.intValue(), v2.realValue()));
      else if ("integer".equals("" + v1.type()) && "integer".equals("" + v2.type())) {
         if (v2.intValue() >= 0) {
            int r = 1;
            for (int i = 0; i < v2.intValue(); i++)
               r = v1.intValue();
            res = new IntegerValue(r);
         } else
            res = new RealValue(Math.pow(v1.intValue(), v2.intValue()));
      } else
         assert false : "missing semantic error check!";

      return res;
   }
}
