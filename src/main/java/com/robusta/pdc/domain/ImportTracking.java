package com.robusta.pdc.domain;

public interface ImportTracking {
    void track(ImportStatement importStatement);
    public void doWithVisitor(Visitor visitor);

    interface Visitor {
        void visit(SourceFile sourceFile, JavaPackage javaPackage);
    }
}
