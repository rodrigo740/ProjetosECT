// Generated from analiseDimensional.g4 by ANTLR 4.8

        import java.util.Map;
        import java.util.HashMap;
        import lib.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link analiseDimensionalParser}.
 */
public interface analiseDimensionalListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(analiseDimensionalParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(analiseDimensionalParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAssing}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatAssing(analiseDimensionalParser.StatAssingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAssing}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatAssing(analiseDimensionalParser.StatAssingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatPrint}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatPrint(analiseDimensionalParser.StatPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatPrint}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatPrint(analiseDimensionalParser.StatPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatFor}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatFor(analiseDimensionalParser.StatForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatFor}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatFor(analiseDimensionalParser.StatForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatWhile}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatWhile(analiseDimensionalParser.StatWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatWhile}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatWhile(analiseDimensionalParser.StatWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatDo}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatDo(analiseDimensionalParser.StatDoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatDo}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatDo(analiseDimensionalParser.StatDoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatIfCond}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatIfCond(analiseDimensionalParser.StatIfCondContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatIfCond}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatIfCond(analiseDimensionalParser.StatIfCondContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatImport}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatImport(analiseDimensionalParser.StatImportContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatImport}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatImport(analiseDimensionalParser.StatImportContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatNot}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatNot(analiseDimensionalParser.StatNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatNot}
	 * labeled alternative in {@link analiseDimensionalParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatNot(analiseDimensionalParser.StatNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(analiseDimensionalParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(analiseDimensionalParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#loopFor}.
	 * @param ctx the parse tree
	 */
	void enterLoopFor(analiseDimensionalParser.LoopForContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#loopFor}.
	 * @param ctx the parse tree
	 */
	void exitLoopFor(analiseDimensionalParser.LoopForContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#loopWhile}.
	 * @param ctx the parse tree
	 */
	void enterLoopWhile(analiseDimensionalParser.LoopWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#loopWhile}.
	 * @param ctx the parse tree
	 */
	void exitLoopWhile(analiseDimensionalParser.LoopWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#doWhile}.
	 * @param ctx the parse tree
	 */
	void enterDoWhile(analiseDimensionalParser.DoWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#doWhile}.
	 * @param ctx the parse tree
	 */
	void exitDoWhile(analiseDimensionalParser.DoWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#ifCond}.
	 * @param ctx the parse tree
	 */
	void enterIfCond(analiseDimensionalParser.IfCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#ifCond}.
	 * @param ctx the parse tree
	 */
	void exitIfCond(analiseDimensionalParser.IfCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(analiseDimensionalParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(analiseDimensionalParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(analiseDimensionalParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(analiseDimensionalParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(analiseDimensionalParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(analiseDimensionalParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#importFile}.
	 * @param ctx the parse tree
	 */
	void enterImportFile(analiseDimensionalParser.ImportFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#importFile}.
	 * @param ctx the parse tree
	 */
	void exitImportFile(analiseDimensionalParser.ImportFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(analiseDimensionalParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(analiseDimensionalParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link analiseDimensionalParser#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(analiseDimensionalParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link analiseDimensionalParser#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(analiseDimensionalParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(analiseDimensionalParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(analiseDimensionalParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPow(analiseDimensionalParser.ExprPowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPow(analiseDimensionalParser.ExprPowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolBinary}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolBinary(analiseDimensionalParser.BoolBinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolBinary}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolBinary(analiseDimensionalParser.BoolBinaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolLiteral}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(analiseDimensionalParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolLiteral}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(analiseDimensionalParser.BoolLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprString(analiseDimensionalParser.ExprStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprString(analiseDimensionalParser.ExprStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParent(analiseDimensionalParser.ExprParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParent(analiseDimensionalParser.ExprParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolComparator}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolComparator(analiseDimensionalParser.BoolComparatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolComparator}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolComparator(analiseDimensionalParser.BoolComparatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNumberUnity}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNumberUnity(analiseDimensionalParser.ExprNumberUnityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNumberUnity}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNumberUnity(analiseDimensionalParser.ExprNumberUnityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprReal}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprReal(analiseDimensionalParser.ExprRealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprReal}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprReal(analiseDimensionalParser.ExprRealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInteger(analiseDimensionalParser.ExprIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInteger}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInteger(analiseDimensionalParser.ExprIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNewDimension}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNewDimension(analiseDimensionalParser.ExprNewDimensionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNewDimension}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNewDimension(analiseDimensionalParser.ExprNewDimensionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprID(analiseDimensionalParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link analiseDimensionalParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprID(analiseDimensionalParser.ExprIDContext ctx);
}