package org.genguava.papyrusscroll.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import org.genguava.papyrusscroll.lexer.PapyrusTokenTypes;
import com.intellij.openapi.util.text.StringUtil;

%%

%class _PapyrusLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType

DIGIT = [0-9]
NONZERODIGIT = [1-9]

INTEGER = (({NONZERODIGIT}("_"?{DIGIT})*)|0)

END_OF_LINE_COMMENT=";"[^\r\n]*
MULTILINE_COMMENT_START=";/"
MULTILINE_COMMENT={MULTILINE_COMMENT_START}(.*?){MULTILINE_COMMENT_END}
MULTILINE_COMMENT_END="/;"

IDENT_START = [\w_--\d]
IDENT_CONTINUE = [\w_]
IDENTIFIER = {IDENT_START}{IDENT_CONTINUE}*

FLOATNUMBER=({POINTFLOAT})
POINTFLOAT=(({INTPART})?{FRACTION})|({INTPART}\.)
INTPART = {DIGIT}("_"?{DIGIT})*
FRACTION = \.{INTPART}

//STRING_LITERAL=[UuBb]?({RAW_STRING}|{QUOTED_STRING})
//RAW_STRING=[Rr]{QUOTED_STRING}
//QUOTED_STRING=({TRIPLE_APOS_LITERAL})|({QUOTED_LITERAL})|({DOUBLE_QUOTED_LITERAL})|({TRIPLE_QUOTED_LITERAL})

// If you change patterns for string literals, don't forget to update PyStringLiteralUtil!
// "c" prefix character is included for Cython
SINGLE_QUOTED_STRING=[UuBbCcRrFf]{0,3}({QUOTED_LITERAL} | {DOUBLE_QUOTED_LITERAL})

DOCSTRING_LITERAL=({SINGLE_QUOTED_STRING})

QUOTED_LITERAL="'" ([^\\\'\r\n] | {ESCAPE_SEQUENCE} | (\\[\r\n]))* ("'"|\\)?
DOUBLE_QUOTED_LITERAL=\"([^\\\"\r\n]|{ESCAPE_SEQUENCE}|(\\[\r\n]))*?(\"|\\)?
ESCAPE_SEQUENCE=\\[^\r\n]

ANY_ESCAPE_SEQUENCE = \\[^]

ONE_TWO_QUO = (\"[^\\\"]) | (\"\\[^]) | (\"\"[^\\\"]) | (\"\"\\[^])
QUO_STRING_CHAR = [^\\\"] | {ANY_ESCAPE_SEQUENCE} | {ONE_TWO_QUO}

%state PENDING_DOCSTRING
%state IN_DOCSTRING_OWNER
%{
private int getSpaceLength(CharSequence string) {
String string1 = string.toString();
string1 = StringUtil.trimEnd(string1, "\\");
string1 = StringUtil.trimEnd(string1, ";");
final String s = StringUtil.trimTrailing(string1);
return yylength()-s.length();

}
%}

%%

[\ ]                        { return PapyrusTokenTypes.SPACE; }
[\t]                        { return PapyrusTokenTypes.TAB; }
[\f]                        { return PapyrusTokenTypes.FORMFEED; }
"\\"                        { return PapyrusTokenTypes.BACKSLASH; }

<YYINITIAL> {
[\n]                        { return PapyrusTokenTypes.LINE_BREAK; }
{END_OF_LINE_COMMENT}       { return PapyrusTokenTypes.END_OF_LINE_COMMENT; }
{MULTILINE_COMMENT}         { return PapyrusTokenTypes.MULTILINE_COMMENT; }

{SINGLE_QUOTED_STRING}          { return PapyrusTokenTypes.SINGLE_QUOTED_STRING; }

{SINGLE_QUOTED_STRING}[\ \t]*[\n;]   { yypushback(getSpaceLength(yytext())); return PapyrusTokenTypes.SINGLE_QUOTED_STRING; }

{SINGLE_QUOTED_STRING}[\ \t]*"\\"  {
 yypushback(getSpaceLength(yytext())); return PapyrusTokenTypes.SINGLE_QUOTED_STRING; }
}

[\n]                        { return PapyrusTokenTypes.LINE_BREAK; }
{END_OF_LINE_COMMENT}       { return PapyrusTokenTypes.END_OF_LINE_COMMENT; }
{MULTILINE_COMMENT}         { return PapyrusTokenTypes.MULTILINE_COMMENT; }

<YYINITIAL, IN_DOCSTRING_OWNER> {
{INTEGER}             { return PapyrusTokenTypes.INTEGER_LITERAL; }
{FLOATNUMBER}         { return PapyrusTokenTypes.FLOAT_LITERAL; }

{SINGLE_QUOTED_STRING} { return PapyrusTokenTypes.SINGLE_QUOTED_STRING; }

"as"                  { return PapyrusTokenTypes.AS_KEYWORD; }
"auto"                { return PapyrusTokenTypes.AUTO_KEYWORD; }
"autoreadonly"        { return PapyrusTokenTypes.ARO_KEYWORD; }
"scriptname"          { return PapyrusTokenTypes.SCNAM_KEYWORD; }
"property"            { return PapyrusTokenTypes.PROP_KEYWORD; }
"elseif"              { return PapyrusTokenTypes.ELIF_KEYWORD; }
"else"                { return PapyrusTokenTypes.ELSE_KEYWORD; }
"bool"                { return PapyrusTokenTypes.BOOL_KEYWORD; }
"endevent"            { return PapyrusTokenTypes.ENEVT_KEYWORD; }
"endfunction"         { return PapyrusTokenTypes.ENFUN_KEYWORD; }
"endif"               { return PapyrusTokenTypes.ENDIF_KEYWORD; }
"global"              { return PapyrusTokenTypes.GLOBAL_KEYWORD; }
"if"                  { return PapyrusTokenTypes.IF_KEYWORD; }
"import"              { return PapyrusTokenTypes.IMPORT_KEYWORD; }
"endproperty"         { return PapyrusTokenTypes.ENPR_KEYWORD; }
"endstate"            { return PapyrusTokenTypes.ENST_KEYWORD; }
"endwhile"            { return PapyrusTokenTypes.ENWHI_KEYWORD; }
"event"               { return PapyrusTokenTypes.EVENT_KEYWORD; }
"extends"             { return PapyrusTokenTypes.EXTENDS_KEYWORD; }
"false"               { return PapyrusTokenTypes.FALSE_KEYWORD; }
"float"               { return PapyrusTokenTypes.FLOAT_KEYWORD; }
"return"              { return PapyrusTokenTypes.RETURN_KEYWORD; }
"function"            { return PapyrusTokenTypes.FUN_KEYWORD; }
"while"               { return PapyrusTokenTypes.WHILE_KEYWORD; }
"int"                 { return PapyrusTokenTypes.INT_KEYWORD; }
"native"              { return PapyrusTokenTypes.NATIVE_KEYWORD; }
"new"                 { return PapyrusTokenTypes.NEW_KEYWORD; }
"none"                { return PapyrusTokenTypes.NONE_KEYWORD; }
"parent"              { return PapyrusTokenTypes.PAR_KEYWORD; }
"self"                { return PapyrusTokenTypes.SELF_KEYWORD; }
"state"               { return PapyrusTokenTypes.STATE_KEYWORD; }
"string"              { return PapyrusTokenTypes.STR_KEYWORD; }
"true"                { return PapyrusTokenTypes.TRUE_KEYWORD; }

{IDENTIFIER}          { return PapyrusTokenTypes.IDENTIFIER; }

"+="                  { return PapyrusTokenTypes.PLUSEQ; }
"-="                  { return PapyrusTokenTypes.MINUSEQ; }
"*="                  { return PapyrusTokenTypes.MULTEQ; }
"/="                  { return PapyrusTokenTypes.DIVEQ; }
"%="                  { return PapyrusTokenTypes.PERCEQ; }
"<="                  { return PapyrusTokenTypes.LE; }
">="                  { return PapyrusTokenTypes.GE; }
"=="                  { return PapyrusTokenTypes.EQEQ; }
"!="                  { return PapyrusTokenTypes.NE; }
"+"                   { return PapyrusTokenTypes.PLUS; }
"-"                   { return PapyrusTokenTypes.MINUS; }
"*"                   { return PapyrusTokenTypes.MULT; }
"/"                   { return PapyrusTokenTypes.DIV; }
"%"                   { return PapyrusTokenTypes.PERC; }
"&&"                  { return PapyrusTokenTypes.AND; }
"||"                  { return PapyrusTokenTypes.OR; }
"<"                   { return PapyrusTokenTypes.LT; }
">"                   { return PapyrusTokenTypes.GT; }
"("                   { return PapyrusTokenTypes.LPAR; }
")"                   { return PapyrusTokenTypes.RPAR; }
"["                   { return PapyrusTokenTypes.LBRACKET; }
"]"                   { return PapyrusTokenTypes.RBRACKET; }
","                   { return PapyrusTokenTypes.COMMA; }

"."                   { return PapyrusTokenTypes.DOT; }
"="                   { return PapyrusTokenTypes.EQ; }
"!"                   { return PapyrusTokenTypes.NOT; }

[^]                   { return PapyrusTokenTypes.BAD_CHARACTER; }
}
