package com.robusta.pdc.tracking;

import com.robusta.pdc.domain.AllowedPackages;
import com.robusta.pdc.domain.ImportTracking;

public abstract class ImportTrackingFactory {
    public static ImportTracking violationTracking(AllowedPackages allowedPackages) {
        return new AllowedPackagesAwareDependencyTracker(allowedPackages);
    }
}
