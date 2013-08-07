package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.ImportTracking;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A factory for scanners and callbacks.
 */
public abstract class ScannerFactory {
    /**
     * Import statement tracking source folder scanner
     *
     * @param tracking ImportTracking specifies what the scanner needs
     *                 to do when it scans an import statement.
     * @return SourceFolderScanner
     */
    public static SourceFolderScanner importStatementScanner(ImportTracking tracking) {
        checkArgument(tracking != null, "Factory cannot produce a import statement scanner with a valid tracking option");
        return sourceFolderScanner(
                packageFinder(),
                sourceFileScanningPackageCallback(
                        sourceFileScanner(
                                dependencyTrackingStatementCallback(
                                        tracking))));
    }

    /**
     * General purpose source folder scanner using a package finder
     * and a package callback
     *
     * @param packageFinder PackageFinder
     * @param packageCallback PackageCallback
     * @return SourceFolderScanner
     */
    public static SourceFolderScanner sourceFolderScanner(PackageFinder packageFinder, PackageCallback packageCallback) {
        return new PackageCallbackSourceFolderScanner(packageFinder, packageCallback);
    }

    /**
     * Non recursive package finder
     *
     * @return PackageFinder
     */
    public static PackageFinder packageFinder() {
        return new NonRecursivePackageFinder();
    }

    public static PackageCallback sourceFileScanningPackageCallback(SourceFileScanner sourceFileScanner) {
        return new SourceFileScanningPackageCallback(sourceFileScanner);
    }

    public static SourceFileScanner sourceFileScanner(ImportStatementCallback callback) {
        return new QDocSourceFileScanner(callback);
    }

    public static ImportStatementCallback dependencyTrackingStatementCallback(ImportTracking dependencyTracker) {
        return new DependencyTrackingImportStatementCallback(dependencyTracker);
    }
}
