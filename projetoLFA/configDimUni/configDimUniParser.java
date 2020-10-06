// Generated from configDimUni.g4 by ANTLR 4.8

        import lib.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class configDimUniParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		DefineDim=10, DefineUni=11, Type=12, Signal=13, ID=14, Number=15, WS=16, 
		OPERATIONS=17, COMMENT=18, ERROR=19;
	public static final int
		RULE_main = 0, RULE_program = 1, RULE_expr = 2, RULE_defineDim = 3, RULE_defineUni = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"main", "program", "expr", "defineDim", "defineUni"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'^'", "'*'", "'/'", "'+'", "'-'", "'('", "')'", "','", "':'", 
			"'DefineDim'", "'DefineUni'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "DefineDim", 
			"DefineUni", "Type", "Signal", "ID", "Number", "WS", "OPERATIONS", "COMMENT", 
			"ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "configDimUni.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public configDimUniParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MainContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(configDimUniParser.EOF, 0); }
		public List<ProgramContext> program() {
			return getRuleContexts(ProgramContext.class);
		}
		public ProgramContext program(int i) {
			return getRuleContext(ProgramContext.class,i);
		}
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitMain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitMain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_main);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DefineDim || _la==DefineUni) {
				{
				{
				setState(10);
				program();
				}
				}
				setState(15);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(16);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	 
		public ProgramContext() { }
		public void copyFrom(ProgramContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StatDimensionContext extends ProgramContext {
		public DefineDimContext defineDim() {
			return getRuleContext(DefineDimContext.class,0);
		}
		public StatDimensionContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterStatDimension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitStatDimension(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitStatDimension(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatUniContext extends ProgramContext {
		public DefineUniContext defineUni() {
			return getRuleContext(DefineUniContext.class,0);
		}
		public StatUniContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterStatUni(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitStatUni(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitStatUni(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_program);
		try {
			setState(20);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DefineDim:
				_localctx = new StatDimensionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(18);
				defineDim();
				}
				break;
			case DefineUni:
				_localctx = new StatUniContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				defineUni();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExprAddSubContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprAddSubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterExprAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitExprAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitExprAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPowContext extends ExprContext {
		public ExprContext lhs;
		public ExprContext rhs;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprPowContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterExprPow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitExprPow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitExprPow(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprParentContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprParentContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterExprParent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitExprParent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitExprParent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprUnityIDContext extends ExprContext {
		public Token Unity;
		public TerminalNode ID() { return getToken(configDimUniParser.ID, 0); }
		public ExprUnityIDContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterExprUnityID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitExprUnityID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitExprUnityID(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprNumberUnityContext extends ExprContext {
		public Token Unity;
		public TerminalNode Number() { return getToken(configDimUniParser.Number, 0); }
		public TerminalNode ID() { return getToken(configDimUniParser.ID, 0); }
		public ExprNumberUnityContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterExprNumberUnity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitExprNumberUnity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitExprNumberUnity(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprMultDivContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprMultDivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterExprMultDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitExprMultDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitExprMultDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				_localctx = new ExprParentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(23);
				match(T__5);
				setState(24);
				expr(0);
				setState(25);
				match(T__6);
				}
				break;
			case Number:
				{
				_localctx = new ExprNumberUnityContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(27);
				match(Number);
				setState(29);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(28);
					((ExprNumberUnityContext)_localctx).Unity = match(ID);
					}
					break;
				}
				}
				break;
			case ID:
				{
				_localctx = new ExprUnityIDContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(31);
				((ExprUnityIDContext)_localctx).Unity = match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(43);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new ExprPowContext(new ExprContext(_parentctx, _parentState));
						((ExprPowContext)_localctx).lhs = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(34);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						{
						setState(35);
						match(T__0);
						}
						setState(36);
						((ExprPowContext)_localctx).rhs = expr(6);
						}
						break;
					case 2:
						{
						_localctx = new ExprMultDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(37);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(38);
						((ExprMultDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__1 || _la==T__2) ) {
							((ExprMultDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(39);
						expr(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprAddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(40);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(41);
						((ExprAddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__3 || _la==T__4) ) {
							((ExprAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(42);
						expr(5);
						}
						break;
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DefineDimContext extends ParserRuleContext {
		public Token Dimension;
		public Token Unity;
		public TerminalNode DefineDim() { return getToken(configDimUniParser.DefineDim, 0); }
		public List<TerminalNode> ID() { return getTokens(configDimUniParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(configDimUniParser.ID, i);
		}
		public TerminalNode Type() { return getToken(configDimUniParser.Type, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Signal() { return getToken(configDimUniParser.Signal, 0); }
		public DefineDimContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineDim; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterDefineDim(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitDefineDim(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitDefineDim(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineDimContext defineDim() throws RecognitionException {
		DefineDimContext _localctx = new DefineDimContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_defineDim);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(DefineDim);
			setState(49);
			((DefineDimContext)_localctx).Dimension = match(ID);
			setState(50);
			match(T__5);
			{
			setState(51);
			match(Type);
			}
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(52);
				match(T__7);
				{
				setState(53);
				match(Signal);
				}
				}
			}

			setState(56);
			match(T__6);
			setState(57);
			match(T__8);
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				{
				setState(58);
				((DefineDimContext)_localctx).Unity = match(ID);
				}
				}
				break;
			case 2:
				{
				setState(59);
				expr(0);
				}
				break;
			}
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(62);
				match(T__7);
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(63);
					((DefineDimContext)_localctx).Unity = match(ID);
					}
				}

				setState(66);
				match(T__5);
				setState(67);
				expr(0);
				setState(68);
				match(T__6);
				}
				}
				setState(74);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefineUniContext extends ParserRuleContext {
		public Token Unity;
		public Token Dimension;
		public TerminalNode DefineUni() { return getToken(configDimUniParser.DefineUni, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(configDimUniParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(configDimUniParser.ID, i);
		}
		public DefineUniContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defineUni; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).enterDefineUni(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof configDimUniListener ) ((configDimUniListener)listener).exitDefineUni(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof configDimUniVisitor ) return ((configDimUniVisitor<? extends T>)visitor).visitDefineUni(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineUniContext defineUni() throws RecognitionException {
		DefineUniContext _localctx = new DefineUniContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_defineUni);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(DefineUni);
			setState(76);
			((DefineUniContext)_localctx).Unity = match(ID);
			setState(77);
			match(T__5);
			setState(78);
			expr(0);
			setState(79);
			match(T__6);
			setState(80);
			match(T__8);
			setState(81);
			((DefineUniContext)_localctx).Dimension = match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\25V\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\7\2\16\n\2\f\2\16\2\21\13\2\3\2\3\2\3\3"+
		"\3\3\5\3\27\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4 \n\4\3\4\5\4#\n\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4.\n\4\f\4\16\4\61\13\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\59\n\5\3\5\3\5\3\5\3\5\5\5?\n\5\3\5\3\5\5\5C\n\5\3\5\3"+
		"\5\3\5\3\5\7\5I\n\5\f\5\16\5L\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\2\3\6\7\2\4\6\b\n\2\4\3\2\4\5\3\2\6\7\2\\\2\17\3\2\2\2\4\26\3\2\2\2\6"+
		"\"\3\2\2\2\b\62\3\2\2\2\nM\3\2\2\2\f\16\5\4\3\2\r\f\3\2\2\2\16\21\3\2"+
		"\2\2\17\r\3\2\2\2\17\20\3\2\2\2\20\22\3\2\2\2\21\17\3\2\2\2\22\23\7\2"+
		"\2\3\23\3\3\2\2\2\24\27\5\b\5\2\25\27\5\n\6\2\26\24\3\2\2\2\26\25\3\2"+
		"\2\2\27\5\3\2\2\2\30\31\b\4\1\2\31\32\7\b\2\2\32\33\5\6\4\2\33\34\7\t"+
		"\2\2\34#\3\2\2\2\35\37\7\21\2\2\36 \7\20\2\2\37\36\3\2\2\2\37 \3\2\2\2"+
		" #\3\2\2\2!#\7\20\2\2\"\30\3\2\2\2\"\35\3\2\2\2\"!\3\2\2\2#/\3\2\2\2$"+
		"%\f\b\2\2%&\7\3\2\2&.\5\6\4\b\'(\f\7\2\2()\t\2\2\2).\5\6\4\b*+\f\6\2\2"+
		"+,\t\3\2\2,.\5\6\4\7-$\3\2\2\2-\'\3\2\2\2-*\3\2\2\2.\61\3\2\2\2/-\3\2"+
		"\2\2/\60\3\2\2\2\60\7\3\2\2\2\61/\3\2\2\2\62\63\7\f\2\2\63\64\7\20\2\2"+
		"\64\65\7\b\2\2\658\7\16\2\2\66\67\7\n\2\2\679\7\17\2\28\66\3\2\2\289\3"+
		"\2\2\29:\3\2\2\2:;\7\t\2\2;>\7\13\2\2<?\7\20\2\2=?\5\6\4\2><\3\2\2\2>"+
		"=\3\2\2\2?J\3\2\2\2@B\7\n\2\2AC\7\20\2\2BA\3\2\2\2BC\3\2\2\2CD\3\2\2\2"+
		"DE\7\b\2\2EF\5\6\4\2FG\7\t\2\2GI\3\2\2\2H@\3\2\2\2IL\3\2\2\2JH\3\2\2\2"+
		"JK\3\2\2\2K\t\3\2\2\2LJ\3\2\2\2MN\7\r\2\2NO\7\20\2\2OP\7\b\2\2PQ\5\6\4"+
		"\2QR\7\t\2\2RS\7\13\2\2ST\7\20\2\2T\13\3\2\2\2\f\17\26\37\"-/8>BJ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}