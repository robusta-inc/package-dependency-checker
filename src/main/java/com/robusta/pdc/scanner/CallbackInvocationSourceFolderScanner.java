package com.robusta.pdc.scanner;

import com.robusta.pdc.SourceFolder;
import com.robusta.pdc.SourceFolders;

/**
 * @author sudhir.ravindramohan
 */
public class CallbackInvocationSourceFolderScanner implements SourceFolderScanner {
    private final SourceFolderCallback callback;

    public CallbackInvocationSourceFolderScanner(SourceFolderCallback callback) {
        this.callback = callback;
    }


    @Override
    public void scan(SourceFolders sourceFolders) {
        for (SourceFolder sourceFolder : sourceFolders) {
            callback.doWithSourceFolder(sourceFolder);
        }
    }
}
