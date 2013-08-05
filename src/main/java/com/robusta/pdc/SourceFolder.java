package com.robusta.pdc;

import com.robusta.pdc.scanner.NonRecursiveSourceFolderPackageCallback;
import com.robusta.pdc.scanner.SourceFolderPackageCallback;

import java.io.File;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;

/**
 * @author sudhir.ravindramohan
 */
public class SourceFolder {
    private final File sourceFolder;

    public SourceFolder(String sourceFolderAsString) {
        this.sourceFolder = new File(sourceFolderAsString);
        checkState(sourceFolder.exists(), "The source folder: '%s' does not exist.", sourceFolderAsString);
        checkState(sourceFolder.isDirectory(), "The source folder: '%s' is not a directory", sourceFolderAsString);
        checkState(sourceFolder.canRead(), "The source folder: '%s' is not accessible", sourceFolderAsString);
    }

    public void findPackages(PackageNames packageNames, SourceFolderPackageCallback callback) {

        for (String packageName : packageNames) {
            String packagePath = sourceFolder.getAbsolutePath() + File.separatorChar + packageName.replace('.', File.separatorChar);
            File packageDirectory = new File(packagePath);
            if(packageDirectory.exists()) {
                callback.doWithSourceFolderPackage(this, packageDirectory);
            }
        }
    }

    public Iterable<SourceFolderPackage> matchingPackages(PackageNames packageNames) {
        final List<SourceFolderPackage> sourceFolderPackages = newArrayList();
        findPackages(packageNames, new NonRecursiveSourceFolderPackageCallback(sourceFolderPackages));
        return sourceFolderPackages;
    }

}
