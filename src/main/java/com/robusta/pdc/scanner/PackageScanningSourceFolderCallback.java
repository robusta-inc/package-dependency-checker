package com.robusta.pdc.scanner;

import com.robusta.pdc.PackageNames;
import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolderPackage;

public class PackageScanningSourceFolderCallback implements SourceFolderCallback {
    private final PackageScanner scanner;
    private final PackageNames packageNames;

    public PackageScanningSourceFolderCallback(PackageScanner scanner, PackageNames packageNames) {
        this.scanner = scanner;
        this.packageNames = packageNames;
    }

    @Override
    public void doWithSourceFolder(SourceFolder sourceFolder) {
        for (SourceFolderPackage aPackage : sourceFolder.matchingPackages(packageNames)) {
            scanner.scan(aPackage);
        }
    }
}
