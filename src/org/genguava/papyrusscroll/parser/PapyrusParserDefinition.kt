package org.genguava.papyrusscroll.parser

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.TokenSet
import org.genguava.papyrusscroll.lexer.PapyrusElementType
import org.genguava.papyrusscroll.lexer.PapyrusLexer
import org.genguava.papyrusscroll.lexer.PapyrusStubElementType
import org.genguava.papyrusscroll.lexer.PapyrusTokenTypes

class PapyrusParserDefinition : ParserDefinition {
    private val myWhitespaceTokens = TokenSet.create(PapyrusTokenTypes.LINE_BREAK, PapyrusTokenTypes.SPACE, PapyrusTokenTypes.TAB, PapyrusTokenTypes.FORMFEED)
    private val commentTokens = TokenSet.create(PapyrusTokenTypes.END_OF_LINE_COMMENT, PapyrusTokenTypes.MULTILINE_COMMENT)
    private val stringLiteralTokens = TokenSet.create(PapyrusElementTypes.STRING_LITERAL_EXPRESSION)

    override fun createParser(project: Project?) = PapyrusParser()
    override fun createFile(viewProvider: FileViewProvider) = PapyrusFile(viewProvider)
    override fun spaceExistanceTypeBetweenTokens(left: ASTNode?, right: ASTNode?) = ParserDefinition.SpaceRequirements.MAY
    override fun getStringLiteralElements() = stringLiteralTokens
    override fun getFileNodeType() = PapyrusFileElementType.instance
    override fun createLexer(project: Project?) = PapyrusLexer()
    override fun getWhitespaceTokens() = myWhitespaceTokens

    override fun createElement(node: ASTNode): PsiElement {
        val type = node.elementType
        if(type is PapyrusElementType)
            return type.createElement(node)
        else if(type is PapyrusStubElementType<*, *>)
            return type.createElement(node)
        return ASTWrapperPsiElement(node)
    }

    override fun getCommentTokens() = commentTokens
}