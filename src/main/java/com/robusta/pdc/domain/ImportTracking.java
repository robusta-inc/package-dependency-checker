package com.robusta.pdc.domain;

public interface ImportTracking {
    void track(ImportStatement importStatement);
    public void allowVisitor(Visitor visitor);

    interface Visitor {
        void visit(SourceFile sourceFile, JavaPackage javaPackage);
    }
}
