package com.robusta.pdc.scanner;

import com.robusta.pdc.domain.SourceFile;

/**
 * Accepts a source file for scanning.
 *
 * <p>It is left to the implementation to decide what operations
 * need to be done with the given {@link SourceFile}</p>
 *
 * <p>The package dependency checker checks for the imports.</p>
 * <p>A indentation checker could be looking at the source file
 * lines of code (LOC) to check for styles and indentations</p>
 * <p>Or a variable name scanner might be implemented to check
 * for variable names that follow standard java naming
 * conventions</p>
 */
public interface SourceFileScanner {
    /**
     * Scan the content of the source file.
     * @param sourceFile SourceFile
     */
    void scan(SourceFile sourceFile);
}
