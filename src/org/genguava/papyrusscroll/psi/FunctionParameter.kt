package org.genguava.papyrusscroll.psi

interface FunctionParameter {
    val POSITION_NOT_SUPPORTED get() = -1
    val position: Int
    val name: String?
}