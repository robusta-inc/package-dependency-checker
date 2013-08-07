package com.robusta.pdc.reporting;

import com.robusta.pdc.domain.ImportTracking;
import com.robusta.pdc.domain.JavaPackage;
import com.robusta.pdc.domain.SourceFile;

import java.util.concurrent.atomic.AtomicInteger;

class SysOutDependencyTrackingVisitor implements ImportTracking.Visitor {
    private final AtomicInteger counter;

    public SysOutDependencyTrackingVisitor() {
        this.counter = new AtomicInteger(1);
    }

    @Override
    public void visit(SourceFile sourceFile, JavaPackage javaPackage) {
        System.out.println(String.format("%d. %s depends on %s",
                counter.getAndIncrement(),
                sourceFile.javaFileNameInDotNotation(),
                javaPackage.packageInDotNotation()));
    }
}
