package com.robusta.pdc.scanner;

import com.robusta.pdc.PackageNames;
import com.robusta.pdc.SourceFolders;

/**
 * @author sudhir.ravindramohan
 */
public interface SourceFolderScanner {
    void scan(SourceFolders sourceFolders, PackageNames packageNames);
}
