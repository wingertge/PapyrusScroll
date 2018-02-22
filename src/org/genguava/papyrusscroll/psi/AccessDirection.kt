package org.genguava.papyrusscroll.psi

enum class AccessDirection {
    READ,
    WRITE;

    companion object {
        fun of(element: PapyrusElement): AccessDirection {
            val parent = element.parent
            if(element is PapyrusTargetExpression) return WRITE
            else if(parent is PapyrusAssignmentStatement) {
                for(target in parent.targets) {
                    if(target == element) return WRITE
                }
            }
            return READ
        }
    }
}