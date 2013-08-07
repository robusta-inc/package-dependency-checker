package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.PackageNames;
import com.robusta.pdc.domain.SourceFolder;
import com.robusta.pdc.domain.SourceFolderPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class AbstractDirectoryFindingPackageFinder implements PackageFinder {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Collection<SourceFolderPackage> find(SourceFolder sourceFolder, PackageNames packageNames) {
        List<SourceFolderPackage> packages = newArrayList();
        for (String packageName : packageNames) {
            File packageDirectory = new File(toPackagePath(sourceFolder, packageName));
            logger.debug("For source folder: '{}', package name: '{}', the directory file is: '{}'", sourceFolder, packageName, packageDirectory);
            find(packages, sourceFolder, packageDirectory);
        }
        return packages;
    }

    protected void find(List<SourceFolderPackage> packages, SourceFolder sourceFolder, File packageDirectory) {
        if(packageDirectory.exists() && packageDirectory.isDirectory()) {
            logger.trace("Package directory (as File) exists and is a directory");
            processFoundPackageDirectory(packages, sourceFolder, packageDirectory);
        } else {
            logger.trace("Either the package directory does not exist or is not a directory: '{}'", packageDirectory);
        }
    }

    protected SourceFolderPackage newSourceFolderPackage(SourceFolder sourceFolder, File packageDirectory) {
        return new SourceFolderPackage(sourceFolder, packageDirectory);
    }

    protected abstract void processFoundPackageDirectory(List<SourceFolderPackage> packages, SourceFolder sourceFolder, File packageDirectory);

    protected String toPackagePath(SourceFolder sourceFolder, String packageName) {
        return sourceFolder.getAbsolutePath() + File.separatorChar + packageName.replace('.', File.separatorChar);
    }
}
