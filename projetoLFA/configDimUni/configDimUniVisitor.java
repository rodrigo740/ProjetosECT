// Generated from configDimUni.g4 by ANTLR 4.8

        import lib.*;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link configDimUniParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface configDimUniVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link configDimUniParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(configDimUniParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatDimension}
	 * labeled alternative in {@link configDimUniParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatDimension(configDimUniParser.StatDimensionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatUni}
	 * labeled alternative in {@link configDimUniParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatUni(configDimUniParser.StatUniContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(configDimUniParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprPow}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPow(configDimUniParser.ExprPowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParent(configDimUniParser.ExprParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprUnityID}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnityID(configDimUniParser.ExprUnityIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNumberUnity}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumberUnity(configDimUniParser.ExprNumberUnityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link configDimUniParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultDiv(configDimUniParser.ExprMultDivContext ctx);
	/**
	 * Visit a parse tree produced by {@link configDimUniParser#defineDim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineDim(configDimUniParser.DefineDimContext ctx);
	/**
	 * Visit a parse tree produced by {@link configDimUniParser#defineUni}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefineUni(configDimUniParser.DefineUniContext ctx);
}