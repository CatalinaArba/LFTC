
/* A Bison parser, made by GNU Bison 2.4.1.  */

/* Skeleton interface for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     IDENTIFIER = 258,
     INT_CONST = 259,
     STRING_CONST = 260,
     CHAR_CONST = 261,
     START = 262,
     plus = 263,
     minus = 264,
     mul = 265,
     division = 266,
     mod = 267,
     less = 268,
     lessOrEqual = 269,
     equal = 270,
     different = 271,
     greaterOrEqual = 272,
     greater = 273,
     assign = 274,
     READ = 275,
     WRITE = 276,
     IF = 277,
     ELSE = 278,
     WHILELOOP = 279,
     FORLOOP = 280,
     BOOLEAN = 281,
     CHAR = 282,
     INT = 283,
     DOUBLE = 284,
     STRING = 285,
     openBrace = 286,
     closeBrace = 287,
     openBracket = 288,
     closeBracket = 289,
     openParenthese = 290,
     closeParenthese = 291,
     comma = 292,
     semicolon = 293
   };
#endif
/* Tokens.  */
#define IDENTIFIER 258
#define INT_CONST 259
#define STRING_CONST 260
#define CHAR_CONST 261
#define START 262
#define plus 263
#define minus 264
#define mul 265
#define division 266
#define mod 267
#define less 268
#define lessOrEqual 269
#define equal 270
#define different 271
#define greaterOrEqual 272
#define greater 273
#define assign 274
#define READ 275
#define WRITE 276
#define IF 277
#define ELSE 278
#define WHILELOOP 279
#define FORLOOP 280
#define BOOLEAN 281
#define CHAR 282
#define INT 283
#define DOUBLE 284
#define STRING 285
#define openBrace 286
#define closeBrace 287
#define openBracket 288
#define closeBracket 289
#define openParenthese 290
#define closeParenthese 291
#define comma 292
#define semicolon 293




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


