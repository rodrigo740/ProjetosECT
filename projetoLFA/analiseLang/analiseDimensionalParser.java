// Generated from analiseDimensional.g4 by ANTLR 4.8

        import java.util.Map;
        import java.util.HashMap;
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
public class analiseDimensionalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, Bool=31, BINARY=32, 
		OR=33, AND=34, NOT=35, ID=36, String=37, Real=38, Integer=39, WS=40, COMMENT=41, 
		ERROR=42;
	public static final int
		RULE_main = 0, RULE_program = 1, RULE_type = 2, RULE_loopFor = 3, RULE_loopWhile = 4, 
		RULE_doWhile = 5, RULE_ifCond = 6, RULE_assignment = 7, RULE_declaration = 8, 
		RULE_print = 9, RULE_importFile = 10, RULE_function = 11, RULE_not = 12, 
		RULE_expr = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"main", "program", "type", "loopFor", "loopWhile", "doWhile", "ifCond", 
			"assignment", "declaration", "print", "importFile", "function", "not", 
			"expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'integer'", "'real'", "'boolean'", "'string'", "'for'", "'('", 
			"'='", "';'", "')'", "'{'", "'}'", "'while'", "'do'", "'if'", "'then'", 
			"'else'", "'end'", "'print'", "'import'", "'return'", "'^'", "'*'", "'/'", 
			"'+'", "'-'", "'>'", "'<'", "'<='", "'>='", "'=='", null, null, null, 
			null, "'NOT'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "Bool", "BINARY", "OR", "AND", 
			"NOT", "ID", "String", "Real", "Integer", "WS", "COMMENT", "ERROR"
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
	public String getGrammarFileName() { return "analiseDimensional.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	        static protected Map<String,Symbol> symbolTable = new HashMap<>();

	public analiseDimensionalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MainContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(analiseDimensionalParser.EOF, 0); }
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
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitMain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitMain(this);
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
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__17) | (1L << T__18) | (1L << NOT))) != 0)) {
				{
				{
				setState(28);
				program();
				}
				}
				setState(33);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(34);
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
	public static class StatForContext extends ProgramContext {
		public LoopForContext loopFor() {
			return getRuleContext(LoopForContext.class,0);
		}
		public StatForContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterStatFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitStatFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitStatFor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatImportContext extends ProgramContext {
		public ImportFileContext importFile() {
			return getRuleContext(ImportFileContext.class,0);
		}
		public StatImportContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterStatImport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitStatImport(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitStatImport(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatIfCondContext extends ProgramContext {
		public IfCondContext ifCond() {
			return getRuleContext(IfCondContext.class,0);
		}
		public StatIfCondContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterStatIfCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitStatIfCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitStatIfCond(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatAssingContext extends ProgramContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public StatAssingContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterStatAssing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitStatAssing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitStatAssing(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatPrintContext extends ProgramContext {
		public PrintContext print() {
			return getRuleContext(PrintContext.class,0);
		}
		public StatPrintContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterStatPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitStatPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitStatPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatNotContext extends ProgramContext {
		public NotContext not() {
			return getRuleContext(NotContext.class,0);
		}
		public StatNotContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterStatNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitStatNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitStatNot(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatWhileContext extends ProgramContext {
		public LoopWhileContext loopWhile() {
			return getRuleContext(LoopWhileContext.class,0);
		}
		public StatWhileContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterStatWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitStatWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitStatWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatDoContext extends ProgramContext {
		public DoWhileContext doWhile() {
			return getRuleContext(DoWhileContext.class,0);
		}
		public StatDoContext(ProgramContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterStatDo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitStatDo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitStatDo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_program);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
			case T__2:
			case T__3:
				_localctx = new StatAssingContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				assignment();
				}
				break;
			case T__17:
				_localctx = new StatPrintContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				print();
				}
				break;
			case T__4:
				_localctx = new StatForContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(38);
				loopFor();
				}
				break;
			case T__11:
				_localctx = new StatWhileContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(39);
				loopWhile();
				}
				break;
			case T__12:
				_localctx = new StatDoContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(40);
				doWhile();
				}
				break;
			case T__13:
				_localctx = new StatIfCondContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(41);
				ifCond();
				}
				break;
			case T__18:
				_localctx = new StatImportContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(42);
				importFile();
				}
				break;
			case NOT:
				_localctx = new StatNotContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(43);
				not();
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

	public static class TypeContext extends ParserRuleContext {
		public Type res;
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		try {
			setState(54);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				match(T__0);
				((TypeContext)_localctx).res =  new IntegerType();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				match(T__1);
				((TypeContext)_localctx).res =  new RealType();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(50);
				match(T__2);
				((TypeContext)_localctx).res =  new BooleanType();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(52);
				match(T__3);
				((TypeContext)_localctx).res =  new StringType();
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

	public static class LoopForContext extends ParserRuleContext {
		public Token var;
		public Token n;
		public ExprContext e1;
		public ExprContext e2;
		public TerminalNode ID() { return getToken(analiseDimensionalParser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode Integer() { return getToken(analiseDimensionalParser.Integer, 0); }
		public TerminalNode Real() { return getToken(analiseDimensionalParser.Real, 0); }
		public List<ProgramContext> program() {
			return getRuleContexts(ProgramContext.class);
		}
		public ProgramContext program(int i) {
			return getRuleContext(ProgramContext.class,i);
		}
		public LoopForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterLoopFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitLoopFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitLoopFor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopForContext loopFor() throws RecognitionException {
		LoopForContext _localctx = new LoopForContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_loopFor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__4);
			setState(57);
			match(T__5);
			setState(58);
			((LoopForContext)_localctx).var = match(ID);
			setState(59);
			match(T__6);
			setState(60);
			((LoopForContext)_localctx).n = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==Real || _la==Integer) ) {
				((LoopForContext)_localctx).n = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(61);
			match(T__7);
			setState(62);
			((LoopForContext)_localctx).e1 = expr(0);
			setState(63);
			match(T__7);
			setState(64);
			((LoopForContext)_localctx).e2 = expr(0);
			setState(65);
			match(T__8);
			setState(66);
			match(T__9);
			setState(68); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(67);
				program();
				}
				}
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__17) | (1L << T__18) | (1L << NOT))) != 0) );
			setState(72);
			match(T__10);
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

	public static class LoopWhileContext extends ParserRuleContext {
		public ExprContext e;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<ProgramContext> program() {
			return getRuleContexts(ProgramContext.class);
		}
		public ProgramContext program(int i) {
			return getRuleContext(ProgramContext.class,i);
		}
		public LoopWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterLoopWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitLoopWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitLoopWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopWhileContext loopWhile() throws RecognitionException {
		LoopWhileContext _localctx = new LoopWhileContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_loopWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__11);
			setState(75);
			match(T__5);
			setState(76);
			((LoopWhileContext)_localctx).e = expr(0);
			setState(77);
			match(T__8);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(78);
				match(T__9);
				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(79);
					program();
					}
					}
					setState(82); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__17) | (1L << T__18) | (1L << NOT))) != 0) );
				setState(84);
				match(T__10);
				}
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

	public static class DoWhileContext extends ParserRuleContext {
		public LoopWhileContext loopWhile() {
			return getRuleContext(LoopWhileContext.class,0);
		}
		public List<ProgramContext> program() {
			return getRuleContexts(ProgramContext.class);
		}
		public ProgramContext program(int i) {
			return getRuleContext(ProgramContext.class,i);
		}
		public DoWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterDoWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitDoWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitDoWhile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoWhileContext doWhile() throws RecognitionException {
		DoWhileContext _localctx = new DoWhileContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_doWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__12);
			setState(89);
			match(T__9);
			setState(91); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(90);
				program();
				}
				}
				setState(93); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__17) | (1L << T__18) | (1L << NOT))) != 0) );
			setState(95);
			match(T__10);
			setState(96);
			loopWhile();
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

	public static class IfCondContext extends ParserRuleContext {
		public ProgramContext trueSL;
		public ProgramContext falseSL;
		public List<ProgramContext> program() {
			return getRuleContexts(ProgramContext.class);
		}
		public ProgramContext program(int i) {
			return getRuleContext(ProgramContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext not() {
			return getRuleContext(NotContext.class,0);
		}
		public IfCondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifCond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterIfCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitIfCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitIfCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfCondContext ifCond() throws RecognitionException {
		IfCondContext _localctx = new IfCondContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifCond);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__13);
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
			case T__24:
			case Bool:
			case ID:
			case String:
			case Real:
			case Integer:
				{
				setState(99);
				expr(0);
				}
				break;
			case NOT:
				{
				setState(100);
				not();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(103);
			match(T__14);
			setState(104);
			((IfCondContext)_localctx).trueSL = program();
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(105);
				match(T__15);
				setState(106);
				((IfCondContext)_localctx).falseSL = program();
				}
			}

			setState(109);
			match(T__16);
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

	public static class AssignmentContext extends ParserRuleContext {
		public ExprContext e;
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			declaration();
			setState(112);
			match(T__6);
			setState(113);
			((AssignmentContext)_localctx).e = expr(0);
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

	public static class DeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(analiseDimensionalParser.ID, 0); }
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			type();
			setState(116);
			match(ID);
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

	public static class PrintContext extends ParserRuleContext {
		public Token var;
		public TerminalNode ID() { return getToken(analiseDimensionalParser.ID, 0); }
		public PrintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitPrint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintContext print() throws RecognitionException {
		PrintContext _localctx = new PrintContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_print);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__17);
			setState(119);
			((PrintContext)_localctx).var = match(ID);
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

	public static class ImportFileContext extends ParserRuleContext {
		public Token s;
		public TerminalNode String() { return getToken(analiseDimensionalParser.String, 0); }
		public ImportFileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importFile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterImportFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitImportFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitImportFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportFileContext importFile() throws RecognitionException {
		ImportFileContext _localctx = new ImportFileContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_importFile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__18);
			setState(122);
			((ImportFileContext)_localctx).s = match(String);
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

	public static class FunctionContext extends ParserRuleContext {
		public Token name;
		public Token r;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(analiseDimensionalParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(analiseDimensionalParser.ID, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<ProgramContext> program() {
			return getRuleContexts(ProgramContext.class);
		}
		public ProgramContext program(int i) {
			return getRuleContext(ProgramContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			type();
			setState(125);
			((FunctionContext)_localctx).name = match(ID);
			setState(126);
			match(T__5);
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__24) | (1L << Bool) | (1L << ID) | (1L << String) | (1L << Real) | (1L << Integer))) != 0)) {
				{
				{
				setState(127);
				expr(0);
				}
				}
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(133);
			match(T__8);
			setState(134);
			match(T__9);
			setState(136); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(135);
				program();
				}
				}
				setState(138); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__17) | (1L << T__18) | (1L << NOT))) != 0) );
			setState(140);
			match(T__19);
			setState(141);
			((FunctionContext)_localctx).r = match(ID);
			setState(142);
			match(T__10);
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

	public static class NotContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(analiseDimensionalParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotContext not() throws RecognitionException {
		NotContext _localctx = new NotContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_not);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(NOT);
			setState(145);
			expr(0);
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
		public Type ti;
		public String var;
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
			this.ti = ctx.ti;
			this.var = ctx.var;
		}
	}
	public static class ExprAddSubContext extends ExprContext {
		public ExprContext e1;
		public Token op;
		public ExprContext e2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprAddSubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterExprAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitExprAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitExprAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPowContext extends ExprContext {
		public ExprContext e1;
		public ExprContext e2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprPowContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterExprPow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitExprPow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitExprPow(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolBinaryContext extends ExprContext {
		public ExprContext e1;
		public Token op;
		public ExprContext e2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode BINARY() { return getToken(analiseDimensionalParser.BINARY, 0); }
		public BoolBinaryContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterBoolBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitBoolBinary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitBoolBinary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolLiteralContext extends ExprContext {
		public TerminalNode Bool() { return getToken(analiseDimensionalParser.Bool, 0); }
		public BoolLiteralContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterBoolLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitBoolLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprStringContext extends ExprContext {
		public TerminalNode String() { return getToken(analiseDimensionalParser.String, 0); }
		public ExprStringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterExprString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitExprString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitExprString(this);
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
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterExprParent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitExprParent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitExprParent(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolComparatorContext extends ExprContext {
		public ExprContext e1;
		public Token op;
		public ExprContext e2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BoolComparatorContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterBoolComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitBoolComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitBoolComparator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprNumberUnityContext extends ExprContext {
		public Token n;
		public Token Unity;
		public TerminalNode Integer() { return getToken(analiseDimensionalParser.Integer, 0); }
		public TerminalNode Real() { return getToken(analiseDimensionalParser.Real, 0); }
		public TerminalNode ID() { return getToken(analiseDimensionalParser.ID, 0); }
		public ExprNumberUnityContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterExprNumberUnity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitExprNumberUnity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitExprNumberUnity(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprRealContext extends ExprContext {
		public TerminalNode Real() { return getToken(analiseDimensionalParser.Real, 0); }
		public ExprRealContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterExprReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitExprReal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitExprReal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprIntegerContext extends ExprContext {
		public TerminalNode Integer() { return getToken(analiseDimensionalParser.Integer, 0); }
		public ExprIntegerContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterExprInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitExprInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitExprInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprNewDimensionContext extends ExprContext {
		public ExprContext e1;
		public Token op;
		public ExprContext e2;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ExprNewDimensionContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterExprNewDimension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitExprNewDimension(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitExprNewDimension(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprIDContext extends ExprContext {
		public TerminalNode ID() { return getToken(analiseDimensionalParser.ID, 0); }
		public ExprIDContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).enterExprID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof analiseDimensionalListener ) ((analiseDimensionalListener)listener).exitExprID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof analiseDimensionalVisitor ) return ((analiseDimensionalVisitor<? extends T>)visitor).visitExprID(this);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				_localctx = new BoolLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(148);
				match(Bool);
				}
				break;
			case 2:
				{
				_localctx = new ExprParentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(149);
				match(T__5);
				setState(150);
				expr(0);
				setState(151);
				match(T__8);
				}
				break;
			case 3:
				{
				_localctx = new ExprNumberUnityContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__24) {
					{
					setState(153);
					match(T__24);
					}
				}

				setState(156);
				((ExprNumberUnityContext)_localctx).n = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Real || _la==Integer) ) {
					((ExprNumberUnityContext)_localctx).n = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(158);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(157);
					((ExprNumberUnityContext)_localctx).Unity = match(ID);
					}
					break;
				}
				}
				break;
			case 4:
				{
				_localctx = new ExprStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(160);
				match(String);
				}
				break;
			case 5:
				{
				_localctx = new ExprRealContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161);
				match(Real);
				}
				break;
			case 6:
				{
				_localctx = new ExprIntegerContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(162);
				match(Integer);
				}
				break;
			case 7:
				{
				_localctx = new ExprIDContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(163);
				match(ID);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(183);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(181);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new ExprPowContext(new ExprContext(_parentctx, _parentState));
						((ExprPowContext)_localctx).e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(166);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						{
						setState(167);
						match(T__20);
						}
						setState(168);
						((ExprPowContext)_localctx).e2 = expr(12);
						}
						break;
					case 2:
						{
						_localctx = new ExprNewDimensionContext(new ExprContext(_parentctx, _parentState));
						((ExprNewDimensionContext)_localctx).e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(169);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(170);
						((ExprNewDimensionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__21 || _la==T__22) ) {
							((ExprNewDimensionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(171);
						((ExprNewDimensionContext)_localctx).e2 = expr(12);
						}
						break;
					case 3:
						{
						_localctx = new ExprAddSubContext(new ExprContext(_parentctx, _parentState));
						((ExprAddSubContext)_localctx).e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(172);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(173);
						((ExprAddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__23 || _la==T__24) ) {
							((ExprAddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(174);
						((ExprAddSubContext)_localctx).e2 = expr(11);
						}
						break;
					case 4:
						{
						_localctx = new BoolComparatorContext(new ExprContext(_parentctx, _parentState));
						((BoolComparatorContext)_localctx).e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(175);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(176);
						((BoolComparatorContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29))) != 0)) ) {
							((BoolComparatorContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(177);
						((BoolComparatorContext)_localctx).e2 = expr(10);
						}
						break;
					case 5:
						{
						_localctx = new BoolBinaryContext(new ExprContext(_parentctx, _parentState));
						((BoolBinaryContext)_localctx).e1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(178);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(179);
						((BoolBinaryContext)_localctx).op = match(BINARY);
						setState(180);
						((BoolBinaryContext)_localctx).e2 = expr(9);
						}
						break;
					}
					} 
				}
				setState(185);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u00bd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\7\2 \n\2\f\2\16\2#\13\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\5\49\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6"+
		"\5G\n\5\r\5\16\5H\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\6\6S\n\6\r\6\16\6T\3"+
		"\6\3\6\5\6Y\n\6\3\7\3\7\3\7\6\7^\n\7\r\7\16\7_\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\5\bh\n\b\3\b\3\b\3\b\3\b\5\bn\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u0083\n\r\f\r\16\r\u0086"+
		"\13\r\3\r\3\r\3\r\6\r\u008b\n\r\r\r\16\r\u008c\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u009d\n\17\3\17\3\17"+
		"\5\17\u00a1\n\17\3\17\3\17\3\17\3\17\5\17\u00a7\n\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u00b8"+
		"\n\17\f\17\16\17\u00bb\13\17\3\17\2\3\34\20\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\2\6\3\2()\3\2\30\31\3\2\32\33\4\2\t\t\34 \2\u00ce\2!\3\2\2\2"+
		"\4.\3\2\2\2\68\3\2\2\2\b:\3\2\2\2\nL\3\2\2\2\fZ\3\2\2\2\16d\3\2\2\2\20"+
		"q\3\2\2\2\22u\3\2\2\2\24x\3\2\2\2\26{\3\2\2\2\30~\3\2\2\2\32\u0092\3\2"+
		"\2\2\34\u00a6\3\2\2\2\36 \5\4\3\2\37\36\3\2\2\2 #\3\2\2\2!\37\3\2\2\2"+
		"!\"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2$%\7\2\2\3%\3\3\2\2\2&/\5\20\t\2\'/\5"+
		"\24\13\2(/\5\b\5\2)/\5\n\6\2*/\5\f\7\2+/\5\16\b\2,/\5\26\f\2-/\5\32\16"+
		"\2.&\3\2\2\2.\'\3\2\2\2.(\3\2\2\2.)\3\2\2\2.*\3\2\2\2.+\3\2\2\2.,\3\2"+
		"\2\2.-\3\2\2\2/\5\3\2\2\2\60\61\7\3\2\2\619\b\4\1\2\62\63\7\4\2\2\639"+
		"\b\4\1\2\64\65\7\5\2\2\659\b\4\1\2\66\67\7\6\2\2\679\b\4\1\28\60\3\2\2"+
		"\28\62\3\2\2\28\64\3\2\2\28\66\3\2\2\29\7\3\2\2\2:;\7\7\2\2;<\7\b\2\2"+
		"<=\7&\2\2=>\7\t\2\2>?\t\2\2\2?@\7\n\2\2@A\5\34\17\2AB\7\n\2\2BC\5\34\17"+
		"\2CD\7\13\2\2DF\7\f\2\2EG\5\4\3\2FE\3\2\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2"+
		"\2\2IJ\3\2\2\2JK\7\r\2\2K\t\3\2\2\2LM\7\16\2\2MN\7\b\2\2NO\5\34\17\2O"+
		"X\7\13\2\2PR\7\f\2\2QS\5\4\3\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2"+
		"UV\3\2\2\2VW\7\r\2\2WY\3\2\2\2XP\3\2\2\2XY\3\2\2\2Y\13\3\2\2\2Z[\7\17"+
		"\2\2[]\7\f\2\2\\^\5\4\3\2]\\\3\2\2\2^_\3\2\2\2_]\3\2\2\2_`\3\2\2\2`a\3"+
		"\2\2\2ab\7\r\2\2bc\5\n\6\2c\r\3\2\2\2dg\7\20\2\2eh\5\34\17\2fh\5\32\16"+
		"\2ge\3\2\2\2gf\3\2\2\2hi\3\2\2\2ij\7\21\2\2jm\5\4\3\2kl\7\22\2\2ln\5\4"+
		"\3\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\7\23\2\2p\17\3\2\2\2qr\5\22\n\2r"+
		"s\7\t\2\2st\5\34\17\2t\21\3\2\2\2uv\5\6\4\2vw\7&\2\2w\23\3\2\2\2xy\7\24"+
		"\2\2yz\7&\2\2z\25\3\2\2\2{|\7\25\2\2|}\7\'\2\2}\27\3\2\2\2~\177\5\6\4"+
		"\2\177\u0080\7&\2\2\u0080\u0084\7\b\2\2\u0081\u0083\5\34\17\2\u0082\u0081"+
		"\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0087\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\7\13\2\2\u0088\u008a\7"+
		"\f\2\2\u0089\u008b\5\4\3\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f\7\26"+
		"\2\2\u008f\u0090\7&\2\2\u0090\u0091\7\r\2\2\u0091\31\3\2\2\2\u0092\u0093"+
		"\7%\2\2\u0093\u0094\5\34\17\2\u0094\33\3\2\2\2\u0095\u0096\b\17\1\2\u0096"+
		"\u00a7\7!\2\2\u0097\u0098\7\b\2\2\u0098\u0099\5\34\17\2\u0099\u009a\7"+
		"\13\2\2\u009a\u00a7\3\2\2\2\u009b\u009d\7\33\2\2\u009c\u009b\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a0\t\2\2\2\u009f\u00a1\7&"+
		"\2\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a7\3\2\2\2\u00a2"+
		"\u00a7\7\'\2\2\u00a3\u00a7\7(\2\2\u00a4\u00a7\7)\2\2\u00a5\u00a7\7&\2"+
		"\2\u00a6\u0095\3\2\2\2\u00a6\u0097\3\2\2\2\u00a6\u009c\3\2\2\2\u00a6\u00a2"+
		"\3\2\2\2\u00a6\u00a3\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7"+
		"\u00b9\3\2\2\2\u00a8\u00a9\f\16\2\2\u00a9\u00aa\7\27\2\2\u00aa\u00b8\5"+
		"\34\17\16\u00ab\u00ac\f\r\2\2\u00ac\u00ad\t\3\2\2\u00ad\u00b8\5\34\17"+
		"\16\u00ae\u00af\f\f\2\2\u00af\u00b0\t\4\2\2\u00b0\u00b8\5\34\17\r\u00b1"+
		"\u00b2\f\13\2\2\u00b2\u00b3\t\5\2\2\u00b3\u00b8\5\34\17\f\u00b4\u00b5"+
		"\f\n\2\2\u00b5\u00b6\7\"\2\2\u00b6\u00b8\5\34\17\13\u00b7\u00a8\3\2\2"+
		"\2\u00b7\u00ab\3\2\2\2\u00b7\u00ae\3\2\2\2\u00b7\u00b1\3\2\2\2\u00b7\u00b4"+
		"\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba"+
		"\35\3\2\2\2\u00bb\u00b9\3\2\2\2\22!.8HTX_gm\u0084\u008c\u009c\u00a0\u00a6"+
		"\u00b7\u00b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}