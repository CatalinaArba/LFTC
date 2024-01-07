%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define YYDEBUG 1
%}

%union {
    int int_val;
    double double_val;
    char* string_val;
    struct {
        char* type;
        char* identifier;
    } type_identifier_val;
}

%token <int_val> INT_CONST
%token <string_val> STRING_CONST
%token <string_val> IDENTIFIER
%type <double_val> DOUBLE
%type <string_val> STRING
%type <string_val> CHAR

%type <type_identifier_val> type



%type <int_val> expression term factor
%type <int_val> listInt

%type <string_val> listString listDeclaration
%type <string_val> stmtlist




%token plus
%token minus
%token mul
%token division
%token mod
%token less
%token lessOrEqual
%token equal
%token different
%token greaterOrEqual
%token greater
%token assign


%token READ
%token WRITE
%token IF
%token ELSE
%token WHILELOOP
%token FORLOOP
%token BOOLEAN
%token CHAR
%token INT
%token DOUBLE
%token STRING


%token openBrace
%token closeBrace
%token openBracket
%token closeBracket
%token openParenthese
%token closeParenthese
%token comma
%token semicolon






%%
program : decllist cmpdstmt
	;
decllist : declaration semicolon
	   | declaration semicolon decllist
	   ;
declaration : type listDeclaration 
	   | type IDENTIFIER
	   ;
type : BOOLEAN 
	| CHAR 
	| INT 
	| STRING 
	| DOUBLE
        ;
listDeclaration : IDENTIFIER openBracket listInt closeBracket 	{ $$ = $3; }
	| IDENTIFIER openBracket listString closeBracket      { $$ = $3; }
	| IDENTIFIER openBracket closeBracket	{ $$ = NULL; }
	| { $$ = NULL; }
	;
listInt : INT_CONST comma listInt 
	| INT_CONST
	;
listString : STRING_CONST comma listString 
	| STRING_CONST
	;
cmpdstmt : stmtlist
	;
stmtlist : stmt semicolon stmtlist 
	| stmt semicolon
	| { $$ = NULL; }
	;
stmt : simplstmt 
	| structstmt
	;
simplstmt : assignstmt 
	| iostmt
	;
assignstmt : IDENTIFIER assign expression
	;
expression : term plus expression 
	| term minus expression 
	| term
	;
term : factor mul term 
	| factor division term 
	| factor mod term 
	| factor
	;
factor : openParenthese expression closeParenthese 
	| IDENTIFIER 
	| INT_CONST 
	| IDENTIFIER openBracket expression closeBracket
	;
iostmt : READ IDENTIFIER 
	| WRITE IDENTIFIER 
	| WRITE STRING_CONST 
	| WRITE INT_CONST 
	| READ IDENTIFIER openBracket expression closeBracket
	;
structstmt : ifstmt 
	| whilestmt 
	| forstmt
	;
ifstmt : ifsimplestmt 
	| ifelsestmt
	;
ifsimplestmt : IF openParenthese condition closeParenthese openBrace stmtlist closeBrace
	;
ifelsestmt : IF openParenthese condition closeParenthese openBrace stmtlist closeBrace ELSE openBrace stmtlist closeBrace
	;
whilestmt : WHILELOOP openParenthese condition closeParenthese openBrace stmtlist closeBrace
	;
forstmt : FORLOOP openParenthese assignstmt semicolon condition semicolon assignstmt closeParenthese openBrace stmtlist closeBrace
	;
condition : expression less expression 
	| expression lessOrEqual expression 
	| expression equal expression 
	| expression different expression 
	| expression greaterOrEqual expression 
	| expression greater expression
	;

%%

yyerror(char *s)
{	
	printf("%s\n",s);
}

extern FILE *yyin;

int main(int argc, char** argv)
{
    if (argc > 1) yyin = fopen(argv[1], "r");
    if (argc > 2 && !strcmp(argv[2], "-d")) yydebug = 1;
    if (!yyparse()) fprintf(stderr, "\tProgram is syntactically correct.\n");
    return 0;
}
