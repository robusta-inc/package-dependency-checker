package com.robusta.pdc.tracking;

import com.robusta.pdc.domain.AllowedPackages;
import com.robusta.pdc.domain.ImportTracking;

/**
 * A factory for producing import tracking implementations
 */
public abstract class ImportTrackingFactory {
    /**
     * A factory api that produces an {@link ImportTracking} tracker
     * that understands {@link AllowedPackages} and tracks import
     * statements that depend on packages that are not in the allowed
     * list of packages.
     *
     * @param allowedPackages AllowedPackages
     * @return ImportTracking import violations tracking
     */
    public static ImportTracking violationTracking(AllowedPackages allowedPackages) {
        return new AllowedPackagesAwareDependencyTracker(allowedPackages);
    }
}
