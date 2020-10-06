// Generated from analiseDimensional.g4 by ANTLR 4.8

        import java.util.Map;
        import java.util.HashMap;
        import lib.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link analiseDimensionalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface analiseDimensionalVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(analiseDimensionalParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatAssing}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatAssing(analiseDimensionalParser.StatAssingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatPrint}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatPrint(analiseDimensionalParser.StatPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatFor}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatFor(analiseDimensionalParser.StatForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatWhile}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatWhile(analiseDimensionalParser.StatWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatDo}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatDo(analiseDimensionalParser.StatDoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatIfCond}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatIfCond(analiseDimensionalParser.StatIfCondContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatImport}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatImport(analiseDimensionalParser.StatImportContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatNot}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatNot(analiseDimensionalParser.StatNotContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(analiseDimensionalParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#loopFor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopFor(analiseDimensionalParser.LoopForContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#loopWhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopWhile(analiseDimensionalParser.LoopWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#doWhile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhile(analiseDimensionalParser.DoWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#ifCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfCond(analiseDimensionalParser.IfCondContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(analiseDimensionalParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(analiseDimensionalParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(analiseDimensionalParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#importFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportFile(analiseDimensionalParser.ImportFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(analiseDimensionalParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link analiseDimensionalParser#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(analiseDimensionalParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(analiseDimensionalParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPow(analiseDimensionalParser.ExprPowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolBinary}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolBinary(analiseDimensionalParser.BoolBinaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolLiteral}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(analiseDimensionalParser.BoolLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprString(analiseDimensionalParser.ExprStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParent(analiseDimensionalParser.ExprParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolComparator}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolComparator(analiseDimensionalParser.BoolComparatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNumberUnity}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumberUnity(analiseDimensionalParser.ExprNumberUnityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprReal}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprReal(analiseDimensionalParser.ExprRealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInteger(analiseDimensionalParser.ExprIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNewDimension}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNewDimension(analiseDimensionalParser.ExprNewDimensionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprID(analiseDimensionalParser.ExprIDContext ctx);
}