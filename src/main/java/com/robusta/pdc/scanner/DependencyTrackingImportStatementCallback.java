package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.AllowedPackages;
import com.robusta.pdc.domain.DependencyTracker;
import com.robusta.pdc.domain.ImportStatement;


public class DependencyTrackingImportStatementCallback implements ImportStatementCallback {
    private final DependencyTracker dependencyTracker;
    private final AllowedPackages allowedPackages;

    public DependencyTrackingImportStatementCallback(DependencyTracker dependencyTracker, AllowedPackages allowedPackages) {
        this.dependencyTracker = dependencyTracker;
        this.allowedPackages = allowedPackages;
    }

    @Override
    public void doWithImportStatement(ImportStatement importStatement) {
        if(!allowedPackages.isAllowed(importStatement.importedPackage())) {
            dependencyTracker.track(importStatement);
        }
    }
}
