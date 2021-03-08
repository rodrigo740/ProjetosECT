// Generated from configDimUni.g4 by ANTLR 4.8

        import lib.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link configDimUniParser}.
 */
public interface configDimUniListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link configDimUniParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(configDimUniParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link configDimUniParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(configDimUniParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatDimension}
	 * labeled alternative in {@link configDimUniParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatDimension(configDimUniParser.StatDimensionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatDimension}
	 * labeled alternative in {@link configDimUniParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatDimension(configDimUniParser.StatDimensionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatUni}
	 * labeled alternative in {@link configDimUniParser#program}.
	 * @param ctx the parse tree
	 */
	void enterStatUni(configDimUniParser.StatUniContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatUni}
	 * labeled alternative in {@link configDimUniParser#program}.
	 * @param ctx the parse tree
	 */
	void exitStatUni(configDimUniParser.StatUniContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(configDimUniParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(configDimUniParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprPow(configDimUniParser.ExprPowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprPow(configDimUniParser.ExprPowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParent(configDimUniParser.ExprParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParent(configDimUniParser.ExprParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprUnityID}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprUnityID(configDimUniParser.ExprUnityIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprUnityID}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprUnityID(configDimUniParser.ExprUnityIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNumberUnity}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNumberUnity(configDimUniParser.ExprNumberUnityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNumberUnity}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNumberUnity(configDimUniParser.ExprNumberUnityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMultDiv(configDimUniParser.ExprMultDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMultDiv(configDimUniParser.ExprMultDivContext ctx);
	/**
	 * Enter a parse tree produced by {@link configDimUniParser#defineDim}.
	 * @param ctx the parse tree
	 */
	void enterDefineDim(configDimUniParser.DefineDimContext ctx);
	/**
	 * Exit a parse tree produced by {@link configDimUniParser#defineDim}.
	 * @param ctx the parse tree
	 */
	void exitDefineDim(configDimUniParser.DefineDimContext ctx);
	/**
	 * Enter a parse tree produced by {@link configDimUniParser#defineUni}.
	 * @param ctx the parse tree
	 */
	void enterDefineUni(configDimUniParser.DefineUniContext ctx);
	/**
	 * Exit a parse tree produced by {@link configDimUniParser#defineUni}.
	 * @param ctx the parse tree
	 */
	void exitDefineUni(configDimUniParser.DefineUniContext ctx);
}