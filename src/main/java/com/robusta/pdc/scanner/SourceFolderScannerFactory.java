package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.AllowedPackages;
import com.robusta.pdc.domain.DependencyTracker;

public abstract class SourceFolderScannerFactory {
    public static SourceFolderScanner newScannerFor(AllowedPackages allowedPackages, DependencyTracker dependencyTracker) {
        return sourceFolderScanner(
                packageFinder(),
                packageCallback(
                        sourceFileScanner(
                                statementCallback(allowedPackages,
                                        dependencyTracker))));
    }

    private static PackageCallbackSourceFolderScanner sourceFolderScanner(NonRecursivePackageFinder packageFinder, SourceFileScanningPackageCallback packageCallback) {
        return new PackageCallbackSourceFolderScanner(
                packageFinder,
                packageCallback);
    }

    private static NonRecursivePackageFinder packageFinder() {
        return new NonRecursivePackageFinder();
    }

    private static SourceFileScanningPackageCallback packageCallback(QDocSourceFileScanner sourceFileScanner) {
        return new SourceFileScanningPackageCallback(
                sourceFileScanner
        );
    }

    private static QDocSourceFileScanner sourceFileScanner(DependencyTrackingImportStatementCallback callback) {
        return new QDocSourceFileScanner(callback);
    }

    private static DependencyTrackingImportStatementCallback statementCallback(AllowedPackages allowedPackages, DependencyTracker dependencyTracker) {
        return new DependencyTrackingImportStatementCallback(dependencyTracker, allowedPackages);
    }

    public static DependencyTracker newDependencyTracker() {
        return new DependencyTracker();
    }
}
