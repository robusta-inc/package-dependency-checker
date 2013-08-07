package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.AllowedPackages;
import com.robusta.pdc.domain.ImportStatement;
import com.robusta.pdc.domain.ImportTracking;


class DependencyTrackingImportStatementCallback implements ImportStatementCallback {
    private final ImportTracking dependencyTracker;
    private final AllowedPackages allowedPackages;

    public DependencyTrackingImportStatementCallback(ImportTracking dependencyTracker, AllowedPackages allowedPackages) {
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
