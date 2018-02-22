package org.genguava.papyrusscroll.psi

import java.math.BigDecimal
import java.math.BigInteger

interface PapyrusNumericLiteralExpression : PapyrusLiteralExpression {
    val longValue: Long?
    val bigIntValue: BigInteger?
    val bigDecimalValue: BigDecimal?
    val isIntegerLiteral: Boolean
}