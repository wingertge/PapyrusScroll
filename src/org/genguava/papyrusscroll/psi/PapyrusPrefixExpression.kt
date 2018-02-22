package org.genguava.papyrusscroll.psi

import org.genguava.papyrusscroll.lexer.PapyrusElementType

interface PapyrusPrefixExpression : PapyrusQualifiedExpression, PapyrusReferenceOwner, PapyrusCallSiteExpression {
    val operand: PapyrusExpression?
    val operator: PapyrusElementType
}