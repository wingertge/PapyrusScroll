package org.genguava.papyrusscroll.psi.stubs

import com.intellij.psi.stubs.NamedStub
import com.intellij.psi.util.QualifiedName
import org.genguava.papyrusscroll.psi.PapyrusTargetExpression

interface PapyrusTargetExpressionStub : NamedStub<PapyrusTargetExpression> {
    enum class InitializerType private constructor(val index: Int) {
        ReferenceExpression(1),
        CallExpression(2),
        Custom(3),
        Other(0);


        companion object {

            fun fromIndex(index: Int): InitializerType {
                when (index) {
                    1 -> return ReferenceExpression
                    2 -> return CallExpression
                    3 -> return Custom
                    else -> return Other
                }
            }
        }
    }

    fun getInitializerType(): InitializerType
    fun getInitializer(): QualifiedName?
    fun isQualified(): Boolean
    fun <T : CustomTargetExpressionStub> getCustomStub(stubClass: Class<T>): T?
    fun getDocString(): String?
}