%{
#include <stdio.h>
#include <stdlib.h>

#define YYDEBUG 1
%}

%token PLUS
%token MINUS
%token TIMES
%token DIV
%token MOD
%token ASSIGN
%token EQUAL
%token NOTEQUAL
%token LESS
%token LESSEQUAL
%token GREATER
%token GREATEREQUAL

%token BRACKETCLOSELEFT
%token BRACKETCLOSERIGHT
%token BRACKETOPENLEFT
%token BRACKETOPENRIGHT
%token COLON

%token GO
%token TO
%token IF
%token ELSE
%token WHILE
%token READ
%token WRITE

%start program

%%

program: cmpdstmt {printf("program -> cmpdstmt\n");}
arraydecl: identifier EQUAL BRACKETCLOSELEFT BRACKETCLOSERIGHT {printf("arraydecl -> identifier = []\n");}
cmpdstmt: stmtlist {printf("cmpdstmt -> stmtlist\n");}
stmtlist: stmt {printf("stmtlist -> stmt\n");} | stmt stmtlist {printf("stmtlist -> stmt stmtlist\n");}
stmt: simplstmt {printf("stmt -> simplstmt\n");}| structstmt {printf("stmt-> structstmt\n");}
simplstmt: assignstmt {printf("simplstmt -> assignstmt\n");} | iostmt {printf("simplstmt -> iostmt\n");}
assignstmt: identifier EQUAL expression {printf("assignstmt -> identifier = expression\n");} | arraydecl {printf("assignstmt -> arraydecl\n");}
expression: expression PLUS term {printf("expression -> expression + term\n");} 
			| expression MINUS term {printf("expression -> expression - term\n");}
			| expression DIV term {printf("expression -> expression / term\n");} 
			| expression MOD term {printf("expression -> expression % term\n");} 
			| term {printf("expression -> term\n");}
term: term TIMES factor {printf("term -> term * factor\n");} | factor {printf("term -> factor\n");}
factor: BRACKETOPENLEFT expression BRACKETOPENRIGHT {printf("factor -> ( expression )\n");} | identifier {printf("factor -> identifier\n");}
iostmt: READ BRACKETOPENLEFT identifier BRACKETOPENRIGHT {printf("iostmt -> read ( identifier )\n");} | WRITE BRACKETOPENLEFT identifier BRACKETOPENRIGHT {printf("iostmt -> write ( identifier )\n");}
structstmt: cmpdstmt {printf("structstmt -> cmpdstmt\n");}
			| ifstmt {printf("structstmt -> ifstmt\n");}
			| whilestmt {printf("structstmt -> whilestmt\n");}
			| forstmt {printf("structstmt -> forstmt\n");}
ifstmt: IF condition COLON stmtlist ELSE COLON stmtlist {printf("ifstmt -> if condition : stmtlist else : stmtlist\n");} 
whilestmt: WHILE condition COLON stmtlist {printf("whilestmt -> while condition : stmtlist\n");} 
forstmt: GO identifier TO expression COLON stmtlist {printf("forstmt -> go identifier to expression : stmtlist\n");} 
condition: expression relation expression {printf("condition -> expression relation expression\n");} 
relation: 	LESS {printf("relation -> <\n");} 
			| LESSEQUAL {printf("relation -> <=\n");} 
			| EQUAL {printf("relation -> ==\n");} 
			| NOTEQUAL {printf("relation -> !=\n");} 
			| GREATER {printf("relation -> >\n");} 
			| GREATEREQUAL{printf ("relation -> >=\n");} 

%%

yyerror(char *s)
{
  printf("%s\n", s);
}

extern FILE *yyin;

main(int argc, char **argv)
{
  if(argc>1) yyin = fopen(argv[1], "r");
  if(!yyparse()) fprintf(stderr,"\tO.K.\n");
}