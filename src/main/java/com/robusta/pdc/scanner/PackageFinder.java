package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.PackageNames;
import com.robusta.pdc.domain.SourceFolder;
import com.robusta.pdc.domain.SourceFolderPackage;

import java.util.Collection;

/**
 * Find packages under a given source folder.
 *
 * @author sudhir.ravindramohan
 */
public interface PackageFinder {
    /**
     * Find packages specified by {@link PackageNames} that fall under
     * the source folder specified by {@link SourceFolder}
     *
     * @param sourceFolder SourceFolder
     * @param packageNames PackageNames
     * @return Collection a collection of found source folder packages.
     */
    Collection<SourceFolderPackage> find(SourceFolder sourceFolder, PackageNames packageNames);
}
