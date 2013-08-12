package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.PackageNames;
import com.robusta.pdc.domain.SourceFolders;

/**
 * Source folder scanning abstraction.
 *
 * <p>Implementations are expected to scan the source folders
 * for the presence of the required packages. It is left to
 * the implementation on what it needs to do with the scanned
 * packages.</p>
 *
 * @author sudhir.ravindramohan
 */
public interface SourceFolderScanner {
    /**
     * Accept {@link SourceFolders} for scanning for {@link PackageNames}
     *
     * @param sourceFolders SourceFolders
     * @param packageNames PackageNames
     */
    void scan(SourceFolders sourceFolders, PackageNames packageNames);
}
