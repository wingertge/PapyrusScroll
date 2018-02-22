package org.genguava.papyrusscroll.lexer;

import com.intellij.lexer.FlexAdapter;

public class PapyrusLexer extends FlexAdapter {
    public PapyrusLexer() {
        super(new _PapyrusLexer(null));
    }
}
