package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.ImportStatement;
import com.robusta.pdc.domain.ImportTracking;


class DependencyTrackingImportStatementCallback implements ImportStatementCallback {
    private final ImportTracking dependencyTracker;

    public DependencyTrackingImportStatementCallback(ImportTracking dependencyTracker) {
        this.dependencyTracker = dependencyTracker;
    }

    @Override
    public void doWithImportStatement(ImportStatement importStatement) {
        dependencyTracker.track(importStatement);
    }
}
