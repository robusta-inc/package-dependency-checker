package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.PackageNames;
import com.robusta.pdc.domain.SourceFolder;
import com.robusta.pdc.domain.SourceFolderPackage;
import com.robusta.pdc.domain.SourceFolders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * A {@link SourceFolderScanner} implementation that accepts a
 * uses a {@link PackageFinder} to detect packages in each
 * {@link SourceFolder} and invokes the {@link PackageCallback}
 * for every found package.
 *
 * @author sudhir.ravindramohan
 */
class PackageCallbackSourceFolderScanner implements SourceFolderScanner {
    private final PackageFinder packageFinder;
    private final PackageCallback packageCallback;

    private static final Logger LOGGER = LoggerFactory.getLogger(PackageCallbackSourceFolderScanner.class);

    public PackageCallbackSourceFolderScanner(PackageFinder packageFinder, PackageCallback packageCallback) {
        this.packageFinder = packageFinder;
        this.packageCallback = packageCallback;
    }

    @Override
    public void scan(SourceFolders sourceFolders, PackageNames packageNames) {
        LOGGER.trace("Scanning source folders: '{}' for packages with names: '{}'", sourceFolders, packageNames);
        for (SourceFolder sourceFolder : sourceFolders) {
            LOGGER.debug("Processing source folder: '{}'", sourceFolder);
            Collection<SourceFolderPackage> packages = packageFinder.find(sourceFolder, packageNames);
            debug(sourceFolder, packageNames, packages);
            for (SourceFolderPackage aPackage : packages) {
                LOGGER.trace("Processing package: '{}' with PackageCallback: '{}'", aPackage, packageCallback);
                packageCallback.doWithPackage(aPackage);
            }
        }
    }

    private static void debug(SourceFolder sourceFolder, PackageNames packageNames, Collection<SourceFolderPackage> packages) {
        if(LOGGER.isDebugEnabled()) {
            if(packages == null || packages.isEmpty()) {
               LOGGER.debug("In source folder: '{}' matching package names: '{}' package finder found 0 packages", sourceFolder, packageNames);
            } else {
                LOGGER.debug("In source folder: '{}' matching package names: '{}' package finder found '{}' packages", sourceFolder, packageNames, packages);
            }
        }
    }
}
