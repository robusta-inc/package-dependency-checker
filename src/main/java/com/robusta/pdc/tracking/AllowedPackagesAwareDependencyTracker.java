package com.robusta.pdc.tracking;

import com.robusta.pdc.domain.AllowedPackages;
import com.robusta.pdc.domain.ImportStatement;
import com.robusta.pdc.domain.ImportTracking;

class AllowedPackagesAwareDependencyTracker implements ImportTracking {
    private final AllowedPackages allowedPackages;
    private final DependencyTracker dependencyTracker;

    public AllowedPackagesAwareDependencyTracker(AllowedPackages allowedPackages, DependencyTracker dependencyTracker) {
        this.allowedPackages = allowedPackages;
        this.dependencyTracker = dependencyTracker;
    }

    public AllowedPackagesAwareDependencyTracker(AllowedPackages allowedPackages) {
        this(allowedPackages, new DependencyTracker());
    }

    @Override
    public void track(ImportStatement importStatement) {
        if(!allowedPackages.isAllowed(importStatement.importedPackage())) {
            dependencyTracker.track(importStatement);
        }
    }

    @Override
    public void allowVisitor(Visitor visitor) {
        dependencyTracker.allowVisitor(visitor);
    }
}
