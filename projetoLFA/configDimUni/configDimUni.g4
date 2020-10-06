grammar configDimUni;

@parser::header {
        import lib.*;
}


main: program* EOF;

program: defineDim                                      #StatDimension
        |defineUni                                      #StatUni
        ;

expr:     <assoc=right> lhs = expr ('^') rhs = expr     #ExprPow
        | expr op=('*'|'/') expr                        #ExprMultDiv
        | expr op=('+'|'-') expr                        #ExprAddSub  
        | '(' expr ')'                                  #ExprParent
        | Number (Unity = ID)?                          #ExprNumberUnity
        | Unity = ID                                    #ExprUnityID
        ;

defineDim: DefineDim Dimension=ID '('(Type)(','(Signal))?')' ':' ((Unity=ID) | expr) (',' Unity=ID? '(' expr ')' )*;
defineUni: DefineUni Unity=ID '(' expr ')' ':' Dimension=ID;

DefineDim: 'DefineDim';
DefineUni: 'DefineUni';
Type: 'real'|'int';
Signal: 'pos' | 'neg';
ID: [A-Za-z0-9]+;
Number:[0-9]*'.'[0-9]+ | [0-9]+;
WS: [ \t\r\n]+ -> skip;
OPERATIONS: '/' | '*' | '+' | '-' | '^';
COMMENT: '#' .*? '\n' ->skip;
ERROR: .;