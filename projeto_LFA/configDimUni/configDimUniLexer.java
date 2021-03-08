// Generated from configDimUni.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class configDimUniLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		DefineDim=10, DefineUni=11, Type=12, Signal=13, ID=14, Number=15, WS=16, 
		OPERATIONS=17, COMMENT=18, ERROR=19;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"DefineDim", "DefineUni", "Type", "Signal", "ID", "Number", "WS", "OPERATIONS", 
			"COMMENT", "ERROR"
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


	public configDimUniLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "configDimUni.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\25\u008e\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\5\rW\n\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16_\n\16\3\17\6\17b\n"+
		"\17\r\17\16\17c\3\20\7\20g\n\20\f\20\16\20j\13\20\3\20\3\20\6\20n\n\20"+
		"\r\20\16\20o\3\20\6\20s\n\20\r\20\16\20t\5\20w\n\20\3\21\6\21z\n\21\r"+
		"\21\16\21{\3\21\3\21\3\22\3\22\3\23\3\23\7\23\u0084\n\23\f\23\16\23\u0087"+
		"\13\23\3\23\3\23\3\23\3\23\3\24\3\24\3\u0085\2\25\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		"\3\2\6\5\2\62;C\\c|\3\2\62;\5\2\13\f\17\17\"\"\6\2,-//\61\61``\2\u0096"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5+\3\2\2\2\7-\3\2\2\2\t/\3\2\2"+
		"\2\13\61\3\2\2\2\r\63\3\2\2\2\17\65\3\2\2\2\21\67\3\2\2\2\239\3\2\2\2"+
		"\25;\3\2\2\2\27E\3\2\2\2\31V\3\2\2\2\33^\3\2\2\2\35a\3\2\2\2\37v\3\2\2"+
		"\2!y\3\2\2\2#\177\3\2\2\2%\u0081\3\2\2\2\'\u008c\3\2\2\2)*\7`\2\2*\4\3"+
		"\2\2\2+,\7,\2\2,\6\3\2\2\2-.\7\61\2\2.\b\3\2\2\2/\60\7-\2\2\60\n\3\2\2"+
		"\2\61\62\7/\2\2\62\f\3\2\2\2\63\64\7*\2\2\64\16\3\2\2\2\65\66\7+\2\2\66"+
		"\20\3\2\2\2\678\7.\2\28\22\3\2\2\29:\7<\2\2:\24\3\2\2\2;<\7F\2\2<=\7g"+
		"\2\2=>\7h\2\2>?\7k\2\2?@\7p\2\2@A\7g\2\2AB\7F\2\2BC\7k\2\2CD\7o\2\2D\26"+
		"\3\2\2\2EF\7F\2\2FG\7g\2\2GH\7h\2\2HI\7k\2\2IJ\7p\2\2JK\7g\2\2KL\7W\2"+
		"\2LM\7p\2\2MN\7k\2\2N\30\3\2\2\2OP\7t\2\2PQ\7g\2\2QR\7c\2\2RW\7n\2\2S"+
		"T\7k\2\2TU\7p\2\2UW\7v\2\2VO\3\2\2\2VS\3\2\2\2W\32\3\2\2\2XY\7r\2\2YZ"+
		"\7q\2\2Z_\7u\2\2[\\\7p\2\2\\]\7g\2\2]_\7i\2\2^X\3\2\2\2^[\3\2\2\2_\34"+
		"\3\2\2\2`b\t\2\2\2a`\3\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\36\3\2\2\2"+
		"eg\t\3\2\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2"+
		"km\7\60\2\2ln\t\3\2\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2pw\3\2\2"+
		"\2qs\t\3\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vh\3\2\2"+
		"\2vr\3\2\2\2w \3\2\2\2xz\t\4\2\2yx\3\2\2\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2"+
		"\2|}\3\2\2\2}~\b\21\2\2~\"\3\2\2\2\177\u0080\t\5\2\2\u0080$\3\2\2\2\u0081"+
		"\u0085\7%\2\2\u0082\u0084\13\2\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0088\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0089\7\f\2\2\u0089\u008a\3\2\2\2\u008a\u008b\b\23"+
		"\2\2\u008b&\3\2\2\2\u008c\u008d\13\2\2\2\u008d(\3\2\2\2\f\2V^chotv{\u0085"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}