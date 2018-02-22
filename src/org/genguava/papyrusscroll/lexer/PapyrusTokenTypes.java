package org.genguava.papyrusscroll.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public class PapyrusTokenTypes {
    public static final PapyrusElementType IDENTIFIER = new PapyrusElementType("IDENTIFIER");
    public static final PapyrusElementType LINE_BREAK = new PapyrusElementType("LINE_BREAK");
    public static final PapyrusElementType STATEMENT_BREAK = new PapyrusElementType("STATEMENT_BREAK");
    public static final PapyrusElementType SPACE = new PapyrusElementType("SPACE");
    public static final PapyrusElementType TAB = new PapyrusElementType("TAB");
    public static final PapyrusElementType FORMFEED = new PapyrusElementType("FORMFEED");
    public static final IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    public static final PapyrusElementType END_OF_LINE_COMMENT = new PapyrusElementType("END_OF_LINE_COMMENT");
    public static final PapyrusElementType MULTILINE_COMMENT = new PapyrusElementType("MULTILINE_COMMENT");

    public static final PapyrusElementType AS_KEYWORD = new PapyrusElementType("AS_KEYWORD");
    public static final PapyrusElementType SCNAM_KEYWORD = new PapyrusElementType("SCNAM_KEYWORD");
    public static final PapyrusElementType ELIF_KEYWORD = new PapyrusElementType("ELIF_KEYWORD");
    public static final PapyrusElementType ELSE_KEYWORD = new PapyrusElementType("ELSE_KEYWORD");
    public static final PapyrusElementType ENDIF_KEYWORD = new PapyrusElementType("ENDIF_KEYWORD");
    public static final PapyrusElementType AUTO_KEYWORD = new PapyrusElementType("AUTO_KEYWORD");
    public static final PapyrusElementType ARO_KEYWORD = new PapyrusElementType("ARO_KEYWORD");
    public static final PapyrusElementType BOOL_KEYWORD = new PapyrusElementType("BOOL_KEYWORD");
    public static final PapyrusElementType EVENT_KEYWORD = new PapyrusElementType("EVENT_KEYWORD");
    public static final PapyrusElementType ENEVT_KEYWORD = new PapyrusElementType("ENEVT_KEYWORD");
    public static final PapyrusElementType FUN_KEYWORD = new PapyrusElementType("FUN_KEYWORD");
    public static final PapyrusElementType ENFUN_KEYWORD = new PapyrusElementType("ENFUN_KEYWORD");
    public static final PapyrusElementType IF_KEYWORD = new PapyrusElementType("IF_KEYWORD");
    public static final PapyrusElementType PROP_KEYWORD = new PapyrusElementType("PROP_KEYWORD");
    public static final PapyrusElementType ENPR_KEYWORD = new PapyrusElementType("ENPR_KEYWORD");
    public static final PapyrusElementType STATE_KEYWORD = new PapyrusElementType("STATE_KEYWORD");
    public static final PapyrusElementType ENST_KEYWORD = new PapyrusElementType("ENST_KEYWORD");
    public static final PapyrusElementType WHILE_KEYWORD = new PapyrusElementType("WHILE_KEYWORD");
    public static final PapyrusElementType ENWHI_KEYWORD = new PapyrusElementType("ENWHI_KEYWORD");
    public static final PapyrusElementType EXTENDS_KEYWORD = new PapyrusElementType("EXTENDS_KEYWORD");
    public static final PapyrusElementType FALSE_KEYWORD = new PapyrusElementType("FALSE_KEYWORD");
    public static final PapyrusElementType FLOAT_KEYWORD = new PapyrusElementType("FLOAT_KEYWORD");
    public static final PapyrusElementType GLOBAL_KEYWORD = new PapyrusElementType("GLOBAL_KEYWORD");
    public static final PapyrusElementType IMPORT_KEYWORD = new PapyrusElementType("IMPORT_KEYWORD");
    public static final PapyrusElementType INT_KEYWORD = new PapyrusElementType("INT_KEYWORD");
    public static final PapyrusElementType NATIVE_KEYWORD = new PapyrusElementType("NATIVE_KEYWORD");
    public static final PapyrusElementType NEW_KEYWORD = new PapyrusElementType("NEW_KEYWORD");
    public static final PapyrusElementType NONE_KEYWORD = new PapyrusElementType("NONE_KEYWORD");
    public static final PapyrusElementType PAR_KEYWORD = new PapyrusElementType("PAR_KEYWORD");
    public static final PapyrusElementType RETURN_KEYWORD = new PapyrusElementType("RETURN_KEYWORD");
    public static final PapyrusElementType SELF_KEYWORD = new PapyrusElementType("SELF_KEYWORD");
    public static final PapyrusElementType STR_KEYWORD = new PapyrusElementType("STR_KEYWORD");
    public static final PapyrusElementType TRUE_KEYWORD = new PapyrusElementType("TRUE_KEYWORD");

    public static final PapyrusElementType INTEGER_LITERAL = new PapyrusElementType("INTEGER_LITERAL");
    public static final PapyrusElementType FLOAT_LITERAL = new PapyrusElementType("FLOAT_LITERAL");
    public static final PapyrusElementType SINGLE_QUOTED_STRING = new PapyrusElementType("SINGLE_QUOTED_STRING");

    // Operators
    public static final PapyrusElementType PLUS = new PapyrusElementType("PLUS");// +
    public static final PapyrusElementType MINUS = new PapyrusElementType("MINUS");// -
    public static final PapyrusElementType MULT = new PapyrusElementType("MULT");// *
    public static final PapyrusElementType DIV = new PapyrusElementType("DIV"); // /
    public static final PapyrusElementType PERC = new PapyrusElementType("PERC");// %
    public static final PapyrusElementType AND = new PapyrusElementType("AND");// &&
    public static final PapyrusElementType OR = new PapyrusElementType("OR");// ||
    public static final PapyrusElementType LT = new PapyrusElementType("LT");// <
    public static final PapyrusElementType GT = new PapyrusElementType("GT");// >
    public static final PapyrusElementType LE = new PapyrusElementType("LE");// <=
    public static final PapyrusElementType GE = new PapyrusElementType("GE");// >=
    public static final PapyrusElementType EQEQ = new PapyrusElementType("EQEQ");// ==
    public static final PapyrusElementType NE = new PapyrusElementType("NE");// !=
    public static final PapyrusElementType NOT = new PapyrusElementType("NOT"); // !

    // Delimiters
    public static final PapyrusElementType LPAR = new PapyrusElementType("LPAR");// (
    public static final PapyrusElementType RPAR = new PapyrusElementType("RPAR");// )
    public static final PapyrusElementType LBRACKET = new PapyrusElementType("LBRACKET");// [
    public static final PapyrusElementType RBRACKET = new PapyrusElementType("RBRACKET");// ]
    public static final PapyrusElementType COMMA = new PapyrusElementType("COMMA");// ,
    public static final PapyrusElementType DOT = new PapyrusElementType("DOT");// .
    public static final PapyrusElementType EQ = new PapyrusElementType("EQ");// =
    public static final PapyrusElementType PLUSEQ = new PapyrusElementType("PLUSEQ");// +=
    public static final PapyrusElementType MINUSEQ = new PapyrusElementType("MINUSEQ");// -=
    public static final PapyrusElementType MULTEQ = new PapyrusElementType("MULTEQ");// *=
    public static final PapyrusElementType DIVEQ = new PapyrusElementType("DIVEQ"); // /=
    public static final PapyrusElementType PERCEQ = new PapyrusElementType("PERCEQ");// %=

    public static final TokenSet OPERATIONS = TokenSet.create(
            PLUS, MINUS, MULT, DIV, PERC, AND, OR,
            LT, GT, LE, GE, EQEQ, NE, NOT, EQ,
            PLUSEQ, MINUSEQ, MULTEQ, DIVEQ, PERCEQ);

    public static final TokenSet COMPARISON_OPERATIONS = TokenSet.create(
            LT, GT, EQEQ, GE, LE, NE, NOT);

    public static final TokenSet ADDITIVE_OPERATIONS = TokenSet.create(PLUS, MINUS);
    public static final TokenSet MULTIPLICATIVE_OPERATIONS = TokenSet.create(MULT, DIV, PERC);
    public static final TokenSet UNARY_OPERATIONS = TokenSet.create(PLUS, MINUS, NOT);
    public static final TokenSet BITWISE_OPERATIONS = TokenSet.create(AND, OR);
    public static final TokenSet EQUALITY_OPERATIONS = TokenSet.create(EQEQ, NE);
    public static final TokenSet RELATIONAL_OPERATIONS = TokenSet.create(LT, GT, LE, GE);
    public static final TokenSet END_OF_STATEMENT = TokenSet.create(STATEMENT_BREAK);
    public static final TokenSet WHITESPACE = TokenSet.create(SPACE, TAB, FORMFEED);
    public static final TokenSet WHITESPACE_OR_LINEBREAK = TokenSet.create(SPACE, TAB, FORMFEED, LINE_BREAK);
    public static final TokenSet OPEN_BRACES = TokenSet.create(LBRACKET, LPAR);
    public static final TokenSet CLOSE_BRACES = TokenSet.create(RBRACKET, RPAR);

    public static final TokenSet NUMERIC_LITERALS = TokenSet.create(FLOAT_LITERAL, INTEGER_LITERAL);
    public static final TokenSet BOOL_LITERALS = TokenSet.create(TRUE_KEYWORD, FALSE_KEYWORD);
    public static final TokenSet SCALAR_LITERALS = TokenSet.orSet(BOOL_LITERALS, NUMERIC_LITERALS, TokenSet.create(NONE_KEYWORD));
    public static final TokenSet EXPRESSION_KEYWORDS = TokenSet.create(TRUE_KEYWORD, FALSE_KEYWORD, NONE_KEYWORD);

    public static final TokenSet AUG_ASSIGN_OPERATIONS = TokenSet.create(PLUSEQ, MINUSEQ, MULTEQ, DIVEQ, PERCEQ);

    public static final PapyrusElementType BACKSLASH = new PapyrusElementType("BACKSLASH");
}
