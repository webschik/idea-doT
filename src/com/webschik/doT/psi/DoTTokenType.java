package com.webschik.doT.psi;

import com.intellij.psi.tree.IElementType;
import com.webschik.doT.DoTLanguage;
import org.jetbrains.annotations.*;

public class DoTTokenType extends IElementType {
    public DoTTokenType(@NotNull @NonNls String debugName) {
        super(debugName, DoTLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "DoTTokenType." + super.toString();
    }
}
