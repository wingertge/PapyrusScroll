package org.genguava.papyrusscroll.psi.types

import org.genguava.papyrusscroll.psi.PapyrusExpression
import org.genguava.papyrusscroll.psi.PapyrusParameter
import org.genguava.papyrusscroll.psi.PapyrusType
import org.genguava.papyrusscroll.psi.TypeEvalContext
import java.util.function.Predicate

interface PapyrusCallableParameter {
    val name: String?
    fun getType(context: TypeEvalContext): PapyrusType?
    val parameter: PapyrusParameter?
    val defaultValue: PapyrusExpression?
    val hasDefaultValue: Boolean
    val defaultValueText: String?
    val isPositionalContainer: Boolean
    val isKeywordContainer: Boolean
    val isSelf: Boolean
    fun getPresentableText(includeDefaultValue: Boolean, context: TypeEvalContext? = null)
    fun getPresentableText(includeDefaultValue: Boolean, context: TypeEvalContext?, typeFilter: Predicate<PapyrusType>)
    fun getArgumentType(context: TypeEvalContext): PapyrusType?
}