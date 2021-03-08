grammar analiseDimensional;

@parser::header {
        import java.util.Map;
        import java.util.HashMap;
        import lib.*;
}

@parser::members {
        static protected Map<String,Symbol> symbolTable = new HashMap<>();
}

main: program* EOF;

program:  assignment                          #StatAssing
        | print                               #StatPrint
        | loopFor                             #StatFor
        | loopWhile                           #StatWhile
        | doWhile                             #StatDo
        | ifCond                              #StatIfCond
        | importFile                          #StatImport
        | not                                 #StatNot
        ;

type returns[Type res]:
     'integer' {$res = new IntegerType();}
   | 'real'    {$res = new RealType();}
   | 'boolean' {$res = new BooleanType();}
   | 'string'  {$res = new StringType();}
   ;

loopFor: 'for' '(' var=ID '=' n=(Integer|Real) ';' e1=expr ';' e2=expr ')' '{' program+ '}';
loopWhile: 'while' '(' e=expr ')' ('{' program+ '}')? ;
doWhile: 'do' '{' program+ '}' loopWhile;
ifCond: 'if' (expr|not) 'then' trueSL=program ('else' falseSL=program)? 'end';
assignment: declaration '=' e=expr;  
declaration: type ID;
print: 'print' var=ID; 
importFile: 'import' s=String;
function: type name=ID'(' expr* ')' '{' program+ 'return' r=ID '}'; 
not: NOT expr;

expr    returns[Type ti, String var]:
         <assoc=right> e1=expr ('^') e2=expr                               #ExprPow
        | e1=expr op=('*'|'/') e2=expr                                     #ExprNewDimension
        | e1=expr op=('+'|'-') e2=expr                                     #ExprAddSub
        | e1=expr op=('='|'>'|'<'|'<='|'>=' |'==') e2=expr                 #BoolComparator
        | e1=expr op=BINARY e2=expr                                        #BoolBinary
        | Bool                                                             #BoolLiteral
        //| NOT expr                                                         #ExprNot
        | '(' expr ')'                                                     #ExprParent  
        | '-'? n=(Integer|Real) (Unity = ID)?                              #ExprNumberUnity  
        | String                                                           #ExprString
        | Real                                                             #ExprReal
        | Integer                                                          #ExprInteger
        | ID                                                               #ExprID
        ;

Bool: 'true' | 'false';
BINARY: AND | OR;
OR   : 'OR' | '|';
AND  : 'AND' | '&';
NOT: 'NOT';
ID: [A-Za-z]+;
String: '"'.*?'"';
Real: [0-9]*'.'[0-9]+;
Integer: [0-9]+;   
WS: [ \t\r\n]+ -> skip;
COMMENT: '#' .*? '\n' ->skip;
ERROR: .;
