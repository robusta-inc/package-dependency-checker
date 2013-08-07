package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.PackageNames;
import com.robusta.pdc.domain.SourceFolders;

/**
 * @author sudhir.ravindramohan
 */
public interface SourceFolderScanner {
    void scan(SourceFolders sourceFolders, PackageNames packageNames);
}
